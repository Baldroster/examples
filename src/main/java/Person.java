import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "Person")
@XmlRootElement
public class Person implements Serializable {

    public String name;
    @XmlAttribute(name = "CardNumber")
    public String cardNumber;
    @SerializedName("CVV2")
    @XmlAttribute(name = "CVV2")
    public String cardCVV2;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardCVV2='" + cardCVV2 + '\'' +
                '}';
    }
}
