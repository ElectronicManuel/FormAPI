package me.emanuel.formapi.validator;

public class LettersSpacesOnly extends Validator {

	@Override
	public boolean validate(Object o) {
		String name = (String)o;
		
		return name.matches("^[a-zA-Z\\s]*$");
	}

}
