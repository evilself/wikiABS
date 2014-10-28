package com.americanbanksystems.wiki.web.helpers;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service //NOTE SERVICE here
public class FileUpload {
 
	protected MultipartFile file;
	//getter and setter methods
	
	protected List<MultipartFile> uploadFiles;

	public List<MultipartFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<MultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}	
 
}