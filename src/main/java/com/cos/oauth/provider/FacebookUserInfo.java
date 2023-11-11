package com.cos.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

    Map<String,Object> attributes;
    public FacebookUserInfo(Map<String,Object> attributes){
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String geteEamil() {
        return (String)attributes.get("email");
    }

}
