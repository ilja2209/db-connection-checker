package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("Args:");
            System.out.println("DB Host");
            System.out.println("DB Port");
            System.out.println("DB User");
            System.out.println("DB Password");
            System.out.println("DB Name");
        }
        System.out.println("Trying to connect to DB...");
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;

        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%s/%s", args[0], args[1], args[4]));
        config.setUsername(args[2]);
        config.setPassword(args[3]);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
        System.out.println("Execute query select 1 as \"proba\"");
        try (Connection con = ds.getConnection();
             PreparedStatement pst = con.prepareStatement("select 1 as \"proba\"");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                System.out.printf("Result: %d%n", rs.getInt("proba"));
            }
        }
    }
}
