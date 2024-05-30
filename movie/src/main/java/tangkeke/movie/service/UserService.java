package tangkeke.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tangkeke.movie.entity.User;
import tangkeke.movie.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public void register(User user){
        userMapper.insert(user);
    }
}
