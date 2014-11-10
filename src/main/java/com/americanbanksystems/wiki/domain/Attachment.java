package com.americanbanksystems.wiki.domain;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="attachment")
public class Attachment extends BaseEntity implements Serializable{	
	private static final long serialVersionUID = -8498517146565952292L;
	
	protected Long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ATTACHMENT_ID")
	public Long getId() {
	   return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	protected String attachmentTitle;
	protected String actualFilename;
	protected String attachmentType;
	protected String contentType;	
	protected Article article;
	protected byte[] attachment;

	public Attachment() {
		
	}
	
	public Attachment(String attachmentTitle, String actualFilename, String attachmentType, byte[] attachment) {
		this.attachmentTitle = attachmentTitle;
		this.actualFilename = actualFilename;	
		this.attachmentType = attachmentType;	
		this.attachment = attachment;	
	}

	@Column(name="ATTACHMENT_TITLE")
	public String getAttachmentTitle() {
		return attachmentTitle;
	}

	public void setAttachmentTitle(String attachmentTitle) {
		this.attachmentTitle = attachmentTitle;
	}

	@Column(name="ACTUAL_FILENAME")
	public String getActualFilename() {
		return actualFilename;
	}

	public void setActualFilename(String actualFilename) {
		this.actualFilename = actualFilename;
	}

	@Column(name="ATTACHMENT_TYPE")
	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ARTICLE_ID")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Column(name="ATTACHMENT")
	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	@Column(name="CONTENT_TYPE")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actualFilename == null) ? 0 : actualFilename.hashCode());
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + Arrays.hashCode(attachment);
		result = prime * result
				+ ((attachmentTitle == null) ? 0 : attachmentTitle.hashCode());
		result = prime * result
				+ ((attachmentType == null) ? 0 : attachmentType.hashCode());
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
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
		Attachment other = (Attachment) obj;
		if (actualFilename == null) {
			if (other.actualFilename != null)
				return false;
		} else if (!actualFilename.equals(other.actualFilename))
			return false;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (!Arrays.equals(attachment, other.attachment))
			return false;
		if (attachmentTitle == null) {
			if (other.attachmentTitle != null)
				return false;
		} else if (!attachmentTitle.equals(other.attachmentTitle))
			return false;
		if (attachmentType == null) {
			if (other.attachmentType != null)
				return false;
		} else if (!attachmentType.equals(other.attachmentType))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentTitle=" + attachmentTitle
				+ ", actualFilename=" + actualFilename + ", attachmentType="
				+ attachmentType + ", contentType=" + contentType
				+ ", article=" + article + ", attachment="
				+ Arrays.toString(attachment) + "]";
	}	
	
}
