package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class UnAuthenticatedUserAccessException extends BusinessException{
    public UnAuthenticatedUserAccessException() {
        super(CommonExceptionMessage.UnAuthenticatedUserAccess);
    }
}
