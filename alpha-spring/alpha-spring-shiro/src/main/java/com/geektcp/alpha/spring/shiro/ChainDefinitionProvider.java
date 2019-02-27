package com.geektcp.alpha.spring.shiro;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chengmo on 2018/1/4.
 */
public class ChainDefinitionProvider {

    @Transactional
    public Map<String, String> getAllRolesPermissions() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-ui.html#/**", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        map.put("/auth/login", "anon");
        map.put("/auth/logout", "anon");

        // Subject.login(), isAuthenticated()==true
        //map.put("/**", "authc");
        // isRemembered()==true or isAuthenticated()==true
        map.put("/**", "user");
        return map;
    }
}
