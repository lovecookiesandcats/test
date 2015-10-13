package appWeb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="orderisready")
@ViewScoped
public class OrderIsReady implements Serializable {

	private String id;
	 
	private static final long serialVersionUID = -2053813003831497539L;

	public OrderIsReady() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
