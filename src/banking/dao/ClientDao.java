package banking.dao;

import banking.db.DbConnection;
import banking.model.Client;
import banking.model.LoginModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public Client getByUserNameAndPassword(LoginModel loginModel) {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "SELECT " +
                    "clienti.id, " +
                    "clienti.nume, " +
                    "clienti.prenume, " +
                    "clienti.userName, " +
                    "clienti.parola, " +
                    "roluri.rol " +
                    "FROM clienti " +
                    "JOIN roluri on roluri.id_rol = clienti.id_rol " +
                    "WHERE userName = ? AND parola = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int parameterIndex = 1;
            stmt.setString(parameterIndex++, loginModel.getUserName());
            stmt.setString(parameterIndex++, loginModel.getPassword());
            ResultSet result = stmt.executeQuery();
            Client client = null;
            if(result.next()) {
                client = new Client();
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("nume"));
                client.setFirstName(result.getString("prenume"));
                client.setRole(result.getString("rol"));
            }
            return client;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
    }

    public List<Client> getAll(){
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "SELECT " +
                    "id, " +
                    "nume, " +
                    "prenume " +
                    "FROM clienti";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("nume"));
                client.setFirstName(result.getString("prenume"));

                clients.add(client);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
        return clients;
    }
}
