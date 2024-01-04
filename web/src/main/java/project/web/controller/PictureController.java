package project.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.model.validators.CompanyException;
import project.core.service.IPictureService;
import project.web.converter.PictureConverter;
import project.web.dto.PictureDto;
import project.web.dto.PicturesDto;

import java.util.Set;


@RestController
public class PictureController {
    public static final Logger log = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private PictureConverter pictureConverter;

    @RequestMapping(value = "/pictures", method = RequestMethod.GET)
    Set<PictureDto> getAllPictures(){
        log.trace("start get pictures");
        Set<PictureDto> pictures = pictureConverter.convertModelsToDtos(pictureService.getAllPictures());
        log.trace("end get pictures={}", pictures);
        return pictures;
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.POST)
    ResponseEntity<?> savePicture(@RequestBody PictureDto pictureDto){
        log.trace("start save picture={}", pictureDto);
        try{
            var picture = pictureConverter.convertDtoToModel(pictureDto);
            pictureService.savePicture(picture);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updatePicture(@PathVariable Long id, @RequestBody PictureDto pictureDto){
        log.trace("start update picture={}", pictureDto);
        try{
            pictureService.updatePicture(pictureConverter.convertDtoToModel(pictureDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePicture(@PathVariable Long id){
        log.trace("start delete picture with id={}", id);
        try{
            pictureService.deletePicture(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pictures/filter/{title}", method = RequestMethod.GET)
    Set<PictureDto> filterClients(@PathVariable String title){
        log.trace("start filter pictures by title={}", title);
        Set<PictureDto> pictures = pictureConverter.convertModelsToDtos(pictureService.filterPictures(title));
        log.trace("end filter pictures={}", pictures);
        return pictures;
    }
}
