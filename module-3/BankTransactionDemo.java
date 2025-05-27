public class BankTransactionDemo {
    public static void main(String[] args) {
        try {
            AccountDAO accountDAO = new AccountDAO();
            
            // Create the accounts table
            accountDAO.createAccountsTable();
            
            // Create two test accounts
            Account account1 = new Account("ACC001", "John Doe", 1000.00);
            Account account2 = new Account("ACC002", "Jane Smith", 500.00);
            
            // Insert the accounts
            accountDAO.createAccount(account1);
            accountDAO.createAccount(account2);
            
            System.out.println("Initial account balances:");
            displayAccounts(accountDAO);
            
            // Perform successful transaction
            System.out.println("\nPerforming transfer of $300 from ACC001 to ACC002...");
            try {
                boolean success = accountDAO.transferMoney("ACC001", "ACC002", 300.00);
                System.out.println("Transfer successful: " + success);
            } catch (Exception e) {
                System.out.println("Transfer failed: " + e.getMessage());
            }
            
            System.out.println("\nAccount balances after successful transfer:");
            displayAccounts(accountDAO);
            
            // Attempt transfer with insufficient funds (should fail and rollback)
            System.out.println("\nAttempting transfer of $1000 from ACC002 to ACC001 (insufficient funds)...");
            try {
                boolean success = accountDAO.transferMoney("ACC002", "ACC001", 1000.00);
                System.out.println("Transfer successful: " + success);
            } catch (Exception e) {
                System.out.println("Transfer failed (as expected): " + e.getMessage());
            }
            
            System.out.println("\nAccount balances after failed transfer (should be unchanged):");
            displayAccounts(accountDAO);
            
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void displayAccounts(AccountDAO accountDAO) throws Exception {
        System.out.println("----------------------------------------");
        for (Account account : accountDAO.getAllAccounts()) {
            System.out.printf("Account: %s, Holder: %s, Balance: $%.2f%n",
                account.getAccountNumber(),
                account.getAccountHolder(),
                account.getBalance());
        }
        System.out.println("----------------------------------------");
    }
} 