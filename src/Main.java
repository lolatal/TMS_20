import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
        Statement statement = connection.createStatement();
//        statement.execute("insert into users (name, username, password, address, phone) values ('Olga', 'olga', '123', 'Denmark', '+451234567')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('Irina', 'irina', '987', 'Belarus', '+37529123456')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('Michael', 'michael', '564', 'Italy', '+391234567')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('John', 'john', '345', 'USA', '+718234566')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('Anna', 'anna', '012', 'Poland', '+421234567')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('Irina', 'test', 'sdfg', 'Italy', '+390123456')");
//        statement.execute("insert into users (name, username, password, address, phone) values ('Irina', 'irina2', '09876', 'Belarus', '+35330987654')");
    }
}
