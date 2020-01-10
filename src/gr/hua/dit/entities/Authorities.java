package gr.hua.dit.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//uniqueConstraints = @UniqueConstraint(
//columnNames = { "role", "user_id" }))

@Entity
@Table(name = "authorities") //, uniqueConstraints = @UniqueConstraint(columnNames = { "authority", "user_id" }))
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_id")
	private int auth_id;

	@Column(name = "authority")
	private String authority;

//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;

	@ManyToMany(mappedBy = "authorities")
    private List<User> users;
	
	public Authorities() {
		super();
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(int auth_id) {
		this.auth_id = auth_id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	

}