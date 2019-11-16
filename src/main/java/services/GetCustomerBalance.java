package services;
import data.Balance;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetCustomerBalance {
    @WebMethod
    public Balance GetCustomerBalance(@XmlElement(name = "account") String account) {
        Balance result = new Balance();

        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro","root", "");
            Statement stmt = conn.createStatement();
            String query = "select balance from customers where accountNumber = " + account;
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                result.setStatus(200);
                result.setBalance(res.getString("balance"));
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
