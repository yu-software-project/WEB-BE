package SwProject.Exception.collections.business;

import SwProject.Exception.message.TokenExceptonMessage;

public class TokenMissingException extends BusinessException {
    public TokenMissingException() {
        super(TokenExceptonMessage.TOKEN_MISSING_HEADER);
    }

}
