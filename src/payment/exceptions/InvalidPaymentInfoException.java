package payment.exceptions;

public class InvalidPaymentInfoException extends PaymentException {
    public InvalidPaymentInfoException(String message) {
        super(message);
    }
}
