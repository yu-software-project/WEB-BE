package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RecruitmentWaitingRepository extends JpaRepository<RecruitmentWaiting, CrudRepository>, RecruitmentWaitingRepositoryCustom {
    Optional<RecruitmentWaiting> findByRecruitmentIdAndVolunteerIdAndRecruitmentDatesContains(Long recruitmentId, Long volunteerId, LocalDate date);
}
