package SwProject.Exception.collections.IoException;

import SwProject.Exception.message.S3ExceptionMessage;

import java.io.IOException;

public class ImageInputException extends IOException {

    public ImageInputException() {
        super(S3ExceptionMessage.ImageInputException);
    }
}
