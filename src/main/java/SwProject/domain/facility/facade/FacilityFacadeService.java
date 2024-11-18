package SwProject.domain.facility.facade;

import SwProject.Exception.collections.IoException.ImageInputException;
import SwProject.domain.facility.floorPicutre.dto.FloorPictureDto;
import SwProject.domain.facility.floorSize.dto.UpdateFloorSizeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FacilityFacadeService {
    void createOrUpdateFloorSize(List<UpdateFloorSizeDto> updateFloorSizeDtoList);
    void createFloorPicture(List<FloorPictureDto> createFloorPictureDtoList, List<MultipartFile> multipartFile) throws ImageInputException;
    void updateFloorPicuture(List<FloorPictureDto> updateFloorPictureDtoList, List<MultipartFile> multipartFiles) throws ImageInputException;
}
