package SwProject.domain.center.likeChildCenter.service;

import SwProject.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import SwProject.domain.center.likeChildCenter.model.LikeCenter;
import SwProject.domain.volunteer.model.Volunteer;

import java.util.List;

public interface LikeCenterService {
    void createLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
    void deleteLikeCenter(LikeCenter likeCenter);
    LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
    List<LikeCenter> findByVolunteer(Volunteer volunteer);

}
