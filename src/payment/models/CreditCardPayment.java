package payment.models;

import payment.exceptions.InvalidPaymentInfoException;
import payment.exceptions.PaymentException;
import payment.exceptions.PaymentProcessingException;
import payment.interfaces.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CVV_LENGTH = 3;

    private String cardNumber;
    private String cardHolder;
    private String cvv;
    private String expiryDate;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CreditCardPayment(String cardNumber, String cardHolder,
                             String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void validatePaymentInfo() throws InvalidPaymentInfoException {
        if (cardNumber == null || cardNumber.length() != CARD_NUMBER_LENGTH) {
            throw new InvalidPaymentInfoException(
                    "Invalid card number: must be " + CARD_NUMBER_LENGTH + " digits"
            );
        }

        if (cvv == null || cvv.length() != CVV_LENGTH) {
            throw new InvalidPaymentInfoException(
                    "Invalid CVV: must be " + CVV_LENGTH + " digits"
            );
        }

        if (cardHolder == null || cardHolder.trim().isEmpty()) {
            throw new InvalidPaymentInfoException(
                    "Card holder name is required"
            );
        }

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new InvalidPaymentInfoException(
                    "Expiry date is required"
            );
        }
    }

    @Override
    public void processPayment(double amount) throws PaymentException {
        validatePaymentInfo();

        System.out.println("Processing Credit Card Payment...");
        System.out.println("Card Holder: " + cardHolder);
        System.out.println("Amount: $" + String.format("%.2f", amount));

        try {
            Thread.sleep(1000);
            System.out.println("Payment successful via Credit Card!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                    "Payment processing was interrupted"
            );
        }
    }

    @Override
    public String getPaymentDetails() {
        return "Credit Card ending in " + cardNumber.substring(12);
    }
}
