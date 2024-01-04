package project.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.core.model.Client;
import project.core.model.validators.CompanyException;
import project.core.model.validators.Validator;
import project.core.model.validators.ValidatorException;
import project.core.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService implements IClientService {
    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Validator<Client> clientValidator;

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients --- method entered");

        List<Client> result = clientRepository.findAll();

        log.trace("getAllClients: result{}", result);

        return result;
    }

    @Override
    public void saveClient(Client client) throws CompanyException {
        log.trace("saveClient - method entered client={}", client);
        try{
            clientValidator.validate(client);
        }
        catch (ValidatorException exception){
            log.trace("saveClient - method finished, client was not saved - invalid client");
            throw new CompanyException(exception.getMessage());
        }

        Client c = clientRepository.save(client);
        log.trace("saveClient - method finished client={}", c);
    }

    @Override
    @Transactional
    public void updateClient(Client client) throws CompanyException {
        log.trace("updateClient - method entered: client{}", client);

        try{
            clientValidator.validate(client);
        }
        catch (ValidatorException exception){
            log.trace("updateClient - method finished, client was not updated - invalid client");
            throw new CompanyException(exception.getMessage());
        }

        if(!clientRepository.existsById(client.getId())){
            log.trace("updateClient - method finished, client was not updated - invalid id");
            throw new CompanyException("Client does not exist");
        }


        clientRepository.findById(client.getId())
                .ifPresent(c -> {
                    c.setEmail(client.getEmail());
                    c.setName(client.getName());
                    c.setPhoneNumber(client.getPhoneNumber());
                    log.debug("updateClient - updated: c={}", c);
                });
        log.trace("updateClient - method finished");
    }

    @Override
    public void deleteClient(Long id) throws CompanyException {
        log.trace("deleteClient - method entered id={}", id);

        if(!clientRepository.existsById(id)){
            log.trace("deleteClient - method finished, client was not deleted - invalid id");
            throw new CompanyException("Client does not exist");
        }

        clientRepository.deleteById(id);
        log.trace("deleteClient - method finished");
    }

    @Override
    public List<Client> filterClients(String name) {
        log.trace("filterClients - method entered name={}", name);


        Iterable<Client> clients = clientRepository.filterClientsByName(name);

        List<Client> result = StreamSupport.stream(clients.spliterator(), false)
                .filter(client -> client.getName().contains(name))
                .collect(Collectors.toList());

        log.trace("filterClients: result{}", result);

        return result;
    }
}
