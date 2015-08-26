package ec.master.assignment1.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Logger
 * @Description: TODO
 * @date 14/08/2015 11:03:35 pm
 * 
 */
public class Logger {
	private static Logger logger = null;
	private static int status;
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	

	private Logger() {
		status = INFO;
	}

	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	public void setStatus(int s) {
		switch (s) {
		case DEBUG:
			status = s;
			break;
		case INFO:
			status = s;
			break;
		default:
			status = INFO;
			break;
		}
	}
	
	public void setStatus(String s) {
		status = INFO;
		if ("debug".equalsIgnoreCase(s)) {
			status = DEBUG;
		} else if ("error".equalsIgnoreCase(s)) {
			status = ERROR;
		} else {
			status = INFO;
		}
	}

	private static int getStatus() {
		return status;
	}

	public void debug(String msg) {
		if (isLogging(DEBUG))
			printer(msg, "DEBUG");
	}
	
	public void debug(int msg) {
		debug(String.valueOf(msg));
	}
	
	public void debug(double msg) {
		debug(String.valueOf(msg));
	}
	
	public void info(String msg) {
		if (isLogging(INFO))
			printer(msg, "INFO");
	}
	
	public void info(int msg) {
		info(String.valueOf(msg));
	}
	
	public void info(double msg) {
		info(String.valueOf(msg));
	}

	public void error(String msg) {
		if (isLogging(ERROR)) {
			printer(msg, "ERROR");
		}
	}
	
	public void errorException(String msg) {
		if (isLogging(ERROR)) {
			String className = new Exception().getStackTrace()[1].getClassName();
			String fileName = new Exception().getStackTrace()[1].getFileName();
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			int lineNumber = new Exception().getStackTrace()[1].getLineNumber();
			printer(msg, "ERROR");
			printer("at " + className + "." + methodName + "(" +fileName + ": " + lineNumber + ")", "ERROR");
		}
	}
	
	public void error(int msg) {
		error(String.valueOf(msg));
	}
	
	public void error(double msg) {
		error(String.valueOf(msg));
	}

	private boolean isLogging(int status) {
		if (getStatus() <= status) {
			return true;
		}
		return false;
	}
	
	public String getTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return format.format(date);
	}
	
	private void printer(String msg, String level) {
		System.out.println(getTime() + "    " + level + "        " + msg);
	}
}
