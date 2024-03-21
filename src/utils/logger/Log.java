package utils.logger;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    static final String CONFIG_PATH = "log.config";
    static {
        try(FileInputStream in = new FileInputStream(CONFIG_PATH)) {
            LogManager.getLogManager().readConfiguration(in);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger logger(String className) {
        return Logger.getLogger(className);
    }
}
