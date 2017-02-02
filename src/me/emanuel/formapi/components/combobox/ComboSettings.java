package me.emanuel.formapi.components.combobox;

import me.emanuel.formapi.controller.InputSettings;
import me.emanuel.formapi.validator.Validator;

public class ComboSettings extends InputSettings {
	
	private Object[] objects;
	
	public ComboSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator, Object[] objects) {
		super(key, displayName, defaultValue, required, validator);
		setObjects(objects);
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}
	
}
