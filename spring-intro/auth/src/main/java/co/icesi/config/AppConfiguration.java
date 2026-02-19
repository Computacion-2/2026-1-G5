package co.icesi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import co.icesi.repositories.RoleRepository;
import co.icesi.services.RoleService;

@Configuration
@ComponentScan(basePackages = "co.icesi")
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer configur(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private RoleRepository repository;

    public RoleRepository createRoleRepository(){
        return new RoleRepository();
    }

    @Bean
    public RoleService service(){
        RoleService service = new RoleService();
        service.setRoleRepository(repository);
        return service;
    }
    
}
