package services;
import data.History;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetHistory {
    @WebMethod
    public History GetHistory(@XmlElement(name = "account") String account) {
        History result = new History();

        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query = "select * from transactions where accountNumber = " + account + " order by transactionTime desc";
            ResultSet res = stmt.executeQuery(query);

            if (res.next()) {
                res.beforeFirst();

                List<String> dataTime = new ArrayList<String>();
                List<String> dataType = new ArrayList<String>();
                List<String> dataAmount = new ArrayList<String>();
                List<String> dataAccount = new ArrayList<String>();

                result.setStatus(200);

                while (res.next()) {
                    dataTime.add(res.getString("transactionTime"));
                    dataType.add(res.getString("transactionType"));
                    dataAmount.add(res.getString("amount"));
                    dataAccount.add(res.getString("targetAccount"));
                }

                result.setTransactionTime(dataTime);
                result.setTransactionType(dataType);
                result.setAmount(dataAmount);
                result.setTargetAccount(dataAccount);
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
