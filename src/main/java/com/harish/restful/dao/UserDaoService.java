package com.harish.restful.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.harish.restful.entity.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 3;
	static {
		
		users.add(new User(1,"Harish", new Date()));
		users.add(new User(11,"Jason", new Date()));
		users.add(new User(13,"Hadoop", new Date()));
		
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user) {
		user.setId(usersCount++);
		users.add(user);
		return user;
	}
	
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		//** While removing elements from collection one cannot use for-each loop as it will throw ConcurrentModificationException one has to use iterator.remove()
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
