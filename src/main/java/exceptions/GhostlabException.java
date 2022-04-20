package main.java.exceptions;

public class GhostlabException extends Exception {
    
    public GhostlabException() {
	}
	
	public GhostlabException(String str) {
		super(str);
		
	}
	
	public GhostlabException(String str, Throwable cause) {
		super(str, cause);
		
	}
	
	public GhostlabException(Throwable cause) {
		super(cause);
		
	}

}
