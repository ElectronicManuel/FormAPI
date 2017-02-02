package me.emanuel.formapi.components.textfield;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.emanuel.formapi.controller.CustomComponent;
import me.emanuel.formapi.controller.Form;

public class CustomTextField extends CustomComponent<JTextField> implements DocumentListener {
	
	public CustomTextField(TextSettings opts, Form form) {
		super(opts, form);
		
		if(opts.getMaxLength() > 0) {
			setComponent(new JTextField(opts.getMaxLength()));
		}
		else {
			setComponent(new JTextField());
		}
		getComponent().getDocument().addDocumentListener(this);
		
		verify();
	}
	
	@Override
	public Object getValue() {
		return getComponent().getText();
	}

	@Override
	public void clear() {
		getComponent().setText("");
	}

	@Override
	public boolean isNull() {
		return getComponent().getText().length() == 0;
	}

	public void changedUpdate(DocumentEvent arg0) {
		if(form.isReady()) {
			verify();
		}
	}
	
	public void insertUpdate(DocumentEvent arg0) {
		if(form.isReady()) {
			verify();
		}
	}

	public void removeUpdate(DocumentEvent arg0) {
		if(form.isReady()) {
			verify();
		}
	}

	@Override
	public boolean setValue(Object toSet) {
		if(toSet == null) {
			getComponent().setText("");
		}
		else {
			String s = toSet.toString();
			getComponent().setText(s);
		}
		return true;
	}

}
