package sec.project.repository;

import org.springframework.stereotype.Component;
import sec.project.domain.Signup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class VulnerableSignupRepository implements SignupRepository {

    private H2DB db;

    public VulnerableSignupRepository() {
        db = new H2DB();

        createTable();
    }

    private void createTable() {
        try (Connection connection = db.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS t (" +
                    "id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "name varchar(1000) NOT NULL, " +
                    "address varchar(1000) NOT NULL" +
                    ")");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        System.out.println("Table Created");
    }

    @Override
    public Signup findOne(Long id) {
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM t WHERE id=" + id)
        ) {
            if (resultSet.next()) {
                return new Signup(
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Signup> findAll() {
        List<Signup> results = new ArrayList<>();
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM t")
        ) {
            while (resultSet.next()) {
                results.add(new Signup(
                        resultSet.getString("name"),
                        resultSet.getString("address")
                ));
            }
            return results;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Signup signup) {
        try (
                Connection connection = db.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute("INSERT INTO t (name, address) VALUES ('"
                    + signup.getName() + "', '" + signup.getAddress() + "')");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
