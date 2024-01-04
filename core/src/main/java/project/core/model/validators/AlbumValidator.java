package project.core.model.validators;

import org.springframework.stereotype.Component;
import project.core.model.Album;

import java.util.Optional;

@Component
public class AlbumValidator implements Validator<Album> {
    /**
     * Validate an album
     *
     * @param entity
     *          Album to be validated
     * @throws ValidatorException
     *          if album id is negative
     *          if invalid characters in photo session name
     *          if clientId is empty
     *          if photographerId is empty
     */
    @Override
    public void validate(Album entity) throws ValidatorException{
      /*  Optional.of(entity)
                .filter(album -> album.getId() > 0)
                .orElseThrow(() -> new ValidatorException("Invalid album id")); */

        Optional.of(entity)
                .filter(album -> album.getPhotoSessionName().matches("^[a-zA-Z]+$"))
                .orElseThrow(() -> new ValidatorException("Invalid photo session name"));

        Optional.of(entity)
                .filter(album -> album.getClientId() != null)
                .orElseThrow(() -> new ValidatorException("Client id can not be null"));

        Optional.of(entity)
                .filter(album -> album.getPhotographerId() != null)
                .orElseThrow(() -> new ValidatorException("Photographer id can not be null"));


    }
}

