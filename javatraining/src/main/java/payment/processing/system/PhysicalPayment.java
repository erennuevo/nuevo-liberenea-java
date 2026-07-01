package payment.processing.system;

/* Inheritance and encapsulation are demonstrated through the class hierarchy. 
 * The balance state is defined in the abstract base classes and protected from 
 * external modification, while remaining accessible to authorized child classes. 
 * Sealed classes further enhance the design by enforcing strict type safety, 
 * explicitly categorizing and limiting which implementations can be considered 
 * physical or online payments. */
public abstract sealed class PhysicalPayment
	permits CreditCard {
	
	protected int balance;

    protected PhysicalPayment(int balance) {
        this.balance = balance;
    }

    public String checkBalance() {
        return "Physical Payment balance: " + balance;
    }
}
