package com.superfight;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {


  @Bean public DataSource dataSource() {

    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:mysql://localhost/superDB");
    dataSource.setUsername("root");
    dataSource.setPassword("");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;

  }

  @Bean
  public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }
}
