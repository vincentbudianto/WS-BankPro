package services;
import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetVirtualNumber {
    @WebMethod
    public String GetVirtualNumber(@XmlElement(name = "senderAccount") String senderAccount,
            @XmlElement(name = "receiverAccount") String receiverAccount) {
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro","root", "");
            Statement stmt = conn.createStatement();
            String query = "insert into virtual_accounts (accountNumber, virtualNumber) values (" + receiverAccount + ", " + senderAccount + ")";
            stmt.executeQuery(query);

            return "200";
        } catch (SQLException e) {
            e.printStackTrace();
            return "500";
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        }
    }
}