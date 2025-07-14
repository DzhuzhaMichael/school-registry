package test.task.schoolregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.task.schoolregistry.dict.SchoolType;
import test.task.schoolregistry.model.School;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    boolean existsByEdrpou(String edrpou);

    @Query("""
        SELECT s FROM School s
        WHERE (:region IS NULL OR s.region = :region)
        AND (:type IS NULL OR s.type = :type)
        AND (:active IS NULL OR s.isActive = :active)
    """)
    List<School> findByFilters(@Param("region") String region,
                               @Param("type") SchoolType type,
                               @Param("active") Boolean active);
}
