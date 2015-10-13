package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import dao.DAO;

public class Fil implements Filter {

    /**
     * Default constructor. 
     */
    public Fil() {
        // TODO Auto-generated constructor stub
    }

    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	private dao.DAO<model.Product> pdao = new DAO<model.Product>(model.Product.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String param = request.getParameter("sid");
		
		if (param != null)
		{
			int sid = 0;
		try
		{
		sid = Integer.parseInt(param);
		}
		catch (Exception e)
		{
			chain.doFilter(request, response);
		}
		
		
		
		if (sid > 0)
		{
			
			EntityManagerFactory factory = null;
			EntityManager em = null;
			try
			{
			factory = Persistence.createEntityManagerFactory("soap");
			em = factory.createEntityManager();
			em.getTransaction().begin();	
			
			
		List<model.Product> products = pdao.getAll("SELECT * FROM product WHERE subcategory_subcategoryid = " + sid, em);
		
		 
		List<model.Row> rows = new ArrayList<model.Row>();
		
		for(model.Product p : products)
		{
			model.Row row = new model.Row();
			row.setProduct(p);
			
			if (row.getProduct().getType() == 0)
				row.setCount(1);
				else
				row.setCount(100);
			
			rows.add(row);
		}
		
		HttpServletRequest req = (HttpServletRequest)request;
		req.getSession().setAttribute("mass", rows);
		
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
		

		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
