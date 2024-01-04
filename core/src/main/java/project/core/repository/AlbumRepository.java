package project.core.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.core.model.Album;

import java.util.List;


public interface AlbumRepository extends Repository<Album, Long> {
    @Query(value = "SELECT a.id, a.clientId, a.photosessionname, a.photographerid FROM album a WHERE a.photosessionname LIKE %:name%", nativeQuery = true)
    List<Album> filterAlbums(@Param("name") String name);
}

