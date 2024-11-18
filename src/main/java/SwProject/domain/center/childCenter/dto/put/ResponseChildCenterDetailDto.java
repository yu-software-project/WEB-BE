package SwProject.domain.center.childCenter.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChildCenterDetailDto {
    private Long id;
    private String centerName;
    private String roadAddress;
    private String detailAddress;
    private String phoneNumber;
    private boolean isLike;
}