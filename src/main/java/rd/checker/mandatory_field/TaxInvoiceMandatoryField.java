package rd.checker.mandatory_field;

import model.pojo.RootXml;
import model.pojo.exchanged_document_context.GuidelineSpecifiedDocumentContextParameter;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;

public class TaxInvoiceMandatoryField extends MandatoryFieldChecker {
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
        // 3.1.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty");
        // 3.1.2.3
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getName()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|Name");
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
        // // 3.1.5
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument");
        // if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
        //     for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
        // // 3.1.5.1
        //         checkValue(new N<>(() -> "" + ard.getIssuerAssignedID()),
        //             "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID");
        // // 3.1.5.2
        //         checkValue(new N<>(() -> "" + ard.getIssueDateTime()),
        //             "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime");
        // // 3.1.5.3
        //         checkValue(new N<>(() -> "" + ard.getReferenceTypeCode()),
        //             "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|ReferenceTypeCode");
        //     }
        // } else {
        //     errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID");
        //     errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime");
        //     errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|ReferenceTypeCode");
        // }
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
        // 3.3.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax");
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
            // 3.3.2.1
                checkValue(new N<>(() -> "" + att.getTypeCode()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode");
            // 3.3.2.2
                checkValue(new N<>(() -> "" + att.getCalculatedRate()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedRate");
            // 3.3.2.3
                checkValue(new N<>(() -> "" + att.getBasisAmount()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount");
            // 3.3.2.4
                checkValue(new N<>(() -> "" + att.getCalculatedAmount()),
                "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedAmount");
            }
        } else {
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|TypeCode");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedRate");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedAmount");
        }
        // 3.3.5
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation");
        // 3.3.5.2
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getLineTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount");
        // 3.3.5.6
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxBasisTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxBasisTotalAmount");
        // 3.3.5.7
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getTaxTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount");
        // 3.3.5.8
        checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getGrandTotalAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|GrandTotalAmount");
        // // 3.3.6
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty");
        // // 3.3.6.3
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getName()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|Name");
        // // 3.3.6.4
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration");
        // // 3.3.6.4.1
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getSpecifiedTaxRegistration().getId()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID");
        // // 3.3.6.6
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress");
        // // 3.3.6.6.11
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress().getCountryID()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CountryID");
        // // 3.3.7
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty");
        // // 3.3.7.3
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getName()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|Name");
        // // 3.3.7.4
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration");
        // // 3.3.7.4.1
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getSpecifiedTaxRegistration().getId()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID");
        // // 3.3.7.6
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress");
        // // 3.3.7.6.1
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getPostcodeCode()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|PostcodeCode");
        // // 3.3.7.6.11
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress().getCountryID()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CountryID");
        // // 3.3.8
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty");
        // // 3.3.8.3
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getName()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|Name");
        // // 3.3.8.4
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration");
        // // 3.3.8.4.1
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getSpecifiedTaxRegistration().getId()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID");
        // // 3.3.9
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty");
        // // 3.3.9.3
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getName()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|Name");
        // // 3.3.9.4
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration");
        // // 3.3.9.4.1
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getSpecifiedTaxRegistration().getId()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID");
        // // 3.3.9.6
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress");
        // // 3.3.9.6.11
        // checkValue(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress().getCountryID()),
        //     "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CountryID");
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
        // 3.4.5.1
                checkValue(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement().getApplicableTradeTax()),
                    "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax");
                if (!isNull(new N<>(() -> "" + isctlt.getSpecifiedLineTradeSettlement().getApplicableTradeTax()))) {
                    for (ApplicableTradeTax satt : isctlt.getSpecifiedLineTradeSettlement().getApplicableTradeTax()) {
        // 3.4.5.1.1
                        checkValue(new N<>(() -> "" + satt.getTypeCode()),
                            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|TypeCode");
        // 3.4.5.1.2
                        checkValue(new N<>(() -> "" + satt.getCalculatedRate()),
                            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate");
        // 3.4.5.1.3
                        checkValue(new N<>(() -> "" + satt.getBasisAmount()),
                            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount");
        // 3.4.5.1.4
                        checkValue(new N<>(() -> "" + satt.getCalculatedAmount()),
                            "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount");
                    }
                } else {
                    errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|TypeCode");
                    errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate");
                    errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount");
                    errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount");
                }
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
        } else {
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|TypeCode");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount");
            errors.setErrorMassage("Missing CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount");
        }
    }

    public void checkNotUsedField(RootXml rootXml) {
        // 3.3.5.1
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getOriginalInformationAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount");
        // 3.3.5.3
        checkNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation().getDifferenceInformationAmount()),
            "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount");
    }
}
