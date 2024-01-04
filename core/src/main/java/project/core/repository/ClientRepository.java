package project.core.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.core.model.Client;

import java.util.List;

public interface ClientRepository extends Repository<Client, Long> {
    @Query(value = "SELECT c.id, c.name, c.email, c.phoneNumber FROM client c WHERE c.name LIKE %:name%", nativeQuery = true)
    List<Client> filterClientsByName(@Param("name") String name);

}
