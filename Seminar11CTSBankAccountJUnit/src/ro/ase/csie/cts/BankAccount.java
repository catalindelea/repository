package ro.ase.csie.cts;

public class BankAccount {
	double balance;
	double limit;
	double lowestBalance;
	
	public BankAccount(double b, double l, double lb){
		balance=b;
		limit=l;
		lowestBalance=lb;
	}
	
	//Retrage bani
	public void withdraw(double amount){
			balance=balance-amount;
	}
	
	//Depozit
	public void deposit(double amount) 
			throws NegativeAmountException, ZeroAmountException, FloatingValueException, DepositLimitException{
		if(amount < 0.0)
			throw new NegativeAmountException();
		if(amount == 0.0)
			throw new ZeroAmountException();
		if(amount >= this.limit)
			throw new DepositLimitException();
		
		
/*		String valoare = String.format("%.2f", amount);
		System.out.println(" Valoarea este"+valoare);
		
		if(valoare.indexOf('.')<=valoare.length()-3)
			throw new FloatingValueException();*/
		
		int valoareInt = (int)(amount*100.0);
		if(amount-((double)valoareInt/100.0)>0)
			throw new FloatingValueException();
		
		balance=balance+amount;
	}
	
	//Get the value of balance
	public double getBalance(){
		return balance;
	}
}