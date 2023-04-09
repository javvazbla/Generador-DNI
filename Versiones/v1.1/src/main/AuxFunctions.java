package main;

public class AuxFunctions {
	
	final static String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	/**
	 * Función que genera un DNI válido
	 * @return String DNI válido
	 */
	public static String generateDni() {

	    int number = (int) (Math.random() * 100000000);
	    int letterIndex = number % 23;
	    char letter = letters.charAt(letterIndex);
	    return String.format("%08d%c", number, letter);  
	}
	
	/**
	 * TODO Función que valida DNI
	 * @return boolean valid true si es válido
	 */
	public static boolean ValidateDNI(String dni) {
		boolean valid = false;

	    if (dni.length() == 9) {
	        char letter = Character.toUpperCase(dni.charAt(8));
	        int numbers = Integer.parseInt(dni.substring(0, 8));
	        int remainder = numbers % 23;
	        char calculatedLetter = letters.charAt(remainder);
	        System.out.println(calculatedLetter);
	        if (letter == calculatedLetter) {
	        	valid = true;
	        }
	    }

	    return valid;
	}
	
	
}
