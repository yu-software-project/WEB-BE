package SwProject.Exception.collections.redis;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.Exception.message.RedisExceptionMessage;

public class NotMatchVerificatonCodeByEmail extends BusinessException {
    public NotMatchVerificatonCodeByEmail() {
        super(RedisExceptionMessage.NotMatchVerificatonCodeByEmail);
    }
}
