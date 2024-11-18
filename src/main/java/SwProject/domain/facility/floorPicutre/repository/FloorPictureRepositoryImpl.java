package SwProject.domain.facility.floorPicutre.repository;

import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import SwProject.domain.facility.floorPicutre.model.QFloorPicture;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FloorPictureRepositoryImpl implements FloorPictureRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public UpdateFloorPictureDto updateFloorPicture(FloorPictureDto updateFloorPicture) {
//        //imageIndex로 기존의 객체 찾기
//        FloorPicture existingFloorPicture = getFloorPictureToUpdate(updateFloorPicture.getImageIndex());
//
//        // 새로운 정보로 FloorPictureDto로부터 FloorPicture 객체 생성
//        FloorPicture newFloorPicture = FloorPicture.builder()
//                .floor(updateFloorPicture.getFloor())
//                .purpose(updateFloorPicture.getPurpose())
//                .imageIndex(updateFloorPicture.getImageIndex())
//                .floorPictureCluster(existingFloorPicture.getFloorPictureCluster()) // 기존의 FloorPictureCluster를 유지
//                .build();
//
//        UpdateFloorPictureDto updateFloorPictureDto = UpdateFloorPictureDto.builder()
//                .oldFloorPicture(existingFloorPicture)
//                .newFloorPicture(newFloorPicture)
//                .build();
//
//        return updateFloorPictureDto;
//    }

//    private FloorPicture getFloorPictureToUpdate(int imageIndex) {
//        QFloorPicture qFloorPicture = QFloorPicture.floorPicture;
//
//        FloorPicture excitiongFloorPicture = jpaQueryFactory
//                .selectFrom(qFloorPicture)
//                .where(qFloorPicture.imageIndex.eq(imageIndex))
//                .fetchOne();
//
//        if(excitiongFloorPicture==null){
//            throw new DatabaseNotFoundException(DbExceptionMessage.FloorPictureDatabaseNotFoundException);
//        }
//
//        return excitiongFloorPicture;
//    }


    @Override
    public FloorPicture updateFloorPicture(FloorPicture newFloorPicture) {

        QFloorPicture qFloorPicture = QFloorPicture.floorPicture;

        FloorPicture oldFloorPicture  = jpaQueryFactory
                .selectFrom(qFloorPicture)
                .where(qFloorPicture.floor.eq(newFloorPicture.getFloor())
                        .and(qFloorPicture.floorPictureCluster.eq(newFloorPicture.getFloorPictureCluster()))
                        .and(qFloorPicture.imageIndex.eq(newFloorPicture.getImageIndex())))
                .fetchOne();

        return oldFloorPicture;
    }

}
