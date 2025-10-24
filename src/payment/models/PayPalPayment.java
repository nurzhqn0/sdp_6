package payment.models;

import payment.exceptions.InvalidPaymentInfoException;
import payment.exceptions.PaymentException;
import payment.exceptions.PaymentProcessingException;
import payment.interfaces.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    private static final int MIN_PASSWORD_LENGTH = 6;

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void isPaymentInfoValid() throws InvalidPaymentInfoException {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidPaymentInfoException(
                    "Invalid email format: must contain @ and domain"
            );
        }

        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidPaymentInfoException(
                    "Invalid password: must be at least " +
                            MIN_PASSWORD_LENGTH + " characters"
            );
        }
    }

    @Override
    public void processPayment(double amount) throws PaymentException {
        isPaymentInfoValid();

        System.out.println("Processing PayPal Payment...");
        System.out.println("Email: " + email);
        System.out.println("Amount: $" + String.format("%.2f", amount));

        try {
            Thread.sleep(1000);
            System.out.println("Payment successful via PayPal!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                    "PayPal payment processing was interrupted"
            );
        }
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal account: " + email;
    }
}