package SwProject.domain.scrapRecruitment.service;

import SwProject.domain.scrapRecruitment.dto.ScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.model.ScrapRecruitment;
import SwProject.domain.scrapRecruitment.repository.ScrapRecruitmentRepository;
import SwProject.domain.volunteer.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapRecruitmentServiceImpl implements ScrapRecruitmentService{
    private final ScrapRecruitmentRepository scrapRecruitmentRepository;

    @Override
    public ScrapRecruitment findScrapRecruitment(ScrapRecruitmentDto scrapRecruitmentDto) {
        return scrapRecruitmentRepository.findScrapRecruitment(scrapRecruitmentDto);
    }

    @Override
    public void createScrapRecruitment(ScrapRecruitmentDto fethedScrapRecruitmentDto) {
        ScrapRecruitment scrapRecruitment = ScrapRecruitment.builder()
                .recruitment(fethedScrapRecruitmentDto.getRecruitment())
                .volunteer(fethedScrapRecruitmentDto.getVolunteer())
                .build();

        scrapRecruitmentRepository.save(scrapRecruitment);
    }

    @Override
    public void deleteScrapRecruitment(ScrapRecruitment srapRecruitment) {
        scrapRecruitmentRepository.delete(srapRecruitment);
    }

    @Override
    public List<ScrapRecruitment> findByVolunteer(Volunteer volunteer) {
        return scrapRecruitmentRepository.findByVolunteer(volunteer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScrapRecruitment> findTop3ByVolunteerOrderByIdDesc(Volunteer volunteer) {
        return scrapRecruitmentRepository.findTop3ByVolunteerOrderByIdDesc(volunteer);
    }
}
