package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class ChildCenterAlreadyExitsException extends BusinessException {
    public ChildCenterAlreadyExitsException() {
        super(CommonExceptionMessage.ChildCenterAlreadyExists);
    }
}
