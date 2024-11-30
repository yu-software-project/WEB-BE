package SwProject.Exception.message;

public class TokenExceptonMessage {
    public static String TOKEN_MISSING_HEADER = "헤더에 토큰이 포함되어 있지 않습니다.";
    public static final String SignatureNotMatchException = "시크릿 키가 동일하지 않습니다.";
    public static final String JwtAccessTokenExpired = "액세스 토큰이 만료되었습니다.";
    public static final String UndefinedException = "정의되지 않은 예외 처리입니다.";
    public static final String InvalidJwtToken =  "입력하신 이메일의 계정이 존재하지 않습니다.";
    public static final String NotApprovalExceptionMessage = "미승인 계정입니다. 승인을 기다리세요.";
}