package test.task.schoolregistry.service.mapper;

import org.springframework.stereotype.Component;
import test.task.schoolregistry.dict.SchoolType;
import test.task.schoolregistry.dto.SchoolRequestDto;
import test.task.schoolregistry.dto.SchoolResponseDto;
import test.task.schoolregistry.model.School;

@Component
public class SchoolMapper {

    public School mapToModel(SchoolRequestDto dto) {
        School school = new School();
        school.setName(dto.getName());
        school.setEdrpou(dto.getEdrpou());
        school.setRegion(dto.getRegion());
        school.setType(SchoolType.fromCode(dto.getType()));
        school.setActive(true);
        return school;
    }

    public SchoolResponseDto mapToDto(School school) {
        SchoolResponseDto dto = new SchoolResponseDto();
        dto.setId(school.getId());
        dto.setName(school.getName());
        dto.setEdrpou(school.getEdrpou());
        dto.setRegion(school.getRegion());
        dto.setType(school.getType().getCode());
        dto.setActive(school.isActive());
        return dto;
    }
}
