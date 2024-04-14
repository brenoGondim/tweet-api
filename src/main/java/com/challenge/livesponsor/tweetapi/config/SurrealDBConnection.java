package com.challenge.livesponsor.tweetapi.config;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//colocar os valores no arquivo de configuração
@Component
public class SurrealDBConnection {

    @Bean
    public static SyncSurrealDriver getDriver() {
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection("localhost", 8000, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn("root", "root");
        driver.use("test", "test");
        return driver;
    }
}
