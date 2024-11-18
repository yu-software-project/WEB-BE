package SwProject.Exception.message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommonExceptionMessage {
    public static final String UserAlreadyExists = "이미 존재하는 관리자의 이메일 입니다.";
    public static final String ChildCenterAlreadyExists = "이미 존재하는 보육원 입니다.";
    public static final String UnAuthenticatedUserAccess = "인증되지 않은 사용자입니다.";
    public static final String UsernameNotFoundException = "입력하신 이메일의 계정이 존재하지 않습니다.";
    public static final String ACCOUNT_PASSWORD_NOT_MATCH = "입력하신 Password가 일치하지 않습니다.";
    public static final String CenterByManagerNotFound = "해당 보육원을 찾을 수 없습니다.";
    public static final String DuplicatedUniqueKey = "이미 등록된 정보입니다. 다른 정보를 입력해주세요.";
    public static final String UnVerifiedUserInfo = "인증을 먼저 진행해주세요.";
    public static final String BindingErrorMessage = "필드값이 유효하지 않습니다. 올바르 필드값을 입력해주세요.";
    public static final String DuplicateRecruitmentPerVolunteer = "해당 날짜에 이미 신청된 봉사공고가 존재합니다.";
}
