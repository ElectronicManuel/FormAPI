package me.emanuel.formapi.test;

import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import me.emanuel.formapi.components.file.FileChooserSettings;
import me.emanuel.formapi.controller.Form;
import me.emanuel.formapi.controller.FormFactory;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {
	
	private Form form;

	public static void main(String[] args) {
		new TestFrame();
	}
	
	public TestFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Test");
		
		setLayout(new FlowLayout());
		
		FormFactory factory = new FormFactory();
		
		factory.addInput(new FileChooserSettings("pfad", "Pfad", "abc", true, null, JFileChooser.DIRECTORIES_ONLY));
		
		form = factory.produceForm("Testing", null);
		add(form);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
