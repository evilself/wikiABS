package com.americanbanksystems.wiki.domain;

/*
 * 
 *  @author BorisM 
 *  @date   10.26.2014
 * 
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="product")
public class Product extends BaseEntity implements Serializable {	
	private static final long serialVersionUID = 4881203836824067361L;
	
	protected Long id;
	
	@NotEmpty
	@Size(min=1, max=40)
	protected String productName;
	protected String productIdentity;
	protected boolean custom;	

	@NotEmpty
	@Size(min=1, max=124)
	protected String description;	

	public Product() {
		
	}
	
	public Product(String productName) {
		this.productName = productName;
		
	}
	
	@Column(name="CUSTOM")
	public boolean getCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}
	
	@Column(name="PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="PRODUCT_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="PRODUCT_IDENTITY")
	public String getProductIdentity() {
		return productIdentity;
	}

	public void setProductIdentity(String productIdentity) {
		this.productIdentity = productIdentity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (custom ? 1231 : 1237);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((productIdentity == null) ? 0 : productIdentity.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
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
		Product other = (Product) obj;
		if (custom != other.custom)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (productIdentity == null) {
			if (other.productIdentity != null)
				return false;
		} else if (!productIdentity.equals(other.productIdentity))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productIdentity="
				+ productIdentity + ", custom=" + custom + ", description="
				+ description + "]";
	}	
}
