package SwProject.domain.RecruitmentManagement.facade;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.*;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentWaitingUserInfoDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RecruitmentFacadeService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto);
    void updateRecruitment(RequestRecruitmentUpdateDto requestRecruitmentUpdateDto);
    RecruitmentPageDto getRecruitments(Pageable pageable);
    void addVolunteerToRecruitment(RequestAssignmentDto requestAssignmentDto);
    VolunteerByDateResponseDto getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate);
    void acceptVolunteer(Long recruitmentId, Long volunteerId);
    RecruitmentPageDto findByNameWithPagination(RequestFindByName requestFindByName, Pageable pageable);
    void deleteVolunteerFromRecruitmentAccept (Long recruitmentId, Long volunteerId);
    void deleteVolunteerFromRecruitmentWaiting (Long recruitmentId, Long volunteerId);
    RecruitmentWaitingUserInfoDto showWaitingUserDetailInfo(Long userId);
}
