package services;
import data.VirtualNumber;
import java.sql.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService()
public class GetVirtualNumber {
    public static String ShuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        String result = "";
        Collections.shuffle(letters);

        for (String letter : letters) {
            result += letter;
        }

        return result;
    }

    @WebMethod
    public VirtualNumber GetVirtualNumber(@XmlElement(name = "senderAccount") String senderAccount,
            @XmlElement(name = "receiverAccount") String receiverAccount) {
        VirtualNumber result = new VirtualNumber();

        try {
            Long random = Long.parseLong(ShuffleString(Long.toString(10000000000L - Instant.now().getEpochSecond())));

            if (random < 1000000000L) {
                random += (long) (Math.random() * ((9 - 1) + 1)) * 1000000000L;
            }

            String virtual = Long.toString(random) + ShuffleString(senderAccount).substring(0, 6);

            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank_pro", "root", "");
            Statement stmt = conn.createStatement();
            String query = "insert into virtual_accounts (accountNumber, virtualNumber) values (" + receiverAccount + ", " + virtual + ")";
            stmt.executeQuery(query);

            result.setStatus(200);
            result.setVirtualNumber(virtual);
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