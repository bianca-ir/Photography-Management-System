package project.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.core.model.Picture;
import project.core.model.validators.CompanyException;
import project.core.model.validators.Validator;
import project.core.model.validators.ValidatorException;
import project.core.repository.PictureRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PictureService implements IPictureService {

    public static final Logger log = LoggerFactory.getLogger(PictureService.class);


    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private Validator<Picture> pictureValidator;


    @Override
    public List<Picture> getAllPictures() {
        log.trace("getAllPictures --- method entered");

        List<Picture> result = pictureRepository.findAll();

        log.trace("getAllPictures: result{}", result);

        return result;
    }

    @Override
    public void savePicture(Picture picture) throws CompanyException {
        log.trace("savePicture - method entered picture={}", picture);
        try{
            pictureValidator.validate(picture);
        }
        catch (ValidatorException exception){
            log.trace("savePicture - method finished, picture was not saved - invalid picture");
            throw new CompanyException(exception.getMessage());
        }

        Picture p = pictureRepository.save(picture);
        log.trace("savePicture - method finished picture={}", p);
    }

    @Override
    @Transactional
    public void updatePicture(Picture picture) throws CompanyException {
        log.trace("updatePicture - method entered: picture{}", picture);

        try{
            pictureValidator.validate(picture);
        }
        catch (ValidatorException exception){
            log.trace("updatePicture - method finished, picture was not updated - invalid picture");
            throw new CompanyException(exception.getMessage());
        }

        if(!pictureRepository.existsById(picture.getId())){
            log.trace("updatePicture - method finished, picture was not updated - invalid id");
            throw new CompanyException("Picture does not exist");
        }


        pictureRepository.findById(picture.getId())
                .ifPresent(p -> {
                    p.setTitle(picture.getTitle());
                    p.setDescription(picture.getDescription());
                    p.setWidth(picture.getWidth());
                    p.setHeight(picture.getHeight());
                    log.debug("updatePicture - updated: p={}", p);
                });
        log.trace("updatePicture - method finished");
    }

    @Override
    public void deletePicture(Long id) throws CompanyException {
        log.trace("deletePicture - method entered id={}", id);

        if(!pictureRepository.existsById(id)){
            log.trace("deletePicture - method finished, picture was not deleted - invalid id");
            throw new CompanyException("Picture does not exist");
        }

        pictureRepository.deleteById(id);
        log.trace("deletePicture - method finished");
    }

    @Override
    public List<Picture> filterPictures(String title) {
        log.trace("filterPictures - method entered title={}", title);


        List<Picture> pictures = pictureRepository.filterPicturesByTitle(title);

        log.trace("filterPictures: result{}", pictures);

        return pictures;
    }
}
