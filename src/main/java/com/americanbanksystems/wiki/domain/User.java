package com.americanbanksystems.wiki.domain;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User extends BaseEntity implements Serializable {	
	private static final long serialVersionUID = 7031136505427336149L;
	
	protected Long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	public Long getId() {
	   return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	@Size(max=64)
	protected String firstName;
	
	@NotEmpty
	@Size(max=64)
	protected String lastName;
	
	@NotEmpty
	@Size(max=32)
	protected String userName;
	
	@NotEmpty	
	protected String password;
	
	@Email
	protected String email;
	protected boolean enabled = true;	
	protected UserRole role;
	
	protected SecurityInfo securityInfo;	
	

	protected List<Article> createdArticles;	

	public User() {
		
	}
	
	public User(String fn, String ln, String un, String pass, UserRole role) {
		this.firstName = fn;
		this.lastName = ln;
		this.userName = un;
		this.password = pass;
		this.role = role;
	}
	
	public User(String fn, String ln, String un, String pass, UserRole role, SecurityInfo securityInfo) {
		this.firstName = fn;
		this.lastName = ln;
		this.userName = un;
		this.password = pass;
		this.role = role;
		this.securityInfo=securityInfo;
	}
	
	@OneToMany(mappedBy="createdByUser")
	public List<Article> getCreatedArticles() {
		return createdArticles;
	}

	public void setCreatedArticles(List<Article> createdArticles) {
		this.createdArticles = createdArticles;
	}	

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="ENABLED", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ROLE_ID")
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SECURITY_INFO_ID")
	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", email=" + email + "]";
	}	
}
