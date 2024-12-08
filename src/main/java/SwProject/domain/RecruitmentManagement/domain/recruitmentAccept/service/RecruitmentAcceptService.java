package SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.service;


import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentAcceptService {
    void acceptVolunteer(Long recruitmentId, Long volunteerId);
    void deleteVolunteer(Long recruitmentId, Long volunteerId);
    boolean isVolunteerAlreadyAccepted(Long volunteerId);
    boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto);
    List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter);
    int countCompletedRecruitmentsByVolunteer(Volunteer volunteer);
}