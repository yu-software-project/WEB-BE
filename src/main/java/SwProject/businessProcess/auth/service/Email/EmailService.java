package SwProject.businessProcess.auth.service.Email;

import SwProject.businessProcess.auth.smtp.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendVerifyNumberByEmail(EmailDto emailDto) throws MessagingException;
    void checkVerifyNumberByEmail(String Email, String verifyNum);
    void sendCenterRegisterNotApprovalReason(String adminEmail, String reason)  throws MessagingException;
}
