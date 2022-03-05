package com.saapi.saapi.model;
import java.util.Set;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"} )} )
public class Users {
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private int id;
     
     @Column(name="fname")
     private String fname;
     
     @Column(name="lname")
     private String lname;
     
     @Column(name="username")
     private String username; 

	 @Column(name="email")
     private String email;
     
     @Column(name="password")
     private String password;
     
     @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinTable(name = "user_roles", 
     joinColumns = @JoinColumn(name = "user_id",  referencedColumnName = "id"), 
     inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
     
     private Set<Role> roles; 
     
     public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
