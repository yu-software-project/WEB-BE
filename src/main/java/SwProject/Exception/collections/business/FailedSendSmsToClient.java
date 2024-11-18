package SwProject.Exception.collections.business;

import SwProject.Exception.message.SmsExceptionMessage;

public class FailedSendSmsToClient extends BusinessException{
    public FailedSendSmsToClient() {
        super(SmsExceptionMessage.FailedSendSmsToClient);
    }
}
