package SwProject.businessProcess.util;

import java.io.IOException;

public interface UtilService {
    String getRandomNum();
    String getRandomUUID(String originalName);
    String getInitImagePath() throws IOException;
    boolean checkPassword(String password, String checkPassword);
}
