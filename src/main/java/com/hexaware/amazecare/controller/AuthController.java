//package com.hexaware.amazecare.controller;
//
//import java.security.Principal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hexaware.amazecare.JwtUtil;
//import com.hexaware.amazecare.dto.JwtDto;
//import com.hexaware.amazecare.dto.ResponseMessageDto;
//import com.hexaware.amazecare.exceptions.InvalidUsernameException;
//import com.hexaware.amazecare.model.User;
//import com.hexaware.amazecare.service.UserSecurityService;
//import com.hexaware.amazecare.service.UserService;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired 
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserSecurityService userSecurityService;
//    //resolve d
//    @Autowired
//	private UserService userService;
//
//    @PostMapping("/api/token")
//    public ResponseEntity<?> getToken(@RequestBody User user) {
//        JwtDto dto = new JwtDto();
//        try {
//            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            authenticationManager.authenticate(auth);
//            // now check username if is in db
//            user = (User) userSecurityService.loadUserByUsername(user.getUsername());
//            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
//            dto.setUsername(user.getUsername());
//            dto.setToken(jwt);
//            return ResponseEntity.ok(dto);
//        } catch (AuthenticationException ae) {
//            return ResponseEntity.badRequest().body(ae.getMessage());
//        }
//    }
//    //14-11   auth
//    @GetMapping("/api/hello")
//    public String sayHello(Principal principal) {
//    	String user ="";
//    	if(principal == null)
//    	{
//    		user = "TEMP_USER";
//    	}else {
//    		user = principal.getName();
//    	}
//    	return "api accessed by: " + user;
//    }
//    @PostMapping("/auth/sign-up")
//    public ResponseEntity<?>signUp(@RequestBody User user,ResponseMessageDto dto){
//    	try {
//    		 return ResponseEntity.ok(userService.signUp(user));
//    	}catch(InvalidUsernameException e) {
//    		dto.setMsg(e.getMessage());
//    		return ResponseEntity.badRequest().body(dto);
//    	}
//    }
//}
//
package com.hexaware.amazecare.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.JwtUtil;
import com.hexaware.amazecare.dto.JwtDto;
import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.UserSecurityService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    private JwtUtil jwtUtil;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/token")
   
    public ResponseEntity<?> getToken(@RequestBody User user) {
        JwtDto dto = new JwtDto();
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(auth);
            user = (User) userSecurityService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
            dto.setUsername(user.getUsername());
            dto.setToken(jwt);
            return ResponseEntity.ok(dto);
        } catch (AuthenticationException ae) {
            return ResponseEntity.badRequest().body(ae.getMessage());
        }
    }

    @GetMapping("/api/hello")
    public String sayHello(Principal principal) {
        String user = principal == null ? "TEMP_USER" : principal.getName();
        return "api accessed by: " + user;
    }

    @PostMapping("/auth/sign-up")
    
    public ResponseEntity<?> signUp(@RequestBody User user, ResponseMessageDto dto) {
        try {
            return ResponseEntity.ok(userService.signUp(user));
        } catch (InvalidUsernameException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
        catch (Exception e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
    }
    @GetMapping("/auth/user")
    public ResponseEntity<?> getUserDetails(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        User user = (User) userSecurityService.loadUserByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }
}
