
package project.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.core.model.Photographer;
import project.core.model.validators.CompanyException;
import project.core.model.validators.Validator;
import project.core.model.validators.ValidatorException;
import project.core.repository.PhotographerRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class PhotographerService implements IPhotographerService {
    public static final Logger log = LoggerFactory.getLogger(PhotographerService.class);


    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private Validator<Photographer> photographerValidator;

    @Override
    public List<Photographer> getAllPhotographers() {
        log.trace("getAllPhotographers --- method entered");

        List<Photographer> result = photographerRepository.findAll();

        log.trace("getAllPhotographers: result{}", result);

        return result;
    }

    @Override
    public void savePhotographer(Photographer photographer) throws CompanyException {
        log.trace("savePhotographer - method entered photographer={}", photographer);
        try{
            photographerValidator.validate(photographer);
        }
        catch (ValidatorException exception){
            log.trace("savePhotographer - method finished, photographer was not saved - invalid photographer");
            throw new CompanyException(exception.getMessage());
        }

        Photographer p = photographerRepository.save(photographer);
        log.trace("savePhotographer - method finished photographer={}", p);
    }

    @Override
    @Transactional
    public void updatePhotographer(Photographer photographer) throws CompanyException {
        log.trace("updatePhotographer - method entered: photographer{}", photographer);

        try{
            photographerValidator.validate(photographer);
        }
        catch (ValidatorException exception){
            log.trace("updatePhotographer - method finished, photographer was not updated - invalid photographer");
            throw new CompanyException(exception.getMessage());
        }

        if(!photographerRepository.existsById(photographer.getId())){
            log.trace("updatePhotographer - method finished, photographer was not updated - invalid id");
            throw new CompanyException("Photographer does not exist");
        }


        photographerRepository.findById(photographer.getId())
                .ifPresent(p -> {
                    p.setName(photographer.getName());
                    p.setAge(photographer.getAge());
                    p.setCameraBrand(photographer.getCameraBrand());
                    p.setRating(photographer.getRating());
                    log.debug("updatePhotographer - updated: p={}", p);
                });
        log.trace("updatePhotographer - method finished");
    }

    @Override
    public void deletePhotographer(Long id) throws CompanyException {
        log.trace("deletePhotographer - method entered id={}", id);

        if(!photographerRepository.existsById(id)){
            log.trace("deletePhotographer - method finished, photographer was not deleted - invalid id");
            throw new CompanyException("Photographer does not exist");
        }

        photographerRepository.deleteById(id);
        log.trace("deletePhotographer - method finished");
    }

    @Override
    public List<Photographer> filterPhotographers(String name) {
        log.trace("filterPhotographers - method entered name={}", name);


        List<Photographer> photographers = photographerRepository.filterPhotographersByName(name);


        log.trace("filterPhotographers: result{}", photographers);

        return photographers;
    }






















}
