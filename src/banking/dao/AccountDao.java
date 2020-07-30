package banking.dao;

import banking.db.DbConnection;
import banking.model.Account;
import banking.model.NewAccountModel;
import banking.model.PendingAccountModel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public boolean checkAccountNumber(NewAccountModel accountModel) {
        Connection connection = null;
        Boolean exists = false;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "SELECT " +
                    "nr_cont " +
                    "FROM conturi " +
                    "WHERE id_client = ? " +
                    "AND nr_cont = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int parameterIndex = 1;
            stmt.setInt(parameterIndex++, accountModel.getClientId());
            stmt.setInt(parameterIndex++, accountModel.getAccountNumber());
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                exists = true;
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
        return exists;
    }

    public void insert(NewAccountModel accountModel) {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "INSERT " +
                    "INTO conturi(id,nr_cont,banca,suma,id_stare,id_client) " +
                    "VALUES(conturi_seq.nextval,?,'Banca Transilvania',0,(SELECT id FROM stari WHERE stare = 'IN APROBARE'),?)";

            int parameterIndex = 1;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(parameterIndex++, accountModel.getAccountNumber());
            stmt.setInt(parameterIndex++, accountModel.getClientId());
            stmt.executeUpdate();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
    }
    
    public BigDecimal getSumById(Integer accountId) {
        Connection connection = null;
        BigDecimal sum = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "SELECT suma " +
                    "FROM conturi " +
                    "WHERE id = ? ";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            int parameterIndex = 1;
            stmt.setInt(parameterIndex++,accountId);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                sum = result.getBigDecimal("suma");
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
        return sum;
    }

    public BigDecimal updateSum(Integer accountId, BigDecimal depositSum) {
        Connection connection = null;
        BigDecimal updatedSum;
        try {
            connection = DbConnection.getConnection();
            String updateSql = "" +
                    "UPDATE " +
                    "conturi " +
                    "SET suma = suma + ? " +
                    "WHERE id = ?";

            int parameterIndex = 1;
            PreparedStatement stmt = connection.prepareStatement(updateSql);
            stmt.setBigDecimal(parameterIndex++,depositSum);
            stmt.setInt(parameterIndex++,accountId);
            stmt.executeUpdate();

            updatedSum = getSumById(accountId);

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
        return updatedSum;
    }

    public List<Account> getAllByClientId(Integer clientId) {
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "SELECT " +
                    "conturi.id, " +
                    "conturi.nr_cont, " +
                    "conturi.banca, " +
                    "conturi.suma, " +
                    "stari.stare " +
                    "FROM conturi " +
                    "    JOIN stari " +
                    "    ON stari.id=conturi.id_stare " +
                    "WHERE id_client=? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int parameterIndex = 1;
            stmt.setInt(parameterIndex++, clientId);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Account account = new Account();
                account.setId(result.getInt("id"));
                account.setAccountNumber(result.getInt("nr_cont"));
                account.setBank(result.getString("banca"));
                account.setSum(result.getBigDecimal("suma"));
                account.setState(result.getString("stare"));

                accounts.add(account);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
        return accounts;
    }

    public List<Account> getAllActive() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "SELECT " +
                    "conturi.id, " +
                    "conturi.nr_cont, " +
                    "conturi.banca, " +
                    "conturi.suma, " +
                    "stari.stare, " +
                    "conturi.id_client " +
                    "FROM conturi " +
                    "    JOIN stari " +
                    "    ON stari.id=conturi.id_stare " +
                    "WHERE stari.stare='ACTIV'";
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while(result.next()) {
                Account account = new Account();
                account.setId(result.getInt("id"));
                account.setAccountNumber(result.getInt("nr_cont"));
                account.setBank(result.getString("banca"));
                account.setSum(result.getBigDecimal("suma"));
                account.setState(result.getString("stare"));
                account.setClientId(result.getInt("id_client"));

                accounts.add(account);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
        return accounts;
    }
    public List<Account> getActiveByClientId(Integer idClient) {
        List<Account> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "SELECT " +
                    "conturi.id, " +
                    "conturi.nr_cont, " +
                    "conturi.banca, " +
                    "conturi.suma, " +
                    "stari.stare " +
                    "FROM conturi " +
                    "    JOIN stari " +
                    "    ON stari.id = conturi.id_stare " +
                    "WHERE id_client = ? " +
                    "AND stari.stare = 'ACTIV'";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int parameterIndex = 1;
            stmt.setInt(parameterIndex++, idClient);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Account account = new Account();
                account.setId(result.getInt("id"));
                account.setAccountNumber(result.getInt("nr_cont"));
                account.setBank(result.getString("banca"));
                account.setSum(result.getBigDecimal("suma"));
                account.setState(result.getString("stare"));

                accounts.add(account);
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
        return accounts;
    }

    public List<PendingAccountModel> getPending(){
        List<PendingAccountModel> accounts = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "SELECT " +
                    "conturi.id, " +
                    "conturi.nr_cont, " +
                    "conturi.id_stare, " +
                    "clienti.nume, " +
                    "clienti.prenume " +
                    "FROM conturi " +
                    "    JOIN stari " +
                    "    ON stari.id = conturi.id_stare " +
                    "    JOIN clienti " +
                    "    ON clienti.id = conturi.id_client " +
                    "WHERE stari.stare = 'IN APROBARE'";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                PendingAccountModel account = new PendingAccountModel();
                account.setId(result.getInt("id"));
                account.setAccountNumber(result.getInt("nr_cont"));
                account.setClientName(result.getString("nume")+" "+result.getString("prenume"));
                account.setStateId(result.getInt("id_stare"));

                accounts.add(account);
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
        return accounts;
    }
    public void updatePending(PendingAccountModel model) {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "UPDATE " +
                    "conturi " +
                    "SET id_stare = ? " +
                    "WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            Integer parameterIndex = 1;
            stmt.setInt(parameterIndex++,model.getStateId());
            stmt.setInt(parameterIndex++,model.getId());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }finally {
            DbConnection.closeConnection(connection);
        }
    }

}
