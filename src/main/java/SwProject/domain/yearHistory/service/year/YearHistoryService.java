package SwProject.domain.yearHistory.service.year;

import SwProject.domain.yearHistory.dto.yearHistory.CreateYearHistoryDto;
import SwProject.domain.yearHistory.model.YearHistory;

import java.util.List;

public interface YearHistoryService {
    List<YearHistory> saveAllYear( CreateYearHistoryDto createYearHistoryDto);
}
