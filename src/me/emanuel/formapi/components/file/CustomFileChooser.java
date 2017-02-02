package me.emanuel.formapi.components.file;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.emanuel.formapi.controller.CustomComponent;
import me.emanuel.formapi.controller.Form;

public class CustomFileChooser extends CustomComponent<JPanel> implements ActionListener, DocumentListener {
	
	private JFileChooser chooser;
	private JTextField path;
	private JButton openButton;
	private File selectedFile;
	
	public CustomFileChooser(FileChooserSettings settings, Form form) {
		super(settings, form);
		JPanel panel = new JPanel(new FlowLayout());
		
		// FileChooser
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		chooser.setFileSelectionMode(settings.getSelectionMode());
		
		// Textfeld
		path = new JTextField(20);
		path.getDocument().addDocumentListener(this);
		
		// Button
		openButton = new JButton("Durchsuchen");
		openButton.addActionListener(this);
		
		panel.add(path);
		panel.add(openButton);
		
		setComponent(panel);
		verify();
	}
	
	@Override
	public boolean isValidType() {
		boolean valid = selectedFile != null && selectedFile instanceof File && selectedFile.exists();
		return valid;
	}
	
	@Override
	public boolean isNull() {
		return selectedFile == null;
	}

	@Override
	public Object getValue() {
		return selectedFile;
	}

	@Override
	public boolean setValue(Object toSet) {
		if(toSet instanceof File) {
			File f = (File)toSet;
			selectedFile = f;
			path.setText(f.getAbsolutePath());
			return true;
		}
		else {
			return true;
		}
	}

	@Override
	public void clear() {
		selectedFile = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = chooser.showOpenDialog(path);
		if (result == JFileChooser.APPROVE_OPTION) {
		    setValue(chooser.getSelectedFile());
		}
		verify();
	}
	
	private File getFileFromPath(String path) {
		File f = new File(path);
		return f;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		selectedFile = getFileFromPath(path.getText());
		verify();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		selectedFile = getFileFromPath(path.getText());
		verify();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		selectedFile = getFileFromPath(path.getText());
		verify();
	}

}
