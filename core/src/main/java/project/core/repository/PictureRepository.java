package project.core.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.core.model.Picture;

import java.util.List;

public interface PictureRepository extends Repository<Picture, Long> {
    @Query(value = "SELECT p.id, p.description, p.height, p.title, p.width FROM photographer p WHERE p.title LIKE %:title%", nativeQuery = true)
    List<Picture> filterPicturesByTitle(@Param("title") String title);

}
