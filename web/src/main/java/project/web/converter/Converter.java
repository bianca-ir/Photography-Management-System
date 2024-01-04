package project.web.converter;


import project.core.model.BaseEntity;
import project.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
