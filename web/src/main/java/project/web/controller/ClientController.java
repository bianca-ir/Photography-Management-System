package project.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.core.model.validators.CompanyException;
import project.core.service.IClientService;
import project.web.converter.ClientConverter;
import project.web.dto.ClientDto;
import project.web.dto.ClientsDto;

import java.util.Set;


@RestController
public class ClientController {
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDto> getAllClients(){
        log.trace("start get clients");
        Set<ClientDto> clients = clientConverter.convertModelsToDtos(clientService.getAllClients());
        log.trace("end get clients={}", clients);
        return clients;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ResponseEntity<?> saveClient(@RequestBody ClientDto clientDto){
        log.trace("start save client={}", clientDto);
        try{
            var client = clientConverter.convertDtoToModel(clientDto);
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
        log.trace("start update client={}", clientDto);
        try{
            clientService.updateClient(clientConverter.convertDtoToModel(clientDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("start delete client with id={}", id);
        try{
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CompanyException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/filter/{name}", method = RequestMethod.GET)
    Set<ClientDto> filterClients(@PathVariable String name){
        log.trace("start filter clients by name={}", name);
        Set<ClientDto> clients = clientConverter.convertModelsToDtos(clientService.filterClients(name));
        log.trace("end filter clients={}", clients);
        return clients;
    }
}
