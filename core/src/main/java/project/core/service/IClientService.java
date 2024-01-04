package project.core.service;


import project.core.model.Client;
import project.core.model.validators.CompanyException;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();
    void saveClient(Client client) throws CompanyException;
    void updateClient(Client client) throws CompanyException;
    void deleteClient(Long id) throws CompanyException;
    List<Client> filterClients(String name);
}
