package model.pojo.trade_transaction.application_header;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import model.pojo.trade_transaction.application_header.communication.EmailURIUniversalCommunication;
import model.pojo.trade_transaction.application_header.communication.TelephoneUniversalCommunication;

@JsonPropertyOrder({"personName", "departmentName", "emailURIUniversalCommunication", "telephoneUniversalCommunication"})
@JacksonXmlRootElement(localName = "ram:DefinedTradeContact")
public class DefinedTradeContact {

    @JacksonXmlProperty(localName = "ram:PersonName")
    private String personName;

    @JacksonXmlProperty(localName = "ram:DepartmentName")
    private String departmentName;

    @JacksonXmlProperty(localName = "ram:EmailURIUniversalCommunication")
    private EmailURIUniversalCommunication emailURIUniversalCommunication;

    @JacksonXmlProperty(localName = "ram:TelephoneUniversalCommunication")
    private TelephoneUniversalCommunication telephoneUniversalCommunication;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public EmailURIUniversalCommunication getEmailURIUniversalCommunication() {
        return emailURIUniversalCommunication;
    }

    public void setEmailURIUniversalCommunication(EmailURIUniversalCommunication emailURIUniversalCommunication) {
        this.emailURIUniversalCommunication = emailURIUniversalCommunication;
    }

    public TelephoneUniversalCommunication getTelephoneUniversalCommunication() {
        return telephoneUniversalCommunication;
    }

    public void setTelephoneUniversalCommunication(TelephoneUniversalCommunication telephoneUniversalCommunication) {
        this.telephoneUniversalCommunication = telephoneUniversalCommunication;
    }
}



