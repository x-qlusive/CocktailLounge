package at.fh.swenga.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 8198173157518983615L;
	
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	private Set<Comment> userComments = new HashSet<Comment>(0);

	public User() {
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String username, String password, boolean enabled,
			Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password", nullable = false, length = 60)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addComment(Comment comment){
		this.userComments.add(comment);
	}

	@OneToMany(mappedBy="commentOwner", fetch=FetchType.EAGER)
	public Set<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<Comment> userComments) {
		this.userComments = userComments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	
	

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
