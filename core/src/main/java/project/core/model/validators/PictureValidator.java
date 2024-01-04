package project.core.model.validators;


import org.springframework.stereotype.Component;
import project.core.model.Picture;

import java.util.Optional;


@Component
public class PictureValidator implements Validator<Picture> {
    @Override
    public void validate(Picture entity) throws ValidatorException{
        Optional.of(entity)
                .filter(picture->!picture.getTitle().equals(""))
                .orElseThrow(()-> new ValidatorException("Title must not be empty"));
        Optional.of(entity)
                .filter(picture->!picture.getDescription().equals(""))
                .orElseThrow(()-> new ValidatorException("Description must not be empty"));
        Optional.of(entity)
                .filter(picture->picture.getWidth()>0)
                .orElseThrow(()-> new ValidatorException("Width must not be less or equal than zero"));
        Optional.of(entity)
                .filter(picture->picture.getHeight()>0)
                .orElseThrow(()-> new ValidatorException("Height must not be less or equal than zero"));
    }
}
