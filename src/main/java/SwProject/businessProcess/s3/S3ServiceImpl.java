package SwProject.businessProcess.s3;

import SwProject.Exception.collections.IoException.ImageInputException;
import SwProject.businessProcess.util.UtilService;
import SwProject.config.s3.S3Config;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service{
    private final UtilService utilService;
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;

    @Override
    public String uploadImageToS3(MultipartFile image) throws ImageInputException {
        String bucketName = s3Config.getBucketName();
        String originName = image.getOriginalFilename(); //원본 이미지 이름
        String ext = originName.substring(originName.lastIndexOf(".")); //확장자
        String changedName = utilService.getRandomUUID(originName); //새로 생성된 이미지 이름
        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터

        metadata.setContentType("image/"+ext);
        try {
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                    bucketName, changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            throw new ImageInputException();
        }

        String s3url = amazonS3.getUrl(bucketName, changedName).toString();

        return s3url; //데이터베이스에 저장할 이미지가 저장된 주소
    }

}
