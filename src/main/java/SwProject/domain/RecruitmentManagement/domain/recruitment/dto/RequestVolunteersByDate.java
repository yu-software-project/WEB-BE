package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestVolunteersByDate {
    @NotNull
    private Long recruitmentId;
    @NotNull
    private LocalDate localDate;
}