package me.emanuel.formapi.controller;

import java.awt.Color;

import javax.swing.JComponent;

import me.emanuel.formapi.Design;
import me.emanuel.formapi.validator.Validator;

public abstract class CustomComponent<T extends JComponent> {
	
	protected T component;
	
	protected InputSettings options;
	protected Form form;
	protected Validator validator;
	protected boolean valid;
	
	public CustomComponent(InputSettings options, Form form) {
		this.options = options;
		this.form = form;
		this.validator = options.getValidator();
	}
	
	// Methods to implement
	public abstract boolean isNull();
	public abstract Object getValue();
	public abstract boolean setValue(Object toSet);
	public abstract void clear();
	
	// Implemented Methods
	
	public void setColor(Color c) {
		getComponent().setBackground(c);
	}
	
	public void changeColor() {
		Color toSet = valid ? Design.getOkColor() : Design.getErrorColor();
		setColor(toSet);
	}
	
	public void verify() {
		boolean oldValid = valid;
		if(isValidType()) {
			if(validator != null) {
				if(validator.validate(getValue())) {
					valid = true;
				}
				else {
					valid = false;
				}
			}
			else {
				valid = true;
			}
		}
		else {
			valid = false;
		}
			
		if(isNull() && !getOptions().isRequired()) {
			valid = true;
		}
		if(isNull() && getOptions().isRequired()) {
			valid = false;
		}
		
		changeColor();
		
		if(oldValid != valid) {
			if(form.isReady()) {
				form.valueChanged();
			}
		}
	}
	
	// Getter / Setter
	
	public boolean isValidType() {
		return true;
	}
	
	public T getComponent() {
		return component;
	}
	public void setComponent(T comp) {
		this.component = comp;
	}
	public InputSettings getOptions() {
		return options;
	}
	public void setOptions(InputSettings options) {
		this.options = options;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public Validator getValidator() {
		return validator;
	}
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
