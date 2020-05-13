package com.frank.api.gateway.auth;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiControllerTest {

    protected WebTestClient rest;

//    @LocalServerPort
//    private int port = 8103;

//    @Before
//    public void setUp() {
//        this.rest = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(10))
//            .baseUrl("http://localhost:" + this.port)
//            .defaultHeader("signature", "8f9238516f1ffb26986b4aa51692710492b87fbf9589cc70ef37ab5616cfa23d")
//            .defaultHeader("timestamp", "1525687385086")
//            .defaultHeader("user-id", "3ee73f38497a498e93a7fee6badb298b")
//            .defaultHeader("user-name", "root")
//            .build();
//    }



}
