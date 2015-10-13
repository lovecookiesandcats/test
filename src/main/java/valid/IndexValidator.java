package valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ivalidator")
public class IndexValidator implements Validator {

	public IndexValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		Pattern p = Pattern.compile("^[0-9]{6}$");  
        Matcher m = p.matcher(arg2.toString());  
        if (!m.matches())
        {
        	 FacesMessage msg = new FacesMessage("Data validation failed", "Неправильный индекс");
	         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	         throw new ValidatorException(msg);
        }
		
	}

}
