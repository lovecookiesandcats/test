package appWeb;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.DAO;
import model.Product;

@ManagedBean(name="productInfo")
@ViewScoped
public class ProductInfo implements Serializable  {
	 
	private static final long serialVersionUID = -8017550633795981527L;
	
	private int id;
	
	private model.Row row;
	
	public ProductInfo() {
		
	}
	
	
	private dao.DAO<model.Product> pdao = new dao.DAO<model.Product>(model.Product.class);
	public void ProdInfo()
	{
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		try
		{
		factory = Persistence.createEntityManagerFactory("soap");
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Product p = pdao.get(id, em);
			
		row.setProduct(p);
		if (row.getProduct().getType() == 0)
			row.setCount(1);
			else
			row.setCount(100);
		
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
	
	@ManagedProperty(value="#{cart}")
	private Cart cart;
	
	public void setCart(Cart cart)
	{
		this.cart = cart;
		
	}
	
	public void Add(model.Row row) throws IOException
	{
		cart.AddToCart(row);
		
	}

	public model.Row getRow() {
		return row;
	}

	public void setRow(model.Row row) {
		this.row = row;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
