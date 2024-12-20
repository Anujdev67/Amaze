//package com.hexaware.amazecare;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
///*
// * This class will bring JWT filter to Spring and integrate everything.
// * Along with this, this class will also point Spring to User table
// * */
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.hexaware.amazecare.JwtFilter;
//
//@Configuration
//public class SecurityConfig {
//@Autowired
//private UserSecurityService userSecurityService;
//
//
//@Autowired
//private JwtFilter jwtFilter;
//@Bean
//SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//	http
//	
//	 .csrf((csrf) -> csrf.disable())
//	 .authorizeHttpRequests(authorize -> authorize
////			 	.requestMatchers(HttpMethod.GET, "/auth/login").authenticated()
//			 	.requestMatchers(HttpMethod.GET,"/api/token").permitAll()
//			 	.requestMatchers(HttpMethod.POST, "/auth/switch-status/{id}").hasRole("EXECUTIVE")
//			 	.requestMatchers(HttpMethod.GET, "/api/hello").hasRole("CUSTOMER")                              
//				.requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()  
//			.anyRequest().permitAll()
//		) 
//		.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		
//	   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	 
//	return http.build();
//}
//
//
//@Bean
//PasswordEncoder getEncoder() {
//	return new BCryptPasswordEncoder();
//}
//
//@Bean
//AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//	return config.getAuthenticationManager();
//}
//
//@Bean
//DaoAuthenticationProvider authenticationProvider(){
//	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	authenticationProvider.setUserDetailsService(userSecurityService);
//	authenticationProvider.setPasswordEncoder(getEncoder());
//	return authenticationProvider;
//}
//}
///*
// * Spring needs a middleware to understand and decode jwt token 
// * 
// */
//
//package com.hexaware.amazecare;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.hexaware.amazecare.service.UserSecurityService;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private UserSecurityService userSecurityService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(HttpMethod.GET, "/api/token").permitAll()
//                .requestMatchers(HttpMethod.POST, "/auth/switch-status/{id}").hasRole("EXECUTIVE")
//                .requestMatchers(HttpMethod.GET, "/api/hello").hasRole("CUSTOMER")
//                .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
//                .anyRequest().permitAll()
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userSecurityService);
//        authenticationProvider.setPasswordEncoder(getEncoder());
//        return authenticationProvider;
//    }
//}




//package com.hexaware.amazecare;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.hexaware.amazecare.service.UserSecurityService;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private UserSecurityService userSecurityService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(HttpMethod.GET, "/api/token").permitAll()
//                .requestMatchers(HttpMethod.POST, "/auth/switch-status/{id}").hasRole("EXECUTIVE")
//                .requestMatchers(HttpMethod.GET, "/api/hello").hasRole("LAB_OPERATOR")
//                .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
//                .anyRequest().permitAll()
////                .anyRequest().authenticated()   for all and also .requestMatchers(HttpMethod.GET, "/api/token").permitAll() tokengener8
//                //.requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll() 
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userSecurityService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//}

// 26-11

//package com.hexaware.amazecare;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.hexaware.amazecare.service.UserSecurityService;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private UserSecurityService userSecurityService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(HttpMethod.POST, "/api/token").permitAll()
//                .requestMatchers(HttpMethod.POST, "/auth/switch-status/{id}").hasRole("EXECUTIVE")
//                .requestMatchers(HttpMethod.GET, "/api/hello").hasRole("LAB_OPERATOR")
//                .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userSecurityService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//}


// this is 15-12-2024 

package com.hexaware.amazecare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.amazecare.service.UserSecurityService;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) // enabled cors using customizer
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/api/token").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/switch-status/{id}").hasRole("EXECUTIVE")
                .requestMatchers(HttpMethod.GET, "/api/hello").hasRole("LAB_OPERATOR")
                .requestMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                .requestMatchers(HttpMethod.GET, "/auth/user").authenticated()
                .requestMatchers(HttpMethod.POST, "/laboperator/simple-report/create").hasAuthority("ROLE_LAB_OPERATOR")
                .requestMatchers(HttpMethod.GET, "/laboperator/simple-report/all").hasAuthority("ROLE_LAB_OPERATOR")
                .requestMatchers(HttpMethod.GET, "/laboperator/simple-report/{id}").hasAuthority("ROLE_LAB_OPERATOR")
                .requestMatchers(HttpMethod.GET, "/laboperator/testandscans/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/laboperator/**").hasAuthority("ROLE_LAB_OPERATOR")
                .requestMatchers(HttpMethod.POST, "/laboperator/upload/lab/image/{id}").authenticated()
                .requestMatchers(HttpMethod.GET, "/laboperator/get/lab/image/{id}").authenticated()
                .requestMatchers(HttpMethod.GET,"/laboperator/patient/all").hasAuthority("ROLE_LAB_OPERATOR")
                .requestMatchers(HttpMethod.GET,"/laboperator/testandscans/all").hasAuthority("ROLE_LAB_OPERATOR")
                
                .anyRequest().permitAll()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userSecurityService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}


