package SwProject.Exception.collections.redis;

import SwProject.Exception.collections.business.BusinessException;
import SwProject.Exception.message.RedisExceptionMessage;

public class RefreshTokenExpirationException extends BusinessException {
    public RefreshTokenExpirationException() {
        super(RedisExceptionMessage.RefreshTokenExpirationException);
    }
}
