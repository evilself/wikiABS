package com.americanbanksystems.wiki.security.dao.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.28.2014
 * 
 */

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    //protected final Log logger = LogFactory.getLog(getClass());
     
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
         
    	
    	System.out.println("HERE DAMN IN");
        //logger.info("loadUserByUsername username="+username);
         
        if(!username.equals("pankaj")){
            throw new UsernameNotFoundException(username + " not found");
        }
         
        //creating dummy user details, should do JDBC operations
        return new UserDetails() {
             
            private static final long serialVersionUID = 2059202961588104658L;
 
            @Override
            public boolean isEnabled() {
                return true;
            }
             
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }
             
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }
             
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }
             
            @Override
            public String getUsername() {
                return username;
            }
             
            @Override
            public String getPassword() {
                return "pankaj123";
            }
             
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
                auths.add(new SimpleGrantedAuthority("Admin"));
                return auths;
            }
        };
    }
 
}
