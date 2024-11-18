package SwProject.domain.facility.floorPicutre.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FloorPictureDto {
    @NotNull
    private int floor;

    @NotBlank
    private String purpose;

    @NotNull
    private int imageIndex; //몇 번째 인덱스에 위치하는지 표시해줄 dto (0-3) -> update 로직에서 식별자로 쓰일 예정이다.

    //위 3개는 db에 들어갈 값, imageName은 매핑을 위한 값
    @NotBlank
    private String imageName; //이미지 이름을 받은 후, 추후에 multipartFile 리스트에서 해당하는 key값과 매핑해서 찾아줄 것

}
