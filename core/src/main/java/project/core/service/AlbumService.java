package project.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.core.model.Album;
import project.core.model.Client;
import project.core.model.validators.CompanyException;
import project.core.model.validators.Validator;
import project.core.model.validators.ValidatorException;
import project.core.repository.AlbumRepository;
import project.core.repository.ClientRepository;
import project.core.repository.PhotographerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AlbumService implements IAlbumService {
    public static final Logger log = LoggerFactory.getLogger(AlbumService.class);

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private PhotographerRepository photographerRepo;

    @Autowired
    private Validator<Album> albumValidator;

    @Override
    public List<Album> getAllAlbums() {
        log.trace("getAllAlbums --- method entered");

        List<Album> result = albumRepository.findAll();

        log.trace("getAllAlbums: result{}", result);

        return result;
    }

    @Override
    public void saveAlbum(Album album) throws CompanyException {
        if (!clientRepo.existsById(album.getClientId())) {
            throw new CompanyException("Client id does not exist");

        }

        if (!photographerRepo.existsById(album.getPhotographerId())) {
            throw new CompanyException("Photographer id does not exist");
        }
        log.trace("saveAlbum - method entered album={}", album);
        try {
            albumValidator.validate(album);
        } catch (ValidatorException exception) {
            log.trace("saveAlbum - method finished, album was not saved - invalid album");
            throw new CompanyException(exception.getMessage());
        }

        Album a = albumRepository.save(album);
        log.trace("saveAlbum - method finished album={}", a);
    }

    @Override
    @Transactional
    public void updateAlbum(Album album) throws CompanyException {
        log.trace("updateAlbum - method entered: album{}", album);

        try {
            albumValidator.validate(album);
        } catch (ValidatorException exception) {
            log.trace("updateAlbum - method finished, album was not updated - invalid album");
            throw new CompanyException(exception.getMessage());
        }

        if (!albumRepository.existsById(album.getId())) {
            log.trace("updateAlbum - method finished, album was not updated - invalid id");
            throw new CompanyException("Album does not exist");
        }


        albumRepository.findById(album.getId())
                .ifPresent(a -> {
                    a.setPhotoSessionName(album.getPhotoSessionName());
                    a.setClientId(album.getClientId());
                    a.setPhotographerId(album.getPhotographerId());
                    log.debug("updateAlbum - updated: a={}", a);
                });
        log.trace("updateAlbum - method finished");
    }

    @Override
    public void deleteAlbum(Long id) throws CompanyException {
        log.trace("deleteAlbum - method entered id={}", id);

        if (!albumRepository.existsById(id)) {
            log.trace("deleteAlbum - method finished, album was not deleted - invalid id");
            throw new CompanyException("Album does not exist");
        }

        albumRepository.deleteById(id);
        log.trace("deleteAlbum - method finished");
    }

    @Override
    public List<Album> filterAlbums(String name) {
        log.trace("filterAlbums - method entered name={}", name);



        Iterable<Album> albums = albumRepository.filterAlbums(name);

        List<Album> result = StreamSupport.stream(albums.spliterator(), false)
                .filter(album -> album.getPhotoSessionName().contains(name))
                .collect(Collectors.toList());

        log.trace("filterAlbums: result{}", result);

        return result;
    }

    public Set<Client> getAllClients() {
        Iterable<Client> clients = clientRepo.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }
}


