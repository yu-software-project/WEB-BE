package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class RecruitmentPaginationResponseDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalApplicants;
    private int currentApplicants;
    private int view;
}