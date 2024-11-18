package SwProject.domain.yearHistory.service.decadeYear;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.yearHistory.dto.decadeYear.CreateDecadeYearDto;
import SwProject.domain.yearHistory.model.DecadeYear;

import java.util.List;

public interface DecadeYearService {
    DecadeYear createDecadeYear(CreateDecadeYearDto createDecadeYearDto);
    List<DecadeYear> findAllDecadeYearDesc(ChildCenter fechedChildCenter);
}
