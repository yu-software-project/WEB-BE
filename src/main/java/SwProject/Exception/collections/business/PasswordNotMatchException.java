package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;
public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(CommonExceptionMessage.ACCOUNT_PASSWORD_NOT_MATCH);
    }
}