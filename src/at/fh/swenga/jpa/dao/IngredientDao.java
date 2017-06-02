package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.Ingredient;
 
@Repository
@Transactional
public class IngredientDao {
 
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<Ingredient> getIngredients() {
 
		TypedQuery<Ingredient> typedQuery = entityManager.createQuery(
				"select p from Ingredient p", Ingredient.class);
		List<Ingredient> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public Ingredient getIngredient(String name) {
		try {
			TypedQuery<Ingredient> typedQuery = entityManager.createQuery(
					"select p from Ingredient p where p.name = :name",
					Ingredient.class);
			typedQuery.setParameter("name", name);
			Ingredient Ingredient = typedQuery.getSingleResult();
			return Ingredient;
		} catch (NoResultException e) {
			return null;
		}
 
	}
}