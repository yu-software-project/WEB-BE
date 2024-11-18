package SwProject.domain.center.likeChildCenter.service;

import SwProject.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import SwProject.domain.center.likeChildCenter.model.LikeCenter;
import SwProject.domain.center.likeChildCenter.repository.LikeCenterRepository;
import SwProject.domain.volunteer.model.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeCenterServiceImpl implements LikeCenterService {
    private final LikeCenterRepository likeCenterRepository;

    @Override
    public void createLikeCenter(LikeCenterInfoDto likeCenterInfoDto) {
        LikeCenter likeCenter = LikeCenter.builder()
                .childCenter(likeCenterInfoDto.getChildCenter())
                .volunteer(likeCenterInfoDto.getVolunteer())
                .build();

        likeCenterRepository.save(likeCenter);
    }

    @Override
    public void deleteLikeCenter(LikeCenter likeCenter) {
        likeCenterRepository.delete(likeCenter);
    }

    @Override
    public LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto) {
        return likeCenterRepository.findLikeCenter(likeCenterInfoDto);
    }

    @Override
    public List<LikeCenter> findByVolunteer(Volunteer volunteer) {
        return likeCenterRepository.findByVolunteer(volunteer);
    }

}
