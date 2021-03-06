package me.emanuel.formapi.components.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.emanuel.formapi.controller.CustomComponent;
import me.emanuel.formapi.controller.Form;

public class CustomDate extends CustomComponent<JTextField> implements DocumentListener {
	
	private SimpleDateFormat format;

	public CustomDate(DateSettings options, Form form) {
		super(options, form);
		format = new SimpleDateFormat("dd.MM.yyyy");
		setComponent(new JTextField());
		getComponent().getDocument().addDocumentListener(this);
		getComponent().setColumns(20);
		
		verify();
	}

	@Override
	public boolean isNull() {
		boolean nil = getComponent().getText().length() == 0;
		return nil;
	}

	@Override
	public Object getValue() {
		Date toReturn = null;
		try {
			toReturn = format.parse(getComponent().getText());
		} catch (ParseException e) {
		}
		
		return toReturn;
	}
	
	@Override
	public boolean isValidType() {
		boolean val = false;
		
		val = getValue() != null;
		
		return val;
	}

	@Override
	public void clear() {
		getComponent().setText("");
	}

	@Override
	public boolean setValue(Object toSet) {
		if(toSet instanceof Date) {
			Date d = (Date)toSet;
			getComponent().setText(format.format(d));
			return true;
		}
		else {
			return false;
		}
	}

	public void changedUpdate(DocumentEvent arg0) {
		verify();
	}

	public void insertUpdate(DocumentEvent arg0) {
		verify();
	}

	public void removeUpdate(DocumentEvent arg0) {
		verify();
	}

}
