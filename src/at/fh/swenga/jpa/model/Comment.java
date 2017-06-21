package at.fh.swenga.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Comment")
public class Comment implements java.io.Serializable {
	private int id;

	private String comment;

	
	private User commentOwner;
	
	
	private CocktailModel belongsTo;
	

	@Column(name="comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Id
	@Column(name = "comment_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	public User getCommentOwner() {
		return commentOwner;
	}
	public boolean isOldOwner(User newOwner){
		return commentOwner==null? newOwner == null : commentOwner.equals(newOwner);
		
	}

	public void setCommentOwner(User commentOwner) {
		if(isOldOwner(commentOwner)){
			return;
		}
		
		this.commentOwner = commentOwner;
	}
	public boolean isOldCocktail(CocktailModel newCocktail){
		return belongsTo==null? newCocktail == null : belongsTo.equals(newCocktail);
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id", nullable = false)
	public CocktailModel getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(CocktailModel belongsTo) {
		if(isOldCocktail(belongsTo))
		{
			return;
		}
		this.belongsTo = belongsTo;
	}
	

}
