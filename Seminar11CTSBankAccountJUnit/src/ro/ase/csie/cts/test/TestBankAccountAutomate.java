package ro.ase.csie.cts.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ro.ase.csie.cts.BankAccount;
import ro.ase.csie.cts.DepositLimitException;
import ro.ase.csie.cts.FloatingValueException;
import ro.ase.csie.cts.NegativeAmountException;
import ro.ase.csie.cts.ZeroAmountException;
import junit.framework.TestCase;

public class TestBankAccountAutomate extends TestCase {

	// definire preconditii - Fixture
	BankAccount account;
	final String fisierValoriOk = "BankAccountValues.txt";

	public void setUp() throws Exception {
		System.out.println("setUp Unit test");

		File file = new File(fisierValoriOk);
		if (!file.exists())
			throw new FileNotFoundException("Lipsa fisier " + this.fisierValoriOk);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String linie = null;
		while ((linie = reader.readLine()) != null) {

			// elimin comentariile
			if (linie.startsWith("#"))
				continue;

			// elimin valorile initiale
			if (linie.startsWith("initial")) {
				String[] valori = linie.split("\t");
				double soldInitial = Double.parseDouble(valori[1]);
				double limita = Double.parseDouble(valori[2]);
				double limitaSold = Double.parseDouble(valori[3]);
				account = new BankAccount(soldInitial, limita, limitaSold);
				break;
			}
		}
		reader.close();

	}

	// unit test pentru preluare valori ok din fisier
	public void testNormalValues() throws IOException, NegativeAmountException, ZeroAmountException, FloatingValueException, DepositLimitException {
		File file = new File(fisierValoriOk);
		if (!file.exists())
			throw new FileNotFoundException("Lipsa fisier " + this.fisierValoriOk);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String linie = null;
		while ((linie = reader.readLine()) != null) {

			// elimin comentariile
			if (linie.startsWith("#"))
				continue;

			// elimin valorile initiale
			if (linie.startsWith("initial"))
				continue;

			// elimin spatiile si liniile goale
			linie = linie.trim();
			if (linie.isEmpty())
				continue;

			// procesare si testare valori
			BankAccount ba = new BankAccount(account.getBalance(), 5000.0, 10.0);
			String[] valori = linie.split("\t");
			if (valori.length == 1)
				continue;
			double deposit = Double.parseDouble(valori[0]);
			double expected = Double.parseDouble(valori[1]);

			ba.deposit(deposit);

			assertEquals("Testare deposit() cu valori ok", expected, ba.getBalance());

			System.out.println("Testare: " + linie);

		}

		reader.close();
	}

}
