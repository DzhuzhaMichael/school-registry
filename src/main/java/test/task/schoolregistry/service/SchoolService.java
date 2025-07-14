package test.task.schoolregistry.service;

import test.task.schoolregistry.dto.SchoolFilterDto;
import test.task.schoolregistry.dto.SchoolRequestDto;
import test.task.schoolregistry.dto.SchoolResponseDto;

import java.util.List;

public interface SchoolService {

    SchoolResponseDto save(SchoolRequestDto schoolRequestDto);

    List<SchoolResponseDto> findByFilter(SchoolFilterDto filter);

    SchoolResponseDto deactivate(Long id);
}
