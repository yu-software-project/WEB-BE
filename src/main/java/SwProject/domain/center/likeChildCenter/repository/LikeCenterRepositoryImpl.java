package SwProject.domain.center.likeChildCenter.repository;

import SwProject.domain.center.likeChildCenter.dto.LikeCenterInfoDto;
import SwProject.domain.center.likeChildCenter.model.LikeCenter;
import SwProject.domain.center.likeChildCenter.model.QLikeCenter;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LikeCenterRepositoryImpl implements LikeCenterRepositoryCustom{
    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public LikeCenter findLikeCenter(LikeCenterInfoDto likeCenterInfoDto) {
        QLikeCenter qLikeCenter = QLikeCenter.likeCenter;

        return jpqlQueryFactory
                .selectFrom(qLikeCenter)
                .where(qLikeCenter.volunteer.eq(likeCenterInfoDto.getVolunteer())
                        .and(qLikeCenter.childCenter.eq(likeCenterInfoDto.getChildCenter())))
                .fetchOne();
    }

}
