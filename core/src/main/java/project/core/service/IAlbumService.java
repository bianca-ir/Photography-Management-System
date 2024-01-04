package project.core.service;

import project.core.model.Album;
import project.core.model.validators.CompanyException;

import java.util.List;

public interface IAlbumService {
    List<Album> getAllAlbums();
    void saveAlbum(Album album) throws CompanyException;
    void updateAlbum(Album album) throws CompanyException;
    void deleteAlbum(Long id) throws CompanyException;
    List<Album> filterAlbums(String name);
}
