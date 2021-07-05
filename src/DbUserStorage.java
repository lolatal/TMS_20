import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbUserStorage {

    private Connection connection;

    public DbUserStorage(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
        DbUserStorage dbUserStorage = new DbUserStorage(connection);
//        dbUserStorage.updateNameById(1, "Olya");
//        dbUserStorage.updatePasswordById(2, "qwerty");
//        Optional <User> trial = dbUserStorage.getByUsername("anna");
//        System.out.println(trial);
//        dbUserStorage.deleteById(2);
//        Optional <List> trial2 = dbUserStorage.getAllByName("Irina");
//        System.out.println(trial2);
//        Optional <List> trial3 = dbUserStorage.getAllByAddress("Italy");
//        System.out.println(trial3);
//        dbUserStorage.getAll();
//        dbUserStorage.existsById(2);
//        dbUserStorage.exists(new User("John", "john", "USA", "+718234566"));
    }

    public void save(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e1) {
            try{
                connection.rollback();
            } catch (SQLException e){
                e.printStackTrace();
            }
            e1.printStackTrace();
        }finally {
            try{
                connection.setAutoCommit(true);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateNameById (int id, String name){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updatePasswordById (int id, String password){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Optional<User> getByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int anInt = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String username2 = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address = resultSet.getString(5);
                String phone = resultSet.getString(6);
                return Optional.of(new User(anInt, name, username2, password, address, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List> getAllByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userWithSameName = new ArrayList<>();
            while (resultSet.next()){
                int anInt = resultSet.getInt(1);
                String name2 = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address = resultSet.getString(5);
                String phone = resultSet.getString(6);
                userWithSameName.add(new User(anInt, name2, username, password, address, phone));
            }
            return Optional.of(userWithSameName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<List> getAllByAddress(String address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where address = ?");
            preparedStatement.setString(1, address);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userWithSameAddress = new ArrayList<>();
            while (resultSet.next()){
                int anInt = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                String address2 = resultSet.getString(5);
                String phone = resultSet.getString(6);
                userWithSameAddress.add(new User(anInt, name, username, password, address2, phone));
            }
            return Optional.of(userWithSameAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public void getAll(){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                System.out.println(id + ",Name:  " + name + ", Username: " + username + ", Password: " + password + ", Address: " + address + ", Phone: " + phone + ". ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteById (int id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(User user){
        boolean isExists = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ? and username = ? and address = ? and phone = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println(true);
            }else{
                System.out.println(false);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsById(int id){
        boolean isExists = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.println(true);
                }else{
                    System.out.println(false);
                }
            } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
