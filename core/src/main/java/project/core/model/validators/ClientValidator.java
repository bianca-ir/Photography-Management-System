package project.core.model.validators;

import org.springframework.stereotype.Component;
import project.core.model.Client;

import java.util.Optional;

@Component
public class ClientValidator implements Validator<Client> {
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
    public void validate(Client entity) throws ValidatorException{
        Optional.of(entity)
                .filter(client -> !client.getName().equals(""))
                .orElseThrow(() -> new ValidatorException("Name must not be empty"));

        Optional.of(entity)
                .filter(client -> !client.getEmail().equals(""))
                .orElseThrow(() -> new ValidatorException("Email must not be empty"));

        Optional.of(entity)
                .filter(client -> client.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
                .orElseThrow(() -> new ValidatorException("Invalid email"));

        Optional.of(entity)
                .filter(client -> !client.getPhoneNumber().equals(""))
                .orElseThrow(() -> new ValidatorException("Phone number must not be empty"));

        Optional.of(entity)
                .filter(client -> client.getPhoneNumber().matches("^[0-9]{10}$"))
                .orElseThrow(() -> new ValidatorException("Invalid phone number"));
    }
}
