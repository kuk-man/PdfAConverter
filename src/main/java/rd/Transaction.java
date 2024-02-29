package rd;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import model.pojo.RootXml;
import rd.checker.CalculationChecker;
import rd.checker.FormatChecker;
import rd.checker.RuleChecker;
import rd.checker.TransactionMandatoryFieldChecker;

public class Transaction {
    RootXml rootXml;

    public String processJson(String json) throws IOException {
        if(!isValidJson(json))
            return "Wrong JSON Format.";

        rootXml = convertJsonToPojo(json);

        String errorMessage = checkPojo(rootXml);
        if (!errorMessage.isBlank()) {
            return errorMessage;
        }

        return "";
    }

    public String generateXml() throws JsonProcessingException {
        String xml = convertPojoToXml(rootXml);
        xml = modifyXml(xml, rootXml.getTransaction());
        return xml;
    }

    private boolean isValidJson(String json) throws IOException {
        try{ 
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.readTree(json);
        } catch(JsonProcessingException e){
            return false;
        }
        return true;
    }

    private RootXml convertJsonToPojo(String json) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        jsonMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        jsonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return jsonMapper.readValue(json, RootXml.class);
    }

    private String checkPojo(RootXml rootXml) {
        String errorMessage;

        // mandatory field checker
        TransactionMandatoryFieldChecker mandatoryFieldChecker = new TransactionMandatoryFieldChecker();
        errorMessage = mandatoryFieldChecker.verifyMandatoryField(rootXml);
        if (!errorMessage.isBlank())
            return errorMessage;

        // rule checker
        RuleChecker ruleChecker = new RuleChecker("db connection");
        errorMessage = ruleChecker.verifyRules(rootXml);
        if (!errorMessage.isBlank())
            return errorMessage;

        // format checker
        FormatChecker formatChecker = new FormatChecker();
        errorMessage = formatChecker.verifyFormats(rootXml);
        if (!errorMessage.isBlank())
            return errorMessage;
            
        // calculation checker
        CalculationChecker calculationChecker = new CalculationChecker();
        errorMessage = calculationChecker.verifyCalculations(rootXml);
        if (!errorMessage.isBlank())
            return errorMessage;

        return "";
    }

    private String convertPojoToXml(RootXml rootXml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.NON_EMPTY); 
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootXml);
    }

    private String modifyXml(String xml, String transactionType) {
        String modifiedXml = "";
        
        // delete line
        modifiedXml = xml.lines().filter(str -> !str.contains("RootXml") && 
                                                !str.contains("<transaction>") &&
                                                !str.contains("<business>"))
            .collect(Collectors.joining("\n"));

        // adjust line
        modifiedXml = modifiedXml.replace("rsm:CrossIndustryInvoice", "rsm:" + transactionType + "_CrossIndustryInvoice");
        modifiedXml = modifiedXml.replaceFirst("_CrossIndustryInvoice", 
            "_CrossIndustryInvoice\n" +
            "  xmlns:rsm=\"urn:etda:uncefact:data:standard:" + transactionType + "_CrossIndustryInvoice:2\"\n" +
            "  xmlns:ram=\"urn:etda:uncefact:data:standard:" + transactionType+ "_ReusableAggregateBusinessInformationEntity:2\"\n" +
            "  xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\""
        );
        return modifiedXml;
    }
}
