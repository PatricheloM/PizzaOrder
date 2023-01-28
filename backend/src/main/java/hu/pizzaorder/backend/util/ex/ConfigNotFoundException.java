package hu.pizzaorder.backend.util.ex;

public class ConfigNotFoundException extends RuntimeException {

    public ConfigNotFoundException(String msg) {
        super(msg);
    }

    public ConfigNotFoundException(Exception e) {
        super(e);
    }

    public ConfigNotFoundException() {
        super();
    }
}
