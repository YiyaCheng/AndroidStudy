package com.Interface;

import java.util.ArrayList;
import java.util.HashMap;

import com.bean.User;

public interface UserInterface {

	public int insert(User user);
	public int deleteById(int userid);
	public User getUserById(int userid);
	public  int update(User user);
	public ArrayList<HashMap<String,Object>> getAllUsers();
	public ArrayList<HashMap<String, Object>> getUsersByName(String name);
}
