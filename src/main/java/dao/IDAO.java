package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface IDAO<T>
{
	public T get(int id, EntityManager em);
	
	public List<T> getAll(String sql, EntityManager em);
	
	public T add(T obj, EntityManager em);
	
	public T update(T obj, EntityManager em);
	
	public void delete(int id, EntityManager em);
	
}
