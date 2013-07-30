package com.kossel.webapp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kossel.dao.UserDao;
import com.kossel.model.User;
 
 
public class CppAuthoritiesUserDetailsServiceImpl implements UserDetailsService {
 
  //  private final transient Log log = LogFactory.getLog(CppAuthoritiesPopulator.class);
    private UserDao userDao;
     
    @Autowired   
    public CppAuthoritiesUserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
     
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        User user = (User) userDao.loadUserByUsername(username);
        return user;
    }
}
