package main;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ButtonActions {
	
	/**
	 * El único campo que tiene la clase es el objeto GeneradorDNI
	 */
	private GeneradorDNI generadorDNI;
	
	/**
	 * Constructor: Recime un objeto GeneradorDNI con todos los botones disponibles.
	 * @param generadorDNI
	 */	
	public ButtonActions(GeneradorDNI generadorDNI) {
	    this.generadorDNI = generadorDNI;
	}
	
	/**
	 * Método que gestiona la acción del botón de "Copiar". Copia el DNI seleccionado al portapapeles.
	 * @param event Evento de acción del botón.
	 */
	public void copyButton(ActionEvent event) {
		int index = generadorDNI.resultList.getSelectedIndex();
		//Comprueba que el index del DNI selecionado esté dentro de la tabla (mayor que -1)
	    if (index == -1) {
	      JOptionPane.showMessageDialog(generadorDNI, "Selecciona un DNI de la lista", "Error", JOptionPane.ERROR_MESSAGE);
	      return;
	    }
	    
	    String dni = generadorDNI.model.get(index);
	    //Comprueba que el DNI seleccionado no sea nulo
	    if (dni == null) {
            JOptionPane.showMessageDialog(generadorDNI, "Selecciona un DNI de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
	    
	    //Se copia al portapapeles el DNI seleccionado
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(new StringSelection(dni), null);
	}

	/**
	 * Método que gestiona la acción del botón "Generar DNIs". Genera los DNI indicados por el usuario en la lista.
	 * @param event Evento de acción del botón.
	 */
	public void generateMultipleButton(ActionEvent event) {
		
		// Comprueba si hay algún valor insertado por el usuario en el número de DNI
		String numberString = generadorDNI.numberField.getText();
        if (numberString.isEmpty()) {
            JOptionPane.showMessageDialog(generadorDNI, "Debes introducir un número de DNI a generar","Error - Campo de número de DNI vacío", JOptionPane.ERROR_MESSAGE);
            return;
         }
        // Comprueba si lo introducido por el usuario es un número
        int number;
        try {
          number = Integer.parseInt(numberString);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(generadorDNI, "El número de DNI a generar no es válido", "Error - Número no válido", JOptionPane.ERROR_MESSAGE);
          return;
        }
        // Comprueba si el número introducido por el usuario es mayor a 0
        if (number<=0) {
      	  JOptionPane.showMessageDialog(generadorDNI, "La cantidad de DNI a generar ha de ser mayor a 0", "Error - Número no válido", JOptionPane.ERROR_MESSAGE);
	          return;
        }
        
        // Crea una lista vacía que se rellenará con el número de DNI indicados.
        List<String> dnis = new ArrayList<>();
        for (int i = 0; i < number; i++) {
          dnis.add(AuxFunctions.generateDni());
        }
        
        // Limpia la lista de los DNI y la rellena de nuevo con los DNIs nuevos generados.
        generadorDNI.model.clear();
        for (String dni : dnis) {
        	generadorDNI.model.addElement(dni);
        }
	}

	/** 
	 * Método que gestiona la acción del botón "Exportar a fichero .txt". Exporta los DNI generados a un fichero .txt
	 * @param event Evento de acción del botón.
	 */
	public void exportButton(ActionEvent event) { 
		// Crea una lista con los DNI que hay en el modelo y los exportra a un fichero .txt
		List<String> dnis = new ArrayList<>(generadorDNI.model.size());
        for (int i = 0; i < generadorDNI.model.size(); i++) {
          dnis.add(generadorDNI.model.get(i));
        }
        exportToTxt(dnis);	
	}
	
	/** 
	 * Método que gestiona la acción del botón "Comprobar DNI". Dado un DNI,
	 * muestra un mensaje si el DNI introducido es válido o no.
	 * @param event Evento de acción del botón.
	 */
	public void checkButton(ActionEvent event) {
		String numberToCheck = generadorDNI.numberToCheckField.getText();
		if (numberToCheck.isEmpty()) {
            JOptionPane.showMessageDialog(generadorDNI, "Debes introducir un número de DNI a comprobar","Error - Campo de número de DNI vacío", JOptionPane.ERROR_MESSAGE);
         }else if(AuxFunctions.ValidateDNI(numberToCheck)){
        	 JOptionPane.showMessageDialog(generadorDNI, "El DNI es correcto.","DNI Válido", JOptionPane.INFORMATION_MESSAGE);
         }else {
        	 JOptionPane.showMessageDialog(generadorDNI, "El DNI no es válido","DNI no válido", JOptionPane.ERROR_MESSAGE);
         }
		 
	}

	/**
	 * Exporta una lista de DNI recibida como parámetro a un fichero .txt. Si se produce algún error 
	 * a la hora de almacenar el dichero muestra un mensaje de error.
	 * @param dnis Lista con los DNI
	 * @param event Evento de acción del botón.
	 */
	private void exportToTxt(List<String> dnis) {
		  JFileChooser chooser = new JFileChooser();
		  chooser.setSelectedFile(new File("dnis.txt"));
		  int result = chooser.showSaveDialog(generadorDNI);
		  if (result == JFileChooser.APPROVE_OPTION) {
		    try (PrintWriter out = new PrintWriter(chooser.getSelectedFile())) {
		    		for (String dni : dnis) {
		    		out.println(dni);
		    	}
		   } catch (FileNotFoundException ex) {
		        JOptionPane.showMessageDialog(generadorDNI, "Error guardando el fichero", "Error", JOptionPane.ERROR_MESSAGE);
		   		}
		  }
	}
	
	/**
	 * Muestra información básica del proyecto
	 * @param event Evento de acción del botón.
	 */ 
	public void aboutButton(ActionEvent event) {
		JOptionPane.showMessageDialog(generadorDNI, "Generador DNI v1.1 \n Desarrollado por Javier Blasco Vázquez\n Correo de contacto: jblascovazquez@gmail.com.", "Sobre Generador DNI", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Abre la url de issues de Github
	 * @param event Evento de acción del botón.
	 */
	public void reportButton(ActionEvent event) {
		try {
  	      Desktop.getDesktop().browse(new URI("https://github.com/javvazbla/Generador-DNI/issues"));
  	    } catch (IOException | URISyntaxException ex) {
  	      ex.printStackTrace();
  	    }
	}
}
