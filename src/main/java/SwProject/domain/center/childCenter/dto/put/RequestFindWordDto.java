package SwProject.domain.center.childCenter.dto.put;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestFindWordDto {

    @NotBlank(message = "검색어를 입력해주세요.")
    private String findWord;

}