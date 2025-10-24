package payment.models;

import payment.exceptions.InvalidPaymentInfoException;
import payment.exceptions.PaymentException;
import payment.exceptions.PaymentProcessingException;
import payment.interfaces.PaymentStrategy;


public class CryptoPayment implements PaymentStrategy {
    private static final int MIN_WALLET_LENGTH = 26;

    private String walletAddress;
    private String cryptoType;

    public CryptoPayment(String walletAddress, String cryptoType) {
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;
    }

    @Override
    public void isPaymentInfoValid() throws InvalidPaymentInfoException {
        if (walletAddress == null || walletAddress.length() < MIN_WALLET_LENGTH) {
            throw new InvalidPaymentInfoException(
                    "Invalid wallet address: must be at least " +
                            MIN_WALLET_LENGTH + " characters"
            );
        }

        if (cryptoType == null || cryptoType.trim().isEmpty()) {
            throw new InvalidPaymentInfoException(
                    "Cryptocurrency type is required"
            );
        }
    }

    @Override
    public void processPayment(double amount) throws PaymentException {
        isPaymentInfoValid();

        System.out.println("Processing Cryptocurrency Payment...");
        System.out.println("Crypto Type: " + cryptoType);
        System.out.println("Wallet: " + walletAddress.substring(0, 10) + "...");
        System.out.println("Amount: $" + String.format("%.2f", amount));

        try {
            Thread.sleep(1500);
            System.out.println("Payment successful via " + cryptoType + "!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaymentProcessingException(
                    "Crypto payment processing was interrupted"
            );
        }
    }

    @Override
    public String getPaymentDetails() {
        return cryptoType + " wallet: " + walletAddress.substring(0, 10) + "...";
    }
}