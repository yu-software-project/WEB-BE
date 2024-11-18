package SwProject.Exception.collections.InputValid;

import static SwProject.Exception.message.CommonExceptionMessage.BindingErrorMessage;

public class BindingErrors extends InvalidRequestException{
    public BindingErrors() {
        super(BindingErrorMessage);
    }
}
