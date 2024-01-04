package project.core.model.validators;

public class ValidatorException extends CompanyException{
    public ValidatorException(String message){super(message);}

    public ValidatorException(String message, Throwable cause){super(message);}

    public ValidatorException(Throwable cause) {super(cause);}

}
