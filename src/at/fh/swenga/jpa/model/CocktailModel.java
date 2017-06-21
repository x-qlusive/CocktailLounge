package at.fh.swenga.jpa.model;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Cocktail")
public class CocktailModel implements java.io.Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private Float alc;

	@ManyToOne(cascade = CascadeType.PERSIST)
	Type type;
	/*
	 * @JoinTable( name="EMP_PROJ", joinColumns={
	 * 
	 * @JoinColumn(name="EMP_ID", referencedColumnName="ID")},
	 * inverseJoinColumns={@JoinColumn(name="PROJ_ID",
	 * referencedColumnName="ID")})
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Ingredient> ingredients;

	@ElementCollection(targetClass = Float.class, fetch=FetchType.EAGER)
	private List<Float> rating;

	private float avgRating;
	
	private String stringIngredients;

	@Version
	long version;

	public CocktailModel() {
	}

	public CocktailModel(String name, Float alc) {
		super();
		this.name = name;
		this.alc = alc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAlc() {
		return alc;
	}

	public void setAlc(Float alc) {
		this.alc = alc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		if (ingredients == null) {
			ingredients = new ArrayList<Ingredient>();
		}
		ingredients.add(ingredient);
	}

	public List<Float> getRating() {
		return rating;
	}

	public void setRating(Float newRating) {
		rating.add(newRating);
	}

	public float getAvgCalc() {
		if (rating.size() == 0) {
			return 0;
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
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public String getStringIngredients() {
		return stringIngredients;
	}

	public void setStringIngredients(String stringIngredients) {
		this.stringIngredients = stringIngredients;
	}

	public void setRating(List<Float> rating) {
		this.rating = rating;
	}
	
	

}