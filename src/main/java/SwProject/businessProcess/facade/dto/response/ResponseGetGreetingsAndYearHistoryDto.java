package SwProject.businessProcess.facade.dto.response;

import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.yearHistory.model.DecadeYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGetGreetingsAndYearHistoryDto {
    Greetings greeting;
    List<DecadeYear> decadeYearList;
}
