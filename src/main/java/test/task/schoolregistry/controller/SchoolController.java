package test.task.schoolregistry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.schoolregistry.dto.SchoolRequestDto;
import test.task.schoolregistry.dto.SchoolResponseDto;
import test.task.schoolregistry.model.School;
import test.task.schoolregistry.service.SchoolService;
import test.task.schoolregistry.service.mapper.SchoolMapper;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    private final SchoolMapper schoolMapper;

    public SchoolController(SchoolService schoolService, SchoolMapper schoolMapper) {
        this.schoolService = schoolService;
        this.schoolMapper = schoolMapper;
    }

    @PostMapping
    public ResponseEntity<SchoolResponseDto> addSchool(@RequestBody SchoolRequestDto schoolRequestDto) {
        School school = schoolService.save(schoolMapper.mapToModel(schoolRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolMapper.mapToDto(school));
    }
}
