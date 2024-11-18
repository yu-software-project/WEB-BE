package SwProject.Exception.collections.business;

import SwProject.Exception.message.CommonExceptionMessage;

public class UsernameNotFoundException extends BusinessException {
    public UsernameNotFoundException() {
        super(CommonExceptionMessage.UsernameNotFoundException);
    }
}
