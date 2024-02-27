package rd.checker;

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
        errors = new ErrorMessage();

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
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnGoods = dbCon.getPurposeCodeToDescriptionForDebitNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForDebitNoteOnService = dbCon.getPurposeCodeToDescriptionForDebitNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnGoods = dbCon.getPurposeCodeToDescriptionForCreditNoteOnGoods();
        Map<String, String> purposeCodeToDescriptionForCreditNoteOnService = dbCon.getPurposeCodeToDescriptionForCreditNoteOnService();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice();
        Map<String, String> purposeCodeToDescriptionForCancellationCauseOnNewReceipt = dbCon.getPurposeCodeToDescriptionForCancellationCauseOnNewReceipt();

        // Transaction Type
        String transactionType = "";
        if (!isNull(new N<>(() -> "" + rootXml.getTransaction()))) {
            transactionType = rootXml.getTransaction();
        }

        // rule 8.3
        // (2.2) ExchangedDocument|Name
        // (2.3) ExchangedDocument|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument";
            ExchangedDocument ed = rootXml.getCrossIndustryInvoice().getExchangedDocument();
            (new Rule_3()).checkExchangedDocumentTypeCodeAndTypeName(transactionType, ed, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object, errors);
        }

        // ???
        // // rule 8.4
        // // (2.5) ExchangedDocument|Purpose
        // if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose()))) {
        //     String purpose = rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose();
        //     checkPurpose(String actionTransactionType, String causeTransactionType, String goodsService, String purposeCode, String purposeDescription);
        // }
        
        // rule 8.5
        // (2.8) [] ExchangedDocument|CreationDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime()))) {
            String[] creationDatetime = rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime();
            for (String datetime : creationDatetime) {
                String object = "CrossIndustryInvoice|ExchangedDocument|CreationDateTime";
                (new Rule_5()).checkDateTimeFormat(datetime, object, errors);
            }
        }
        // (3.1.4.2) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime())))  {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime";
            String issueDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime();
            (new Rule_5()).checkDateTimeFormat(issueDateTime, object, errors);
        }
        // (3.1.5.2) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {   
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
                if (!isNull(new N<>(() -> "" + ard.getIssueDateTime()))) {
                    String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime";
                    String issueDateTime = ard.getIssueDateTime();
                    (new Rule_5()).checkDateTimeFormat(issueDateTime, object, errors);
                }
            }
        }
        // (3.2.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime";
            String occurrenceDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime();
            (new Rule_5()).checkDateTimeFormat(occurrenceDateTime, object, errors);
        }
        // (3.4.2.5.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()))) {
                    for (IndividualTradeProductInstance itpi : isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()) {
                        if (!isNull(new N<>(() -> "" + itpi.getExpiryDateTime()))) {
                            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime";
                            String expiryDateTime = itpi.getExpiryDateTime();
                            (new Rule_5()).checkDateTimeFormat(expiryDateTime, object, errors);
                        }
                    }
                }
            }
        }

        // rule 8.6
        // (3.1.1.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.1.2.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.2.1.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.2.2.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.3.6.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.3.7.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.3.8.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }
        // (3.3.9.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID";
            for (ID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getId()) {
                (new Rule_6()).checkID(id, object, errors);
            }
        }

        // (3.1.2.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.2.1.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.2.2.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.3.6.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.3.7.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.1.1.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.3.8.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getGlobalID()) {
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
        // (3.3.9.2) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getGlobalID()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID";
            for (GlobalID id : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getGlobalID()) {                
                (new Rule_6()).checkGlobalID(id, object, errors);
            }
        }
    
        // (3.1.1.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }
        // (3.1.2.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }
        // (3.3.6.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }
        // (3.3.7.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }
        // (3.3.8.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }
        // (3.3.9.4.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID";
            ID id = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId();
            (new Rule_6()).checkID(id, object, errors);
        }

        // rule 8.7
        // rule 8.8
        // (3.1.1.6.10), (3.1.2.6.10), (3.2.1.5.10), (3.3.6.6.10), (3.3.7.6.10), (3.3.8.6.10), (3.3.9.6.10)
        // rule 8.9
        // (3.1.1.6.9), (3.1.2.6.9), (3.2.1.5.9), (3.3.6.6.9), (3.3.7.6.9), (3.3.8.6.9), (3.3.9.6.9)
        // rule 8.10
        // (3.1.1.6.12), (3.1.2.6.12), (3.2.1.5.12), (3.3.6.6.12), (3.3.7.6.12), (3.3.8.6.12), (3.3.9.6.12)
        // rule 8.11
        // (3.1.1.6.11), (3.1.2.6.11), (3.2.1.5.11), (3.3.6.6.11), (3.3.7.6.11), (3.3.8.6.11), (3.3.9.6.11)

        // 3.1.1 SellerTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);

            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.1.2 BuyerTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.2.1 ShipToTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress";
            ShipTradePostalTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String[] countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.6 InvoicerTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.7 InvoiceeTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.8 PayerTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (countryID.equals("TH") && !isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }
        // 3.3.9 PayeeTradeParty
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress";
            InvolverTradeAddress address = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress();
            String lineOne = address.getLineOne() == null ? "" : address.getLineOne();
            String lineTwo = address.getLineTwo() == null ? "" : address.getLineTwo();
            String citySubDivisionName = address.getCitySubDivisionName() == null ? "" : address.getCitySubDivisionName();
            String cityName = address.getCityName() == null ? "" : address.getCityName();
            String countryID = address.getCountryID() == null ? "" : address.getCountryID();
            String postcodeCode = address.getPostcodeCode() == null ? "" : address.getPostcodeCode();
            (new Rule_7_11()).checkAddress(lineOne, lineTwo, citySubDivisionName, cityName, countryID, postcodeCode, citySubDivisionNames, cityNames, countryIDs, object, errors);
            
            if (!isNull(new N<>(() -> "" + address.getCountrySubDivisionID()))) {
                if (countryID.equals("TH")) {
                    String countrySubDivisionID = address.getCountrySubDivisionID();
                    (new Rule_7_11()).checkCountrySubDivisionID(countrySubDivisionID, countrySubDivisionIDs, object, errors);
                }
            } else {
                errors.setErrorMassage("Check " + object + "|CountrySubDivisionID: CountryID = TH then CountrySubDivisionID cannot be blank. Current CountrySubDivisionID = ");
            }
        }

        // rule 8.12
        // (3.1.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms|DeliveryTypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms().getDeliveryTypeCode()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms|DeliveryTypeCode";
            String deliveryTypeCode = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms().getDeliveryTypeCode();
            (new Rule_12()).checkTradeTermCode(deliveryTypeCode, incotermCodes, object, errors);
        }

        // rule 8.13
        // (3.1.4) SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument";
            BuyerOrderReferencedDocument buyerOrderReferencedDocument = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument();    
            (new Rule_13()).checkBuyerOrderReferencedDocument(buyerOrderReferencedDocument, referencedTypeCodes, object, errors);
        }
        // (3.1.5) [] SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument";
            AdditionalReferencedDocument[] additionalReferencedDocuments = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument();    
            (new Rule_13()).checkAdditionalReferencedDocument(additionalReferencedDocuments, transactionType, invoiceTypeCodeToName, taxInvoiceTypeCodeToName, object, errors);
        }

        // rule 8.14
        // (3.3.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceCurrencyCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceCurrencyCode";
            String currencyCode = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode();
            (new Rule_14()).checkCurrencyCode(currencyCode, currencyCodes, object, errors);
        }

        // rule 8.15
        // (3.3.2.1) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode";
            String taxCode = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode();
            (new Rule_15()).checkTaxCode(taxCode, taxCodes, object, errors);
        }

        // rule 8.16
        // (3.3.3.5) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge";
            String object2 = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation";
            Charge[] charges = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge();
            if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()))) {
                SpecifiedTradeSettlementHeaderMonetarySummation stshms = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation();
                (new Rule_16()).checkAllowanceCharge(charges, stshms, allowanceChargeTypeCodes, object1, object2, errors);
            } else {
                errors.setErrorMassage("Check CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation: SpecifiedTradeAllowanceCharge has value then SpecifiedTradeSettlementHeaderMonetarySummation cannot be blank. Current SpecifiedTradeSettlementHeaderMonetarySummation = ");
            }
        }
        // (3.4.3.1.2.5) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object1 = "CrossIndustryInvoiceSupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge";
            String object2 = "";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    Charge[] charges = isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge();
                    (new Rule_16()).checkAllowanceCharge(charges, null, allowanceChargeTypeCodes, object1, object2, errors);
                }
            }
        }
        // (3.4.5.2.5) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object1 = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge";
            String object2 = "";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    Charge[] charges = isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge();
                    (new Rule_16()).checkAllowanceCharge(charges, null, allowanceChargeTypeCodes, object1, object2, errors);
                }
            }
        }
        
    // rule 8.17
    // (3.3.4.1) [] SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description";
        for (SpecifiedTradePaymentTerms stpt : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()) {
            if (!isNull(new N<>(() -> "" + stpt.getDescription()))) {
                Description[] descriptions = stpt.getDescription();
                (new Rule_17()).checkSpecifiedTradePaymentTerms(descriptions, tradePaymentCodes, object, errors);
            }
        }
    }

    // rule 8.18
    // (3.4.4.1) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeDelivery().getBilledQuantity()))) {
                Quantity quantity = tsctli.getSpecifiedLineTradeDelivery().getBilledQuantity();
                (new Rule_18()).checkUnitCodeAndQuantity(quantity, unitCodes, object, errors);
            }
        }
    }
    // (3.4.4.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeDelivery().getPerPackageUnitQuantity()))) {
                Quantity quantity = tsctli.getSpecifiedLineTradeDelivery().getPerPackageUnitQuantity();
                (new Rule_18()).checkUnitCodeAndQuantity(quantity, unitCodes, object, errors);
            }
        }
    }

    // rule 8.19
    // (3.4.5.3.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount()))) {
                Amount[] amounts = tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount();
                for (Amount amount : amounts) {
                    (new Rule_19()).checkAmountAndCurrencyCode(amount, currencyCodes, object, errors);
                }
            }
        }
    }
    // (3.4.5.3.3) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetIncludingTaxesLineTotalAmount()))) {
                Amount[] amounts = tsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount();
                for (Amount amount : amounts) {
                    (new Rule_19()).checkAmountAndCurrencyCode(amount, currencyCodes, object, errors);
                }
            }
        }
    }

    // rule 8.20
    // (3.4.2.1) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|ID
    // (3.4.2.2) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|GlobalID
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedTradeProduct()))) {
                SpecifiedTradeProduct stp = tsctli.getSpecifiedTradeProduct();
                (new Rule_20()).checkSpecifiedTradeProduct(stp, object, errors);
            }
        }
    }

    // rule 8.21
    // (3.4.2.6) SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
        String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification";
        for (IncludedSupplyChainTradeLineItem tsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
            if (!isNull(new N<>(() -> "" + tsctli.getSpecifiedTradeProduct().getDesignatedProductClassification()))) {
                DesignatedProductClassification dpc = tsctli.getSpecifiedTradeProduct().getDesignatedProductClassification();
                (new Rule_21()).checkDesignatedProductClassification(dpc, unspscCodes, languageCodes, object, errors);
            }
        }
    }

    // rule 8.22
    // (2.7) ExchangedDocument|GlobalID
    if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID()))) {
        String object = "CrossIndustryInvoice|ExchangedDocument|GlobalID";
        ID globalID = rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID();
        String value = globalID.getValue() == null ? "" : globalID.getValue();
        (new Rule_22()).checkGlobalID(value, object, errors);
    }

    // ???
    // // rule 8.23
    // // (3.3.6) SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty
    // if (isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()))) {
    //     String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty";
    //     (new Rule_23()).checkInvoicerTradeParty(transactionType, object, errors);
    // }

        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }
}
