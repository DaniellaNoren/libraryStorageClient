package daniella.iths.se.librarystorageclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ClientConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
