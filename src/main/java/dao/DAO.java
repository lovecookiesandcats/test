/**
 * 
 */
package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * @author asus-pc
 *
 */
public class DAO<T> implements IDAO<T>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3505461655971297276L;
	
	private final Class<T> type;
	
	public DAO(Class<T> type) {
		this.type = type;
	}

	@Override
	public T get(int id, EntityManager em) {
		T entity = em.find(type, id);
		return entity;
	}

	@Override
	public List<T> getAll(String sql, EntityManager em) {
        return em.createNativeQuery(sql, type).getResultList();
	}


	@Override
	public T add(T obj, EntityManager em) {
		em.persist(obj);
		return obj;
	}

	@Override
	public T update(T obj, EntityManager em) {
		em.merge(obj);
		return obj;
	}

	@Override
	public void delete(int id, EntityManager em) {
		em.remove(get(id, em));

	}
	
	

}
