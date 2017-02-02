package me.emanuel.formapi.controller;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import me.emanuel.formapi.Design;

@SuppressWarnings({ "rawtypes", "serial" })
public class Form extends JPanel {
	
	private boolean locked = false;
	private boolean preparing = true;
	
	private String title;
	
	private List<InputSettings> fields;
	private Map<String, CustomComponent> components = new LinkedHashMap<String, CustomComponent>();
	private ActionListener submitListener;
	private JButton submit;
	
	Form(List<InputSettings> fields, String title, ActionListener submitListener) {
		this.fields = fields;
		this.title = title;
		this.submitListener = submitListener;
		init();
		
		boolean valid = isValid();
		submit.setEnabled(valid);
		preparing = false;
	}
	
	private void init() {
		// Look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}
		
		// Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(2, 2, 2, 2);
		
		// Components
		int i = 0;
		
		c.gridy = i;
		c.gridx = 0;
		
		c.gridwidth = 2;
		
		JLabel titleLabel = new JLabel(title);
		Font f = new Font("", Font.BOLD, Design.getTitleSize());
		titleLabel.setFont(f);
		add(titleLabel, c);
		
		c.gridwidth = 1;
		
		i++;
		for(InputSettings options : fields) {
			// Setting position
			c.gridy = i;
			c.gridx = 0;
		
			// Adding label
			add(new JLabel(options.getDisplayName() + (options.isRequired() ? " *" : "")), c);
			
			// Moving +1 @ x
			c.gridx = 1;
			
			// Add the corresponding custom component
			CustomComponent comp = FormFactory.getComponent(options, this);
			components.put(options.getKey(), comp);
			add(comp.getComponent(), c);
			
			i++;
		}
		
		// Submit button
		submit = new JButton("Bestätigen");
		submit.setEnabled(isValid());
		if(submitListener != null) {
			submit.addActionListener(submitListener);
		}
		
		c.gridy = i;
		c.gridx = 0;
		
		add(submit, c);
	}
	
	protected void valueChanged() {
		if(!preparing) {
			if(submit != null) {
				submit.setEnabled(isValid());
			}
		}
	}
	
	public boolean isValid() {
		boolean valid = true;
		if(components != null) {
			for(CustomComponent cc : components.values()) {
				if(!cc.isValid()) {
					valid = false;
				}
			}
		}
		if(locked) {
			valid = false;
		}
		return valid;
	}
	
	public Object getValue(String key) {
		if(components.containsKey(key)) {
			return components.get(key).getValue();
		}
		return null;
	}
	
	public Map<String, Object> getValues() {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for(String k : components.keySet()) {
			values.put(k, components.get(k).getValue());
		}
		return values;
	}
	
	public boolean setValue(String key, Object value) {
		if(components.containsKey(key)) {
			CustomComponent cc = components.get(key);
			cc.setValue(value);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clear() {
		for(CustomComponent cc : components.values()) {
			cc.clear();
		}
	}
	
	public boolean checkSubmitButton(JButton toCheck) {
		return toCheck == submit;
	}
	
	public void lock(boolean lock) {
		locked = lock;
		if(lock) {
			submit.setEnabled(false);
			for(CustomComponent cc : components.values()) {
				cc.getComponent().setEnabled(false);
			}
		}
		else {
			valueChanged();
			for(CustomComponent cc : components.values()) {
				cc.getComponent().setEnabled(true);
			}
		}
	}
	
	public void fillDefaultValues() {
		for(CustomComponent cc : components.values()) {
			cc.setValue(cc.getOptions().getDefaultValue());
		}
	}
	
	public void changeAllColors() {
		for(CustomComponent cc : components.values()) {
			cc.changeColor();
		}
	}
	
	public boolean isReady() {
		return !preparing;
	}
	
}
