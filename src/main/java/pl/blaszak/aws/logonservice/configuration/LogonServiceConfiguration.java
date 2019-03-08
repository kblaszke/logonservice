package pl.blaszak.aws.logonservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import pl.blaszak.aws.logonservice.couchbase.repository.UserRepository;
import pl.blaszak.aws.logonservice.service.UserService;

import java.util.Collections;
import java.util.List;

@Configuration
public class LogonServiceConfiguration extends AbstractCouchbaseConfiguration {

    @Bean
    public UserService getCouchbaseUserService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "logonServiceBucket";
    }

    @Override
    protected String getBucketPassword() {
        return "Wiosna2019";
    }
}
