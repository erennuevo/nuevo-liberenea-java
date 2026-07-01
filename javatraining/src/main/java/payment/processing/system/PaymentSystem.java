package payment.processing.system;

public interface PaymentSystem {
	
	String checkBalance();
	void payAmount(int amount);
	
}
