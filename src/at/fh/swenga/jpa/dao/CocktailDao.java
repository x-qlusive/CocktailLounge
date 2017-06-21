package at.fh.swenga.jpa.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
import at.fh.swenga.jpa.model.CocktailModel;
 
@Repository
@Transactional
public class CocktailDao {
 
	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<CocktailModel> getCocktails() {
		TypedQuery<CocktailModel> typedQuery = entityManager.createQuery("select e from CocktailModel e",
				CocktailModel.class);
		List<CocktailModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public List<CocktailModel> searchCocktails(String searchString) {
		TypedQuery<CocktailModel> typedQuery = entityManager.createQuery(
				"select e from CocktailModel e where e.name like :search or e.type like :search",
				CocktailModel.class);
		typedQuery.setParameter("search", "%" + searchString + "%");
		List<CocktailModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public CocktailModel getCocktail(int i) throws DataAccessException {
		return entityManager.find(CocktailModel.class, i);
	}
	
	public CocktailModel getCocktailById(int i) throws DataAccessException {
		return entityManager.find(CocktailModel.class, i);
	}
 
	public void persist(CocktailModel cocktail) {
		entityManager.persist(cocktail);
	}
 
	public CocktailModel merge(CocktailModel cocktail) {
		return entityManager.merge(cocktail);
	}
 
	public void delete(CocktailModel cocktail) {
		entityManager.remove(cocktail);
	}
 
	public int deleteAllCocktails() {
		int count = entityManager.createQuery("DELETE FROM CocktailModel").executeUpdate();
		return count;
	}
 
	public void delete(int id) {
		CocktailModel cocktail = getCocktail(id);
		if (cocktail != null)
			delete(cocktail);
	}
}
 