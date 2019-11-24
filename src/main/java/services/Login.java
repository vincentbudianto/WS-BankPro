package services;
import data.Customer;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class Login {
    @WebMethod
    public Customer Login(@XmlElement(name = "account") String account) {
        Customer result = new Customer();

        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query = "select * from customers where accountNumber = " + account;
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                result.setStatus(200);
                result.setAccountNumber(res.getString("accountNumber"));
                result.setCustomerName(res.getString("customerName"));
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
