package fon.njt.cvbuilderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class CvBuilderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvBuilderApiApplication.class, args);
    }

}
