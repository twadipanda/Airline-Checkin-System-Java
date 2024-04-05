package constants_and_singleton_classes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Logger class provides functionality for logging messages with different severity levels to a file.
 */
public final class Logger {
  private static Logger logger;
  private PrintWriter writer;

  /**
   * Private constructor to prevent instantiation from outside the class.
   */
  private Logger() {
    try {
      writer = new PrintWriter(new FileWriter("logs.txt", true));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the instance of the Logger class, creating it if it doesn't exist.
   *
   * @return The Logger instance.
   */
  public static synchronized Logger getLogger() {
    if (logger == null) {
      logger = new Logger();
    }
    return logger;
  }

  /**
   * Logs a message with the specified severity level.
   *
   * @param message The message to be logged.
   * @param level   The severity level of the message.
   */
  public synchronized void log(String message, Enums.Severity level) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String timestamp = dateFormat.format(new Date());
    writer.println(timestamp + " - " + "[" + level + "] - " + "[FILE: " + StackWalker.getInstance()
    .walk(s -> s.skip(1).findFirst())
    .get()
    .getFileName() + "] - " + "[CLASS: " + StackWalker.getInstance()
    .walk(s -> s.skip(1).findFirst())
    .get()
    .getClassName() + "] - " + "[METHOD: " + StackWalker.getInstance()
    .walk(s -> s.skip(1).findFirst())
    .get()
    .getMethodName() + "] - " + "[LINE: " + StackWalker.getInstance()
    .walk(s -> s.skip(1).findFirst())
    .get()
    .getLineNumber() + "] - " + "[LOG: " + message + "]");
    writer.flush();
  }

  /**
   * Closes the PrintWriter used for logging.
   */
  public synchronized void close() {
    if (writer != null) {
      writer.close();
    }
  }
}
