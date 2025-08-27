package pe.joedayz.ecommerce.exceptions;

public class AuthenticationFailException extends Exception{
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
