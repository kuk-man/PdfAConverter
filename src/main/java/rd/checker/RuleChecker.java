package rd.checker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connector.DatabaseConnecter;
import model.ErrorMessage;
import model.pojo.RootXml;
import model.pojo.common.Amount;
import model.pojo.common.Charge;
import model.pojo.common.Description;
import model.pojo.common.GlobalID;
import model.pojo.common.ID;
import model.pojo.common.InvolverTradeAddress;
import model.pojo.common.Quantity;
import model.pojo.common.ShipTradePostalTradeAddress;
import model.pojo.exchanged_document.ExchangedDocument;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.application_header.document.BuyerOrderReferencedDocument;
import model.pojo.trade_transaction.trade_line_item.DesignatedProductClassification;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;
import model.pojo.trade_transaction.trade_line_item.IndividualTradeProductInstance;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeProduct;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.application_header.SpecifiedTradePaymentTerms;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;
import rd.checker.rule.Rule_12;
import rd.checker.rule.Rule_13;
import rd.checker.rule.Rule_14;
import rd.checker.rule.Rule_15;
import rd.checker.rule.Rule_16;
import rd.checker.rule.Rule_17;
import rd.checker.rule.Rule_18;
import rd.checker.rule.Rule_19;
import rd.checker.rule.Rule_20;
import rd.checker.rule.Rule_21;
import rd.checker.rule.Rule_22;
import rd.checker.rule.Rule_23;
import rd.checker.rule.Rule_3;
import rd.checker.rule.Rule_4;
import rd.checker.rule.Rule_5;
import rd.checker.rule.Rule_6;
import rd.checker.rule.Rule_7_11;


public class RuleChecker extends Checker {
    private DatabaseConnecter dbCon;

    public RuleChecker(String dbConntion) { 
        dbCon = new DatabaseConnecter(dbConntion);
        errors = new ErrorMessage();
    }

    public String verifyRules(RootXml rootXml) {
        String errorMessage = checkRules(rootXml);
        if (!errorMessage.isBlank())
            return "Check Rules:\n" + errorMessage;
        return "";
    }

