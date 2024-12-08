package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAssignmentDto {
    @NotNull
    private Long RecruitmentId;
    @NotNull
    private String selfIntroduction;
}
