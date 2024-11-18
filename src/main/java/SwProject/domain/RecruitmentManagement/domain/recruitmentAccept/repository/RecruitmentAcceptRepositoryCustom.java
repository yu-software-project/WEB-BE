package SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecruitmentAcceptRepositoryCustom {
    List<Volunteer> findVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildenter);
    Optional<RecruitmentAccept> findByRecruitmentIdAndVolunteerIdAndRecruitmentDate(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
    int countCompletedRecruitmentsByVolunteer(Volunteer volunteer);
}
