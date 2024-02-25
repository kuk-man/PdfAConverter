package rd.checker.mandatory_field;

import model.pojo.RootXml;
import model.pojo.exchanged_document_context.GuidelineSpecifiedDocumentContextParameter;
import model.pojo.trade_transaction.application_header.DefinedTradeContact;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;

public class AbbreviatedTaxInvoiceMandatoryField extends MandatoryFieldChecker {
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
        // 3.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery");
        // 3.2.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty");
        // 3.2.2.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|Name");
        // 3.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement");
        // 3.3.5
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation");
        // 3.3.5.8
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getGrandTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount");
        // 3.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            for (IncludedSupplyChainTradeLineItem isctlt : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
        // 3.4.1
                checkValue(new N<>(() -> "" + isctlt.getAssociatedDocumentLineDocument()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument");
        // 3.4.1.1
                checkValue(new N<>(() -> "" + isctlt.getAssociatedDocumentLineDocument().getLineID()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID");
        // 3.4.2
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedTradeProduct()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct");
        // 3.4.2.3
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedTradeProduct().getName()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name");
        // 3.4.3
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeAgreement()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement");
        // 3.4.3.1
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice");
        // 3.4.3.1.1
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getChargeAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount");
        // 3.4.4
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeDelivery()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery");
        // 3.4.5
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement");
        // 3.4.5.3
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation");
        // 3.4.5.3.2
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetLineTotalAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount");
        // 3.4.5.3.3
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation().getNetIncludingTaxesLineTotalAmount()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount");
            }
        }
    }

    public void checkNotUsedField(RootXml rootXml) {
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
        // 3.1.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
        // 3.1.5.1
                checkNull(new N<>(() -> "" + ard.getIssuerAssignedID()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID");
        // 3.1.5.2
                checkNull(new N<>(() -> "" + ard.getIssueDateTime()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime");
        // 3.1.5.3
                checkNull(new N<>(() -> "" + ard.getReferenceTypeCode()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|ReferenceTypeCode");
            }
        }
        // 3.3.5.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount");
        // 3.3.5.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount");
        // 3.3.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty");
        // 3.3.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID");
        // 3.3.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getGlobalID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID");
        // 3.3.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|Name");
        // 3.3.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration");
        // 3.3.6.4.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getDefinedTradeContact()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getDefinedTradeContact()))) {
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getDefinedTradeContact()) {
        // 3.3.6.5.1
                checkNull(new N<>(() -> "" + dtc.getPersonName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|PersonName");
        // 3.3.6.5.2
                checkNull(new N<>(() -> "" + dtc.getDepartmentName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|DepartmentName");
        // 3.3.6.5.3
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication");
        // 3.3.6.5.3.1
                checkNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID");
        // 3.3.6.5.4
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication");
        // 3.3.6.5.4.1
                checkNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber");
            }
        }
        // 3.3.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress");
        // 3.3.6.6.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getPostcodeCode()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|PostcodeCode");
        // 3.3.6.6.2
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getBuildingName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|BuildingName");
        // 3.3.6.6.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getLineOne()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineOne");
        // 3.3.6.6.4
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getLineTwo()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineTwo");
        // 3.3.6.6.5
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getLineThree()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineThree");
        // 3.3.6.6.6
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getLineFour()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineFour");
        // 3.3.6.6.7
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getLineFive()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineFive");
        // 3.3.6.6.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getStreetName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|StreetName");
        // 3.3.6.6.9
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCityName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CityName");
        // 3.3.6.6.10
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCitySubDivisionName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CitySubDivisionName");
        // 3.3.6.6.11
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CountryID");
        // 3.3.6.6.12
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCountrySubDivisionID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CountrySubDivisionID");
        // 3.3.6.6.13
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getBuildingNumber()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|BuildingNumber");
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
    }
}
