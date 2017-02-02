package me.emanuel.formapi.components.date;

import me.emanuel.formapi.controller.InputSettings;
import me.emanuel.formapi.validator.Validator;

public class DateSettings extends InputSettings {

	public DateSettings(String key, String displayName, Object defaultValue, boolean required, Validator validator) {
		super(key, displayName, defaultValue, required, validator);
	}

}
