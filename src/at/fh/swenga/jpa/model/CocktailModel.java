package at.fh.swenga.jpa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Cocktail")
public class CocktailModel implements java.io.Serializable {

	private int id;

	private String name;


	private Float alc;
	
	private String recipie;


	Type type;
	/*
	 * @JoinTable( name="EMP_PROJ", joinColumns={
	 * 
	 * @JoinColumn(name="EMP_ID", referencedColumnName="ID")},
	 * inverseJoinColumns={@JoinColumn(name="PROJ_ID",
	 * referencedColumnName="ID")})
	 */

	private String ingredients;
	
	
	private Set<Comment> comments = new HashSet<Comment>(0);

	
	private ArrayList<Float> rating;

	private float avgRating;
	
	public CocktailModel() {
	}

	public CocktailModel(String name, Float alc, String ingredients, String recipie) {
		super();
		this.name = name;
		this.alc = alc;
		this.ingredients = ingredients;
		this.recipie = recipie;
	}

	@Column(nullable = false, length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 30)
	public Float getAlc() {
		return alc;
	}

	public void setAlc(Float alc) {
		this.alc = alc;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(cascade = CascadeType.PERSIST)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(nullable = false, length = 60)
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	@Column(nullable = false, length = 60)
	public String getRecipie() {
		return recipie;
	}

	public void setRecipie(String recipie) {
		this.recipie = recipie;
	}
	public void addRating(Float rating){
		if(this.rating == null){
			this.rating = new ArrayList<Float>();
		}
		this.rating.add(rating);
	}

	public ArrayList<Float> getRating() {
		return rating;
	}

	public void setRating(Float newRating) {
		rating.add(newRating);
	}
	

	@OneToMany(fetch=FetchType.EAGER, mappedBy="belongsTo")	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment){
		comments.add(comment);
	}
	
	public float calcAvgRating()
	{
		if (rating == null) {
			Random generator = new Random();
			return (generator.nextFloat()*5);
			
		} else {
			float sum = 0;
			for (int i = 0; i < rating.size(); i++) {
				sum += rating.get(i);
			}
			this.avgRating = (sum / rating.size());
			return this.avgRating;
		}
		
	}
	public float getAvgRating() {
		return this.avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public void setRating(ArrayList<Float> rating) {
		this.rating = rating;
	}


}