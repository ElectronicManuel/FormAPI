package me.emanuel.formapi.components.file;

import me.emanuel.formapi.controller.InputSettings;
import me.emanuel.formapi.validator.Validator;

public class FileChooserSettings extends InputSettings {
	
	private int selectionMode;

	public FileChooserSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator, int selectionMode) {
		super(key, displayName, defaultValue, required, validator);
		this.selectionMode = selectionMode;
	}

	public int getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(int selectionMode) {
		this.selectionMode = selectionMode;
	}
	
}
