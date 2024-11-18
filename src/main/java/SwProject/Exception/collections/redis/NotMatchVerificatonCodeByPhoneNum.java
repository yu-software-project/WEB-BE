package SwProject.Exception.collections.redis;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.Exception.message.RedisExceptionMessage;

public class NotMatchVerificatonCodeByPhoneNum extends BusinessException {
    public NotMatchVerificatonCodeByPhoneNum() {
        super(RedisExceptionMessage.NotMatchVerificatonCodeByPhoneNum);
    }
}
