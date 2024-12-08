package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.service;

import SwProject.Exception.collections.business.WaitingUserNotFoundException;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentAssignmentDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RecruitmentWaitingUserInfoDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.repository.RecruitmentWaitingRepository;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitmentWaitingServiceImpl implements RecruitmentWaitingService {
    private final RecruitmentWaitingRepository recruitmentWaitingRepository;

    @Override
    @Transactional
    public void addVolunteerToRecruitment(RecruitmentAssignmentDto recruitmentAssignmentDto) {
        RecruitmentWaiting recruitmentWaiting = RecruitmentWaiting.builder()
                .volunteer(recruitmentAssignmentDto.getVolunteer())
                .recruitment(recruitmentAssignmentDto.getRecruitment())
                .selfIntroduction(recruitmentAssignmentDto.getSelfIntroduction())
                .build();

        recruitmentWaitingRepository.save(recruitmentWaiting);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter) {
        return recruitmentWaitingRepository.findVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);
    }


    @Transactional
    @Override
    public void deleteFromWaitingList(Long recruitmentId, Long volunteerId) {
        RecruitmentWaiting recruitmentWaiting = recruitmentWaitingRepository.findByRecruitmentIdAndVolunteerId(recruitmentId, volunteerId)
                .orElseThrow(() -> new IllegalArgumentException("Waiting list entry not found"));

        recruitmentWaitingRepository.delete(recruitmentWaiting);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return recruitmentWaitingRepository.isDuplicateRecruitment(volunteer, requestAssignmentDto);
    }

    @Override
    public RecruitmentWaitingUserInfoDto showWaitingUserDetailInfo(Long userId) {
        Optional<RecruitmentWaiting> recruitmentWaiting = recruitmentWaitingRepository.findByVolunteerId(userId);

        if(!recruitmentWaiting.isPresent()){
            throw new WaitingUserNotFoundException();
        }

        Volunteer foundVolunteer = recruitmentWaiting.get().getVolunteer();

        RecruitmentWaitingUserInfoDto recruitmentWaitingUserInfoDto = new RecruitmentWaitingUserInfoDto(
                foundVolunteer.getName(),
                foundVolunteer.getGender(),
                foundVolunteer.getFormattedBirthDate(),
                foundVolunteer.getPhoneNum(),
                recruitmentWaiting.get().getSelfIntroduction());

        return recruitmentWaitingUserInfoDto;
    }
}