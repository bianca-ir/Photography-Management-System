package project.web.converter;
import org.springframework.stereotype.Component;
import project.core.model.Photographer;
import project.web.dto.PhotographerDto;

@Component
public class PhotographerConverter extends BaseConverter<Photographer, PhotographerDto> {

    @Override
    public Photographer convertDtoToModel(PhotographerDto dto) {
        Photographer photographer = Photographer.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .cameraBrand(dto.getCameraBrand())
                .rating(dto.getRating())
                .build();
        photographer.setId(dto.getId());
        return photographer;
    }

    @Override
    public PhotographerDto convertModelToDto(Photographer photographer) {
        PhotographerDto dto = PhotographerDto.builder()
                .name(photographer.getName())
                .age(photographer.getAge())
                .cameraBrand(photographer.getCameraBrand())
                .rating(photographer.getRating())
                .build();
        dto.setId(photographer.getId());
        return dto;
    }
}
