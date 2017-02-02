package me.emanuel.formapi.controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import me.emanuel.formapi.components.combobox.ComboSettings;
import me.emanuel.formapi.components.combobox.CustomComboBox;
import me.emanuel.formapi.components.date.CustomDate;
import me.emanuel.formapi.components.date.DateSettings;
import me.emanuel.formapi.components.file.CustomFileChooser;
import me.emanuel.formapi.components.file.FileChooserSettings;
import me.emanuel.formapi.components.radio.CustomRadioList;
import me.emanuel.formapi.components.radio.RadioSettings;
import me.emanuel.formapi.components.textfield.CustomTextField;
import me.emanuel.formapi.components.textfield.TextSettings;

public class FormFactory {
	
	private List<InputSettings> settings;
	
	public FormFactory() {
		settings = new ArrayList<InputSettings>();
	}
	
	public Form produceForm(String title, ActionListener submitListener) {
		Form form = new Form(settings, title, submitListener);
		return form;
	}
	
	@SuppressWarnings("rawtypes")
	public static CustomComponent getComponent(InputSettings options, Form form) {
		CustomComponent cc = null;
		if(options instanceof TextSettings) {
			cc =  new CustomTextField((TextSettings)options, form);
		}
		else if(options instanceof ComboSettings) {
			cc =  new CustomComboBox((ComboSettings)options, form);
		}
		else if(options instanceof RadioSettings) {
			cc =  new CustomRadioList((RadioSettings)options, form);
		}
		else if(options instanceof DateSettings) {
			cc =  new CustomDate((DateSettings)options, form);
		}
		else if(options instanceof FileChooserSettings) {
			cc = new CustomFileChooser((FileChooserSettings)options, form);
		}
		return cc;
	}
	
	public void addInput(InputSettings input) {
		settings.add(input);
	}
	
	public void removeInput(InputSettings input) {
		settings.remove(input);
	}
	
	public void removeInput(int index) {
		settings.remove(index);
	}

}
