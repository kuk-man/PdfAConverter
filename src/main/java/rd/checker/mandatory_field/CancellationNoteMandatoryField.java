package rd.checker.mandatory_field;

import model.pojo.RootXml;
import model.pojo.exchanged_document_context.GuidelineSpecifiedDocumentContextParameter;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.application_header.DefinedTradeContact;
import model.pojo.trade_transaction.application_header.SpecifiedTradePaymentTerms;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;
import model.pojo.trade_transaction.trade_line_item.IndividualTradeProductInstance;
import model.pojo.trade_transaction.trade_line_item.InformationNote;
import model.pojo.trade_transaction.trade_line_item.charge.AppliedTradeAllowanceCharge;
import model.pojo.trade_transaction.trade_line_item.charge.SpecifiedTradeAllowanceCharge;

public class CancellationNoteMandatoryField extends MandatoryFieldChecker {
    public void checkRequiredField(RootXml rootXml) {
        // 0
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice()),
            "CrossIndustryInvoice");
        // 1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocumentContext()),
            "CrossIndustryInvoice|ExchangedDocumentContext");
        // 1.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocumentContext().getGuidelineSpecifiedDocumentContextParameter()), 
            "CrossIndustryInvoice|ExchangedDocumentContext|GuidelineSpecifiedDocumentContextParameter");
        // 1.1.1
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocumentContext().getGuidelineSpecifiedDocumentContextParameter()))) {
            for (GuidelineSpecifiedDocumentContextParameter gsdcp : rootXml.getCrossIndustryInvoice().getExchangedDocumentContext().getGuidelineSpecifiedDocumentContextParameter()) {
                checkValue(new N<>(() -> "" + gsdcp.getId()),
                    "CrossIndustryInvoice|ExchangedDocumentContext|GuidelineSpecifiedDocumentContextParameter|ID");
            }
        }
        // 2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument()),
            "CrossIndustryInvoice|ExchangedDocument");
        // 2.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getId()),
            "CrossIndustryInvoice|ExchangedDocument|ID");
        // 2.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getName()),
            "CrossIndustryInvoice|ExchangedDocument|Name");
        // 2.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getTypeCode()),
            "CrossIndustryInvoice|ExchangedDocument|TypeCode");
        // 2.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getIssueDateTime()),
            "CrossIndustryInvoice|ExchangedDocument|IssueDateTime");
        // 2.8
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime()),
            "CrossIndustryInvoice|ExchangedDocument|CreationDateTime");
        // 3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction");
        // 3.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement");
        // 3.1.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty");
        // 3.1.1.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|Name");
        // 3.1.1.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|pecifiedTaxRegistration");
        // 3.1.1.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|pecifiedTaxRegistration|ID");
        // 3.1.1.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress");
        // 3.1.1.6.11
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|CountryID");
        // 3.1.5
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
        // 3.1.5.1
                checkValue(new N<>(() -> "" + ard.getIssuerAssignedID()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID");
        // 3.1.5.2
                checkValue(new N<>(() -> "" + ard.getIssueDateTime()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime");
        // 3.1.5.3
                checkValue(new N<>(() -> "" + ard.getReferenceTypeCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|ReferenceTypeCode");
            }
        }
        // 3.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement");
        // 3.3.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty");
        // 3.3.6.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|Name");
        // 3.3.6.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration");
        // 3.3.6.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.6.6.11
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CountryID");
    }

    public void checkNotUsedField(RootXml rootXml) {
        // 2.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurposeCode()),
            "CrossIndustryInvoice|ExchangedDocument|PurposeCode");
        // 3.1.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty");
        // 3.1.2.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID");
        // 3.1.2.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID");
        // 3.1.2.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|Name");
        // 3.1.2.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration");
        // 3.1.2.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.1.2.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getDefinedTradeContact()) {
        // 3.1.2.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|PersonName");
        // 3.1.2.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|DepartmentName");
        // 3.1.2.5.3
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication");
        // 3.1.2.5.3.1
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID");
        // 3.1.2.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.1.2.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.1.2.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress");
        // 3.1.2.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.1.2.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|BuildingName");
        // 3.1.2.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineOne");
        // 3.1.2.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineTwo");
        // 3.1.2.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineThree");
        // 3.1.2.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineFour");
        // 3.1.2.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineFive");
        // 3.1.2.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|StreetName");
        // 3.1.2.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CityName");
        // 3.1.2.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.1.2.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CountryID");
        // 3.3.6.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.1.2.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.1.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms");
        // 3.1.3.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getApplicableTradeDeliveryTerms().getDeliveryTypeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|ApplicableTradeDeliveryTerms|DeliveryTypeCode");
        // 3.1.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument");
        // 3.1.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssuerAssignedID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssuerAssignedID");
        // 3.1.4.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime");
        // 3.1.4.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getReferenceTypeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|ReferenceTypeCode");
        // 3.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery");
        // 3.2.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty");
        // 3.2.1.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID");
        // 3.2.1.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID");
        // 3.2.1.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|Name");
        // 3.2.1.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getDefinedTradeContact()) {
        // 3.2.1.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|PersonName");
        // 3.2.1.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|DepartmentName");
        // 3.2.1.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.2.1.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.2.1.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress");
        // 3.2.1.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.2.1.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|BuildingName");
        // 3.2.1.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineOne");
        // 3.2.1.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineTwo");
        // 3.2.1.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineThree");
        // 3.2.1.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineFour");
        // 3.2.1.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineFive");
        // 3.2.1.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|StreetName");
        // 3.2.1.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CityName");
        // 3.2.1.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.2.1.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CountryID");
        // 3.2.1.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.2.1.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.2.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty");
        // 3.2.2.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID");
        // 3.2.2.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID");
        // 3.2.2.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|Name");
        // 3.2.2.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getDefinedTradeContact()) {
        // 3.2.2.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|PersonName");
        // 3.2.2.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|DepartmentName");
        // 3.2.2.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.2.2.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.2.2.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress");
        // 3.2.2.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.2.2.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|BuildingName");
        // 3.2.2.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineOne");
        // 3.2.2.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineTwo");
        // 3.2.2.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineThree");
        // 3.2.2.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineFour");
        // 3.2.2.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineFive");
        // 3.2.2.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|StreetName");
        // 3.2.2.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CityName");
        // 3.2.2.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.2.2.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CountryID");
        // 3.2.2.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.2.2.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.2.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent");
        // 3.2.3.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime");
        // 3.3.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceCurrencyCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceCurrencyCode");
        // 3.3.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
        // 3.3.2.1
                checkNull(new N<>(() -> "" + att.getTypeCode()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode");
        // 3.3.2.2
                checkNull(new N<>(() -> "" + att.getCalculatedRate()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedRate");
        // 3.3.2.3
                checkNull(new N<>(() -> "" + att.getBasisAmount()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount");
        // 3.3.2.4
                checkNull(new N<>(() -> "" + att.getCalculatedAmount()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedAmount");
            }
        }
        // 3.3.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            for (SpecifiedTradeAllowanceCharge stac : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
        // 3.3.3.1
            checkNull(new N<>(() -> "" + stac.getChargeIndicator()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator");
        // 3.3.3.2
            checkNull(new N<>(() -> "" + stac.getActualAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount");
        // 3.3.3.3
            checkNull(new N<>(() -> "" + stac.getReasonCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode");
        // 3.3.3.4
            checkNull(new N<>(() -> "" + stac.getReason()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|Reason");
        // 3.3.3.5
            checkNull(new N<>(() -> "" + stac.getTypeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode");
            }
        }
        // 3.3.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()))) {
            for (SpecifiedTradePaymentTerms stpt : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()) {
        // 3.3.4.1
            checkNull(new N<>(() -> "" + stpt.getDescription()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description");
        // 3.3.4.2
            checkNull(new N<>(() -> "" + stpt.getDueDateDateTime()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|DueDateDateTime");
        // 3.3.4.3
            checkNull(new N<>(() -> "" + stpt.getTypeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|TypeCode");
            }
        }
        // 3.3.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation");
        // 3.3.5.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount");
        // 3.3.5.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount");
        // 3.3.5.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount");
        // 3.3.5.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getAllowanceTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|AllowanceTotalAmount");
        // 3.3.5.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getChargeTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|ChargeTotalAmount");
        // 3.3.5.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxBasisTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxBasisTotalAmount");
        // 3.3.5.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount");
        // 3.3.5.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getGrandTotalAmount()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount");        
        // 3.3.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty");
        // 3.3.7.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID");
        // 3.3.7.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID");
        // 3.3.7.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|Name");
        // 3.3.7.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration");
        // 3.3.7.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.7.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getDefinedTradeContact()) {
        // 3.3.7.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|PersonName");
        // 3.3.7.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|DepartmentName");
        // 3.3.7.5.3
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication");
        // 3.3.7.5.3.1
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID");
        // 3.3.7.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.3.7.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.3.7.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress");
        // 3.3.7.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.3.7.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|BuildingName");
        // 3.3.7.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineOne");
        // 3.3.7.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineTwo");
        // 3.3.7.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineThree");
        // 3.3.7.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineFour");
        // 3.3.7.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineFive");
        // 3.3.7.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|StreetName");
        // 3.3.7.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CityName");
        // 3.3.7.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.3.7.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CountryID");
        // 3.3.7.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.3.7.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.3.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty");
        // 3.3.8.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID");
        // 3.3.8.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID");
        // 3.3.8.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|Name");
        // 3.3.8.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration");
        // 3.3.8.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.8.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getDefinedTradeContact()) {
        // 3.3.8.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|PersonName");
        // 3.3.8.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|DepartmentName");
        // 3.3.8.5.3
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication");
        // 3.3.8.5.3.1
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID");
        // 3.3.8.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.3.8.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.3.8.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress");
        // 3.3.8.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.3.8.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|BuildingName");
        // 3.3.8.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineOne");
        // 3.3.8.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineTwo");
        // 3.3.8.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineThree");
        // 3.3.8.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineFour");
        // 3.3.8.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineFive");
        // 3.3.8.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|StreetName");
        // 3.3.8.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CityName");
        // 3.3.8.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.3.8.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CountryID");
        // 3.3.8.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.3.8.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.3.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty");
        // 3.3.9.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID");
        // 3.3.9.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID");
        // 3.3.9.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|Name");
        // 3.3.9.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration");
        // 3.3.9.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.9.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getDefinedTradeContact()) {
        // 3.3.9.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|PersonName");
        // 3.3.9.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|DepartmentName");
        // 3.3.9.5.3
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication");
        // 3.3.9.5.3.1
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID");
        // 3.3.9.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.3.9.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.3.9.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress");
        // 3.3.9.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.3.9.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|BuildingName");
        // 3.3.9.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineOne");
        // 3.3.9.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineTwo");
        // 3.3.9.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineThree");
        // 3.3.9.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineFour");
        // 3.3.9.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineFive");
        // 3.3.9.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|StreetName");
        // 3.3.9.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CityName");
        // 3.3.9.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.3.9.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CountryID");
        // 3.3.9.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.3.9.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|BuildingNumber");
        // 3.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
        // 3.4.1
                checkNull(new N<>(() -> "" + isctli.getAssociatedDocumentLineDocument()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument");
        // 3.4.1.1
                checkNull(new N<>(() -> "" + isctli.getAssociatedDocumentLineDocument().getLineID()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID");
        // 3.4.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct");
        // 3.4.2.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|ID");
        // 3.4.2.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|GlobalID");
        // 3.4.2.3
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name");
        // 3.4.2.4
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getDescription()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Description");
        // 3.4.2.5
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance");
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()))) {
                    for (IndividualTradeProductInstance itpi : isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()) {
        // 3.4.2.5.1
                        checkNull(new N<>(() -> "" + itpi.getBatchID()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|BatchID");
        // 3.4.2.5.2
                        checkNull(new N<>(() -> "" + itpi.getExpiryDateTime()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime");
                    }
                }
        // 3.4.2.6
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getDesignatedProductClassification()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification");
        // 3.4.2.6.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getDesignatedProductClassification().getClassCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassCode");
        // 3.4.2.6.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getDesignatedProductClassification().getClassName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassName");
        // 3.4.2.7
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getOriginTradeCountry()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|OriginTradeCountry");
        // 3.4.2.7.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getOriginTradeCountry().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|OriginTradeCountry|ID");
        // 3.4.2.8
                checkNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getInformationNote()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote");
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getInformationNote()))) {
                    for (InformationNote in : isctli.getSpecifiedTradeProduct().getInformationNote()) {
        // 3.4.2.8.1
                        checkNull(new N<>(() -> "" + in.getSubject()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Subject");
        // 3.4.2.8.2
                        checkNull(new N<>(() -> "" + in.getContent()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Content");
                    }
                }
        // 3.4.3
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement");
        // 3.4.3.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice");
        // 3.4.3.1.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getChargeAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount");
        // 3.4.3.1.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge");
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    for (AppliedTradeAllowanceCharge atac : isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()) {
        // 3.4.3.1.2.1
                        checkNull(new N<>(() -> "" + atac.getChargeIndicator()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ChargeIndicator");
        // 3.4.3.1.2.2
                        checkNull(new N<>(() -> "" + atac.getActualAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ActualAmount");
        // 3.4.3.1.2.3
                        checkNull(new N<>(() -> "" + atac.getReasonCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ReasonCode");
        // 3.4.3.1.2.4
                        checkNull(new N<>(() -> "" + atac.getReason()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|Reason");
        // 3.4.3.1.2.5
                        checkNull(new N<>(() -> "" + atac.getTypeCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|TypeCode");
                    }
                }
        // 3.4.4
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeDelivery()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery");
        // 3.4.4.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeDelivery().getBilledQuantity()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity");
        // 3.4.4.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeDelivery().getPerPackageUnitQuantity()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity");
        // 3.4.5
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement");
        // 3.4.5.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()),
        "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax");
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()))) {
                    for (ApplicableTradeTax att : isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()) {
        // 3.4.5.1.1
                        checkNull(new N<>(() -> "" + att.getTypeCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|TypeCode");
        // 3.4.5.1.2
                        checkNull(new N<>(() -> "" + att.getCalculatedRate()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate");
        // 3.4.5.1.3
                        checkNull(new N<>(() -> "" + att.getBasisAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount");
        // 3.4.5.1.4
                        checkNull(new N<>(() -> "" + att.getCalculatedAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount");
                    }
                }
        // 3.4.5.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge");
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    for (SpecifiedTradeAllowanceCharge stac : isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
        // 3.4.5.2.1
                        checkNull(new N<>(() -> "" + stac.getChargeIndicator()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator");
        // 3.4.5.2.2
                        checkNull(new N<>(() -> "" + stac.getActualAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount");
        // 3.4.5.2.3
                        checkNull(new N<>(() -> "" + stac.getReasonCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode");
        // 3.4.5.2.4
                        checkNull(new N<>(() -> "" + stac.getReason()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|Reason");
        // 3.4.5.2.5
                        checkNull(new N<>(() -> "" + stac.getTypeCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode");
                    }
                }
        // 3.4.5.3
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation");
        // 3.4.5.3.1
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getTaxTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|TaxTotalAmount");
        // 3.4.5.3.2
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount");
        // 3.4.5.3.3
                checkNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetIncludingTaxesLineTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount");
            }
        }
    }
}
