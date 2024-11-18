package SwProject.domain.yearHistory.facade;

import SwProject.domain.yearHistory.dto.decadeYear.RequestDecadeDataDto;

import java.util.List;

public interface YearFacadeService {
    void createYearHistory(List<RequestDecadeDataDto> yearDataList);
    void updateYearHistory(List<RequestDecadeDataDto> yearDataList);
}
