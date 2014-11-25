package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 11.24.2014
 * 
 * */

//This is a base Controller class. I am using this for common functionality. Like log4j
import org.apache.log4j.Logger;

public interface BaseController {
	static final Logger logger = Logger.getLogger(BaseController.class);
}
