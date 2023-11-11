package com.cos.oauth;

import com.cos.auth.PrincipalDetails;
import com.cos.model.User;
import com.cos.oauth.provider.FacebookUserInfo;
import com.cos.oauth.provider.GoogleUserInfo;
import com.cos.oauth.provider.NaverUserInfo;
import com.cos.oauth.provider.OAuth2UserInfo;
import com.cos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    // 함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String password = bCryptPasswordEncoder.encode("password");
        String username = provider + "_" + userRequest.getClientRegistration().getClientId();

        OAuth2UserInfo UserInfo = null;
        if(provider.equals("google")) UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        else if(provider.equals("facebook")) UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        else if(provider.equals("naver")) UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));

        User userEntity =  userRepository.findByusername(username);
        if(userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(UserInfo.geteEamil())
                    .role("ROLE_USER")
                    .provider(provider)
                    .providerId(UserInfo.getProviderId())
                    .build();
            userRepository.save(userEntity);
        }


        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
