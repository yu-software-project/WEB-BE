package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSummaryDto {
    private Long id;
    private String name;
    private LocalDate recruitmentStartDate;
    private LocalDate recruitmentEndDate;
    private int totalApplicants;
    private int currentApplicants;
}