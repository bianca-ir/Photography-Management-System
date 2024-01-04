package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.Album;
import project.web.dto.AlbumDto;

@Component
public class AlbumConverter extends BaseConverter<Album, AlbumDto> {

    @Override
    public Album convertDtoToModel(AlbumDto dto) {
        Album album = Album.builder()
                .photoSessionName(dto.getPhotoSessionName())
                .clientId(dto.getClientId())
                .photographerId(dto.getPhotographerId())
                .build();
        album.setId(dto.getId());
        return album;
    }

    @Override
    public AlbumDto convertModelToDto(Album album) {
        AlbumDto dto = AlbumDto.builder()
                .photoSessionName(album.getPhotoSessionName())
                .clientId(album.getClientId())
                .photographerId(album.getPhotographerId())
                .build();
        dto.setId(album.getId());
        return dto;
    }
}
