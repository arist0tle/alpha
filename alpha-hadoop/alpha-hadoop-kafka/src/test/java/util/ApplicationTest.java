package util;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tanghaiyang on 2017/12/26.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "")
@EnableScheduling
@EnableJpaAuditing
@EnableJpaRepositories({"com.geektcp.alpha"})
@EntityScan({"com.geektcp.alpha"})
@ComponentScan({"com.geektcp.alpha"})
public class ApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
