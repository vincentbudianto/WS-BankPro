package data;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name = "", propOrder = {
        "status",
        "accountNumber",
        "customerName"
})

@XmlRootElement(name = "getArrResponse")
public class Customer {
    private final static long serialVersionUID = 1L;
    @XmlElement(name = "status", required = true)
    private int status;
    @XmlElement(name = "accountNumber", required = true)
    private String accountNumber;
    @XmlElement(name = "customerName", required = true)
    private String customerName;

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getAccountNumber() { return accountNumber; }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}