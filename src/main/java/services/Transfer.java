package services;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class Transfer {
    @WebMethod
    public String Transfer(@XmlElement(name = "account") String account, @XmlElement(name = "amount") String amount, @XmlElement(name = "targetAccount") String targetAccount, @XmlElement(name = "realAccount") String realAccount) {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query1 = "insert into transactions (accountNumber, transactionType, amount, targetAccount) values (" + account + ", 'debit', " + amount + ", " + targetAccount + ")";
            String query2 = "update customers set balance = balance - " + amount + " where accountNumber = " + account;
            String query3 = "insert into transactions (accountNumber, transactionType, amount, targetAccount) values (" + realAccount + ", 'kredit', " + amount + ", " + account + ")";
            String query4 = "update customers set balance = balance + " + amount + " where accountNumber = " + realAccount;
            stmt.addBatch(query1);
            stmt.addBatch(query2);
            stmt.addBatch(query3);
            stmt.addBatch(query4);
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            return "500";
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        } finally {
            return "200";
        }
    }
}
