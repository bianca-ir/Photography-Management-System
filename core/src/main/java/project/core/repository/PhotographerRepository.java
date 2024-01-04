package project.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.core.model.Photographer;

import java.util.List;

public interface PhotographerRepository extends Repository<Photographer, Long> {
    @Query(value = "SELECT p.id, p.age, p.camerabrand, p.name, p.rating FROM photographer p WHERE p.name LIKE %:name%", nativeQuery = true)
    List<Photographer> filterPhotographersByName(@Param("name") String name);
}
