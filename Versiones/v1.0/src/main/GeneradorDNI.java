package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class GeneradorDNI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton generateMultipleButton;
	private JButton exportButton;
	private JButton copyButton;
	
	JTextField numberField;
	JList<String> resultList;
	DefaultListModel<String> model;
	
	ButtonActions listener = new ButtonActions(this);

	public GeneradorDNI() {
	    super("Generador DNI v1.0");
	
	    copyButton = new JButton("Copiar");
	    numberField = new JTextField();
	    generateMultipleButton = new JButton("Generar DNIs");
	    exportButton = new JButton("Exportar a fichero .txt");
	    model = new DefaultListModel<>();
	    resultList = new JList<>(model);
	    resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    
	    JMenu contactMenu = new JMenu("Ayuda");
	    menuBar.add(contactMenu);
	    
	    JMenuItem reportIssueItem = new JMenuItem("Informar de un problema");
	    contactMenu.add(reportIssueItem);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridLayout(2, 2));
	    
	    JMenuItem aboutItem = new JMenuItem("Sobre Generador DNI");
	    contactMenu.add(aboutItem);
	    
	    buttonPanel.add(numberField);
	    buttonPanel.add(generateMultipleButton);
	    buttonPanel.add(copyButton);
	    buttonPanel.add(exportButton);
	    
	    
	    
	    setLayout(new BorderLayout());
	    add(buttonPanel, BorderLayout.NORTH);
	    add(new JScrollPane(resultList), BorderLayout.CENTER);
	    
	    
	    
	    copyButton.addActionListener(listener::copyButton);
	    generateMultipleButton.addActionListener(listener::generateMultipleButton);
	    exportButton.addActionListener(listener::exportButton);
	    aboutItem.addActionListener(listener::aboutButton);
	    reportIssueItem.addActionListener(listener::reportButton);
	    
	    numberField.setText("1");
	
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null);
	    setSize(350, 500);
	    setVisible(true);
  }

  
}