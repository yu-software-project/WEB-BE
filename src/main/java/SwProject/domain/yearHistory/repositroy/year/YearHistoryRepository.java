package SwProject.domain.yearHistory.repositroy.year;

import SwProject.domain.yearHistory.model.YearHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface YearHistoryRepository extends JpaRepository<YearHistory, CrudRepository>, YearHistoryRepositoryCustom {
    YearHistory findByDisplayIndexAndDecadeYear_ChildCenter_Id(int displayIndex, Long childCenterId);
}
