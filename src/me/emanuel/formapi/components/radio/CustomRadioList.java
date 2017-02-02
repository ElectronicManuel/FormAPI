package me.emanuel.formapi.components.radio;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import me.emanuel.formapi.controller.CustomComponent;
import me.emanuel.formapi.controller.Form;

public class CustomRadioList extends CustomComponent<JPanel> implements ItemListener {
	
	private ButtonGroup group;
	private List<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	public CustomRadioList(RadioSettings opts, Form form) {
		super(opts, form);
		setComponent(new JPanel());
		init(opts);
		
		verify();
	}
	
	private void init(RadioSettings opts) {
		group = new ButtonGroup();
		
		for(Object o : opts.getObjects()) {
			JRadioButton b = new JRadioButton(o.toString());
			b.addItemListener(this);
			group.add(b);
			buttons.add(b);
			getComponent().add(b);
		}
	}
	
	@Override
	public Object getValue() {
		String selected = null;
		for(JRadioButton b : buttons) {
			if(b.isSelected()) selected = b.getText();
		}
		return selected;
	}

	public void itemStateChanged(ItemEvent e) {
		verify();
	}

	@Override
	public void clear() {
		group.clearSelection();
	}

	@Override
	public boolean isNull() {
		boolean nil = getValue() == null;
		return nil;
	}
	
	@Override
	public void setColor(Color c) {
		for(JRadioButton b : buttons) {
			b.setForeground(c);
		}
	}

	@Override
	public boolean setValue(Object toSet) {
		for(JRadioButton b : buttons) {
			if(b.getText().equals(toSet.toString())) {
				b.setSelected(true);
				return true;
			}
		}
		return false;
	}
	
}
