package SwProject.businessProcess.facade.dto;

import SwProject.domain.center.childCenter.model.ChildCenter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ManagerRegisterDto {
    ChildCenter childCenter;
}
