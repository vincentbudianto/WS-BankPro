package services;
import data.Transactions;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class CheckTransactions {
    @WebMethod
    public Transactions CheckTransactions(@XmlElement(name = "account") String account, @XmlElement(name = "type") String type, @XmlElement(name = "amount") String amount, @XmlElement(name = "startTime") String startTime, @XmlElement(name = "endTime") String endTime) {
        Transactions result = new Transactions();

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
        } catch(SQLException e) {
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
