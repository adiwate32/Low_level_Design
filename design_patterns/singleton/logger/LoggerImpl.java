package design_patterns.singleton.logger;

import java.io.File;
import java.io.PrintWriter;


public class LoggerImpl implements Logger {

    private static Logger logger = null;
    private PrintWriter pw;
    private String filePath = null;
    private boolean close = true;

    private LoggerImpl() {}

    public static Logger getInstance ()
    {
        if(logger == null)
        {
            synchronized (LoggerImpl.class)
            {
                if(logger == null) return new LoggerImpl();
            }
        }
        return logger;
    }

    public static void removeInstance ()
    {
        logger = null;
    }

    @Override
    public void log(LogLevel level, String message) {
        if(close || pw == null)
        {
            throw new IllegalStateException();
        }

        try
        {
            pw.append(level.toString());
            pw.append(message);
            pw.println();
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setLogFile(String filePath){
        if(filePath != null)
        {
            this.filePath = filePath;
            try {
                pw = new PrintWriter(new File(filePath));
            }
            catch (Exception ex) {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public String getLogFile() {
        return this.filePath;
    }

    @Override
    public void flush() {
        if(pw != null)
        {
            pw.flush();
        }
    }

    @Override
    public void close() {
        if(pw != null)
        {
            close = true;
            pw.close();
        }
    }
}
