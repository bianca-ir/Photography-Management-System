package project.core.service;

import project.core.model.Photographer;
import project.core.model.validators.CompanyException;

import java.util.List;

public interface IPhotographerService {

    List<Photographer> getAllPhotographers();
    void savePhotographer(Photographer photographer) throws CompanyException;
    void updatePhotographer(Photographer photographer) throws CompanyException;
    void deletePhotographer(Long id) throws CompanyException;
    List<Photographer> filterPhotographers(String name);
}
