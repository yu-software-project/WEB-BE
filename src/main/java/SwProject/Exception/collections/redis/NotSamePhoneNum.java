package SwProject.Exception.collections.redis;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.Exception.message.RedisExceptionMessage;

public class NotSamePhoneNum extends BusinessException {
    public NotSamePhoneNum() {
        super(RedisExceptionMessage.NotSamePhoneNum);
    }
}
