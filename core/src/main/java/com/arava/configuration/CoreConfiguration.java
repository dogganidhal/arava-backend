package com.arava.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Nidhal Dogga
 * Date : 18/01/2020 16:49
 * All rights reserved.
 */


@Slf4j
@Configuration
public class CoreConfiguration {

  @Value("${elasticsearch.host}")
  public String esHost;

  @Value("${elasticsearch.port}")
  public Integer esPort;

  @Value("${elasticsearch.protocol}")
  public String esProtocol;

  @Value("${elasticsearch.username:}")
  public String esUsername;

  @Value("${elasticsearch.password:}")
  public String esPassword;

  @Bean
  @Primary
  public RestHighLevelClient elasticSearchClient() {
    CredentialsProvider esCredentialsProvider = new BasicCredentialsProvider();
    esCredentialsProvider.setCredentials(AuthScope.ANY,
            new UsernamePasswordCredentials(esUsername, esPassword));
    RestClientBuilder builder = RestClient
            .builder(new HttpHost(esHost, esPort, esProtocol))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                    .setDefaultCredentialsProvider(esCredentialsProvider)
            );
    return new RestHighLevelClient(builder);
  }

}
