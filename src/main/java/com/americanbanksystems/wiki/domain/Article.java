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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="article")
public class Article extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1213442486013519868L;
	
	protected Long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ARTICLE_ID")
	public Long getId() {
	   return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	@Size(min=1, max=124)
	protected String title;	
	
	@NotEmpty	
	protected String description;	
	
	@NotEmpty	
	@Size(min=1, max=124)
	protected String tag;			

	protected Product product;	
	
	protected User createdByUser;
	protected User modifiedByUser;
	protected List<Attachment> attachments;
	
	@OneToMany(mappedBy="article")
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}	

	public Article() {
		
	}
	
	public Article(String title, String desc, User user) {
		this.title = title;
		this.description = desc;	
		this.createdByUser = user;
	}	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY_USER_ID")
	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}
	
	@Column(name="MODIFIED_BY_USER_ID")
	public User getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}	

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="tag")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdByUser == null) ? 0 : createdByUser.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((modifiedByUser == null) ? 0 : modifiedByUser.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Article other = (Article) obj;
		if (createdByUser == null) {
			if (other.createdByUser != null)
				return false;
		} else if (!createdByUser.equals(other.createdByUser))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (modifiedByUser == null) {
			if (other.modifiedByUser != null)
				return false;
		} else if (!modifiedByUser.equals(other.modifiedByUser))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", description=" + description
				+ ", tag=" + tag + ", product=" + product + ", createdByUser="
				+ createdByUser + ", modifiedByUser=" + modifiedByUser + "]";
	}	
}
