package co.icesi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import co.icesi.repositories.RoleRepository;
import co.icesi.services.RoleService;

@Configuration
@ComponentScan(basePackages = "co.icesi")
public class AppConfiguration {

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
