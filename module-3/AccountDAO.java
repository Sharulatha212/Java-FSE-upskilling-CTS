 import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Create the accounts table if it doesn't exist
    public void createAccountsTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "account_number VARCHAR(20) UNIQUE NOT NULL, " +
                    "account_holder VARCHAR(100) NOT NULL, " +
                    "balance DECIMAL(10,2) NOT NULL)";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // Insert a new account
    public int createAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (account_number, account_holder, balance) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountHolder());
            pstmt.setDouble(3, account.getBalance());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        account.setId(rs.getInt(1));
                        return account.getId();
                    }
                }
            }
        }
        return -1;
    }

    // Get account by account number
    public Account getAccountByNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, accountNumber);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("id"),
                        rs.getString("account_number"),
                        rs.getString("account_holder"),
                        rs.getDouble("balance")
                    );
                }
            }
        }
        return null;
    }

    // Transfer money between accounts using transaction
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount) 
            throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            // Disable auto-commit to start transaction
            conn.setAutoCommit(false);
            
            // Check if both accounts exist and have sufficient balance
            Account fromAccount = getAccountByNumber(fromAccountNumber);
            Account toAccount = getAccountByNumber(toAccountNumber);
            
            if (fromAccount == null || toAccount == null) {
                throw new SQLException("One or both accounts not found");
            }
            
            if (fromAccount.getBalance() < amount) {
                throw new SQLException("Insufficient balance");
            }
            
            // Perform debit
            String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL)) {
                debitStmt.setDouble(1, amount);
                debitStmt.setString(2, fromAccountNumber);
                debitStmt.setDouble(3, amount);
                
                int debitedRows = debitStmt.executeUpdate();
                if (debitedRows != 1) {
                    throw new SQLException("Debit operation failed");
                }
            }
            
            // Perform credit
            String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {
                creditStmt.setDouble(1, amount);
                creditStmt.setString(2, toAccountNumber);
                
                int creditedRows = creditStmt.executeUpdate();
                if (creditedRows != 1) {
                    throw new SQLException("Credit operation failed");
                }
            }
            
            // If we get here, both operations succeeded, so commit the transaction
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            // If anything goes wrong, roll back the transaction
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            // Restore auto-commit to true and close connection
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Get all accounts
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                accounts.add(new Account(
                    rs.getInt("id"),
                    rs.getString("account_number"),
                    rs.getString("account_holder"),
                    rs.getDouble("balance")
                ));
            }
        }
        return accounts;
    }

    // Helper method to get database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}