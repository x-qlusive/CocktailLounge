package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.Type;
 
@Repository
@Transactional
public class TypeDao {
 
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<Type> getTypes() {
 
		TypedQuery<Type> typedQuery = entityManager.createQuery(
				"select d from Type d", Type.class);
		List<Type> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public Type getType(String name) {
		try {
 
			TypedQuery<Type> typedQuery = entityManager.createQuery(
					"select d from Type d where d.name = :name",
					Type.class);
			typedQuery.setParameter("name", name);
			Type type = typedQuery.getSingleResult();
			return type;
		} catch (NoResultException e) {
			return null;
		}
	}
 
}