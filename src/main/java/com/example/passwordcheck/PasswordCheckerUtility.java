package com.example.passwordcheck;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PasswordCheckerUtility Class
 * @author Ricardo Abuabara
 *
 */
public class PasswordCheckerUtility {
    /**
     *Method to check if the password string fulfills all requirements of a valid password
     * @param password checks for password validity
     * @return true for a valid password
     * @throws LengthException
     * @throws NoUpperAlphaException
     * @throws NoLowerAlphaException
     * @throws NoDigitException
     * @throws NoSpecialCharacterException
     * @throws InvalidSequenceException
     */
    public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
    boolean checkLength = false, checkUp = false, checkLow = false, checkDig = false,
            checkSpec = false, checkSeq = false, checkVal = true;
        try {
            checkLength = isValidLength(password);
            checkUp = hasUpperAlpha(password);
            checkLow = hasLowerAlpha(password);
            checkDig = hasDigit(password);
            checkSeq = isValidSequence(password);
            checkSpec = hasSpecialChar(password);
        }
        finally {
if(checkLength==true&& checkUp==true && checkLow==true && checkDig==true && checkSeq== true && checkSpec== true)
{
    checkVal = true;
}
else {
    checkVal = false;
}
        }
    return checkVal;
    }

    /**
     * checks the password string of the requirement of a valid sequence
     * @param password
     * @return true if the password is a valid sequence or false if it is not a valid sequence
     * @throws InvalidSequenceException
     */
    private static boolean isValidSequence(String password) throws InvalidSequenceException{
        Pattern r = Pattern.compile("((.)\\2{2,})");
        Matcher m = r.matcher(password);

        if (m.find()) {
            throw new InvalidSequenceException("THERE IS AN INVALID SEQUENCE FOUND");
        }
        return true;
    }

    /**
     * Method to check if the password string is a weak password(has between 6 and 9 characters)
     * @return true if password is weak, false if password is not weak
     * @throws WeakPasswordException
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException{
        if(password.length() >= 6 && password.length() <= 9)
        {
            throw new WeakPasswordException("WEAK PASSWORD HAS BEEN THROWN");
        }
        else return false;
    }

    /**
     * Method to find invalid passwords from a list of passwords
     * @param passwords arraylist of string passwords
     * @return an arraylist of invalid passwords
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalid = new ArrayList<>();
        for(int i = 0; i < passwords.size(); i++) {
            try{
                isValidPassword(passwords.get(i));
            }catch(Exception e) {
                invalid.add(passwords.get(i) + " " + e.getMessage());
            }
        }
        return invalid;
    }
    /**
     * Method used to check length of string
     * @param password string for which to check length
     * @return true if length is less than 6 characters, otherwise throws LengthException
     * @throws LengthException
     */
    public static boolean isValidLength(String password) throws LengthException{
        if(password.length() < 6)
            throw new LengthException("YOUR PASSWORD IS TOO SMALL");
        else return true;
    }

    /**
     * Method to check if two passwords are the same
     * @param password initial password to check similarity
     * @param passwordConfirm second password to check similarity
     * @return true if passwords are the same, otherwise false
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
       if(password.equals(passwordConfirm)) return true;
       else return false;
    }

    /**
     * Method to check if the password string has at least one digit
     * @param password string to check if there is at least one digit
     * @return true if there's a digit, otherwise throws NoDigitException
     * @throws NoDigitException
     */
    public static boolean hasDigit(String password) throws NoDigitException {
    char[] passwordCopy = password.toCharArray();
    int count = 0;
    for (int i = 0; i < passwordCopy.length; i++) {
        if (Character.isDigit(passwordCopy[i])) {
            count++;
        }
    }
    if (count == 0) {
        throw new NoDigitException("YOU HAVE NO DIGITS IN YOUR PASSWORD");
    } else {
        return true;
    }
}

    /**
     * Method to check if the password string has at least one upper case character
     * @param password string for which to check if it has upper case characters
     * @return true if there's at least one upper case character, otherwise throws NoUpperAlphaException
     * @throws NoUpperAlphaException
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
        if(password.equals(password.toLowerCase()))
            throw new NoUpperAlphaException("YOU HAVE NO UPPER CASE CHARACTERS");
        else
            return true;
    }
    /**
     * Method to check if string has at least one lower case character
     * @param password string for which to check if it has lower case characters
     * @return true if there's at least one lower case character, otherwise throws NoLowerAlphaException
     * @throws NoLowerAlphaException
     */
    private static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {

        if(password.equals(password.toUpperCase()))
            throw new NoLowerAlphaException("YOU HAVE NO LOWER CASE CHARACTERS");
        else
            return true;

    }

    /**
     * Method to check if two passwords are the same
     * @param password initial password to check similarity
     * @param passwordConfirm second password to check similarity
     * @throws UnmatchedException
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{

            if(comparePasswordsWithReturn(password,passwordConfirm) == false)
            throw new UnmatchedException("THE PASSWORDS ARE NOT THE SAME");
    }



    /**
     * Method to check if string has at least one special character
     * @param password string to check if it contains one special character
     * @return true if there is a special character, otherwise throws NoSpecialCharacterException
     * @throws NoSpecialCharacterException
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
    {
        String regex ="[a-zA-Z0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher mat = pattern.matcher(password);
        if(mat.matches()) {
            throw new NoSpecialCharacterException("YOU HAVE NO SPECIAL CHARACTERS");
        }
        else {
            return true;
        }
    }

}
