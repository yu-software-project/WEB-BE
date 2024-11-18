package SwProject.Exception.collections.IoException;

import SwProject.Exception.message.DbExceptionMessage;

import java.io.IOException;


public class allImageFileIOException extends IOException {

    public allImageFileIOException() {
        super(DbExceptionMessage.AllImageFileIOError);
    }
}
