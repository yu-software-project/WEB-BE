package SwProject.Exception.collections.business;

import SwProject.Exception.message.DbExceptionMessage;

public class WaitingUserNotFoundException extends BusinessException{
    public WaitingUserNotFoundException() {
        super(DbExceptionMessage.WaitingUserNotFoundException);
    }
}
