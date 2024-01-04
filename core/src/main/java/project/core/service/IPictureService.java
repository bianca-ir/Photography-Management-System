package project.core.service;

import project.core.model.Picture;
import project.core.model.validators.CompanyException;

import java.util.List;


public interface IPictureService {

    List<Picture> getAllPictures();
    void savePicture(Picture picture) throws CompanyException;
    void updatePicture(Picture picture) throws CompanyException;
    void deletePicture(Long id) throws CompanyException;
    List<Picture> filterPictures(String title);
}
