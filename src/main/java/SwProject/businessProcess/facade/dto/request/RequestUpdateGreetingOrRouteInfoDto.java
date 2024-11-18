package SwProject.businessProcess.facade.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateGreetingOrRouteInfoDto {
    @NotNull
    String memo;
}
