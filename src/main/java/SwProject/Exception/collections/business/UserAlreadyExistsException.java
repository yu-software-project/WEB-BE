package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException() {
        super(CommonExceptionMessage.UserAlreadyExists);
    }
}