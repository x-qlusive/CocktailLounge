package at.fh.swenga.jpa.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne (cascade = CascadeType.PERSIST)
	Type type;
	  /*@JoinTable(
    name="EMP_PROJ",
    joinColumns={
  		  @JoinColumn(name="EMP_ID", referencedColumnName="ID")},
    inverseJoinColumns={@JoinColumn(name="PROJ_ID", referencedColumnName="ID")}) */
	@ManyToMany(cascade=CascadeType.PERSIST)  
	private List<Ingredient> ingredients;
	
	
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
		if (ingredients== null) {
			ingredients= new ArrayList<Ingredient>();
		}
		ingredients.add(ingredient);
	}
 
 
}