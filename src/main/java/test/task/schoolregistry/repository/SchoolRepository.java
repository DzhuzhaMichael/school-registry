package test.task.schoolregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.task.schoolregistry.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

    boolean existsByEdrpou(String edrpou);
}
