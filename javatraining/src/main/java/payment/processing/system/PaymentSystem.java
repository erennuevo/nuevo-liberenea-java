package payment.processing.system;

/* Abstraction is demonstrated in the PaymentSystem interface because it defines
 * what a payment system can do, but hides how it is actually done. */
public interface PaymentSystem {
	
	String checkBalance();
	void payAmount(int amount);
	
}
