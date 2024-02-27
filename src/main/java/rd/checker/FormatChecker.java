package rd.checker;

import model.ErrorMessage;
import model.pojo.RootXml;
import model.pojo.common.Amount;
import model.pojo.common.ClassCode;
import model.pojo.common.Description;
import model.pojo.common.DocumentContextParameterID;
import model.pojo.common.GlobalID;
import model.pojo.common.ID;
import model.pojo.common.InvolverTradeAddress;
import model.pojo.common.Name;
import model.pojo.common.ShipTradePostalTradeAddress;
import model.pojo.exchanged_document.IncludedNote;
import model.pojo.exchanged_document_context.GuidelineSpecifiedDocumentContextParameter;
import model.pojo.trade_transaction.application_header.ApplicableHeaderTradeSettlement;
import model.pojo.trade_transaction.application_header.ApplicableTradeTax;
import model.pojo.trade_transaction.application_header.DefinedTradeContact;
import model.pojo.trade_transaction.application_header.SpecifiedTradePaymentTerms;
import model.pojo.trade_transaction.application_header.SpecifiedTradeSettlementHeaderMonetarySummation;
import model.pojo.trade_transaction.application_header.document.AdditionalReferencedDocument;
import model.pojo.trade_transaction.application_header.tradeparty.SellerTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.ShipFromTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.ShipToTradeParty;
import model.pojo.trade_transaction.trade_line_item.GrossPriceProductTradePrice;
import model.pojo.trade_transaction.trade_line_item.IncludedSupplyChainTradeLineItem;
import model.pojo.trade_transaction.trade_line_item.IndividualTradeProductInstance;
import model.pojo.trade_transaction.trade_line_item.InformationNote;
import model.pojo.trade_transaction.trade_line_item.SpecifiedLineTradeDelivery;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeProduct;
import model.pojo.trade_transaction.trade_line_item.SpecifiedTradeSettlementLineMonetarySummation;
import model.pojo.trade_transaction.trade_line_item.charge.AppliedTradeAllowanceCharge;
import model.pojo.trade_transaction.trade_line_item.charge.SpecifiedTradeAllowanceCharge;
import model.pojo.trade_transaction.application_header.tradeparty.BuyerTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.InvoiceeTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.InvoicerTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.PayeeTradeParty;
import model.pojo.trade_transaction.application_header.tradeparty.PayerTradeParty;
import rd.checker.format.Format;

public class FormatChecker extends Checker{

    public FormatChecker() { 
        errors = new ErrorMessage();
    }

    public String verifyFormats(RootXml rootXml) {
        String errorMessage = checkFormat(rootXml);
        if (!errorMessage.isBlank())
            return "Check Formats:\n" + errorMessage;
        return "";
    }

