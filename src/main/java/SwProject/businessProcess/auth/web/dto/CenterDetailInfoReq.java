package SwProject.businessProcess.auth.web.dto;

public record CenterDetailInfoReq(
        String ceoName,
        String centerName,
        String phoneNum,
        String address,
        String certificate,
        String adminEmail,
        String adminPhoneNum
)

{}
