package com.arava.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:19
 * All rights reserved.
 */

@Slf4j
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
@EnableCaching
public class AdminApi {

  public static void main(String[] args) {
    SpringApplication.run(AdminApi.class, args);
  }

}
