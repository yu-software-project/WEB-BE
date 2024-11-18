package SwProject.domain.facility.floorSize.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFloorSizeDto {

    @NotBlank
    private String floor; //층수표시

    @Min(0) //들어오는 숫자값이 0 이상이어 함.
    private int area; //프론트에서 , 같은 문자기호 빼고 숫자값으로만 줬음 좋겠음

    @NotBlank
    private String purpose; //주요실

    @NotBlank
    private String remark; //비고

    @NotNull
    private int displayIndex; //실제 표에 나타날 위치인덱스

}
