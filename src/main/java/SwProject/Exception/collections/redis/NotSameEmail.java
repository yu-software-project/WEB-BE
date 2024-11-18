package SwProject.Exception.collections.redis;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.Exception.message.RedisExceptionMessage;

public class NotSameEmail extends BusinessException {
    public NotSameEmail() {
        super(RedisExceptionMessage.NotSameEmail);
    }
}