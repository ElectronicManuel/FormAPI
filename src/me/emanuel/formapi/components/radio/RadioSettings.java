package me.emanuel.formapi.components.radio;

import me.emanuel.formapi.controller.InputSettings;
import me.emanuel.formapi.validator.Validator;

public class RadioSettings extends InputSettings {
	
	public RadioSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator) {
		super(key, displayName, defaultValue, required, validator);
	}
	
	public RadioSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator, Object[] obj) {
		super(key, displayName, defaultValue, required, validator);
		setObjects(obj);
	}

	private Object[] objects;

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}
	
}
