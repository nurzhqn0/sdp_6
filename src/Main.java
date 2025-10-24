import payment.interfaces.PaymentStrategy;
import payment.models.*;
import payment.exceptions.*;
import payment.processes.PaymentProcessor;

public class Main{
    public static void main(String[] args) {
        try {
            System.out.println("\nTesting CreditCardPayment:");
            PaymentProcessor processor = new PaymentProcessor();
            PaymentStrategy creditCard = new CreditCardPayment(
                    "1234567890123456",
                    "Ali Qaz",
                    "123",
                    "12/25"
            );
            processor.setPaymentStrategy(creditCard);
            processor.setAmount(150.00);
            processor.executePayment();
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // paypal
        try {
            System.out.println("\nTesting PayPalPayment:");
            PaymentProcessor processor = new PaymentProcessor();
            PaymentStrategy paypal = new PayPalPayment(
                    "dias_saqyp@gmail.com",
                    "dias2007"
            );
            processor.setPaymentStrategy(paypal);
            processor.setAmount(89.99);
            processor.executePayment();
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // crypto
        try {
            System.out.println("\nTesting CryptoPayment:");
            PaymentProcessor processor = new PaymentProcessor();
            PaymentStrategy crypto = new CryptoPayment(
                    "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa",
                    "TON"
            );
            processor.setPaymentStrategy(crypto);
            processor.setAmount(500.00);
            processor.executePayment();
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // invalid card
        System.out.println("\nTesting CreditCardPayment:");
        try {
            PaymentProcessor processor = new PaymentProcessor();
            PaymentStrategy invalidCard = new CreditCardPayment(
                    "12345",
                    "Jane Doe",
                    "123",
                    "12/25"
            );
            processor.setPaymentStrategy(invalidCard);
            processor.setAmount(100.00);
            processor.executePayment();
        } catch (InvalidPaymentInfoException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // invalid paypal
        System.out.println("\nTesting PayPalPayment:");
        try {
            PaymentProcessor processor = new PaymentProcessor();
            PaymentStrategy invalidPayPal = new PayPalPayment(
                    "invalid-email",
                    "pass"
            );
            processor.setPaymentStrategy(invalidPayPal);
            processor.setAmount(75.50);
            processor.executePayment();
        } catch (InvalidPaymentInfoException e) {
            System.err.println("Validation Error: " + e.getMessage());
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // no payment
        System.out.println("\nTesting no payment:");
        try {
            PaymentProcessor processor = new PaymentProcessor();
            processor.setAmount(200.00);
            processor.executePayment();
        } catch (PaymentException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}