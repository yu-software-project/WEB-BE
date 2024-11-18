package SwProject.domain.RecruitmentManagement.domain.recruitment.service;


import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.*;
import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RecruitmentService {
    void createRecruitment(RequestRecruitmentDto requestRecruitmentDto, ChildCenter childCenterPk);
    void updateRecruitment(RequestRecruitmentUpdateDto requestRecruitmentUpdateDto, ChildCenter childCenter);
    RecruitmentPageDto getRecruitments(Pageable pageable, ChildCenter fetchedChildCenter);
    List<RecruitmentSummaryDto> getRecruitmentSummariesByChildCenter(ChildCenter childCenter);
    Recruitment getRecruitmentById(Long id);
    List<LocalDate> getRecruitmentAllDates(Recruitment recruitment);
    RecruitmentPageDto findByNameWithPagination(String recruitmentName, Pageable pageable, ChildCenter fetchedChildCenter);
    List<Recruitment> findRecruitmentByWord(RequestFindWordDto requestDto);
}
