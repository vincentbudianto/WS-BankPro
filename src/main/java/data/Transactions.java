package data;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name = "", propOrder = {
        "status",
        "transactionTime",
        "transactionType",
        "amount",
        "targetAccount"
})

@XmlRootElement(name = "getArrResponse")
public class Transactions {
    private final static long serialVersionUID = 1L;
    @XmlElement(name = "status", required = true)
    private int status;
    @XmlElement(name = "transactionTime", required = true)
    private List<String> transactionTime;
    @XmlElement(name = "transactionType", required = true)
    private List<String> transactionType;
    @XmlElement(name = "amount", required = true)
    private List<String> amount;
    @XmlElement(name = "targetAccount", required = true)
    private List<String> targetAccount;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(List<String> transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<String> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(List<String> transactionType) {
        this.transactionType = transactionType;
    }

    public List<String> getAmount() {
        return amount;
    }

    public void setAmount(List<String> amount) {
        this.amount = amount;
    }

    public List<String> getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(List<String> targetAccount) {
        this.targetAccount = targetAccount;
    }
}