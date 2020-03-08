package com.geektcp.alpha.socket.grpc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.geektcp.alpha.socket.grpc.client.ExampleClient;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringGrpcApplicationTests {

    @Autowired
    private ExampleClient exampleClient;

    @Test
    public void testSayHello() {
        assertThat(exampleClient.sayHello("John", "Doe")).isEqualTo("Hello John Doe!");
    }
}
