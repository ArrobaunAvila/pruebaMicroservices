package com.neoris.pruebamicroservices.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@Component
@Configuration
public class Database {

  @Value("${retorna.registros.query}")
  private String queryMovimentsByDateAndName;

  @Value("${retorna.registros.query.all}")
  private String queryAllMoviments;

  @Value("${spring.database.jdbc.url}")
  private String url_database;

  @Value("${spring.database.username}")
  private String username_database;

  @Value("${spring.database.password}")
  private String password_database;

  @Value("${spring.database.driverClassName}")
  private String driver_class;

  @Bean(name = "masterH2")
  public DataSource buildDatasource(){
  return DataSourceBuilder.create().password(password_database)
          .username(username_database)
          .url(url_database)
          .driverClassName(driver_class)
          .build();
  }

  @Bean(name = "JdbcMaster")
  public JdbcTemplate jdbcTemplate (@Qualifier("masterH2") DataSource dsMaster){
      return new JdbcTemplate(dsMaster);
  }


}
