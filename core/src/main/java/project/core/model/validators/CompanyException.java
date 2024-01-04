package project.core.model.validators;

public class CompanyException extends RuntimeException{

    public CompanyException(String message) {super(message);}

    public CompanyException(String message, Throwable cause){super(message);}

    public CompanyException(Throwable cause){super(cause);}

}
