package data;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name = "", propOrder = {
        "status",
        "virtualNumber"
})

@XmlRootElement(name = "getArrResponse")
public class VirtualNumber {
    private final static long serialVersionUID = 1L;
    @XmlElement(name = "status", required = true)
    private int status;
    @XmlElement(name = "virtualNumber", required = true)
    private String virtualNumber;

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getVirtualNumber() { return virtualNumber; }

    public void setVirtualNumber(String virtualNumber) { this.virtualNumber = virtualNumber; }
}