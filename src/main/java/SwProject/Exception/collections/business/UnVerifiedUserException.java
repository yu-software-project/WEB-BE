package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class UnVerifiedUserException extends BusinessException{
    public UnVerifiedUserException() {
        super(CommonExceptionMessage.UnVerifiedUserInfo);
    }
}
