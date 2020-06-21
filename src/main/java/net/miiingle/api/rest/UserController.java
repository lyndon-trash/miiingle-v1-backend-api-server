package net.miiingle.api.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("me")
public class UserController {

    @GetMapping
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt principal) {
        Map<String, String> map = new Hashtable<>();
        map.put("organization", principal.getClaimAsString("custom:organization_id"));
        map.put("username", principal.getClaimAsString("cognito:username"));
        return Collections.unmodifiableMap(map);
    }
}
