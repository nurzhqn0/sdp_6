package payment.processes;

import payment.exceptions.PaymentException;
import payment.interfaces.PaymentStrategy;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;
    private double totalAmount;

    public PaymentProcessor() {
        this.totalAmount = 0.0;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = strategy;
        System.out.println("\n--- Payment method selected: " +
                strategy.getPaymentDetails() + " ---");
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive, got: " + amount
            );
        }
        this.totalAmount = amount;
    }

    public void executePayment() throws PaymentException {
        if (paymentStrategy == null) {
            throw new PaymentException("No payment method selected");
        }

        if (totalAmount <= 0) {
            throw new PaymentException("Invalid payment amount: " + totalAmount);
        }

        System.out.println("\n=== Starting Payment Process ===");
        paymentStrategy.processPayment(totalAmount);
        System.out.println("=== Transaction Completed Successfully ===\n");
    }

    public String getCurrentPaymentMethod() {
        return paymentStrategy != null ?
                paymentStrategy.getPaymentDetails() : "None";
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
