package services;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class CheckTransactions {
    @WebMethod
    public String CheckTransactions(@XmlElement(name = "account") String account, @XmlElement(name = "type") String type, @XmlElement(name = "amount") String amount, @XmlElement(name = "startTime") String startTime, @XmlElement(name = "endTime") String endTime) {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = new Date(Long.parseLong(startTime));
            Date endDate = new Date(Long.parseLong(endTime));

            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro","root", "");
            Statement stmt = conn.createStatement();
            String query = "select * from transactions left outer join virtual_accounts on transactions.accountNumber = virtual_accounts.accountNumber where (transactions.accountNumber = " + account + " or virtual_accounts.virtualNumber = " + account + ") and transactionType = '" + type + "' and amount = " + amount + " and (transactionTime >= '" + formatDate.format(startDate) + "' and transactionTime <= '" + formatDate.format(endDate) + "') group by transactionTime order by transactionTime desc";
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
