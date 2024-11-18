package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class CenterNotFoundException extends BusinessException {
    public CenterNotFoundException() {
        super(CommonExceptionMessage.CenterByManagerNotFound );
    }
}
