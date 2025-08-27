package pe.joedayz.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.ecommerce.config.MessageStrings;
import pe.joedayz.ecommerce.exceptions.AuthenticationFailException;
import pe.joedayz.ecommerce.model.AuthenticationToken;
import pe.joedayz.ecommerce.model.User;
import pe.joedayz.ecommerce.repository.TokenRepository;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository repository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken){
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user){
        return repository.findTokenByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken authenticationToken =  repository.findTokenByToken(token);
        if(Objects.nonNull(authenticationToken)){
            if(Objects.nonNull(authenticationToken.getUser())){
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if(!Objects.nonNull(token)){
            throw  new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if(!Objects.nonNull(getUser(token))){
            throw  new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }

    }

}
