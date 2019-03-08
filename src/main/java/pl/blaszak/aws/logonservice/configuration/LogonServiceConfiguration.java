package pl.blaszak.aws.logonservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.blaszak.aws.logonservice.couchbase.repository.UserRepository;
import pl.blaszak.aws.logonservice.service.UserService;

@Configuration
public class LogonServiceConfiguration {

    @Bean
    public UserService getCouchbaseUserService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

}
