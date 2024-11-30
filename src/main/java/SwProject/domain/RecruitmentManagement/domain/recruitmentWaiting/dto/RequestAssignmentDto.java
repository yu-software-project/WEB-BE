package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAssignmentDto {
    private Long RecruitmentId;
    private List<LocalDate> recruitmentDates;
    private String selfIntroduction;
}
