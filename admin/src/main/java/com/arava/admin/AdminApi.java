package com.arava.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Nidhal Dogga
 * Date : 04/02/2020 07:19
 * All rights reserved.
 */

@Slf4j
@SpringBootApplication(exclude = SecurityAutoConfiguration.class, scanBasePackages = "com.arava.*")
@EnableJpaAuditing
@EnableCaching
@EnableJpaRepositories(basePackages = "com.arava.*")
@EntityScan(basePackages = "com.arava.*")
@ComponentScan(basePackages = "com.arava.*")
public class AdminApi {

  public static void main(String[] args) {
    SpringApplication.run(AdminApi.class, args);
  }

}
