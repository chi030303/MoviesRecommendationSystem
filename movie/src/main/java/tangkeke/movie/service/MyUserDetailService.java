package tangkeke.movie.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tangkeke.movie.entity.User;
import tangkeke.movie.entity.UserExample;
import tangkeke.movie.mapper.UserMapper;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        
        User user = userMapper.selectByExample(userExample).stream().findFirst()
        .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (user.getAccess()) { // 如果 access 为 1，表示用户有权限
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 添加管理员角色
        } else { // 否则，为普通用户
            authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // 添加普通用户角色
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }
}
