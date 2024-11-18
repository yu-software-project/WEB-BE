package SwProject.domain.yearHistory.dto.decadeYear;

import SwProject.domain.center.childCenter.model.ChildCenter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDecadeYearDto {
    ChildCenter childCenter;
    int decadeStartYear;
}
