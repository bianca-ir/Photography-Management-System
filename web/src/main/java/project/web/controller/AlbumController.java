package project.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.model.validators.CompanyException;
import project.core.service.IAlbumService;
import project.web.converter.AlbumConverter;
import project.web.dto.AlbumDto;
import project.web.dto.AlbumsDto;

import java.util.Set;


@RestController
public class AlbumController {
    public static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private AlbumConverter albumConverter;

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    Set<AlbumDto> getAllAlbums() {
        log.trace("start get albums");
        Set<AlbumDto> albums = albumConverter.convertModelsToDtos(albumService.getAllAlbums());
        log.trace("end get albums={}", albums);
        return albums;
    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    ResponseEntity<?> saveAlbum(@RequestBody AlbumDto albumDto){
        log.trace("start save album={}", albumDto);
        try{
            var album = albumConverter.convertDtoToModel(albumDto);
            albumService.saveAlbum(album);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateAlbum(@PathVariable Long id, @RequestBody AlbumDto albumDto){
        log.trace("start update album={}", albumDto);
        try{
            albumService.updateAlbum(albumConverter.convertDtoToModel(albumDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/albums/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAlbum(@PathVariable Long id){
        log.trace("start delete album with id={}", id);
        try{
            albumService.deleteAlbum(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/albums/filter/{name}", method = RequestMethod.GET)
    Set<AlbumDto> filterAlbums(@PathVariable String name){
        log.trace("start filter albums by name={}", name);
        Set<AlbumDto> albums =  albumConverter.convertModelsToDtos(albumService.filterAlbums(name));
        log.trace("end filter albums={}", albums);
        return albums;
    }

}
