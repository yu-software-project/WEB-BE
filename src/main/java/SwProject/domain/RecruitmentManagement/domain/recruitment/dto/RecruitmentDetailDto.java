package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDetailDto {
    private Long id;
    private String name;
    private LocalDate recruitmentStartDate;
    private LocalDate recruitmentEndDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalApplicants;
    private String detailInfo;
}
