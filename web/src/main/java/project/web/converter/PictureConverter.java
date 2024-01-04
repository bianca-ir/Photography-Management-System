package project.web.converter;

import org.springframework.stereotype.Component;
import project.core.model.Picture;
import project.web.dto.PictureDto;


@Component
public class PictureConverter extends BaseConverter<Picture, PictureDto> {

    @Override
    public Picture convertDtoToModel(PictureDto dto) {
        Picture picture = Picture.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .build();
        picture.setId(dto.getId());
        return picture;
    }

    @Override
    public PictureDto convertModelToDto(Picture picture) {
        PictureDto dto = PictureDto.builder()
                .title(picture.getTitle())
                .description(picture.getDescription())
                .width(picture.getWidth())
                .height(picture.getHeight())
                .build();
        dto.setId(picture.getId());
        return dto;
    }
}
