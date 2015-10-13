package appWeb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import dao.DAO;

@ManagedBean(name="cart")
@SessionScoped
public class Cart implements Serializable {
	
	private String message;
	
	private static final long serialVersionUID = 4436937836021170501L;
	private List<model.Row> rows = new ArrayList<model.Row>();
	
	private List<model.Category> categories;
	
	public Cart() {
		
	}

	public List<model.Row> getRows() {
		return rows;
	}

	public void setRows(List<model.Row> rows) {
		this.rows = rows;
	}
	
	public void RemoveFromCart(model.Row row)
	{
		for(model.Row r: rows)
		{
			if (r.getProduct().getProductid() == row.getProduct().getProductid())
			{
				rows.remove(r);
				return;
			}
		}
	}
	
	public boolean isCheck(model.Row row)
	{
		for(model.Row r: this.rows)
		{
			if (r.getProduct().getProductid() == row.getProduct().getProductid())
			{
				return true;
			}
		}
		return false;
	}
	
	public void AddToCart(model.Row row)
	{
		if (row.getCount() > 0)
		{
		for(model.Row r: this.rows)
		{
			if (r.getProduct().getProductid() == row.getProduct().getProductid())
			{
				r.setCount(r.getCount() + row.getCount());
				if (row.getProduct().getType() == 1)
				message = "Товар " + row.getProduct().getName() + " в количестве " + row.getCount() + "г. успешно добавлен в корзину";
				else
				message = "Товар " + row.getProduct().getName() + " в количестве " + row.getCount() + " успешно добавлен в корзину";
				return;
			}
		}
		model.Row roww = new model.Row();
		roww.setProduct(row.getProduct());
		roww.setCount(row.getCount());
		this.rows.add(roww);
		if (roww.getProduct().getType() == 1)
			message = "Товар " + roww.getProduct().getName() + " в количестве " + roww.getCount() + "г. успешно добавлен в корзину";
			else
			message = "Товар " + roww.getProduct().getName() + " в количестве " + roww.getCount() + " успешно добавлен в корзину";
		}
		else
		{
			message = "";
			row.setCount(1);
		}
		
	}
	
	public int gCount(model.Row row)
	{
		if (row.getProduct().getType() == 1)
		{
			return row.getProduct().getPrice() * row.getCount() / 100;
		}
		else
		{
			return row.getProduct().getPrice() * row.getCount();
		}
				
	}
	
	public String CartInfoCount()
	{
		int f = 0;
		for(model.Row r: rows)
		{
			if (r.getProduct().getType() == 1)
			{
				f = f + 1;
			}
			else
			{
				f = f + r.getCount();
			}
		}
		return Integer.toString(f);
	}
	
	public String CartInfoPrice()
	{
		int f = 0;
		for(model.Row r: rows)
		{
			if (r.getProduct().getType() == 1)
			{
				f = f + r.getCount() * r.getProduct().getPrice() / 100;
			}
			else
			{
				f = f + r.getCount() * r.getProduct().getPrice();
			}
			
		}
		return Integer.toString(f);
	}
	
	public String CartInfoWeight()
	{
		int f = 0;
		for(model.Row r: rows)
		{
			if (r.getProduct().getType() == 1)
			{
				f = f + r.getCount();
			}
			else
			{
				f = f + r.getCount() * r.getProduct().getWeight();
			}
			
		}
		return Integer.toString(f);
	}
	
	public boolean isAuthorized()
	{
		return isAuthorizedAsAdmin() || isAuthorizedAsCustomer() || isAuthorizedAsCourier();
	}
	
	public boolean isAuthorizedAsAdmin()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().isUserInRole("administrator");
	}
	
	public boolean isAuthorizedAsCustomer()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().isUserInRole("customer");
	}

	public boolean isAuthorizedAsCourier()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getExternalContext().isUserInRole("courier");
	}
	
	public void LogOut()
	{
		FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private dao.DAO<model.Category> cdao = new DAO<model.Category>(model.Category.class);
	
	public List<model.Category> getCategories() {
		if (categories == null)
		{	
			EntityManagerFactory factory = null;
			EntityManager em = null;
			try
			{
			factory = Persistence.createEntityManagerFactory("soap");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			
			categories = cdao.getAll("SELECT * FROM category", em);
		    for(model.Category c: categories)
		    {
		    	c.getSubcategories().size();
		    	for(model.Subcategory s: c.getSubcategories())
		    	{
		    		s.getProducts().size();
		    	}
		    }
		
		    em.getTransaction().commit();
			}
			catch (Exception e)
			{
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
			finally
			{
				
				em.close();
				factory.close();
			}
		}
		return categories;
	}

	public void setCategories(List<model.Category> categories) {
		this.categories = categories;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
