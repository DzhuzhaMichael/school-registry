package test.task.schoolregistry.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.task.schoolregistry.dto.SchoolFilterDto;
import test.task.schoolregistry.dto.SchoolRequestDto;
import test.task.schoolregistry.dto.SchoolResponseDto;
import test.task.schoolregistry.service.SchoolService;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<SchoolResponseDto> addSchool(@Valid @RequestBody SchoolRequestDto schoolRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolService.save(schoolRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<SchoolResponseDto>> getSchoolsByFilter(@ModelAttribute SchoolFilterDto filter) {
        return ResponseEntity.status(HttpStatus.OK).body(schoolService.findByFilter(filter));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<SchoolResponseDto> deactivateSchool(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(schoolService.deactivate(id));
    }
}
