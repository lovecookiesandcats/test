package valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phonevalidator")
public class PhoneValidator implements Validator {

	public PhoneValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		Pattern p = Pattern.compile("^8[0-9]{10}$");  
        Matcher m = p.matcher(arg2.toString()); 
        
        if (!m.matches())
        {
        	 FacesMessage msg = new FacesMessage("Data validation failed", "Телефон должен быть в формате 8XXXXXXXXXX");
	         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	         throw new ValidatorException(msg);
        }
		
	}

}
