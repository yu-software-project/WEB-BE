package SwProject.domain.RecruitmentManagement.domain.recruitment.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.center.childCenter.model.ChildCenter;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecruitmentRepository  extends JpaRepository<Recruitment, CrudRepository>, RecruitmentRepositoryCustom {
    List<Recruitment> findByChildCenter(ChildCenter childCenter);

    Recruitment findById(Long id);

    @Query("SELECT r FROM Recruitment r WHERE r.name LIKE %:keyword%")
    List<Recruitment> findByNameContaining(@Param("keyword") String keyword);

}
