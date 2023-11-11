package com.cos.controller;

import com.cos.auth.PrincipalDetails;
import com.cos.model.User;
import com.cos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login/test")
    public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(authentication.getPrincipal());
        System.out.println(principalDetails.getUser());
        return "login";
    }

    @GetMapping("/login/oauth/test")
    public @ResponseBody String loginOauthTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println((OAuth2User)authentication.getPrincipal());
        // System.out.println(principalDetails.getUser());
        return "login";
    }

    @GetMapping({"/",""})
    public String indexPage(){
        return "index";
    }
    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm() { return "joinForm"; }

    @PostMapping("/join")
    public String join(User user) {
        user.setRole("ROLE_USER");
        String password = user.getPassword();
        String encodePassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodePassword);
        userRepository.save(user);

        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(){

        return "redirect:/";
    }

    @GetMapping("/login/oauth2/code/google")
    public @ResponseBody String oauth_login(){
        return "a";
    }

}
