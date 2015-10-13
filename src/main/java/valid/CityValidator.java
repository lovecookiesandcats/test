package valid;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cvalidator")
public class CityValidator implements Validator {

	public CityValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		String value = arg2.toString();
		if (value.length() > 50)
		{
			FacesMessage msg = new FacesMessage("Data validation failed", "Слишком много букв");
		         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		         throw new ValidatorException(msg);
		}

	}

}
