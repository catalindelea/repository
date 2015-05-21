package ro.ase.csie.cts.test;

import ro.ase.csie.cts.AccountException;
import ro.ase.csie.cts.BankAccount;
import ro.ase.csie.cts.DepositLimitException;
import ro.ase.csie.cts.FloatingValueException;
import ro.ase.csie.cts.NegativeAmountException;
import ro.ase.csie.cts.ZeroAmountException;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


//metoda care primeste o referinta la un obiect (poate fi si wrapper de primitive)
//testam null, daca referinta e String testam sir vid, daca referinta e Wrapper valoarea 0
//daca metoda primeste o referinta catre o colectie: 0, 1, N: numar de valori din colectie;
//generic: verificare format, limite vlaori ok
//prezentare proiecte 28 sau 29 mai
public class TestBankAccount extends TestCase {
	
	//definire preconditii - Fixture
	BankAccount account;
	
	public TestBankAccount(String numeMetoda) {
		super(numeMetoda);
	}

	public void setUp(){
		System.out.println("setUp Unit test");
		account = new BankAccount(100.0, 5000.0, 10.0);
	}
	
	//unit test pentru verificat metoda deposit()
	//testare valori normale (intregi > 0)
	public void testNormalValuesDeposit() 
			throws NegativeAmountException, 
			ZeroAmountException, 
			FloatingValueException, 
			DepositLimitException{
		//definire valori
		double expected = 300;
		account.deposit(200.0);
		assertEquals("Test deposit() cu valori normale",
				300.0,
				account.getBalance(),
				0.0);
	}
	
	//unit test pentru verificat metoda deposit()
	//testare valori negative (< 0)
	public void testNegativeValuesDeposit(){
		try{
		account.deposit(-200.0);
		assertFalse("Metoda nu arunca exceptie pe valori negative", true);
		}
		catch(AccountException ex){
			assertTrue(true);
		}
	}
	
	//unit test pentru verificat metoda deposit()
	//testare valori nule (= 0)
	public void testZeroValueDeposit(){
		try{
		account.deposit(0.0);
		assertFalse("Metoda deposit() nu arunca exceptie pe 0", true);
		}
		catch(AccountException ex){
			assertTrue(true);
		}
	}
	
	//unit test pentru verificat metoda deposit()
	//testare valori pozitive cu maxim 2 zecimale (> 0)
	public void testFloatingValueDeposit(){
		try{
		account.deposit(0.0006);
		assertFalse("Metoda deposit() nu arunca exceptie pe 0", true);
		}
		catch(AccountException ex){
			assertTrue(true);
		}
	}
	
	//unit test pentru verificat metoda deposit()
	//testare valori pozitive care depasesc limita
	public void testLimitValueDeposit(){
		try{
			double valoare = 6000.0;
			account.deposit(valoare);
			fail("Test deposit() cu valoare peste limita");
		}
		catch(AccountException ex){
			
		}
	}
	
	//definire setup global
	//definire bloc static de initializare
	static {
		System.out.println("Executat inainte de testBankAccount");
	}
	public static void setUpGlobal(){
		System.out.println("Executat inainte de testBankAccount");
	}
	
	public static void teardownGlobal(){
		System.out.println("Executat dupa testBankAccount");
	}
	
	public static Test suite(){
		TestSuite testSuite = new TestSuite();
		testSuite.addTestSuite(TestBankAccount.class);
		TestSetup setup = new TestSetup(testSuite){
			@Override
			protected void setUp() throws Exception {
				setUpGlobal();
			}
			
			@Override
			protected void tearDown() throws Exception {
				teardownGlobal();
			}
		};
		
		return setup;
	}
}
