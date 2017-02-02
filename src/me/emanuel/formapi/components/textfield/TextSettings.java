package me.emanuel.formapi.components.textfield;

import me.emanuel.formapi.controller.InputSettings;
import me.emanuel.formapi.validator.Validator;

public class TextSettings extends InputSettings {
	
	private int maxLength;
	
	public TextSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator, int maxLength) {
		super(key, displayName, defaultValue, required, validator);
		setMaxLength(maxLength);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
}
