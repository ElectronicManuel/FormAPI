package me.emanuel.formapi.controller;

import me.emanuel.formapi.validator.Validator;

public abstract class InputSettings {
	
	private String key;
	private String displayName;
	private Object defaultValue;
	private boolean required;
	private Validator validator;
	
	public InputSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator) {
		this.key = key;
		this.displayName = displayName;
		this.defaultValue = defaultValue;
		this.required = required;
		this.validator = validator;
	}
	
	/* Getters / Setters */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
