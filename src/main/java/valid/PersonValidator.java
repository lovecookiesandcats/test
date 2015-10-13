package valid;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("persvalidator")
public class PersonValidator implements Validator {

	public PersonValidator() {
		
     
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		    if (arg2.toString().length() > 200)
	        {
	        	 FacesMessage msg = new FacesMessage("Data validation failed", "Не более 200 символов");
		         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		         throw new ValidatorException(msg);
	        }
		
	}

}
