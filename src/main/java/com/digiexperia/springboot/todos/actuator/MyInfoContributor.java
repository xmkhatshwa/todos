package com.digiexperia.springboot.todos.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyInfoContributor implements InfoContributor {

    @Value("${author.name}")
    private String AUTHOR;

    @Value("${author.email}")
    private String EMAIL;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> authorDetails = new HashMap<>();
        authorDetails.put("name", AUTHOR);
        authorDetails.put("email", EMAIL);

        builder.withDetail("Author", authorDetails);

    }
}
