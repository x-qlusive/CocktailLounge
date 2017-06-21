package at.fh.swenga.jpa.model;
 
import java.util.List;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
 
@Entity
public class Ingredient {
	
	private int id;
 
	private String name;
 
	
	private List<CocktailModel> cocktails;
 
	public Ingredient() {
	}
 
	public Ingredient(String name) {
		super();
		this.name = name;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(mappedBy = "ingredients",fetch=FetchType.EAGER)
	public List<CocktailModel> getCocktails() {
		return cocktails;
	}
 
	public void setCocktails(List<CocktailModel> cocktails) {
		this.cocktails = cocktails;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
}