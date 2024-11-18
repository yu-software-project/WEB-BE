package SwProject.domain.yearHistory.dto.yearHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestYearDataDto {
    private int year;
    private String memo;
    private int displayIndex;
}