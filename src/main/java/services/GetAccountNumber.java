package services;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetAccountNumber {
    @WebMethod
    public String GetAccountNumber(@XmlElement(name = "account") String account) {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro","root", "");
            Statement stmt = conn.createStatement();
            String query = "select * from customers right outer join virtual_accounts on customers.accountNumber = virtual_accounts.accountNumber where customers.accountNumber = " + account + " or virtual_accounts.virtualNumber = " + account;
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                return "200";
            } else {
                return "300";
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return "500";
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        }
    }
}