    private String checkRules(RootXml rootXml) {
        String object = "";

        List<String> citySubDivisionNames = dbCon.getCitySubDivisionNames();
        List<String> cityNames = dbCon.getCityNames();
        List<String> countrySubDivisionIDs = dbCon.getCountrySubDivisionIDs();
        List<String> countryIDs = dbCon.getCountryIDs();
        List<String> referencedTypeCodes = dbCon.getReferencedTypeCodes();
        List<String> currencyCodes = dbCon.getCurrencyCodes();
        List<String> taxCodes = dbCon.getTaxCodes();
        List<String> incotermCodes = dbCon.getIncotermCodes();
        List<String> allowanceChargeTypeCodes = dbCon.getAllowanceChargeTypeCodes();
        List<String> tradePaymentCodes = dbCon.getTradePaymentCodes();
        List<String> unitCodes = dbCon.getUnitCodes();
        List<String> unspscCodes = dbCon.getUnspscCodes();
        List<String> languageCodes = dbCon.getLanguageCodes();
        Map<String, String> invoiceTypeCodeToName = dbCon.getInvoiceTypeCodeToName();
        Map<String, String> taxInvoiceTypeCodeToName = dbCon.getTaxInvoiceTypeCodeToName();

        //??? prepare for front-end selection
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnGoods = dbCon.getPurposeCodeToDescriptionForDebitNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnService = dbCon.getPurposeCodeToDescriptionForDebitNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnGoods = dbCon.getPurposeCodeToDescriptionForCreditNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnService = dbCon.getPurposeCodeToDescriptionForCreditNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewReceipt = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewReceipt();
        
        Map<String, String> purposeCodeToDescription = new HashMap<>();
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForDebitNoteOnGoods);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForDebitNoteOnService);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCreditNoteOnGoods);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCreditNoteOnService);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice);
        purposeCodeToDescription.putAll(purposeCodeToDescriptionForCancellationCauseOnNewReceipt);

        // Transaction Type
        String transactionType = "";
        if (!isNull(new N<>(() -> "" + rootXml.getTransaction()))) {
            transactionType = rootXml.getTransaction();
        }

        // rule 8.3
        Rule_3 rule3 = new Rule_3();
        // (2.2) ExchangedDocument|Name
        // (2.3) ExchangedDocument|TypeCode
        object = "CrossIndustryInvoice|ExchangedDocument";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument()))) {
            ExchangedDocument ed = rootXml.getCrossIndustryInvoice().getExchangedDocument();
            rule3.checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        }

        // rule 8.4
        Rule_4 rule4 = new Rule_4();
        // (2.5) ExchangedDocument|Purpose
        object = "CrossIndustryInvoice|ExchangedDocument";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose()))) {
            String purposeDescription = rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose();
            String purposeCode = rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurposeCode();
            rule4.checkPurpose(purposeCode, purposeDescription, purposeCodeToDescription, object);
        }
        
        // rule 8.5
        Rule_5 rule5 = new Rule_5();
        // (2.8) [] ExchangedDocument|CreationDateTime
        object = "CrossIndustryInvoice|ExchangedDocument|CreationDateTime";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime()))) {
            String[] creationDatetime = rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime();
            for (String datetime : creationDatetime) {
                rule5.checkDateTimeFormat(datetime, object);
            }
        }
        // (3.1.4.2) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime())))  {
            String issueDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime();
            rule5.checkDateTimeFormat(issueDateTime, object);
        }
        // (3.1.5.2) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {   
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
                if (!isNull(new N<>(() -> "" + ard.getIssueDateTime()))) {
                    String issueDateTime = ard.getIssueDateTime();
                    rule5.checkDateTimeFormat(issueDateTime, object);
                }
            }
        }
        // (3.2.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime()))) {
            String occurrenceDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime();
            rule5.checkDateTimeFormat(occurrenceDateTime, object);
        }
        // (3.4.2.5.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()))) {
                    for (IndividualTradeProductInstance itpi : isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()) {
                        if (!isNull(new N<>(() -> "" + itpi.getExpiryDateTime()))) {
                            String expiryDateTime = itpi.getExpiryDateTime();
                            rule5.checkDateTimeFormat(expiryDateTime, object);
                        }
                    }
                }
            }
        }

        // rule 8.6
        Rule_6 rule6 = new Rule_6();
        // (3.1.1.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.1.2.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.2.1.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getId()))) {            
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.2.2.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.3.6.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.3.7.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.3.8.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getId()))) {
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }
        // (3.3.9.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getId()))) {            
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getId()) {
                rule6.checkID(id, object);
            }
        }

        // (3.1.2.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getGlobalID()))) {            
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.2.1.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.2.2.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.3.6.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.3.7.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.1.1.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.3.8.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getGlobalID()))) {            
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getGlobalID()) {
                rule6.checkGlobalID(id, object);
            }
        }
        // (3.3.9.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getGlobalID()))) {
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getGlobalID()) {                
                rule6.checkGlobalID(id, object);
            }
        }
    
        // (3.1.1.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        // (3.1.2.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        // (3.3.6.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        // (3.3.7.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId()))) {
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        // (3.3.8.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId()))) {            
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        // (3.3.9.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId()))) {
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId();
            rule6.checkID(id, object);
        }
        if (!rule6.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule6.getError().getErrorMessage());

        // rule 8.7
        // rule 8.8
        // (3.1.1.6.10), (3.1.2.6.10), (3.2.1.5.10), (3.3.6.6.10), (3.3.7.6.10), (3.3.8.6.10), (3.3.9.6.10)
        // rule 8.9
        // (3.1.1.6.9), (3.1.2.6.9), (3.2.1.5.9), (3.3.6.6.9), (3.3.7.6.9), (3.3.8.6.9), (3.3.9.6.9)
        // rule 8.10
        // (3.1.1.6.12), (3.1.2.6.12), (3.2.1.5.12), (3.3.6.6.12), (3.3.7.6.12), (3.3.8.6.12), (3.3.9.6.12)
        // rule 8.11
        // (3.1.1.6.11), (3.1.2.6.11), (3.2.1.5.11), (3.3.6.6.11), (3.3.7.6.11), (3.3.8.6.11), (3.3.9.6.11)

        Rule_7_11 rule711 = new Rule_7_11();
        // 3.1.1 SellerTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);

            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.1.2 BuyerTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.2.1 ShipToTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress()))) {
            ShipTradePostalTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String[] countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.6 InvoicerTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.7 InvoiceeTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.8 PayerTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.9 PayeeTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()))) {
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            rule711.checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object);
            
            if (!isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    rule711.checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        if (!rule711.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule711.getError().getErrorMessage());

        // rule 8.12
        Rule_12 rule12 = new Rule_12();
        // (3.1.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms|DeliveryTypeCode
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms|DeliveryTypeCode";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms().getDeliveryTypeCode()))) {
            String deliveryTypeCode = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms().getDeliveryTypeCode();
            rule12.checkTradeTermCode(deliveryTypeCode, incotermCodes, object);
        }
        if (!rule12.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule12.getError().getErrorMessage());

        // rule 8.13
        Rule_13 rule13 = new Rule_13();
        // (3.1.4) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument()))) {
            BuyerOrderReferencedDocument buyerOrderReferencedDocument = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument();    
            rule13.checkBuyerOrderReferencedDocument(buyerOrderReferencedDocument, referencedTypeCodes, object);
        }
        // (3.1.5) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            AdditionalReferencedDocument[] additionalReferencedDocuments = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument();    
            rule13.checkAdditionalReferencedDocument(additionalReferencedDocuments, transactionType, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object);
        }
        if (!rule13.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule13.getError().getErrorMessage());

        // rule 8.14
        Rule_14 rule14 = new Rule_14();
        // (3.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceCurrencyCode
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceCurrencyCode";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode()))) {    
            String currencyCode = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode();
            rule14.checkCurrencyCode(currencyCode, currencyCodes, object);
        }

        // rule 8.15
        Rule_15 rule15 = new Rule_15();
        // (3.3.2.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
                if (!isNull(new N<>(() -> "" + att.getTypeCode())))
                    rule15.checkTaxCode(att.getTypeCode(), taxCodes, object);
            }
        }

        // rule 8.16
        Rule_16 rule16 = new Rule_16();
        // (3.3.3.5) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge";
        String object2 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation";            
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            Charge[] charges = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge();
            if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()))) {
                SpecifiedTradeSettlementHeaderMonetarySummation stshms = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation();
                rule16.checkAllowanceCharge(charges, stshms, allowanceChargeTypeCodes, object1, object2);
            } else {
                errors.setErrorMassage("Check CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation: SpecifiedTradeAllowanceCharge has value then SpecifiedTradeSettlementHeaderMonetarySummation cannot be blank. Current SpecifiedTradeSettlementHeaderMonetarySummation = ");
            }
        }
        // (3.4.3.1.2.5) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|TypeCode
        object1 = "CrossIndustryInvoiceSupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge";
        object2 = "";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    Charge[] charges = isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge();
                    rule16.checkAllowanceCharge(charges, null, allowanceChargeTypeCodes, object1, object2);
                }
            }
        }
        // (3.4.5.2.5) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge";
        object2 = "";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    Charge[] charges = isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge();
                    rule16.checkAllowanceCharge(charges, null, allowanceChargeTypeCodes, object1, object2);
                }
            }
        }
        
        // rule 8.17
        Rule_17 rule17 = new Rule_17();
        // (3.3.4.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()))) {        
            for (SpecifiedTradePaymentTerms stpt : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()) {
                if (!isNull(new N<>(() -> "" + stpt.getDescription()))) {
                    Description[] descriptions = stpt.getDescription();
                    rule17.checkSpecifiedTradePaymentTerms(descriptions, tradePaymentCodes, object);
                }
            }
        }

        // rule 8.18
        Rule_18 rule18 = new Rule_18();
        // (3.4.4.1) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeDelivery().getBilledQuantity()))) {
                    Quantity quantity = tsctli.getSpecifiedLineTradeDelivery().getBilledQuantity();
                    rule18.checkUnitCodeAndQuantity(quantity, unitCodes, object);
                }
            }
        }
        // (3.4.4.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeDelivery().getPerPackageUnitQuantity()))) {
                    Quantity quantity = tsctli.getSpecifiedLineTradeDelivery().getPerPackageUnitQuantity();
                    rule18.checkUnitCodeAndQuantity(quantity, unitCodes, object);
                }
            }
        }

        // rule 8.19
        Rule_19 rule19 = new Rule_19();
        // (3.4.5.3.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount()))) {
                    Amount[] amounts = tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount();
                    for (Amount amount : amounts) {
                        rule19.checkAmountAndCurrencyCode(amount, currencyCodes, object);
                    }
                }
            }
        }
        // (3.4.5.3.3) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetIncludingTaxesLineTotalAmount()))) {
                    Amount[] amounts = tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetIncludingTaxesLineTotalAmount();
                    for (Amount amount : amounts) {
                        rule19.checkAmountAndCurrencyCode(amount, currencyCodes, object);
                    }
                }
            }
        }

        // rule 8.20
        Rule_20 rule20 = new Rule_20();
        // (3.4.2.1) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|ID
        // (3.4.2.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|GlobalID
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedTradeProduct()))) {
                    SpecifiedTradeProduct stp = tsctli.getSpecifiedTradeProduct();
                    rule20.checkSpecifiedTradeProduct(stp, object);
                }
            }
        }

        // rule 8.21
        Rule_21 rule21 = new Rule_21();
        // (3.4.2.6) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedTradeProduct().getDesignatedProductClassification()))) {
                    DesignatedProductClassification dpc = tsctli.getSpecifiedTradeProduct().getDesignatedProductClassification();
                    rule21.checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object);
                }
            }
        }

        // rule 8.22
        Rule_22 rule22 = new Rule_22();
        // (2.7) ExchangedDocument|GlobalID
        object = "CrossIndustryInvoice|ExchangedDocument|GlobalID";
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID()))) {
            ID globalID = rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID();
            String value = globalID.getValue() == null ? "" : globalID.getValue();
            rule22.checkGlobalID(value, object);
        }

        // rule 8.23
        Rule_23 rule23 = new Rule_23();
        // (3.3.6) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty
        object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty";
        if (isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()))) {    
            rule23.checkInvoicerTradeParty(transactionType, object);
        }


        if (!rule3.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule3.getError().getErrorMessage());
        if (!rule4.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule4.getError().getErrorMessage());
        if (!rule5.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule5.getError().getErrorMessage());
        if (!rule6.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule6.getError().getErrorMessage());
        if (!rule711.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule711.getError().getErrorMessage());
        if (!rule12.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule12.getError().getErrorMessage());
        if (!rule13.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule13.getError().getErrorMessage());
        if (!rule14.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule14.getError().getErrorMessage());
        if (!rule15.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule15.getError().getErrorMessage());
        if (!rule16.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule16.getError().getErrorMessage());
        if (!rule17.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule17.getError().getErrorMessage());
        if (!rule18.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule18.getError().getErrorMessage());
        if (!rule19.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule19.getError().getErrorMessage());
        if (!rule20.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule20.getError().getErrorMessage());
        if (!rule21.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule21.getError().getErrorMessage());
        if (!rule22.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule22.getError().getErrorMessage());
        if (!rule23.getError().getErrorMessage().isBlank()) errors.setErrorMassage(rule23.getError().getErrorMessage());

        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }
}
