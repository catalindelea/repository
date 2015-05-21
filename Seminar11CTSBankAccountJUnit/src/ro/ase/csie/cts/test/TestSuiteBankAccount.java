package ro.ase.csie.cts.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteBankAccount extends TestCase {
	public static Test suite(){
		TestSuite colectie = new TestSuite();
		//adaugare in totalitate a TestCase-ului Automate;
		colectie.addTestSuite(TestBankAccountAutomate.class);
		//adaugare metode selectate din TestBankAccount
		colectie.addTest(new TestBankAccount("testFloatingValueDeposit"));
		colectie.addTest(new TestBankAccount("testLimitValueDeposit"));
		colectie.addTest(new TestBankAccount("testNegativeValuesDeposit"));
		colectie.addTest(new TestBankAccount("testZeroValueDeposit"));
		return colectie;
	}
}
