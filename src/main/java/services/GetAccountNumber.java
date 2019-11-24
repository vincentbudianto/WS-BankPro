package services;
import data.AccountNumber;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetAccountNumber {
    @WebMethod
    public AccountNumber GetAccountNumber(@XmlElement(name = "account") String account) {
        AccountNumber result = new AccountNumber();

        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query = "select * from customers right outer join virtual_accounts on customers.accountNumber = virtual_accounts.accountNumber where customers.accountNumber = " + account + " or virtual_accounts.virtualNumber = " + account;
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                result.setStatus(200);
                result.setAccountNumber(res.getString("accountNumber"));
            } else {
                result.setStatus(300);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.setStatus(500);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(400);
        } finally {
            return result;
        }
    }
}
