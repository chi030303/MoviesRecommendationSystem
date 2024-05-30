package tangkeke.movie.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tangkeke.movie.dto.JWTAuthResponse;
import tangkeke.movie.dto.LoginDto;
import tangkeke.movie.dto.RegisterDto;
import tangkeke.movie.entity.User;
import tangkeke.movie.security.JwtTokenProvider;
import tangkeke.movie.service.AuthService;
import tangkeke.movie.service.UserService;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> loginUser(@RequestBody LoginDto loginDto) {
        // 调用 UserService 中的方法进行用户登录验证
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        try {
        // 根据注册请求创建新用户
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setGenres("");
        user.setTimestamp(Instant.now().getEpochSecond());
        user.setAccess(false);
        user.setFirst(false);
        // 调用 UserService 中的方法将新用户添加到数据库
        userService.register(user);

        return ResponseEntity.ok("User registered successfully");
    //     redirectAttributes.addFlashAttribute("message", "注册成功！");
    // return "redirect:/home";
    } catch (Exception e) {
        e.printStackTrace();
        // 发生异常时，返回错误消息
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
    }
    }
    
}
