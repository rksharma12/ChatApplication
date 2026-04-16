package com.example.demo.controllers;

import com.example.demo.dtos.LoginResponse;
import com.example.demo.dtos.LoginUserDto;
import com.example.demo.dtos.RegisterDtoUser;
import com.example.demo.dtos.SignUpRespone;
import com.example.demo.entity.User;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RequestMapping("/auth")
    @RestController
    public class AuthenticationController {
        private final JwtService jwtService;

        private final AuthenticationService authenticationService;

        public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
            this.jwtService = jwtService;
            this.authenticationService = authenticationService;
        }

        @PostMapping("/signup")

        public ResponseEntity<SignUpRespone> register(@RequestBody RegisterDtoUser registerUserDto) {
            try {
                User registeredUser = authenticationService.signup(registerUserDto);
                return ResponseEntity.ok(new SignUpRespone());
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body(new SignUpRespone().setError(e.getMessage()).setErr(true));
            }
        }


        @PostMapping("/login")
        public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
            try {
                User authenticatedUser = authenticationService.authenticate(loginUserDto);

                String jwtToken = jwtService.generateToken(authenticatedUser);

                LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
                return ResponseEntity.ok(loginResponse);
            }
            catch (UsernameNotFoundException e) {
                LoginResponse loginResponse = new LoginResponse().setExc(true).setError("User not found");
                return ResponseEntity.status(404).body(loginResponse);

            } catch (BadCredentialsException e) {
                LoginResponse loginResponse = new LoginResponse().setExc(true).setError("User not found");
                return ResponseEntity.status(401).body(loginResponse);
            } catch (Exception e) {
                e.printStackTrace();
                String eror=e.getMessage();
                LoginResponse loginResponse = new LoginResponse().setExc(true).setError(eror);
                return ResponseEntity.status(401).body(loginResponse);
            }
        }
    }

