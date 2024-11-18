package SwProject.Exception.message;

public class RedisExceptionMessage {
    public static String NotSameEmail = "인증된 이메일과 다른 이메일 입니다.";
    public static String NotSamePhoneNum = "인증된 전화번호와 다른 전화번호 입니다.";
    public static String RefreshTokenExpirationException = "만료된 리프래시 토큰입니다.";
    public static String NotMatchVerificatonCodeByEmail = "이메일의 인증번호가 일치하지 않습니다.";
    public static String NotMatchVerificatonCodeByPhoneNum = "전화번호의 인증번호가 일치하지 않습니다.";
}
