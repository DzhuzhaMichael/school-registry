package test.task.schoolregistry.service;

import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import test.task.schoolregistry.model.School;
import test.task.schoolregistry.repository.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School save(School school) {
        if (schoolRepository.existsByEdrpou(school.getEdrpou())) {
            throw new EntityExistsException("School already exists");
        }
        return schoolRepository.save(new School());
    }
}
