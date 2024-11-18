package SwProject.businessProcess.auth.app.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppSignUpDto {

    @NotBlank(message = "사용자의 이름은 필수값입니다.")
    private String name;

    @NotNull(message = "사용자의 성별은 필수값입니다.")
    private boolean gender; //0 : 남자, 1 : 여자

    @Column(nullable = false)
    private String birth;

    @NotBlank(message = "전화번호는 필수 값입니다.")
    @Column(unique=true) // 전화번호를 고유값으로 설정
    @Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message="유효한 휴대폰 번호를 입력해주세요.")
    private String phoneNum;

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message="유효한 이메일 주소를 입력해주세요.")
    @Size(min=5, max=254, message="5이상, 64이하 이메일을 입력해주세요.")
    private String emailId;

    @NotBlank(message="비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$", message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;

    @NotBlank(message="비밀번호 확인란을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$", message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String passwordCheck;
}
