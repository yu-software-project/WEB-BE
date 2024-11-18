package SwProject.businessProcess.s3;

import SwProject.Exception.collections.IoException.ImageInputException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadImageToS3(MultipartFile image) throws ImageInputException;
}
