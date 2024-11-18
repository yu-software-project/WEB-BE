package SwProject.businessProcess.auth.web.dto;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WebSignUpDto {
    @Valid
    private centerInfo centerInfo;
    @Valid
    private ceoInfo ceoInfo;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class centerInfo {
        @NotBlank(message = "관리자의 이름은 필수값입니다.")
        private String ceoName;

        @NotBlank(message = "보육원의 이름은 필수값입니다.")
        private String centerName;

        @NotBlank(message = "전화번호는 필수 값입니다.")
        @Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message="유효한 휴대폰 번호를 입력해주세요.")
        private String phoneNum;

        @NotBlank(message = "도로명 주소는 필수값입니다.")
        private String roadAddress;

        @NotBlank(message = "도로명 상세 주소는 필수값입니다.")
        private String detailAddress;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ceoInfo {
        @NotBlank(message = "이메일은 필수 값입니다.")
        @Email(message="유효한 이메일 주소를 입력해주세요.")
        @Size(min=5, max=254, message="5이상, 64이하 이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "이메일 인증번호를 입력해주세요.")
        private String emailVerificationCode;

        @NotBlank(message = "전화번호는 필수 값입니다.")
        @Column(unique=true) // 전화번호를 고유값으로 설정
        @Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message="유효한 휴대폰 번호를 입력해주세요.")
        private String phoneNum;

        @NotBlank(message = "전화번호 인증번호를 입력해주세요.")
        private String phoneVerificationCode;

        @NotBlank(message="비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$", message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
        private String password;

    }

}
