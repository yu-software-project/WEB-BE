package SwProject.domain.center.likeChildCenter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLikeCenterDto {

    @NotNull(message = "해당 보육원의 id를 입력하세요.")
    private Long childCenterId;

}
