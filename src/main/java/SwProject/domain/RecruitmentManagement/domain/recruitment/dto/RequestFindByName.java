package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFindByName {

    @NotNull(message = "검색할 이름은 필수 항목입니다.")
    private String recruitmentName;

}
