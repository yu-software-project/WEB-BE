package SwProject.domain.scrapRecruitment.repository;

import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;
import SwProject.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScrapRecruitmentRepository extends JpaRepository<ScrapRecruitment, CrudRepository>, ScrapRecruitmentRepositoryCustom {
    List<ScrapRecruitment> findByVolunteer(Volunteer volunteer);
    List<ScrapRecruitment> findTop3ByVolunteerOrderByIdDesc(Volunteer volunteer);
}
