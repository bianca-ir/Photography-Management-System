package project.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.model.validators.CompanyException;
import project.core.service.IPhotographerService;
import project.web.converter.PhotographerConverter;
import project.web.dto.PhotographerDto;
import project.web.dto.PhotographersDto;

import java.util.Set;


@RestController
public class PhotographerController {
    public static final Logger log = LoggerFactory.getLogger(PhotographerController.class);

    @Autowired
    private IPhotographerService photographerService;

    @Autowired
    private PhotographerConverter photographerConverter;

    @RequestMapping(value = "/photographers", method = RequestMethod.GET)
    Set<PhotographerDto> getAllPhotographers(){
        log.trace("start get photographers");
        Set<PhotographerDto> photographers = photographerConverter.convertModelsToDtos(photographerService.getAllPhotographers());
        log.trace("end get photographers={}", photographers);
        return photographers;
    }

    @RequestMapping(value = "/photographers", method = RequestMethod.POST)
    ResponseEntity<?> savePhotographer(@RequestBody PhotographerDto photographerDto){
        log.trace("start save photographer={}", photographerDto);
        try{
            var photographer = photographerConverter.convertDtoToModel(photographerDto);
            photographerService.savePhotographer(photographer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/photographers/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updatePhotographer(@PathVariable Long id, @RequestBody PhotographerDto photographerDto){
        log.trace("start update photographer={}", photographerDto);
        try{
            photographerService.updatePhotographer(photographerConverter.convertDtoToModel(photographerDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/photographers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePhotographer(@PathVariable Long id){
        log.trace("start delete photographer with id={}", id);
        try{
            photographerService.deletePhotographer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/photographers/filter/{title}", method = RequestMethod.GET)
    Set<PhotographerDto> filterPhotographers(@PathVariable String title){
        log.trace("start filter photographers by title={}", title);
        Set<PhotographerDto> photographers = photographerConverter.convertModelsToDtos(photographerService.filterPhotographers(title));
        log.trace("end filter photographers={}", photographers);
        return photographers;
    }
}
