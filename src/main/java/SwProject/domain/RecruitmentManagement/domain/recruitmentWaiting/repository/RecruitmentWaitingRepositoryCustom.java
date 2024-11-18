package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;

import java.util.List;

public interface RecruitmentWaitingRepositoryCustom {
    List<Volunteer> findVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildenter);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
}
