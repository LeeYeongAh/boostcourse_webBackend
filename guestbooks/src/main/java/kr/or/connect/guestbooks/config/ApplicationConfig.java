package kr.or.connect.guestbooks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.guestbooks.dao",  "kr.or.connect.guestbooks.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}