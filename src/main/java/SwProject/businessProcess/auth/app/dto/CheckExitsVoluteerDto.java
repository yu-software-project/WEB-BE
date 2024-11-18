package SwProject.businessProcess.auth.app.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckExitsVoluteerDto {
    private String email;
    private String phonNum;
}
