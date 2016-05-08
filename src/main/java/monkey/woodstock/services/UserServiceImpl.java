package monkey.woodstock.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import monkey.woodstock.domain.User;
import monkey.woodstock.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAllUsers() {
        ArrayList<User> users = (ArrayList<User>)userRepository.findAll();
        Collections.sort(users, new OrdenUser());
        return users;
        
    }
    
    public class OrdenUser implements Comparator<User> {
        public int compare(User user1, User user2) {
            return user1.getUsername().compareTo(user2.getUsername());
        }
    }

    @Override
    public User getUserByName(String name){
        return userRepository.findOne(name);
    }

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(User user){
		userRepository.delete(user);
	}
}

