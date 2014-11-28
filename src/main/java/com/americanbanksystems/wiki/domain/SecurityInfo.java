package com.americanbanksystems.wiki.domain;

/*
 * 
 *  @author BorisM 
 *  @date   11.28.2014
 * 
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="security_info")
public class SecurityInfo implements Serializable {	
	private static final long serialVersionUID = -5721439804825962537L;
	
	protected Long id;
	protected String securityQuestion;
	protected String securityAnswer;
	protected String username;	

	public SecurityInfo() {		
	}
	
	public SecurityInfo(String question, String answer) {
		this.securityQuestion=question;
		this.securityAnswer=answer;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SECURITY_INFO_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SECURITY_QUESTION")
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	@Column(name="SECURITY_ANSWER")
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((securityAnswer == null) ? 0 : securityAnswer.hashCode());
		result = prime
				* result
				+ ((securityQuestion == null) ? 0 : securityQuestion.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		SecurityInfo other = (SecurityInfo) obj;
		if (securityAnswer == null) {
			if (other.securityAnswer != null)
				return false;
		} else if (!securityAnswer.equals(other.securityAnswer))
			return false;
		if (securityQuestion == null) {
			if (other.securityQuestion != null)
				return false;
		} else if (!securityQuestion.equals(other.securityQuestion))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecurityInfo [securityQuestion=" + securityQuestion
				+ ", securityAnswer=" + securityAnswer + ", username="
				+ username + "]";
	}	
}
