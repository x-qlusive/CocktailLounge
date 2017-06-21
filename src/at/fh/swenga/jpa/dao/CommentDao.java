package at.fh.swenga.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.jpa.model.CocktailModel;
import at.fh.swenga.jpa.model.Comment;
import at.fh.swenga.jpa.model.Ingredient;

@Repository
@Transactional
public class CommentDao {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<Comment> getComments() {
		TypedQuery<Comment> typedQuery = entityManager.createQuery("select e from Comment e",
				Comment.class);
		List<Comment> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
	
	public Comment getComment(int id) {
		try {
			TypedQuery<Comment> typedQuery = entityManager.createQuery(
					"select p from comment p where p.id = :id",
					Comment.class);
			typedQuery.setParameter("id", id);
			Comment comment = typedQuery.getSingleResult();
			return comment;
		} catch (NoResultException e) {
			return null;
		}
	}
	public void persist(Comment comment) {
		entityManager.persist(comment);
	}
 
	public Comment merge(Comment comment) {
		return entityManager.merge(comment);
	}
 
	public void delete(Comment comment) {
		entityManager.remove(comment);
	}
 

}