    private String checkFormat(RootXml rootXml) {
        ErrorMessage errors = new ErrorMessage();
        Format format = new Format();

        // Percentage Rate
        // 3.3.2.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|ApplicableTradeTax|CalculatedRate
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|ApplicableTradeTax|CalculatedRate";
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
                if (!isNull(new N<>(() -> "" + att.getCalculatedRate()))) {
                    String rate = att.getCalculatedRate();
                    format.checkPercentageRate(rate, object, errors);
                }
            }
        }
        // 3.4.5.1.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedRate";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()))) {
                    for (ApplicableTradeTax att : isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()) {
                        if (!isNull(new N<>(() -> "" + att.getCalculatedRate()))) {                            
                            String rate = att.getCalculatedRate();
                            format.checkPercentageRate(rate, object, errors);
                        }
                    }
                }
            
            }
        }

        // Amount
        // 3.3.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|BasisAmount
        // 3.3.2.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax|CalculatedAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|ApplicableTradeTax";
            for (ApplicableTradeTax att : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getApplicableTradeTax()) {
                if (!isNull(new N<>(() -> "" + att.getBasisAmount()))) {
                    for (Amount amount : att.getBasisAmount()) {
                        if (!isNull(new N<>(() -> "" + amount.getValue())))
                            format.checkAmount(amount.getValue(), object + "|BasisAmount", errors);
                    }

                    for (Amount amount : att.getCalculatedAmount()) {
                        if (!isNull(new N<>(() -> "" + amount.getValue())))
                            format.checkAmount(amount.getValue(), object + "|CalculatedAmount", errors);
                    }
                }
            }
        }
        // 3.3.3.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount";
            for (SpecifiedTradeAllowanceCharge stac : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                if (!isNull(new N<>(() -> "" + stac.getActualAmount()))) {
                    for (Amount amount : stac.getActualAmount()) {
                        if (!isNull(new N<>(() -> "" + amount.getValue())))
                            format.checkAmount(amount.getValue(), object, errors);
                    }
                }
            }
        }
        // 3.3.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|OriginalInformationAmount
        // 3.3.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|LineTotalAmount
        // 3.3.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|DifferenceInformationAmount
        // 3.3.5.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|AllowanceTotalAmount
        // 3.3.5.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|ChargeTotalAmount
        // 3.3.5.6 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxBasisTotalAmount
        // 3.3.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation|TaxTotalAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeSettlementHeaderMonetarySummation";
            SpecifiedTradeSettlementHeaderMonetarySummation stshms = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeSettlementHeaderMonetarySummation();
            for (Amount amount : stshms.getOriginalInformationAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|OriginalInformationAmount", errors);
            }

            for (Amount amount : stshms.getLineTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|LineTotalAmount", errors);
            }

            for (Amount amount : stshms.getDifferenceInformationAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|DifferenceInformationAmount", errors);
            }

            for (Amount amount : stshms.getAllowanceTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|AllowanceTotalAmount", errors);
            }

            for (Amount amount : stshms.getChargeTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|ChargeTotalAmount", errors);
            }

            for (Amount amount : stshms.getTaxBasisTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|TaxBasisTotalAmount", errors);
            }

            for (Amount amount : stshms.getTaxTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|TaxTotalAmount", errors);
            }

            for (Amount amount : stshms.getGrandTotalAmount()) {
                if (!isNull(new N<>(() -> "" + amount.getValue())))
                    format.checkAmount(amount.getValue(), object + "|GrandTotalAmount", errors);
            }
        }

        // 3.4.3.1.1  SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|ChargeAmount
        // 3.4.3.1.2.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ActualAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice()))) {
                    GrossPriceProductTradePrice  gpptp = isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice();

                    if (!isNull(new N<>(() -> "" + gpptp.getChargeAmount()))) {
                        for (Amount amount : gpptp.getChargeAmount()) {
                            if (!isNull(new N<>(() -> "" + amount.getValue())))
                                format.checkAmount(amount.getValue(), object + "|ChargeAmount", errors);
                        }
                    }

                    if (!isNull(new N<>(() -> "" + gpptp.getAppliedTradeAllowanceCharge()))) {
                        for (AppliedTradeAllowanceCharge atac : gpptp.getAppliedTradeAllowanceCharge()) {
                            if (!isNull(new N<>(() -> "" + atac.getActualAmount()))) {
                                for (Amount amount : atac.getActualAmount()) {
                                    if (!isNull(new N<>(() -> "" + amount.getValue())))
                                        format.checkAmount(amount.getValue(), object + "|AppliedTradeAllowanceCharge|ActualAmount", errors);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 3.4.5.1.3 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|BasisAmount
        // 3.4.5.1.4 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax|CalculatedAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|ApplicableTradeTax";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()))) {
                    for (ApplicableTradeTax att : isctli.getSpecifiedLineTradeSettlement().getApplicableTradeTax()) {
                        if (!isNull(new N<>(() -> "" + att.getBasisAmount()))) {
                            for (Amount amount : att.getBasisAmount()) {
                                if (!isNull(new N<>(() -> "" + amount.getValue())))
                                    format.checkAmount(amount.getValue(), object + "|BasisAmount", errors);
                            }
                        }

                        if (!isNull(new N<>(() -> "" + att.getCalculatedAmount()))) {
                            for (Amount amount : att.getCalculatedAmount()) {
                                if (!isNull(new N<>(() -> "" + amount.getValue())))
                                    format.checkAmount(amount.getValue(), object + "|CalculatedAmount", errors);
                            }
                        }
                    }
                }
            }
        }
        // 3.4.5.2.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ActualAmount";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    for (SpecifiedTradeAllowanceCharge stac : isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + stac.getActualAmount()))) {
                            for (Amount amount : stac.getActualAmount()) {
                                if (!isNull(new N<>(() -> "" + amount.getValue())))
                                    format.checkAmount(amount.getValue(), object, errors);
                            }
                        }
                    }
                }
            }
        }
        // 3.4.5.3.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|TaxTotalAmount
        // 3.4.5.3.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetLineTotalAmount
        // 3.4.5.3.3 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation|NetIncludingTaxesLineTotalAmount
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeSettlementLineMonetarySummation";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation()))) {
                    SpecifiedTradeSettlementLineMonetarySummation stslms = isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeSettlementLineMonetarySummation();

                    if (!isNull(new N<>(() -> "" + stslms.getTaxTotalAmount()))) {
                        for (Amount amount : stslms.getTaxTotalAmount()) {
                            if (!isNull(new N<>(() -> "" + amount.getValue())))
                                format.checkAmount(amount.getValue(), object + "|NetLineTotalAmount", errors);
                        }
                    }

                    if (!isNull(new N<>(() -> "" + stslms.getNetLineTotalAmount()))) {
                        for (Amount amount : stslms.getNetLineTotalAmount()) {
                            if (!isNull(new N<>(() -> "" + amount.getValue())))
                                format.checkAmount(amount.getValue(), object + "|NetLineTotalAmount", errors);
                        }
                    }

                    if (!isNull(new N<>(() -> "" + stslms.getNetIncludingTaxesLineTotalAmount()))) {
                        for (Amount amount : stslms.getNetIncludingTaxesLineTotalAmount()) {
                            if (!isNull(new N<>(() -> "" + amount.getValue())))
                                format.checkAmount(amount.getValue(), object + "|NetIncludingTaxesLineTotalAmount", errors);
                        }
                    }
                }
            }
        }


        // Quantity
        // 3.4.4.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|BilledQuantity
        // 3.4.4.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery|PerPackageUnitQuantity
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeDelivery";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeDelivery()))) {
                    SpecifiedLineTradeDelivery  sltd = isctli.getSpecifiedLineTradeDelivery();

                    if (!isNull(new N<>(() -> "" + sltd.getBilledQuantity().getValue())))
                        format.checkAmount(sltd.getBilledQuantity().getValue(), object + "|BilledQuantity", errors);

                    if (!isNull(new N<>(() -> "" + sltd.getPerPackageUnitQuantity().getValue())))
                        format.checkAmount(sltd.getPerPackageUnitQuantity().getValue(), object + "|BilledQuantity", errors);
                }
                
            }
        }

        // ISO DateTime
        // 2.4 ExchangedDocument|IssueDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getIssueDateTime()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|IssueDateTime";
            String issueDateTime = rootXml.getCrossIndustryInvoice().getExchangedDocument().getIssueDateTime();
            format.checkIsoDateTime(issueDateTime, object, errors);
        }
        // 2.8 ExchangedDocument|CreationDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|CreationDateTime";
            String[] creationDateTimes = rootXml.getCrossIndustryInvoice().getExchangedDocument().getCreationDateTime();
            for (String creationDateTime : creationDateTimes) {
                format.checkIsoDateTime(creationDateTime, object, errors);
            }
        }
        // 3.1.4.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssueDateTime";
            String issueDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssueDateTime();
            format.checkIsoDateTime(issueDateTime, object, errors);
        }
        // 3.1.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssueDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument";
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {
                if (!isNull(new N<>(() -> "" + ard.getIssueDateTime())))
                    format.checkIsoDateTime(ard.getIssueDateTime(), object + "|IssueDateTime", errors);
            }
        }
        // 3.2.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ActualDeliverySupplyChainEvent|OccurrenceDateTime";
            String issueDateTime = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getActualDeliverySupplyChainEvent().getOccurrenceDateTime();
            format.checkIsoDateTime(issueDateTime, object, errors);
        }
        // 3.3.4.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|DueDateDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|DueDateDateTime";
            for (SpecifiedTradePaymentTerms stpt : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradePaymentTerms()) {
                if (!isNull(new N<>(() -> "" + stpt.getDueDateDateTime())))
                    format.checkIsoDateTime(stpt.getDueDateDateTime(), object, errors);
            }
        }
        // 3.4.2.5.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|ExpiryDateTime";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()))) {
                    for (IndividualTradeProductInstance itpi : isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()) {
                        if (!isNull(new N<>(() -> "" + itpi.getExpiryDateTime())))
                            format.checkIsoDateTime(itpi.getExpiryDateTime(), object, errors);
                    }
                }
            }
        }

        // TrueFalse
        // 3.3.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator";
            for (SpecifiedTradeAllowanceCharge stac : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                if (!isNull(new N<>(() -> "" + stac.getChargeIndicator())))
                    format.checkTrueFalse(stac.getChargeIndicator(), object, errors);
            }
        }
        // 3.4.3.1.2.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ChargeIndicator
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ChargeIndicator";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    for (AppliedTradeAllowanceCharge atac : isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + atac.getChargeIndicator())))
                            format.checkTrueFalse(atac.getChargeIndicator(), object, errors);
                    }
                }
            }
        }
        // 3.4.5.2.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ChargeIndicator";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    for (SpecifiedTradeAllowanceCharge stac : isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + stac.getChargeIndicator())))
                            format.checkTrueFalse(stac.getChargeIndicator(), object, errors);
                    }
                }
            }
        }

        // PhoneNumber
        // 3.2.1.4.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) {
                    String phoneNumber = dtc.getTelephoneUniversalCommunication().getCompleteNumber();
                    format.checkPhoneNumber(phoneNumber, object, errors);
                }
            }
        }
        // 3.2.2.4.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) {
                    String phoneNumber = dtc.getTelephoneUniversalCommunication().getCompleteNumber();
                    format.checkPhoneNumber(phoneNumber, object, errors);
                }
            }
        }

        // Max16Text
        // 3.1.1.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|PostcodeCode
        // 3.1.1.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.1.2.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|PostcodeCode
        // 3.1.2.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.2.1.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|PostcodeCode
        // 3.2.1.5.13 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress";
            ShipTradePostalTradeAddress sita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + sita.getPostcodeCode())))
                format.check16Text(sita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + sita.getBuildingName())))
                format.check16Text(sita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.2.2.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|PostcodeCode
        // 3.2.2.5.13 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress";
            ShipTradePostalTradeAddress sita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + sita.getPostcodeCode())))
                format.check16Text(sita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + sita.getBuildingName())))
                format.check16Text(sita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.3.3.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode
        // 3.3.3.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge";
            for (SpecifiedTradeAllowanceCharge stac : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                if (!isNull(new N<>(() -> "" + stac.getReasonCode())))
                    format.check16Text(stac.getReasonCode(), object + "|ReasonCode", errors);

                if (!isNull(new N<>(() -> "" + stac.getTypeCode())))
                    format.checkPhoneNumber(stac.getTypeCode(), object + "|TypeCode", errors); 
            }
        }
        // 3.3.6.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|PostcodeCode
        // 3.3.6.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.3.7.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|PostcodeCode
        // 3.3.7.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.3.8.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|PostcodeCode
        // 3.3.8.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.3.9.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|PostcodeCode
        // 3.3.9.6.13 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|BuildingNumber
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress";
            InvolverTradeAddress ita = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getPostalTradeAddress();
            if (!isNull(new N<>(() -> "" + ita.getPostcodeCode())))
                format.check16Text(ita.getPostcodeCode(), object + "|PostcodeCode", errors);

            if (!isNull(new N<>(() -> "" + ita.getBuildingName())))
                format.check16Text(ita.getBuildingName(), object + "|BuildingNumber", errors);
        }
        // 3.4.3.1.2.3 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|ReasonCode
        // 3.4.3.1.2.5 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    for (AppliedTradeAllowanceCharge atac : isctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + atac.getReasonCode())))
                            format.check16Text(atac.getReasonCode(), object + "|ReasonCode", errors);
            
                        if (!isNull(new N<>(() -> "" + atac.getTypeCode())))
                            format.checkPhoneNumber(atac.getTypeCode(), object + "|TypeCode", errors);        
                    }
                }
            }
        }
        // 3.4.5.2.3 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|ReasonCode
        // 3.4.5.2.5 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|TypeCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    for (SpecifiedTradeAllowanceCharge stac : isctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + stac.getReasonCode())))
                            format.check16Text(stac.getReasonCode(), object + "|ReasonCode", errors);
            
                        if (!isNull(new N<>(() -> "" + stac.getTypeCode())))
                            format.checkPhoneNumber(stac.getTypeCode(), object + "|TypeCode", errors);
                    }
                }
            }
        }
        
        // Max35Text
        // 1.1.1 ExchangedDocumentContext|GuidelineSpecifiedDocumentContextParameter|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocumentContext().getGuidelineSpecifiedDocumentContextParameter()))) {
            String object = "CrossIndustryInvoice|ExchangedDocumentContext|GuidelineSpecifiedDocumentContextParameter|ID";
            for (GuidelineSpecifiedDocumentContextParameter gsdcp : rootXml.getCrossIndustryInvoice().getExchangedDocumentContext().getGuidelineSpecifiedDocumentContextParameter()) {
                if (!isNull(new N<>(() -> "" + gsdcp.getId()))) {
                    DocumentContextParameterID id = gsdcp.getId();
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object, errors);
                }
            }
        }
        // 2.1 ExchangedDocument|ID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getId()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|ID";
            String id = rootXml.getCrossIndustryInvoice().getExchangedDocument().getId();
            format.check35Text(id, object, errors);
        }
        // 2.2 ExchangedDocument|Name
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getName()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|Name";
            for (String name : rootXml.getCrossIndustryInvoice().getExchangedDocument().getName()) 
                format.check35Text(name, object, errors);
        }
        // 3.1.1.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|ID
        // 3.1.1.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|SpecifiedTaxRegistration|ID
        // 3.1.1.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.1.1.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty";
            SellerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.1.2.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|ID
        // 3.1.2.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|SpecifiedTaxRegistration|ID
        // 3.1.2.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.1.2.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty";
            BuyerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.1.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssuerAssignedID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssuerAssignedID()))) {
            String object = "SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerOrderReferencedDocument|IssuerAssignedID";
            String issuerAssignedID = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerOrderReferencedDocument().getIssuerAssignedID();
            format.check35Text(issuerAssignedID, object, errors);
        }
        // 3.1.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()))) {
            String object = "SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|AdditionalReferencedDocument|IssuerAssignedID";
            for (AdditionalReferencedDocument ard : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getAdditionalReferencedDocument()) {            
                if (!isNull(new N<>(() -> "" + ard.getIssuerAssignedID())))
                    format.check35Text(ard.getIssuerAssignedID(), object, errors);
            }
        }
        // 3.2.1.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|ID
        // 3.2.1.5.12 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradePart";
            ShipToTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID()))) {
                for (String countrySubDivisionID : tp.getPostalTradeAddress().getCountrySubDivisionID())
                    format.check35Text(countrySubDivisionID, object + "|PostalTradeAddress|CountrySubDivisionID", errors);
            }
        }
        // 3.2.2.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|ID
        // 3.2.2.5.12 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty";
            ShipFromTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID()))) {
                for (String countrySubDivisionID : tp.getPostalTradeAddress().getCountrySubDivisionID())
                    format.check35Text(countrySubDivisionID, object + "|PostalTradeAddress|CountrySubDivisionID", errors);
            }
        }
        // 3.3.6.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|ID
        // 3.3.6.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|SpecifiedTaxRegistration|ID
        // 3.3.6.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.3.6.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty";
            InvoicerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.3.7.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|ID
        // 3.3.7.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|SpecifiedTaxRegistration|ID
        // 3.3.7.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.3.7.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty";
            InvoiceeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.3.8.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|ID
        // 3.3.8.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|SpecifiedTaxRegistration|ID
        // 3.3.8.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.3.8.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty";
            PayerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.3.9.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|ID
        // 3.3.9.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|SpecifiedTaxRegistration|ID
        // 3.3.9.5.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber
        // 3.3.9.6.12 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CountrySubDivisionID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty";
            PayeeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getId()))) {
                for (ID id : tp.getId()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check35Text(id.getValue(), object + "|ID", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + tp.getSpecifiedTaxRegistration().getId()))) {
                ID id = tp.getSpecifiedTaxRegistration().getId();
                if (!isNull(new N<>(() -> "" + id.getValue())))
                    format.check35Text(id.getValue(), object + "|SpecifiedTaxRegistration|ID", errors);
            }

            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getTelephoneUniversalCommunication().getCompleteNumber()))) 
                        format.check35Text(dtc.getTelephoneUniversalCommunication().getCompleteNumber(), object + "|DefinedTradeContact|TelephoneUniversalCommunication|CompleteNumber", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCountrySubDivisionID())))
                format.check35Text(tp.getPostalTradeAddress().getCountrySubDivisionID(), object + "|PostalTradeAddress|CountrySubDivisionID", errors);
        }
        // 3.4.1.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|AssociatedDocumentLineDocument|LineID";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getAssociatedDocumentLineDocument().getLineID())))
                    format.check35Text(isctli.getAssociatedDocumentLineDocument().getLineID(), object, errors);
            }
        }
        // 3.4.2.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|ID
        // 3.4.2.5.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|IndividualTradeProductInstance|BatchID
        // 3.4.2.6.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassCode
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getId())))
                    format.check35Text(isctli.getSpecifiedTradeProduct().getId(), object + "|ID", errors);

                for (IndividualTradeProductInstance itpi : isctli.getSpecifiedTradeProduct().getIndividualTradeProductInstance()) {
                    if (!isNull(new N<>(() -> "" + itpi.getBatchID())))
                        format.check35Text(itpi.getBatchID(), object + "|IndividualTradeProductInstance|BatchID", errors);
                }

                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getDesignatedProductClassification().getClassCode()))) {
                    ClassCode classCode = isctli.getSpecifiedTradeProduct().getDesignatedProductClassification().getClassCode();
                    if (!isNull(new N<>(() -> "" + classCode.getValue())))
                        format.check35Text(classCode.getValue(), object + "|DesignatedProductClassification|ClassCode", errors);
                }
            }
        }
        
        // Max70Text
        // 2.7 ExchangedDocument|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|GlobalID";
            ID id = rootXml.getCrossIndustryInvoice().getExchangedDocument().getGlobalID();
            if (!isNull(new N<>(() -> "" + id.getValue())))
                format.check70Text(id.getValue(), object, errors);
        }
        // 3.1.1.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|GlobalID
        // 3.1.1.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|BuildingName
        // 3.1.1.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|LineThree
        // 3.1.1.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|LineFour
        // 3.1.1.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|LineFive
        // 3.1.1.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|StreetName
        // 3.1.1.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|CityName
        // 3.1.1.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty";
            SellerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.1.2.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|GlobalID
        // 3.1.2.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|BuildingName
        // 3.1.2.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineThree
        // 3.1.2.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineFour
        // 3.1.2.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineFive
        // 3.1.2.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|StreetName
        // 3.1.2.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CityName
        // 3.1.2.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty";
            BuyerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.2.1.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|GlobalID
        // 3.2.1.5.5 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineThree
        // 3.2.1.5.6 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineFour
        // 3.2.1.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineFive
        // 3.2.1.5.8 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|StreetName
        // 3.2.1.5.9 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CityName
        // 3.2.1.5.10 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty";
            ShipToTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.2.2.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|GlobalID
        // 3.2.2.5.5 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineThree
        // 3.2.2.5.6 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineFour
        // 3.2.2.5.7 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineFive
        // 3.2.2.5.8 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|StreetName
        // 3.2.2.5.9 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CityName
        // 3.2.2.5.10 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty";
            ShipFromTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.3.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|GlobalID
        // 3.3.6.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|BuildingName
        // 3.3.6.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineThree
        // 3.3.6.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineFour
        // 3.3.6.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineFive
        // 3.3.6.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|StreetName
        // 3.3.6.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CityName
        // 3.3.6.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty";
            InvoicerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.3.7.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|GlobalID
        // 3.3.7.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|BuildingName
        // 3.3.7.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineThree
        // 3.3.7.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineFour
        // 3.3.7.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineFive
        // 3.3.7.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|StreetName
        // 3.3.7.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CityName
        // 3.3.7.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty";
            InvoiceeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.3.8.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|GlobalID
        // 3.3.8.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|BuildingName
        // 3.3.8.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineThree
        // 3.3.8.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineFour
        // 3.3.8.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineFive
        // 3.3.8.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|StreetName
        // 3.3.8.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CityName
        // 3.3.8.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty";
            PayerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.3.9.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|GlobalID
        // 3.3.9.6.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|BuildingName
        // 3.3.9.6.5 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineThree
        // 3.3.9.6.6 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineFour
        // 3.3.9.6.7 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineFive
        // 3.3.9.6.8 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|StreetName
        // 3.3.9.6.9 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CityName
        // 3.3.9.6.10 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|CitySubDivisionName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty";
            PayeeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty();

            if (!isNull(new N<>(() -> "" + tp.getGlobalID()))) {
                for (GlobalID id : tp.getGlobalID()) {
                    if (!isNull(new N<>(() -> "" + id.getValue())))
                        format.check70Text(id.getValue(), object + "|GlobalID", errors);
                }
            }
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check70Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineThree())))
                format.check70Text(tp.getPostalTradeAddress().getLineThree(), object + "|PostalTradeAddress|LineThree", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFour())))
                format.check70Text(tp.getPostalTradeAddress().getLineFour(), object + "|PostalTradeAddress|LineFour", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineFive())))
                format.check70Text(tp.getPostalTradeAddress().getLineFive(), object + "|PostalTradeAddress|LineFive", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getStreetName())))
                format.check70Text(tp.getPostalTradeAddress().getStreetName(), object + "|PostalTradeAddress|StreetName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCityName())))
                format.check70Text(tp.getPostalTradeAddress().getCityName(), object + "|PostalTradeAddress|CityName", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getCitySubDivisionName())))
                format.check70Text(tp.getPostalTradeAddress().getCitySubDivisionName(), object + "|PostalTradeAddress|CitySubDivisionName", errors);
        }
        // 3.4.2.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|GlobalID
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getGlobalID().getValue())))
                    format.check70Text(isctli.getSpecifiedTradeProduct().getGlobalID().getValue(), object + "|GlobalID", errors);
            }
        }

        // Max140Text
        // 3.1.1.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|DefinedTradeContact|PersonName
        // 3.1.1.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }
        // 3.1.2.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|PersonName
        // 3.1.2.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }
        // 3.2.1.3 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|Name
        // 3.2.1.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|PersonName
        // 3.2.1.4.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|DepartmentName
        // 3.2.1.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|BuildingName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty";
            ShipToTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                        format.check140Text(dtc.getPersonName(), object + "|DefinedTradeContact|PersonName", errors);
                    if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                        format.check140Text(dtc.getDepartmentName(), object + "|DefinedTradeContact|DepartmentName", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check140Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            
        }
        // 3.2.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|Name
        // 3.2.2.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|PersonName
        // 3.2.2.4.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|DepartmentName
        // 3.2.2.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|BuildingName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty";
            ShipFromTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                        format.check140Text(dtc.getPersonName(), object + "|DefinedTradeContact|PersonName", errors);
                    if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                        format.check140Text(dtc.getDepartmentName(), object + "|DefinedTradeContact|DepartmentName", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getBuildingName())))
                format.check140Text(tp.getPostalTradeAddress().getBuildingName(), object + "|PostalTradeAddress|BuildingName", errors);
            
        }
        // 3.3.6.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|PersonName
        // 3.3.6.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }
        // 3.3.7.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|PersonName
        // 3.3.7.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }
        // 3.3.8.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|PersonName
        // 3.3.8.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }
        // 3.3.9.5.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|PersonName
        // 3.3.9.5.2 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|DepartmentName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getDefinedTradeContact()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact";
            for (DefinedTradeContact dtc : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty().getDefinedTradeContact()) {
                if (!isNull(new N<>(() -> "" + dtc.getPersonName())))
                    format.check140Text(dtc.getPersonName(), object + "|PersonName", errors);
                if (!isNull(new N<>(() -> "" + dtc.getDepartmentName())))
                    format.check140Text(dtc.getDepartmentName(), object + "|DepartmentName", errors);
            }
        }

        // Max256Text
        // 2.5 ExchangedDocument|Purpose
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|Purpose";
            String purpose = rootXml.getCrossIndustryInvoice().getExchangedDocument().getPurpose();
            format.check256Text(purpose, object, errors);
        }

        // 3.1.1.3 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|Name
        // 3.1.1.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.1.1.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|LineOne
        // 3.1.1.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|SellerTradeParty";
            SellerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getSellerTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.1.2.3 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|Name
        // 3.1.2.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.1.2.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineOne
        // 3.1.2.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeAgreement|BuyerTradeParty";
            BuyerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeAgreement().getBuyerTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.2.1.4.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.2.1.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineOne
        // 3.2.1.5.4 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipToTradeParty";
            ShipToTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipToTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.2.2.4.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.2.2.5.3 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineOne
        // 3.2.2.5.4 SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeDelivery|ShipFromTradeParty";
            ShipFromTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeDelivery().getShipFromTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.3.3.4  SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradeAllowanceCharge|Reason
        // 3.3.4.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|SpecifiedTradePaymentTerms|Description
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement";
            ApplicableHeaderTradeSettlement ahts = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement();

            if (!isNull(new N<>(() -> "" + ahts.getSpecifiedTradeAllowanceCharge()))) {
                for (SpecifiedTradeAllowanceCharge stac : ahts.getSpecifiedTradeAllowanceCharge()) {
                    if (!isNull(new N<>(() -> "" + stac.getReason())))
                        format.check140Text(stac.getReason(), object + "|SpecifiedTradeAllowanceCharge|Reason", errors);
                }
            }
            
            if (!isNull(new N<>(() -> "" + ahts.getSpecifiedTradePaymentTerms()))) {
                for (SpecifiedTradePaymentTerms stpt : ahts.getSpecifiedTradePaymentTerms()) {
                    if (!isNull(new N<>(() -> "" + stpt.getDescription()))) {
                        for (Description desc : stpt.getDescription()) {
                            if (!isNull(new N<>(() -> "" + desc.getValue())))
                                format.check140Text(desc.getValue(), object + "|SpecifiedTradePaymentTerms|Description", errors);
                        }
                    }
                }
            }
        }
        // 3.3.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|Name
        // 3.3.6.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.3.6.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineOne
        // 3.3.6.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoicerTradeParty";
            InvoicerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoicerTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.3.7.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|Name
        // 3.3.7.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.3.7.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineOne
        // 3.3.7.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|InvoiceeTradeParty";
            InvoiceeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getInvoiceeTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.3.8.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|Name
        // 3.3.8.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.3.8.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineOne
        // 3.3.8.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayerTradeParty";
            PayerTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayerTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.3.9.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|Name
        // 3.3.9.5.3.1 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|DefinedTradeContact|EmailURIUniversalCommunication|URIID
        // 3.3.9.6.3 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineOne
        // 3.3.9.6.4 SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty|PostalTradeAddress|LineTwo
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|ApplicableHeaderTradeSettlement|PayeeTradeParty";
            PayeeTradeParty tp = rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getApplicableHeaderTradeSettlement().getPayeeTradeParty();
                
            if (!isNull(new N<>(() -> "" + tp.getName())))
                format.check140Text(tp.getName(), object + "|Name", errors);
                
            if (!isNull(new N<>(() -> "" + tp.getDefinedTradeContact()))) {
                for (DefinedTradeContact dtc : tp.getDefinedTradeContact()) {
                    if (!isNull(new N<>(() -> "" + dtc.getEmailURIUniversalCommunication().getUriID())))
                        format.check140Text(dtc.getEmailURIUniversalCommunication().getUriID(), object + "|EmailURIUniversalCommunication|URIID", errors);
                }
            }

            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineOne())))
                format.check140Text(tp.getPostalTradeAddress().getLineOne(), object + "|PostalTradeAddress|LineOne", errors);
            if (!isNull(new N<>(() -> "" + tp.getPostalTradeAddress().getLineTwo())))
                format.check140Text(tp.getPostalTradeAddress().getLineTwo(), object + "|PostalTradeAddress|LineTwo", errors);
        }
        // 3.4.2.3 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Name
        // 3.4.2.4 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|Description
        // 3.4.2.6.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|DesignatedProductClassification|ClassName
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct";
            for (IncludedSupplyChainTradeLineItem iscli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + iscli.getSpecifiedTradeProduct()))) {
                    SpecifiedTradeProduct stp = iscli.getSpecifiedTradeProduct();

                    if (!isNull(new N<>(() -> "" + stp.getName()))) {
                        for (Name name : stp.getName()) {
                            if (!isNull(new N<>(() -> "" + name.getValue())))
                                format.check140Text(name.getValue(), object + "|Name", errors);
                        }
                    }

                    if (!isNull(new N<>(() -> "" + stp.getDescription()))) {
                        for (String desc : stp.getDescription()) {
                            format.check140Text(desc, object + "|Description", errors);
                        }
                    }

                    if (!isNull(new N<>(() -> "" + stp.getDesignatedProductClassification().getClassName()))) {
                        for (Name className : stp.getDesignatedProductClassification().getClassName()) {
                            if (!isNull(new N<>(() -> "" + className.getValue())))
                                format.check140Text(className.getValue(), object + "|DesignatedProductClassification|ClassName", errors);
                        }
                    }
                }
            }
        }

        // 3.4.3.1.2.4 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|Reason
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeAgreement|GrossPriceProductTradePrice|AppliedTradeAllowanceCharge|Reason";
            for (IncludedSupplyChainTradeLineItem icsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + icsctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()))) {
                    for (AppliedTradeAllowanceCharge atac : icsctli.getSpecifiedLineTradeAgreement().getGrossPriceProductTradePrice().getAppliedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + atac.getReason())))
                            format.check140Text(atac.getReason(), object, errors);
                    }
                }
            }
        }
        // 3.4.5.2.4 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|Reason
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedLineTradeSettlement|SpecifiedTradeAllowanceCharge|Reason";
            for (IncludedSupplyChainTradeLineItem icsctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + icsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()))) {
                    for (SpecifiedTradeAllowanceCharge stac : icsctli.getSpecifiedLineTradeSettlement().getSpecifiedTradeAllowanceCharge()) {
                        if (!isNull(new N<>(() -> "" + stac.getReason())))
                            format.check140Text(stac.getReason(), object, errors);
                    }
                }
            }
        }
        
        // Max500Text
        // 2.9.1 ExchangedDocument|IncludedNote|Subject
        // 2.9.2 ExchangedDocument|IncludedNote|Content
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getExchangedDocument().getIncludedNote()))) {
            String object = "CrossIndustryInvoice|ExchangedDocument|IncludedNote|Content";
            for (IncludedNote in : rootXml.getCrossIndustryInvoice().getExchangedDocument().getIncludedNote()) {
                if (!isNull(new N<>(() -> "" + in.getSubject())))
                    format.check500Text(in.getSubject(), object + "|Subject", errors);

                if (!isNull(new N<>(() -> "" + in.getContent()))) {
                    for (String content : in.getContent()) {
                        format.check500Text(content, object + "|Content", errors);
                    }
                }
            }
        }
        // 3.4.2.8.1 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Subject
        // 3.4.2.8.2 SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote|Content
        if (!isNull(new N<>(() -> "" + rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()))) {
            String object = "CrossIndustryInvoice|SupplyChainTradeTransaction|IncludedSupplyChainTradeLineItem|SpecifiedTradeProduct|InformationNote";
            for (IncludedSupplyChainTradeLineItem isctli : rootXml.getCrossIndustryInvoice().getSupplyChainTradeTransaction().getIncludedSupplyChainTradeLineItem()) {
                if (!isNull(new N<>(() -> "" + isctli.getSpecifiedTradeProduct().getInformationNote()))) {
                    for (InformationNote in : isctli.getSpecifiedTradeProduct().getInformationNote()) {
                        if (!isNull(new N<>(() -> "" + in.getSubject())))
                            format.check500Text(in.getSubject(), object + "|Subject", errors);
                        
                        if (!isNull(new N<>(() -> "" + in.getContent()))) {
                            for (String content : in.getContent()) {
                                format.check500Text(content, object + "|Content", errors);
                            }
                        }
                    }
                }
            }
        }
            

        errors = format.getError();
        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }
}
