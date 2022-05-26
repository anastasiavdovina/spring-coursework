package jdbc;

import jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try(Connection connection = JDBCUtils.getNewConnection()) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}