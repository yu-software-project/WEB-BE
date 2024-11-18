package SwProject.domain.yearHistory.service.decadeYear;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.yearHistory.dto.decadeYear.CreateDecadeYearDto;
import SwProject.domain.yearHistory.model.DecadeYear;
import SwProject.domain.yearHistory.repositroy.decadeYear.DecadeYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DecadeYearServiceImpl implements DecadeYearService {
    private final DecadeYearRepository decadeYearRepository;

    @Override
    public DecadeYear createDecadeYear(CreateDecadeYearDto createDecadeYearDto) {
        DecadeYear decadeYear = DecadeYear.builder()
                .decadeStartYear(createDecadeYearDto.getDecadeStartYear())
                .yearList(new ArrayList<>())
                .childCenter(createDecadeYearDto.getChildCenter())
                .build();

        decadeYearRepository.save(decadeYear);

        return decadeYear;
    }

    @Override
    public List<DecadeYear> findAllDecadeYearDesc(ChildCenter fechedChildCenter) {
        //현재 보육원에 해당하는 db객체 찾기
        return decadeYearRepository.findByChildCenterOrderByDecadeStartYearAsc(fechedChildCenter);
    }
}
