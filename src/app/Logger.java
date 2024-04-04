package app;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
  private static Logger logger;
  private PrintWriter writer;

  private Logger() {
    try {
      writer = new PrintWriter(new FileWriter("logs.txt", true));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static synchronized Logger getLogger() {
    if (logger == null) {
      logger = new Logger();
    }
    return logger;
  }

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

  public synchronized void close() {
    if (writer != null) {
      writer.close();
    }
  }
}
