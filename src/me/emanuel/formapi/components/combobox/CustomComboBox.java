package me.emanuel.formapi.components.combobox;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import me.emanuel.formapi.controller.CustomComponent;
import me.emanuel.formapi.controller.Form;

@SuppressWarnings("rawtypes")
public class CustomComboBox extends CustomComponent<JComboBox> implements ItemListener {
	
	@SuppressWarnings("unchecked")
	public CustomComboBox(ComboSettings opts, Form form) {
		super(opts, form);
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("Bitte auswählen");
		
		for(Object o : opts.getObjects()) {
			list.add(o);
		}
		
		setComponent(new JComboBox(list.toArray()));
		
		getComponent().addItemListener(this);
		
		verify();
	}

	@Override
	public Object getValue() {
		return getComponent().getSelectedItem();
	}

	@Override
	public void clear() {
		getComponent().setSelectedIndex(0);
	}

	@Override
	public boolean isNull() {
		boolean nil = getComponent().getSelectedIndex() == 0;
		return nil;
	}

	public void itemStateChanged(ItemEvent e) {
		verify();
	}
	
	@Override
	public void setColor(Color c) {
		getComponent().setForeground(c);
	}

	@Override
	public boolean setValue(Object toSet) {
		getComponent().setSelectedItem(toSet);
		return true;
	}

}
