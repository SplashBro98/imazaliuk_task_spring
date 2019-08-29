package com.epam.esm.pool;


import com.epam.esm.handling.PoolCreationException;
import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

/**
 * custom connection pool
 */
public class GiftDataSource extends AbstractDataSource {
    private static final int CONNECTION_COUNT = 20;
    private static Logger giftLogger = LoggerFactory.getLogger(GiftDataSource.class);

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            giftLogger.error("Can't install driver");
            throw new PoolCreationException("Can't install driver", e);
        }
    }

    private String url;
    private String userName;
    private String password;
    private BlockingQueue<ProxyConnection> allConnections = new LinkedBlockingDeque<>();

    public void init() {
        IntStream.range(0, CONNECTION_COUNT).forEach(c -> {
            try {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(
                        url,
                        userName,
                        password),
                        this);
                addConnection(connection);
            } catch (SQLException e) {
                giftLogger.error("Can't create pool", e);
                throw new PoolCreationException("Can't create pool", e);
            }
        });
    }

    @Override
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = allConnections.take();
        } catch (InterruptedException e) {
            giftLogger.error("There aren't connections", e);
            throw new PoolCreationException("Can't take connection", e);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) {
        return getConnection();
    }

    public void addConnection(ProxyConnection connection) {
        try {
            allConnections.put(connection);
        } catch (InterruptedException e) {
            giftLogger.error("Can't add connection", e);
            throw new PoolCreationException("Can't add connection", e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
