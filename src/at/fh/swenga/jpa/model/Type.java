package at.fh.swenga.jpa.model;
 
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;
 
@Entity
public class Type {
 
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	private String name;
 
	@OneToMany(mappedBy="type",fetch=FetchType.LAZY)
	@OrderBy("name, alc")
    private Set<CocktailModel> cocktails;
 
	@Version
	long version;
 
 
    public Type() {
		// TODO Auto-generated constructor stub
    }
 
	public Type(String name) {
		super();
		this.name = name;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Set<CocktailModel> getCocktails() {
		return cocktails;
	}
 
	public void setCocktails(Set<CocktailModel> cocktails) {
		this.cocktails = cocktails;
	}
 
	public void addCocktail(CocktailModel cocktail) {
		if (cocktails==null) {
			cocktails= new HashSet<CocktailModel>();
		}
		cocktails.add(cocktail);
	}
 
 
}