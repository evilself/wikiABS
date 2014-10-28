package com.americanbanksystems.wiki.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.exception.UserDeleteException;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private UserDao userDao;
	 
	@Autowired
	public void setUserDao(UserDao userDao) {
	    this.userDao = userDao;
	}

	@RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userDao.list();
        model.addAttribute("users", users);
 
        return "users/list";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") long id, Model model) {
	    User user = userDao.findUser(id);
	    model.addAttribute("user", user);
	 
	    return "users/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateEmployee(@PathVariable("id") long id, User user) {
		user.setId(id);
		userDao.updateEntity(user);
	 
	    return "redirect:/users";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
	    User toDelete = userDao.findUser(id);
	    boolean wasDeleted = userDao.removeEntity(toDelete);
	 
	    if (!wasDeleted) {
	        throw new UserDeleteException(toDelete);
	    }
	 
	    // everything OK, see remaining employees
	    return "redirect:/users";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	public String createUserForm(Model model) {
	    model.addAttribute("user", new User());
	    return "users/new";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addUser(User user) {
		userDao.addEntity(user);
	 
	    return "redirect:/users";
	}
	
	
	@ExceptionHandler(UserDeleteException.class)
	public ModelAndView handleDeleteException(UserDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("user", e.getUser());
	    return new ModelAndView("users/delete-error", model);
	}
	
}
