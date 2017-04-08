package ar.edu.itba.paw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.UserDao;
import ar.edu.itba.paw.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User findById(int id) {
		return userDao.findById(id);
	}

	public User create(String username, String password) {
		return userDao.create(username, password);
	}

	public User findByName(String username) {
		return userDao.findByName(username);
	}

}
