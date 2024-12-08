package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto;

import SwProject.domain.RecruitmentManagement.domain.recruitment.model.Recruitment;
import SwProject.domain.volunteer.model.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentAssignmentDto {
    private Recruitment recruitment;
    private Volunteer volunteer;
    private String selfIntroduction;
}