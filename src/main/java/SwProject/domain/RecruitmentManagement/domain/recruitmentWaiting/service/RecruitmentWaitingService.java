package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.service;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentAssignmentDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentWaitingUserInfoDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentWaitingService {
    void addVolunteerToRecruitment(RecruitmentAssignmentDto recruitmentAssignmentDto);
    List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter);
    void deleteFromWaitingList(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
    RecruitmentWaitingUserInfoDto showWaitingUserDetailInfo(Long userId);
}