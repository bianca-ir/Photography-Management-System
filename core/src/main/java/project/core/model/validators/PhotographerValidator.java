package project.core.model.validators;



import org.springframework.stereotype.Component;
import project.core.model.Photographer;

import java.util.Optional;

@Component
public class PhotographerValidator implements Validator<Photographer> {
    /**
     * Validate a client
     *
     * @param entity
     *          Client to be validated
     * @throws ValidatorException
     *          if client code is empty
     *          if client name is empty
     *          if client name is empty
     *          if client phone number is empty
     *          if invalid email format
     *          if invalid phone number format
     */
    @Override
    public void validate(Photographer entity) throws ValidatorException{
        Optional.of(entity)
                .filter(photographer -> !photographer.getName().equals(""))
                .orElseThrow(()-> new ValidatorException("Name must not be empty"));
        Optional.of(entity)
                .filter(photographer -> photographer.getAge()>0)
                .orElseThrow(()-> new ValidatorException("Age must not be less or equal than zero"));
        Optional.of(entity)
                .filter(photographer -> !photographer.getCameraBrand().equals(""))
                .orElseThrow(()-> new ValidatorException("Camera Brand must not be empty"));
        Optional.of(entity)
                .filter(photographer -> (photographer.getRating()>=0 && photographer.getRating()<=10))
                .orElseThrow(()->new ValidatorException("Rating must be greater or equal than 0 and less or equal than ten"));
    }
}