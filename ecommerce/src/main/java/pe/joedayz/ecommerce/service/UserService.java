package pe.joedayz.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.ecommerce.config.MessageStrings;
import pe.joedayz.ecommerce.dto.user.SignInDto;
import pe.joedayz.ecommerce.dto.user.SignInResponseDto;
import pe.joedayz.ecommerce.dto.user.SignUpDto;
import pe.joedayz.ecommerce.dto.user.SignUpResponseDto;
import pe.joedayz.ecommerce.exceptions.AuthenticationFailException;
import pe.joedayz.ecommerce.exceptions.CustomException;
import pe.joedayz.ecommerce.model.AuthenticationToken;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Objects;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;


    public SignUpResponseDto signUp(SignUpDto signUpDto) throws CustomException {
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already exists");
        }
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(),
                signUpDto.getEmail(), encryptedPassword);
        try{
            userRepository.save(user);

            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            authenticationService.saveConfirmationToken(authenticationToken);

            return new SignUpResponseDto("success", "user created successfully");
        }catch (Exception e){
            throw new CustomException("Failed to save user ");
        }

    }

    String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(password.getBytes());
        return HexFormat.of().formatHex(digest).toUpperCase();
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, CustomException {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("User not present");
        }
        try{
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("Invalid password");
            }
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthenticationToken token =  authenticationService.getToken(user);

        if(!Objects.nonNull(token)){
            throw new CustomException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
