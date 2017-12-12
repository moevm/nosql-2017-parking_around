package parking;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import parking.services.DataService;

/**
 * Created by Stanislav on 13.11.2017.
 */

@SpringBootApplication
public class SpringBootNeo4jApplication {

//    @Autowired
//    private DataService dataService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNeo4jApplication.class, args);
    }

//    @Bean
//    InitializingBean sendDatabase() {
//        return () -> dataService.importJSON();
//    }
}
