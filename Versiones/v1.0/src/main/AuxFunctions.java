package main;

public class AuxFunctions {
	
	
	/**
	 * Función que genera un DNI válido
	 * @return String DNI válido
	 */
	public static String generateDni() {
	    String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	    int number = (int) (Math.random() * 100000000);
	    int letterIndex = number % 23;
	    char letter = letters.charAt(letterIndex);
	    return String.format("%08d%c", number, letter);  
	}
	
	/**
	 * TODO Función que valida DNI
	 * @return
	 */
	public static boolean ValidateDNI() {
		return true;
	}
	
	
}
