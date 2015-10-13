package appWeb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAO;

@ManagedBean(name = "index")
@ViewScoped
public class Index implements Serializable {
	
	private static final long serialVersionUID = 4714771298255581872L;
	
	private List<model.Row> rows = new ArrayList<model.Row>();
	private model.Subcategory currentSubcategory = new model.Subcategory();
	
	public Index() {
		
	}
	
	
	private dao.DAO<model.Category> cdao = new DAO<model.Category>(model.Category.class);
	
	public List<model.Category> getCategories()
	{
		
		List<model.Category> categories = null;
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
		return categories;
	}
	
	@ManagedProperty(value="#{cart}")
	private Cart cart;
	
	public void setCart(Cart cart)
	{
		this.cart = cart;
		
	}

	public void Add(model.Row row) throws IOException
	{
		cart.AddToCart(row);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?sid=" + row.getProduct().getSubcategory().getSubcategoryid());
		
	}

	public model.Subcategory getCurrentSubcategory() {
		return currentSubcategory;
	}

	public void setCurrentSubcategory(model.Subcategory currentSubcategory) {
		this.currentSubcategory = currentSubcategory;
	}

	public List<model.Row> getRows() {
		return rows;
		
	}

	public void setRows(List<model.Row> rows) {
		
		this.rows = rows;
	}
	

}
