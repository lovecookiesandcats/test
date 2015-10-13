package filters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import appWeb.ProductInfo;

@ManagedBean(name="inforequest")
@RequestScoped
public class InfoRequest {

	@ManagedProperty("#{productInfo}")
	private ProductInfo info;
	
	public void setInfo(ProductInfo info)
	{
		this.info = info;
	}
	
	public InfoRequest() {
		// TODO Auto-generated constructor stub
	}

	
	public model.Row getR()
	{
		if (info.getRow() == null)
		{
		FacesContext faces = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)faces.getExternalContext().getSession(false);
		model.Row r = (model.Row)session.getAttribute("row");
		info.setRow(r);
		return r;
		}
		else
		{
		return info.getRow();
		}
	    
		
	}

}
