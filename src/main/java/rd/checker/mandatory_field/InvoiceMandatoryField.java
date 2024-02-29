package rd.checker.mandatory_field;

import model.pojo.RootXml;
import model.pojo.exchanged_document_context.GuidelineSpecifiedDocumentContextParameter;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;

public class InvoiceMandatoryField extends MandatoryFieldChecker {
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
        } else {
            errors.setErrorMassage("Missing CrossIndustryInvoice|ExchangedDocumentContext|GuidelineSpecifiedDocumentContextParameter|ID");
        }
        // 2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument()),
            "CrossIndustryInvoice|ExchangedDocument");
        // 2.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getId()),
            "CrossIndustryInvoice|ExchangedDocument|ID");
        // 2.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getIssueDateTime()),
            "CrossIndustryInvoice|ExchangedDocument|IssueDateTime");
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
        // 3.1.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty");
        // 3.1.2.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration");
        // 3.1.2.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.1.2.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress");
        // 3.1.2.6.11
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CountryID");
        // 3.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery");
        // 3.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement");
        // 3.3.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty");
        // 3.3.6.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration");
        // 3.3.6.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.7
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty");
        // 3.3.7.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration");
        // 3.3.7.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.8
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty");
        // 3.3.8.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration");
        // 3.3.8.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.9
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty");
        // 3.3.9.4
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration");
        // 3.3.9.4.1
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID");
        // 3.3.9.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress");
        // 3.3.9.6.11
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCountryID()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CountryID");
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
        // 3.4.5
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement");
            }
        } else {
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement");
        }
    }

    public void checkNotUsedField(RootXml rootXml) {
        // 2.8
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime()),
            "CrossIndustryInvoice|ExchangedDocument|CreationDateTime");
        // 3.3.5.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount");
        // 3.3.5.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount");

    }
}
