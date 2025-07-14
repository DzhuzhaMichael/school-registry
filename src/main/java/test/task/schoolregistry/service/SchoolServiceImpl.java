package test.task.schoolregistry.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import test.task.schoolregistry.dict.SchoolType;
import test.task.schoolregistry.dto.SchoolFilterDto;
import test.task.schoolregistry.dto.SchoolRequestDto;
import test.task.schoolregistry.dto.SchoolResponseDto;
import test.task.schoolregistry.model.School;
import test.task.schoolregistry.repository.SchoolRepository;
import test.task.schoolregistry.service.mapper.SchoolMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    private final SchoolMapper schoolMapper;

    public SchoolServiceImpl(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    @Override
    public SchoolResponseDto save(SchoolRequestDto schoolRequestDto) {
        School school = schoolMapper.mapToModel(schoolRequestDto);
        if (schoolRepository.existsByEdrpou(school.getEdrpou())) {
            throw new EntityExistsException("School already exists");
        }
        return schoolMapper.mapToDto(schoolRepository.save(school));
    }

    @Override
    public List<SchoolResponseDto> findByFilter(SchoolFilterDto filter) {
        SchoolType type = null;
        if (filter.getType() != null) {
            type = SchoolType.fromCode(filter.getType());
        }
        List<School> schools =  schoolRepository.findByFilters(
                filter.getRegion(),
                type,
                filter.getActive());
        return schools.stream()
                .map(schoolMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolResponseDto deactivate(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School with id " + id + " not found"));
        if (!school.isActive()) {
            throw new IllegalStateException("School is already deactivated");
        }
        school.setActive(false);
        School saved = schoolRepository.save(school);
        return schoolMapper.mapToDto(saved);
    }
}
