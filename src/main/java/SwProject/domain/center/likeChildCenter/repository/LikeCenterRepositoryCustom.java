package SwProject.domain.center.likeChildCenter.repository;


import SwProject.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import SwProject.domain.center.likeChildCenter.model.LikeCenter;


public interface LikeCenterRepositoryCustom {
    LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto);
}
