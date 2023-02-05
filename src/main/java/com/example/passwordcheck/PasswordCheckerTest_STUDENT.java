package com.example.passwordcheck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Ricardo Abuabara
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	String password1, password2;

	@Before
	public void setUp() throws Exception {
		//creates passwords


		String[] p = {"ol3$E", "1r*$R", "3edf", "t$e", "j#ec",
				"lhweqwieqdsamw2#", "whewqeersomething4&", "nothinkdrenf1$",
				"FINDTHIS33!", "HOLYMOTHER!@2",
				"DarkSouls2", "EaAsaw21", "PassthisNot",
				"tHis3@", "A!bert2", "Ye!!0w",
				"Str0ngP@ssw0rd",
				"CMCS204TooHard", "Sooo,,,Trues22",
				"HopeThisFailsThisTesttt43@",
				"!thisIsntMyFinal*", "WowSirNicePassword@!" };

		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p)); // puts strings into the ArrayList

	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}
	String[] shortPasswords = {"ol3$E", "1r*$R", "3edf", "t$e", "j#ec"};
	String[] noUpperCasePasswords = {"lhweqwieqdsamw2#", "whewqeersomething4&", "nothinkdrenf1$"};
	String[] noLowerCasePasswords = {"FINDTHIS33!", "HOLYMOTHER!@2"};
	String[] noSpecialCharacterPasswords = { "DarkSouls2", "EaAsaw21", "PassthisNot"};

	String[] validButWeakPasswords = {"tHis3@", "A!bert2", "Ye!!0w"};
	String[] validAndNotWeakPasswords = {"Str0ngP@ssw0rd"};

	String[] invalidSequencePasswords = { "CMCS204ToooHard", "Sooo,,,Trues22", "HopeThisFailsThisTesttt43@"};
	String[] noDigitPasswords = { "!thisIsntMyFinal*", "WowSirNicePassword@!"};

	String[] validPasswords = { "Sh0rt!", "K4tchup%", "{V4lid}", "1@3%$2dGE5j24&$'"};



	/**
	 * Test if the password is less than 8 characters long and throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			for(String password : shortPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch(LengthException le) {
			assertTrue("LengthException was thrown", true);
		} catch(Exception e) {
			assertTrue("An Exception that isn't a LengthException was thrown: " + e, false);
		}
	}

	/**
	 * Test if the password contains at least one symbol and throw NoSpecialCharacterException
	 */
	@Test
	public void testIsValidPasswordNoSymbol()
	{
		try {
			for (String password : noSpecialCharacterPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoSpecialCharacterException nspe) {
			assertTrue("NoSpecialCharacterException was thrown!", true);
		} catch (Exception e) {
			assertTrue("Some other exception was thrown: " + e, false);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character and throw throw a NoUpperAlphaException
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			for(String password : noUpperCasePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoUpperAlphaException nuae) {
			assertTrue("NoUpperAlphaException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoUpperAlphaException was thrown: " + e, false);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character and throw a NoLowerAlphaException
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			for(String password : noLowerCasePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoLowerAlphaException e) {
			assertTrue("NoUpperAlphaException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoLowerAlphaException was thrown: " + e, false);
		}
	}
	/**
	 * test a bunch of weak and nonweak passwords
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("Ima#34"));
			PasswordCheckerUtility.isWeakPassword("");
		}
		catch(WeakPasswordException w) {
			assertTrue("Threw weakPassword exception",true);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence and throw a InvalidSequenceException
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			for(String password : invalidSequencePasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (InvalidSequenceException ise) {
			assertTrue("InvalidSequenceException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a InvalidSequenceException was thrown: " + e, false);
		}
	}

	/**
	 * Test if the password has at least one digit and throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			for(String password : noDigitPasswords)
				PasswordCheckerUtility.isValidPassword(password);
		} catch (NoDigitException nde) {
			assertTrue("NoDigitException was thrown", true);
		} catch (Exception e) {
			assertTrue("An Exception that isn't a NoDigitException was thrown: " + e, false);
		}
	}


	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@SuppressWarnings("resource")
	public void testGetInvalidPasswords() {

		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);

		Scanner scan = new Scanner(results.get(0));
		assertEquals(scan.next(), "ol3$E");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));

		scan = new Scanner(results.get(1));
		assertEquals(scan.next(), "Sooo,,,Trues22");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence"));


		scan = new Scanner(results.get(2));
		assertEquals(scan.next(), "3edf");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));

		scan = new Scanner(results.get(3));
		assertEquals(scan.next(), "nothinkdrenf1$");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));

		scan = new Scanner(results.get(4));
		assertEquals(scan.next(), "whewqeersomething4&");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase") );


		scan = new Scanner(results.get(5));
		assertEquals(scan.next(), "t$e");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long") );

		scan = new Scanner(results.get(6));
		assertEquals(scan.next(), "WowSirNicePassword@!");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );

		scan = new Scanner(results.get(7));
		assertEquals(scan.next(), "DarkSouls2");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );


		scan = new Scanner(results.get(8));
		assertEquals(scan.next(), "!thisIsntMyFinal*");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );

		scan = new Scanner(results.get(9));
		assertEquals(scan.next(), "CMCS204ToooHard");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );

	}
}
