package SwProject.Exception.collections.InputValid;

import SwProject.Exception.message.InvalidRequestExceptionMessage;

public class InvalidClientRequest extends InvalidRequestException{

    public InvalidClientRequest() {
        super(InvalidRequestExceptionMessage.InvalidClientRequest);
    }
}
