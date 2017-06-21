package at.fh.swenga.jpa.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.jpa.dao.CocktailDao;
import at.fh.swenga.jpa.dao.CommentDao;
import at.fh.swenga.jpa.dao.IngredientDao;
import at.fh.swenga.jpa.dao.TypeDao;
import at.fh.swenga.jpa.dao.UserDao;
import at.fh.swenga.jpa.model.CocktailModel;
import at.fh.swenga.jpa.model.Comment;
import at.fh.swenga.jpa.model.Ingredient;
import at.fh.swenga.jpa.model.Type;
 
@Controller
public class CocktailController {
 
	@Autowired
	CocktailDao cocktailDao;
 
	@Autowired
	TypeDao typeDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	IngredientDao ingredientDao;
	
	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
 
		List<CocktailModel> cocktails = cocktailDao.getCocktails();
		List<Type> types = typeDao.getTypes();
		List<Ingredient> ingredients = ingredientDao.getIngredients();
 
		model.addAttribute("cocktails", cocktails);
		model.addAttribute("types", types);
		model.addAttribute("ingredients",ingredients);
		return "index";
	}
	
	@RequestMapping(value = "/addCocktail", method = RequestMethod.GET)
	public String showAddCocktailForm(Model model) {
		return "editCocktail";
	}
	
	@RequestMapping(value = "/addCocktail", method = RequestMethod.POST)	
	public String addCocktail(@Valid @ModelAttribute CocktailModel newCocktailModel, BindingResult bindingResult,
			Model model){
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/list";
		}
 
		CocktailModel cocktail = cocktailDao.getCocktail(newCocktailModel.getId());
 
		if (cocktail != null) {
			model.addAttribute("errorMessage", "cocktail already exists!<br>");
		} else {
			cocktailDao.persist(newCocktailModel);
			model.addAttribute("message", "New cocktail " + newCocktailModel.getId() + " added.");
		}
 
		return "forward:/list";
	}
	
	@RequestMapping(value = "/showCocktail", method = RequestMethod.POST)
	public String addComment(@Valid @ModelAttribute Comment newComment, Model model, @RequestParam int id, HttpServletRequest request, @RequestParam float rating){
		if(rating!=0){
			cocktailDao.getCocktailById(id).addRating(rating);
		}
		if(request.isUserInRole("USER")){
			newComment.setCommentOwner(userDao.findByUsername(request.getRemoteUser()).get(0));
			newComment.setBelongsTo(cocktailDao.getCocktailById(id));
			commentDao.merge(newComment);
		}
		else
		{
			throw new AccessDeniedException("You have to be logged in order to Comment a Cocktail");
		}
		model.addAttribute("cocktail", cocktailDao.getCocktailById(id));
		return "forward:/list";
	}
	
	@RequestMapping(value = "/showCocktail", method = RequestMethod.GET)
	public String showCocktail(Model model, @RequestParam int id){
		model.addAttribute("cocktail", cocktailDao.getCocktailById(id));
		return "showCocktail";
	}
	
	
 
	@RequestMapping("/fillCocktailList")
	@Transactional
	public String fillData(Model model) {

  
		Type typeLD = typeDao.getType("Longdring");
		if (typeLD==null) typeLD = new Type("Longdrink");	
		
		Type typeST = typeDao.getType("Shot");
		if (typeST==null) typeST = new Type("Shot");
 
		Type typeCT = typeDao.getType("Cocktail");
		if (typeCT==null) typeCT = new Type("Cocktail");	
 
 
		Ingredient ingredient1= ingredientDao.getIngredient("Kahlua");
		if (ingredient1== null) ingredient1=new Ingredient("Kahlua");
 
		Ingredient ingredient2= ingredientDao.getIngredient("Sparkling Water");
		if (ingredient2== null) ingredient2=new Ingredient("Sparkling Water");
 
		Ingredient ingredient3= ingredientDao.getIngredient("Cola");
		if (ingredient3== null) ingredient3=new Ingredient("Cola");
 
		Ingredient ingredient4= ingredientDao.getIngredient("Rum");
		if (ingredient4== null) ingredient4=new Ingredient("Rum");
  
		CocktailModel p1 = new CocktailModel("B52", (float) 25.0);
		p1.setType(typeST);
 
		p1.addIngredient(ingredient1);
		p1.addIngredient(ingredient4);

 
		cocktailDao.persist(p1);
 
		CocktailModel p2 = new CocktailModel("Mochito", (float) 25.0);
		p2.setType(typeCT);
 
		p2.addIngredient(ingredient4);
		p2.addIngredient(ingredient2);

 
 
		cocktailDao.persist(p2);
 
		CocktailModel p3 = new CocktailModel("Caipirinha",(float) 25.0);
		p3.setType(typeCT);
 
		p3.addIngredient(ingredient2);
		p3.addIngredient(ingredient4);
 
 
		cocktailDao.persist(p3);
		
		CocktailModel p4 = new CocktailModel("Captain Cola",(float) 25.0);
		p4.setType(typeLD);
 
		p4.addIngredient(ingredient3);
		p4.addIngredient(ingredient4);
 
 
		cocktailDao.persist(p4);
 
		return "forward:list";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String handleLogin() {	
		return "login";
	}	
 
 
	@RequestMapping("/searchCocktails")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("cocktails", cocktailDao.searchCocktails(searchString));
		model.addAttribute("types", typeDao.getTypes());
		model.addAttribute("ingredients",ingredientDao.getIngredients());

		return "index";
	}
 
	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		cocktailDao.delete(id);
 
		return "forward:list";
	}
 
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
 
	}
}