package com.ewallet.validation;

// Contains reusable input-validation rules.
public final class Validator {

    // This is a utility class, so objects of it are not needed.
    private Validator() {
    }

    // Username must start with uppercase and contain 5-20 characters.
    public static boolean isValidUsername(String username) {
        return username != null
                && username.matches("^[A-Z][A-Za-z0-9]{4,19}$");
    }

    // Password needs uppercase, lowercase, a number, and 8+ characters.
    public static boolean isValidPassword(String password) {
        return password != null
                && password.matches(
                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }
	
    // Returns null when all signup fields are valid; otherwise returns an error.
	public static String validateSignup(String username, String password, int age, String phoneNumber) {
		
		if (!isValidUsername(username)) {
            return "Username must start with an uppercase letter and contain 5-20 letters or numbers.";
        }
		
		if (!isValidPassword(password)) {
            return "Password must be at least 8 characters with uppercase, lowercase, and a number.";
        }
		
		if (age < 18) {
            return "You must be at least 18 years old.";
        }

        if (phoneNumber == null || !phoneNumber.matches("^01[0125][0-9]{8}$")) {
            return "Enter a valid Egyptian phone number, such as 01012345678.";
        }
		
		
		return null;
		
	}
	
}
