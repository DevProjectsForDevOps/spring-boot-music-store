package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping ("/api/users")
@CrossOrigin (origins = "http://localhost:4200")
public class UserController
{
        private final UserService userService;

        private final UserAuthentication userAuthentication;

        @Autowired
        public UserController ( UserService userService, UserAuthentication userAuthentication )
        {
                this.userService = userService;
                this.userAuthentication = userAuthentication;
        }

        @PostMapping ("/register")
        @PreAuthorize ("isAnonymous()")
        public void addUser ( @RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult )
        {
                if ( bindingResult.hasErrors() )
                        return;
                userService.create( userDTO );
        }

        @RequestMapping ("/login")
        @PreAuthorize ("isAnonymous()")
        public boolean login ( @RequestBody UserDTO userDTO )
        {
                return userService.isLoginCorrect( userDTO.getUsername(), userDTO.getPassword() );
        }

        @RequestMapping ("/user")
        public Principal user ( HttpServletRequest request )
        {
                String authToken = request.getHeader( "Authorization" )
                        .substring( "Basic".length() ).trim();
                return () -> new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];
        }

        @GetMapping ("/admin")
        @PreAuthorize( "isAuthenticated()" )
        public Boolean hasAdminRole ( @RequestHeader ("Authorization") String auth )
        {
                return userAuthentication.hasAdminRole( auth );
        }
}