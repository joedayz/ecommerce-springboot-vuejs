package pe.joedayz.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.joedayz.ecommerce.dto.user.SignInDto;
import pe.joedayz.ecommerce.dto.user.SignInResponseDto;
import pe.joedayz.ecommerce.dto.user.SignUpDto;
import pe.joedayz.ecommerce.dto.user.SignUpResponseDto;
import pe.joedayz.ecommerce.exceptions.AuthenticationFailException;
import pe.joedayz.ecommerce.exceptions.CustomException;
import pe.joedayz.ecommerce.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")  //POST http://localhost:8080/user/signup
    public SignUpResponseDto signUp(@RequestBody SignUpDto signUpDto) throws CustomException {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }
}
