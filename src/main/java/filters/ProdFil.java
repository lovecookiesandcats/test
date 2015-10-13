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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import dao.DAO;
import model.Product;

/**
 * Servlet Filter implementation class ProdFil
 */
public class ProdFil implements Filter {

    /**
     * Default constructor. 
     */
    public ProdFil() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	private dao.DAO<model.Product> pdao = new DAO<model.Product>(model.Product.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String param = request.getParameter("pid");
		
		if (param != null)
		{
			int pid = 0;
		try
		{
		pid = Integer.parseInt(param);
		}
		catch (Exception e)
		{
			chain.doFilter(request, response);
		}
		
		model.Row row = new model.Row();
		if (pid > 0)
		{
			
			EntityManagerFactory factory = null;
			EntityManager em = null;
			try
			{
			factory = Persistence.createEntityManagerFactory("soap");
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
		    Product p = pdao.get(pid, em);
		    
			row.setProduct(p);
			if (row.getProduct().getType() == 0)
				row.setCount(1);
				else
				row.setCount(100);
			
			HttpServletRequest req = (HttpServletRequest)request;
			req.getSession().setAttribute("row", row);
			
			
			
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
