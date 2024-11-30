package SwProject.Exception.collections.business;

import static SwProject.Exception.message.TokenExceptonMessage.NotApprovalExceptionMessage;

public class NotApprovalException extends BusinessException{

    public NotApprovalException() {
        super(NotApprovalExceptionMessage);
    }
}
