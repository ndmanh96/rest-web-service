package com.manhcode.rest.demo.dao;

import com.manhcode.rest.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCout=3;
    static {
        users.add(new User(1,"Manh",new Date()));
        users.add(new User(2,"Manh2",new Date()));
        users.add(new User(3,"Manh3",new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id) {
        Iterator<User> iteuser = users.iterator();
        while(iteuser.hasNext()) {
            User user = iteuser.next();
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iteuser = users.iterator();
        while(iteuser.hasNext()) {
            User user = iteuser.next();
            if(user.getId() == id) {
                iteuser.remove();
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCout);
            users.add(user);
            return user;
        }
        //update
        Iterator<User> iteuser = users.iterator();
        while(iteuser.hasNext()) {
            User us = iteuser.next();
            if(us.getId() == user.getId()) {
                us.setName(user.getName());
                us.setBirthDate(user.getBirthDate());
                return us;
            }
        }

        return null;
    }
}
