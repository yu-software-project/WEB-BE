package SwProject.domain.RecruitmentManagement.domain.recruitment.service;

import SwProject.Exception.collections.business.CenterNotFoundException;
import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.*;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.DaysOfWeek;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.RecruitmentManagement.domain.recruitment.repository.RecruitmentRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static SwProject.Exception.message.DbExceptionMessage.RecruitmentDatabaseNotFoundException;
import static SwProject.config.constant.DbInitConstants.*;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public void createRecruitment(RequestRecruitmentDto rqRecruitmentDto, ChildCenter childCenterPk) {
        LocalDate todayDate = LocalDate.now();

        String detailInfo = rqRecruitmentDto.getDetailInfo();
        if(detailInfo==null || detailInfo==""){
            detailInfo=initRecruitmentDetailInfo;
        }

        Recruitment newRecruitment = Recruitment.builder()
                .name(rqRecruitmentDto.getName())
                .recruitmentStartDate(todayDate)
                .recruitmentEndDate(rqRecruitmentDto.getRecruitmentEndDate())
                .isTimeExits(rqRecruitmentDto.isTimeExits())
                .startTime(rqRecruitmentDto.getStartTime())
                .endTime(rqRecruitmentDto.getEndTime())
                .startDate(rqRecruitmentDto.getStartDate())
                .endDate(rqRecruitmentDto.getEndDate())
                .isRepeatedDate(rqRecruitmentDto.getIsRepeatedDate())
                .repeatedDays(convertToDaysOfWeek(rqRecruitmentDto.getRepeatedDays()))
                .view(initRecruitmentView)
                .currentApplicants(initRecruitmentCurrentApplicants)
                .totalApplicants(rqRecruitmentDto.getTotalApplicants())
                .detailInfo(detailInfo)
                .childCenter(childCenterPk)
                .build();

        recruitmentRepository.save(newRecruitment);
    }

    public void updateRecruitment(RequestRecruitmentUpdateDto updateRecruitmentDto, ChildCenter childCenter) {

        Recruitment existingRecruitment = recruitmentRepository.findById(updateRecruitmentDto.getId());
        RequestRecruitmentDto rqRecruitmentDto = updateRecruitmentDto.getNewRecruitmentDto();

        if(existingRecruitment==null) throw new DatabaseNotFoundException(RecruitmentDatabaseNotFoundException);

        existingRecruitment.updateRecruitment(rqRecruitmentDto, convertToDaysOfWeek(rqRecruitmentDto.getRepeatedDays()));
    }

    @Transactional(readOnly = true)
    public RecruitmentPageDto getRecruitments(Pageable pageable, ChildCenter fetchedChildCenter) {
        Page<Recruitment> recruitmentPage = recruitmentRepository.findAllWithPagination(fetchedChildCenter, pageable);
        List<Recruitment> recruitments = recruitmentPage.getContent();
        long totalElements = recruitmentPage.getTotalElements();
        int totalPages = recruitmentPage.getTotalPages();
        return new RecruitmentPageDto(recruitments, totalElements , totalPages);
    }


    @Override
    public List<RecruitmentSummaryDto> getRecruitmentSummariesByChildCenter(ChildCenter childCenter) {
        List<Recruitment> recruitments = recruitmentRepository.findByChildCenter(childCenter);
        return recruitments.stream()
                .map(recruitment -> RecruitmentSummaryDto.builder()
                        .id(recruitment.getId())
                        .name(recruitment.getName())
                        .recruitmentStartDate(recruitment.getRecruitmentStartDate())
                        .recruitmentEndDate(recruitment.getRecruitmentEndDate())
                        .totalApplicants(recruitment.getTotalApplicants())
                        .currentApplicants(recruitment.getCurrentApplicants())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Recruitment getRecruitmentById(Long id) {
        return recruitmentRepository.findById(id);
    }

    private DaysOfWeek convertToDaysOfWeek(List<Boolean> repeatedDays) {
        if (repeatedDays.size() != 7) {
            throw new IllegalArgumentException("반복 요일 리스트는 7개의 항목을 가져야 합니다.");
        }

        DaysOfWeek daysOfWeek = new DaysOfWeek();
        daysOfWeek.setSunday(repeatedDays.get(0));
        daysOfWeek.setMonday(repeatedDays.get(1));
        daysOfWeek.setTuesday(repeatedDays.get(2));
        daysOfWeek.setWednesday(repeatedDays.get(3));
        daysOfWeek.setThursday(repeatedDays.get(4));
        daysOfWeek.setFriday(repeatedDays.get(5));
        daysOfWeek.setSaturday(repeatedDays.get(6));

        return daysOfWeek;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocalDate> getRecruitmentAllDates(Recruitment recruitment) {
        List<LocalDate> recruitmentDates = calculateRecruitmentDates(recruitment);

        return recruitmentDates;
    }

    @Override
    public RecruitmentPageDto findByNameWithPagination(String recruitmentName, Pageable pageable, ChildCenter fetchedChildCenter) {
        Page<Recruitment> recruitmentPage = recruitmentRepository.findByNameWithPagination(recruitmentName, fetchedChildCenter, pageable);
        List<Recruitment> recruitments = recruitmentPage.getContent();
        long totalElements = recruitmentPage.getTotalElements();
        int totalPages = recruitmentPage.getTotalPages();
        return new RecruitmentPageDto(recruitments, totalElements, totalPages);
    }


    @Override
    public List<Recruitment> findRecruitmentByWord(RequestFindWordDto requestFindWordDto) {
        List<Recruitment> recruitments = recruitmentRepository.findByNameContaining(requestFindWordDto.getFindWord());

        if(!recruitments.isEmpty()) return recruitments;
        else throw new CenterNotFoundException();

    }


    private List<LocalDate> calculateRecruitmentDates(Recruitment recruitment) {
        List<LocalDate> recruitmentDates = new ArrayList<>();
        if (recruitment.isRepeatedDate()) {
            DaysOfWeek repeatedDays = recruitment.getRepeatedDays();
            LocalDate startDate = recruitment.getStartDate();
            LocalDate endDate = recruitment.getEndDate();

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                boolean isRepeatDay = false;

                switch (dayOfWeek) {
                    case SUNDAY: isRepeatDay = repeatedDays.isSunday(); break;
                    case MONDAY: isRepeatDay = repeatedDays.isMonday(); break;
                    case TUESDAY: isRepeatDay = repeatedDays.isTuesday(); break;
                    case WEDNESDAY: isRepeatDay = repeatedDays.isWednesday(); break;
                    case THURSDAY: isRepeatDay = repeatedDays.isThursday(); break;
                    case FRIDAY: isRepeatDay = repeatedDays.isFriday(); break;
                    case SATURDAY: isRepeatDay = repeatedDays.isSaturday(); break;
                }

                if (isRepeatDay) {
                    recruitmentDates.add(date);
                }
            }
        }

        return recruitmentDates;
    }

}
