package SwProject.domain.yearHistory.dto.decadeYear;

import SwProject.domain.yearHistory.dto.yearHistory.RequestYearDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDecadeDataDto {
    private int decadeStartYear;
    private List<RequestYearDataDto> yearList;
}

//1. decadeStartYear로 된 DecadeYear db가 있는지 확인
//2. 있으면 새로 생성, 없으면 해당 db의 List에  List<YearDataDto> yearList 내용 추가.