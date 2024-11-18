package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class DuplicateUniqueKeyException extends BusinessException{
    public DuplicateUniqueKeyException() {
        super(CommonExceptionMessage.DuplicatedUniqueKey);
    }
}
