package SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.service;


import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.Exception.collections.business.DuplicateRecruitmentPerVolunteerException;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.RecruitmentManagement.domain.recruitment.repository.RecruitmentRepository;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.repository.RecruitmentAcceptRepository;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;
import SwProject.domain.volunteer.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static SwProject.Exception.message.DbExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class RecruitmentAcceptServiceImpl implements RecruitmentAcceptService {
    private final RecruitmentAcceptRepository recruitmentAcceptRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final VolunteerRepository volunteerRepository;

    @Transactional
    @Override
    public void acceptVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId);
        if(recruitment==null) throw new DatabaseNotFoundException(RecruitmentDatabaseNotFoundException);

        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if( volunteer==null) throw new DatabaseNotFoundException(VolunteerDatabaseNotFoundException );

        if (!isVolunteerAlreadyAccepted(volunteerId, recruitmentDate)) {
            RecruitmentAccept acceptList = RecruitmentAccept.builder()
                    .volunteer(volunteer)
                    .recruitment(recruitment)
                    .recruitmentDates(List.of(recruitmentDate))
                    .build();
            recruitmentAcceptRepository.save(acceptList);
            recruitment.incrementCurrentApplicants();

        } else {
            throw new DuplicateRecruitmentPerVolunteerException();
        }
    }

    @Override
    @Transactional
    public void deleteVolunteer(Long recruitmentId, Long volunteerId, LocalDate recruitmentDate) {
        RecruitmentAccept recruitmentAccept = recruitmentAcceptRepository.findByRecruitmentIdAndVolunteerIdAndRecruitmentDate(recruitmentId, volunteerId, recruitmentDate)
                .orElseThrow(() -> new DatabaseNotFoundException(VolunteerDatabaseNotFoundException ));
        recruitmentAcceptRepository.delete(recruitmentAccept);
        Recruitment recruitment = recruitmentAccept.getRecruitment();
        recruitment.decrementCurrentApplicants();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isVolunteerAlreadyAccepted(Long volunteerId, LocalDate date) {
        return recruitmentAcceptRepository.existsByVolunteerIdAndRecruitmentDatesContains(volunteerId, date);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return recruitmentAcceptRepository.isDuplicateRecruitment(volunteer, requestAssignmentDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Volunteer> getVolunteersByDate(RequestVolunteersByDate requestVolunteersByDate, ChildCenter fetchedChildCenter) {
        return recruitmentAcceptRepository.findVolunteersByDate(requestVolunteersByDate, fetchedChildCenter);
    }

    @Override
    @Transactional(readOnly = true)
    public int countCompletedRecruitmentsByVolunteer(Volunteer volunteer) {
        return recruitmentAcceptRepository.countCompletedRecruitmentsByVolunteer(volunteer);
    }

}