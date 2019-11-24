package services;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class Register {
    @WebMethod
    public String Register(@XmlElement(name = "account") String account,
            @XmlElement(name = "name") String name) {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query = "insert into customers (accountNumber, customerName, balance) values (" + account + ", '" + name + "', 0)";
            stmt.executeQuery(query);
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