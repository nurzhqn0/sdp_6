package payment.interfaces;

import payment.exceptions.InvalidPaymentInfoException;
import payment.exceptions.PaymentException;

public interface PaymentStrategy {
    void processPayment(double amount) throws PaymentException;
    String getPaymentDetails();
    void validatePaymentInfo() throws InvalidPaymentInfoException;
}
