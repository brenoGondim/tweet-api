package com.challenge.livesponsor.tweetapi.config;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.SyncSurrealDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SurrealDBConnection {
    @Value("${spring.surreal-db.host}")
    private String host;
    @Value("${spring.surreal-db.port}")
    private int port;
    @Value("${spring.surreal-db.username}")
    private String username;
    @Value("${spring.surreal-db.password}")
    private String password;
    @Value("${spring.surreal-db.namespace}")
    private String namespace;
    @Value("${spring.surreal-db.database}")
    private String database;

    @Bean
    public SyncSurrealDriver getDriver() {
        SurrealWebSocketConnection conn = new SurrealWebSocketConnection(host, port, false);
        conn.connect(5);
        SyncSurrealDriver driver = new SyncSurrealDriver(conn);
        driver.signIn(username, password);
        driver.use(namespace, database);
        return driver;
    }
}
