package me.loda.spring.springsecurityhibernate.user;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/30/2019
 * Github: https://github.com/loda-kun
 */
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Kiểm tra xem user có tồn tại trong database không?
        String pw = encoder.encode("123456");
        User userLogin = new User(1l,"hoat", pw);

        User user = new User();
        if (username.equals(userLogin.getUsername()))
            user = userLogin;

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }


}
