package rd.checker;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import model.ErrorMessage;
import model.pojo.RootXml;
import rd.checker.mandatory_field.AbbreviatedTaxInvoiceMandatoryField;
import rd.checker.mandatory_field.CancellationNoteMandatoryField;
import rd.checker.mandatory_field.DebitCreditNoteMandatoryField;
import rd.checker.mandatory_field.InvoiceMandatoryField;
import rd.checker.mandatory_field.MandatoryFieldChecker;
import rd.checker.mandatory_field.ReceiptMandatoryField;
import rd.checker.mandatory_field.TaxInvoiceMandatoryField;

public class TransactionMandatoryFieldChecker {
    public String verifyMandatoryField(RootXml rootXml) {
        String errorMessage = checkMandatoryField(rootXml);
        if (!errorMessage.isBlank())
            return "Check Mandatory Fields:\n" + errorMessage;
        return "";
    }

    private String checkMandatoryField(RootXml rootXml) {
        ErrorMessage errors;
        MandatoryFieldChecker mandatory;

        switch (rootXml.getTransaction()) {
            case "INV":
                mandatory = new InvoiceMandatoryField();
                break;
            case "RCT":
                mandatory = new ReceiptMandatoryField();
                break;
            case "TIV":
                mandatory = new TaxInvoiceMandatoryField();
                break;
            case "DCN":
                mandatory = new DebitCreditNoteMandatoryField();
                break;
            case "CLN":
                mandatory = new CancellationNoteMandatoryField();
                break;
            case "ABB":
                mandatory = new AbbreviatedTaxInvoiceMandatoryField();
                break;
            default:
                return "Invalid TransactionType (INV/RCT/TIV/DCN/ABB/CLN): Current TransactionType: " + rootXml.getTransaction();
        }

        mandatory.checkRequiredField(rootXml);
        errors = mandatory.getError();
        if (!errors.getErrorMessage().isBlank())
            return errors.getErrorMessage();
        return "";
    }

    // ID, Name, Tag, Min, Max
    protected String[] tagIds = new String[]{
        "0",
        "1",
            "1.1","1.1.1",
        "2",
            "2.1","2.2","2.3","2.4","2.5","2.6","2.7","2.8","2.9","2.9.1","2.9.2",
        "3",
            "3.1","3.1.1","3.1.1.1","3.1.1.2","3.1.1.3","3.1.1.4","3.1.1.4.1","3.1.1.5","3.1.1.5.1","3.1.1.5.2","3.1.1.5.3","3.1.1.5.3.1","3.1.1.5.4","3.1.1.5.4.1","3.1.1.6","3.1.1.6.1","3.1.1.6.2","3.1.1.6.3","3.1.1.6.4","3.1.1.6.5","3.1.1.6.6","3.1.1.6.7","3.1.1.6.8","3.1.1.6.9","3.1.1.6.10","3.1.1.6.11","3.1.1.6.12","3.1.1.6.13","3.1.2","3.1.2.1","3.1.2.2","3.1.2.3","3.1.2.4","3.1.2.4.1","3.1.2.5","3.1.2.5.1","3.1.2.5.2","3.1.2.5.3","3.1.2.5.3.1","3.1.2.5.4","3.1.2.5.4.1","3.1.2.6","3.1.2.6.1","3.1.2.6.2","3.1.2.6.3","3.1.2.6.4","3.1.2.6.5","3.1.2.6.6","3.1.2.6.7","3.1.2.6.8","3.1.2.6.9","3.1.2.6.10","3.1.2.6.11","3.3.6.6.12","3.1.2.6.13","3.1.3","3.1.3.1","3.1.4","3.1.4.1","3.1.4.2","3.1.4.3","3.1.5","3.1.5.1","3.1.5.2","3.1.5.3",
            "3.2","3.2.1","3.2.1.1","3.2.1.2","3.2.1.3","3.2.1.4","3.2.1.4.1","3.2.1.4.2","3.2.1.4.3","3.2.1.4.3.1","3.2.1.4.4","3.2.1.4.4.1","3.2.1.5","3.2.1.5.1","3.2.1.5.2","3.2.1.5.3","3.2.1.5.4","3.2.1.5.5","3.2.1.5.6","3.2.1.5.7","3.2.1.5.8","3.2.1.5.9","3.2.1.5.10","3.2.1.5.11","3.2.1.5.12","3.2.1.5.13","3.2.2 ","3.2.2.1","3.2.2.2","3.2.2.3","3.2.2.4","3.2.2.4.1","3.2.2.4.2","3.2.2.4.3","3.2.2.4.3.1","3.2.2.4.4","3.2.2.4.4.1","3.2.2.5","3.2.2.5.1","3.2.2.5.2","3.2.2.5.3","3.2.2.5.4","3.2.2.5.5","3.2.2.5.6","3.2.2.5.7","3.2.2.5.8","3.2.2.5.9","3.2.2.5.10","3.2.2.5.11","3.2.2.5.12","3.2.2.5.13","3.2.3","3.2.3.1",
            "3.3","3.3.1","3.3.2","3.3.2.1","3.3.2.2","3.3.2.3","3.3.2.4 ","3.3.3","3.3.3.1","3.3.3.2","3.3.3.3","3.3.3.4","3.3.3.5","3.3.4","3.3.4.1","3.3.4.2","3.3.4.3","3.3.5","3.3.5.1","3.3.5.2","3.3.5.3","3.3.5.4","3.3.5.5","3.3.5.6","3.3.5.7","3.3.5.8","3.3.6","3.3.6.1","3.3.6.2","3.3.6.3","3.3.6.4","3.3.6.4.1","3.3.6.5","3.3.6.5.1","3.3.6.5.2","3.3.6.5.3","3.3.6.5.3.1","3.3.6.5.4","3.3.6.5.4.1","3.3.6.6","3.3.6.6.1","3.3.6.6.2","3.3.6.6.3","3.3.6.6.4","3.3.6.6.5","3.3.6.6.6","3.3.6.6.7","3.3.6.6.8","3.3.6.6.9","3.3.6.6.10","3.3.6.6.11","3.3.6.6.12","3.3.6.6.13","3.3.7","3.3.7.1","3.3.7.2","3.3.7.3","3.3.7.4","3.3.7.4.1","3.3.7.5","3.3.7.5.1","3.3.7.5.2","3.3.7.5.3","3.3.7.5.3.1","3.3.7.5.4","3.3.7.5.4.1","3.3.7.6","3.3.7.6.1","3.3.7.6.2","3.3.7.6.3","3.3.7.6.4","3.3.7.6.5","3.3.7.6.6","3.3.7.6.7","3.3.7.6.8","3.3.7.6.9","3.3.7.6.10","3.3.7.6.11","3.3.7.6.12","3.3.7.6.13","3.3.8","3.3.8.1","3.3.8.2","3.3.8.3","3.3.8.4","3.3.8.4.1","3.3.8.5","3.3.8.5.1","3.3.8.5.2","3.3.8.5.3","3.3.8.5.3.1","3.3.8.5.4","3.3.8.5.4.1","3.3.8.6","3.3.8.6.1","3.3.8.6.2","3.3.8.6.3","3.3.8.6.4","3.3.8.6.5","3.3.8.6.6","3.3.8.6.7","3.3.8.6.8","3.3.8.6.9","3.3.8.6.10","3.3.8.6.11","3.3.8.6.12","3.3.8.6.13","3.3.9","3.3.9.1","3.3.9.2","3.3.9.3","3.3.9.4","3.3.9.4.1","3.3.9.5","3.3.9.5.1","3.3.9.5.2","3.3.9.5.3","3.3.9.5.3.1","3.3.9.5.4","3.3.9.5.4.1","3.3.9.6","3.3.9.6.1","3.3.9.6.2","3.3.9.6.3","3.3.9.6.4","3.3.9.6.5","3.3.9.6.6","3.3.9.6.7","3.3.9.6.8","3.3.9.6.9","3.3.9.6.10","3.3.9.6.11","3.3.9.6.12","3.3.9.6.13",
            "3.4","3.4.1","3.4.1.1","3.4.2","3.4.2.1","3.4.2.2","3.4.2.3","3.4.2.4","3.4.2.5","3.4.2.5.1","3.4.2.5.2","3.4.2.6","3.4.2.6.1","3.4.2.6.2","3.4.2.7","3.4.2.7.1","3.4.2.8","3.4.2.8.1","3.4.2.8.2","3.4.3","3.4.3.1","3.4.3.1.1","3.4.3.1.2","3.4.3.1.2.1","3.4.3.1.2.2","3.4.3.1.2.3","3.4.3.1.2.4","3.4.3.1.2.5","3.4.4","3.4.4.1","3.4.4.2","3.4.5","3.4.5.1","3.4.5.1.1","3.4.5.1.2","3.4.5.1.3","3.4.5.1.4","3.4.5.2","3.4.5.2.1","3.4.5.2.2","3.4.5.2.3","3.4.5.2.4","3.4.5.2.5","3.4.5.3","3.4.5.3.1","3.4.5.3.2","3.4.5.3.3"
    };

    private String[] nameEngs = new String[]{
        "Cross Industry Invoice",
        "Exchanged Document Context",
            "Guideline Specified Document Context Parameter","ID",
        "Exchanged Document",
            "ID","Name","Type Code","Issue Date Time","Purpose","Purpose Code","Global ID","Creation Date Time","Included Note","Subject","Content",
        "Supply Chain Trade Transaction",
            "Applicable Header Trade Agreement","Seller Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Buyer Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone UniversalCommunication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Applicable Trade Delivery Terms","Delivery Type Code","Buyer Order Referenced Document","Issuer Assigned ID","Issue Date Time","Reference Type Code","Additional Referenced Document","Issuer Assigned ID","Issue Date Time","Reference Type Code",
            "Applicable Header Trade Delivery","Ship To Trade Party","ID","Global ID","Name","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Ship-to Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Ship From Trade Party","ID","Global ID","Name","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Ship-to Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Actual Delivery Supply Chain Event","Occurrence Date Time",
            "Applicable Header Trade Settlement","Invoice Currency Code","Applicable Trade Tax","Type Code","Calculated Rate","Basis Amount","Calculated Amount","Specified Trade Allowance Charge","Charge Indicator","Actual Amount","Reason Code","Reason","Type Code","Specified Trade PaymentTerms","Description","Due Date Time","Type Code","Specified Trade Settlement Header Monetary Summation","Original Information Amount","Line Total Amount","Difference Sales Information Amount","Allowance Total Amount","Charge Total Amount","Tax Basis Total Amount","Tax Total Amount","Grand Total Amount","Invoicer Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Invoicee Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Payer Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone UniversalCommunication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number","Payee Trade Party","ID","Global ID","Name","Specified Tax Registration","ID","Defined Trade Contact","Person Name","Department Name","E-mail URI Universal Communication","URI ID","Telephone Universal Communication","Phone Number","Postal Trade Address","Post Code","Building Name","Line 1","Line 2","Line 3","Line 4","Line 5","Street Name","City Name","City Sub-Division Name","Country ID","Country Sub-Division ID","Building Number",
            "Included Supply Chain Trade Line Item","Associated Document Line Document","Line ID","Specified Trade Product","ID","Global ID","Name","Description","Individual Trade Product Instance","Batch ID","Expiry Date Time","Designated Product Classification","Class Code","Class Name","Origin Trade Country","ID","Information Note","Subject","Content","Specified Line Trade Agreement","Gross Price Product Trade Price","Charge Amount","Applied Trade Allowance Charge","Charge Indicator","Actual Amount","ReasonCode","Reason","Type Code","Specified Line Trade Delivery","Billed Quantity","Per Package Unit Quantity","Specified Line Trade Settlement","Applicable Trade Tax","Type Code","Calculated Rate","Basis Amount","Calculated Amount","Specified Trade Allowance Charge","Charge Indicator","Actual Amount","Reason Code","Reason","Type Code","Specified Trade Settlement Monetary Summation","Tax Total Amount","Net Line Total Amount","Net Including Taxes Line Total Amount"
    };

    private String[] nameThs = new String[]{
        "",
        "บรบทของเอกสาร",
            "ขอมลคมอมาตรฐาน","รหสคมอมาตรฐาน",
        "หวเรองเอกสาร",
            "เลขทเอกสาร","ชอเอกสาร","รหสประเภทเอกสาร","วนเดอนป ทออกเอกสาร","สาเหตการออกเอกสาร","รหสสาเหตการออกเอกสาร","เลขทเอกสารสากล","วนเดอนปและเวลาทสรางเอกสาร","ขอความเพมเตม","หวขอ","เนอหา",
        "ธรกรรมทางการคา",
            "ขอตกลงทางการคา","ผขาย","รหสผคา (ผขาย)","รหสผคาสากล (ผขาย)","ชอผขาย","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผขาย)","รหสไปรษณย (ผขาย)","ชออาคาร (ผขาย)","ทอยบรรทดท 1 (ผขาย)","ทอยบรรทดท 2 (ผขาย)","ซอย (ผขาย)","หมบาน (ผขาย)","หมท (ผขาย)","ถนน (ผขาย)","ชออาเภอ (ผขาย)","ชอตาบล (ผขาย)","รหสประเทศ (ผขาย)","รหสจงหวด (ผขาย)","บานเลขท (ผขาย)","ผซอ","รหสผคา (ผซอ)","รหสผคาสากล (ผซอ)","ชอผซอ","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผซอ)","รหสไปรษณย (ผซอ)","ชออาคาร (ผซอ)","ทอยบรรทดท 1 (ผซอ)","ทอยบรรทดท 2 (ผซอ)","ซอย (ผซอ)","หมบาน (ผซอ)","หมท (ผซอ)","ถนน (ผซอ)","ชออาเภอ (ผซอ)","ชอตาบล (ผซอ)","รหสประเทศ (ผซอ)","รหสจงหวด (ผซอ)","บานเลขท (ผซอ)","เงอนไขหรอขอกาหนดการสงสนคา","เงอนไขการซอขาย","เอกสารใบสงซออางอง","เลขทใบสงซอ","วนเดอนป ทออกใบสงซอ","รหสเอกสารอางอง ","เอกสารอางอง","เลขทเอกสารอางอง","วนเดอนป ทออกเอกสารอางอง","รหสระบประเภทเอกสารอางอง ",
            "ขอมลการจดสงทางการคา","ขอมลผรบ","รหสผรบ","รหสผคาสากล (ผรบ)","ชอผรบ","รายละเอยดการตดตอ (ผรบ)","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล (ผรบ)","อเมล (ผรบ)","การตดตอทางโทรศพท  (ผรบ)","เบอรโทรศพท (ผรบ)","ทอย (ผรบ)","รหสไปรษณย (ผรบ)","ชออาคาร (ผรบ)","ทอยบรรทดท 1 (ผรบ)","ทอยบรรทดท 2 (ผรบ)","ซอย (ผรบ)","หมบาน (ผรบ)","หมท (ผรบ)","ถนน (ผรบ)","ชออาเภอ (ผรบ)","ชอตาบล (ผรบ)","รหสประเทศ (ผรบ)","รหสจงหวด (ผรบ)","บานเลขท (ผรบ)","ขอมลผสง","รหสผสง ","รหสผคาสากล (ผสง)","ชอผสง","รายละเอยดการตดตอ (ผสง)","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล (ผสง)","อเมล (ผสง)","การตดตอทางโทรศพท (ผสง)","เบอรโทรศพท (ผสง)","ทอย (ผสง)","รหสไปรษณย (ผสง)","ชออาคาร (ผสง)","ทอยบรรทดท 1 (ผสง)","ทอยบรรทดท 2 (ผสง)","ซอย (ผสง)","หมบาน (ผสง)","หมท (ผสง)","ถนน (ผสง)","ชออาเภอ (ผสง)","ชอตาบล (ผสง)","รหสประเทศ (ผสง)","รหสจงหวด (ผสง)","บานเลขท (ผสง)","การนดสงสนคา","วนเวลานดสงสนคา",
            "ขอมลการชาระเงนทางการคา","รหสสกลเงนตรา","ขอมลภาษ","รหสประเภทภาษ","อตราภาษ","มลคาสนคา/บรการ (ไมรวมภาษมลคาเพม ทศนยม 2 ตาแหนง)","มลคาภาษมลคาเพม","ขอมลสวนลดหรอคาธรรมเนยม","ตวบอกสวนลดหรอคาธรรมเนยม","มลคาสวนลดหรอคาธรรมเนยม","รหสเหตผลในการคดสวนลดหรอคาธรรมเนยม","เหตผลในการคดสวนลดหรอคาธรรมเนยม","รหสประเภทสวนลดหรอคาธรรมเนยม","ขอมลการชาระเงน","รายละเอยดเงอนไขการชาระเงน","วนครบกาหนดชาระเงน","รหสเงอนไขการชาระเงน","การสรปมลคาการชาระเงนทางการคา","รวมมูลคาตามเอกสารเดม","รวมมลคาตามรายการ/ มลคาทถกตอง ","มลคาผลตาง","สวนลดทงหมด","คาธรรมเนยมทงหมด","มลคาทนามาคดภาษมลคาเพม","จานวนภาษมลคาเพม (รวมทศนยม 2 ตาแหนง)","ยอดเงนรวม/ ยอดเงนรวมภาษมลคาเพม","ผออกเอกสารแทน","รหสผออกเอกสาร","รหสสากล","ชอผออกเอกสาร","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชื่อผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล (ผออกเอกสารแทน)","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผออกเอกสารแทน)","รหสไปรษณย","ชออาคาร","ทอยบรรทดท 1","ทอยบรรทดท 2 ","ซอย","หมบาน ","หมท","ถนน","ชออาเภอ","ชอตาบล ","รหสประเทศ ","รหสจงหวด ","บานเลขท","ผรบเอกสาร","รหสผรบเอกสาร","รหสสากล","ชอผรบเอกสาร","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล (ผรบเอกสาร)","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผรบเอกสาร)","รหสไปรษณย","ชออาคาร","ทอยบรรทดท 1","ทอยบรรทดท 2 ","ซอย","หมบาน ","หมท","ถนน","ชออาเภอ","ชอตาบล ","รหสประเทศ ","รหสจงหวด ","บานเลขท","ผชาระเงน","รหสผชาระเงน","รหสสากล","ชอผชาระเงน","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล (ผชาระเงน)","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผชาระเงน)","รหสไปรษณย","ชออาคาร","ทอยบรรทดท 1","ทอยบรรทดท 2 ","ซอย","หมบาน ","หมท","ถนน","ชออาเภอ","ชื่อตาบล ","รหสประเทศ ","รหสจงหวด ","บานเลขท","ผรบชาระเงน","รหสผรบชาระเงน","รหสสากล","ผรบชาระเงน","ขอมลการลงทะเบยนผเสยภาษอากร","เลขประจาตวผเสยภาษอากร","รายละเอยดการตดตอ","ชอผตดตอ","ชอแผนก","การตดตอทางอเมล","อเมล (ผรบชาระเงน)","การตดตอทางโทรศพท","เบอรโทรศพท","ทอย (ผรบชาระเงน)","รหสไปรษณย","ชออาคาร","ทอยบรรทดท 1","ทอยบรรทดท 2 ","ซอย","หมบาน ","หมท","ถนน","ชออาเภอ","ชอตาบล ","รหสประเทศ ","รหสจงหวด ","บานเลขท","ขอมลรายการทางการคา","ขอมลระบรายการซอขาย","ลาดบรายการ","ขอมลสนคา","รหสสนคา","รหสสนคาสากล","ชอสนคา","รายละเอยดสนคา","ขอมลสนคาทจดสง","ครงทผลต","วนหมดอาย","ขอมลหมวดหมสนคา","รหสหมวดหมสนคา","ชอหมวดหมสนคา","ขอมลประเทศกาเนด","รหสประเทศกาเนด","ขอความเพมเตม","หวขอ","เนอหา","ขอตกลงทางการคา","ขอมลจานวนเงนสนคาตอหนวย","ราคาตอหนวย","ขอมลสวนลดหรอคาธรรมเนยม (ตอหนวย)","ตวบอกสวนลดหรอคาธรรมเนยม","มลคาสวนลดหรอคาธรรมเนยม","รหสเหตผลในการคดสวนลดหรอคาธรรมเนยม","เหตผลในการคดสวนลดหรอคาธรรมเนยม","รหสประเภทสวนลดหรอคาธรรมเนยม",
            "ขอมลการจดสงทางการคา","จานวนสนคา","ขนาดบรรจตอหนวยขาย","ขอมลการชาระเงนทางการคา","ขอมลภาษ","รหสประเภทภาษ","อตราภาษ","มลคาสนคา/บรการทนามาคดภาษ (ไมรวมภาษมลคาเพม ทศนยม 2 ตาแหนง)","มลคาภาษ (ทศนยม 2 ตาแหนง)","ขอมลการใหสวนลดหรอคาธรรมเนยม (ตอรายการ)","ตวบอกสวนลดหรอคาธรรมเนยม","มลคาสวนลดหรอคาธรรมเนยม","รหสเหตผลในการคดสวนลดหรอคาธรรมเนยม","เหตผลในการคดสวนลดหรอคาธรรมเนยม","รหสประเภทสวนลดหรอคาธรรมเนยม","การสรปมลคาการชาระเงนทางการคา","ภาษมลคาเพม","จานวนเงนรวมกอนภาษ","จานวนเงนรวม"
    };

    protected String[] tags = new String[]{
        "<rsm:CrossIndustryInvoice>",
        "<rsm:ExchangedDocumentContext>",
            "<ram:GuidelineSpecifiedDocumentContextParameter>","<ram:ID>",
        "<rsm:ExchangedDocument>",
            "<ram:ID>","<ram:Name>","<ram:TypeCode>","<ram:IssueDateTime>","<ram:Purpose>","<ram:PurposeCode>","<ram:GlobalID>","<ram:CreationDateTime>","<ram:IncludedNote>","<ram:Subject>","<ram:Content>",
        "<rsm:SupplyChainTradeTransaction>",
            "<ram:ApplicableHeaderTradeAgreement>","<ram:SellerTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration>","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:BuyerTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration>","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:ApplicableTradeDeliveryTerms>","<ram:DeliveryTypeCode>","<ram:BuyerOrderReferencedDocument>","<ram:IssuerAssignedID>","<ram:IssueDateTime>","<ram:ReferenceTypeCode>","<ram:AdditionalReferencedDocument>","<ram:IssuerAssignedID>","<ram:IssueDateTime>","<ram:ReferenceTypeCode>",
            "<ram:ApplicableHeaderTradeDelivery>","<ram:ShipToTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:ShipFromTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:ActualDeliverySupplyChainEvent>","<ram:OccurrenceDateTime>",
            "<ram:ApplicableHeaderTradeSettlement>","<ram:InvoiceCurrencyCode>","<ram:ApplicableTradeTax>","<ram:TypeCode>","<ram:CalculatedRate>","<ram:BasisAmount>","<ram:CalculatedAmount>","<ram:SpecifiedTradeAllowanceCharge>","<ram:ChargeIndicator>","<ram:ActualAmount>","<ram:ReasonCode >","<ram:Reason>","<ram:TypeCode>","<ram:SpecifiedTradePaymentTerms>","<ram:Description>","<ram:DueDateDateTime>","<ram:TypeCode>","<ram:SpecifiedTradeSettlementHeaderMonetarySummation>","<ram:OriginalInformationAmount>","<ram:LineTotalAmount>","<ram:DifferenceInformationAmount>","<ram:AllowanceTotalAmount>","<ram:ChargeTotalAmount>","<ram:TaxBasisTotalAmount >","<ram:TaxTotalAmount>","<ram:GrandTotalAmount>","<ram:InvoicerTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration >","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:InvoiceeTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration>","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:PayerTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration>","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>","<ram:PayeeTradeParty>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:SpecifiedTaxRegistration>","<ram:ID>","<ram:DefinedTradeContact>","<ram:PersonName>","<ram:DepartmentName>","<ram:EmailURIUniversalCommunication>","<ram:URIID>","<ram:TelephoneUniversalCommunication>","<ram:CompleteNumber>","<ram:PostalTradeAddress>","<ram:PostcodeCode>","<ram:BuildingName>","<ram:LineOne>","<ram:LineTwo>","<ram:LineThree>","<ram:LineFour>","<ram:LineFive>","<ram:StreetName>","<ram:CityName>","<ram:CitySubDivisionName>","<ram:CountryID>","<ram:CountrySubDivisionID>","<ram:BuildingNumber>",
            "<ram:IncludedSupplyChainTradeLineItem>","<ram:AssociatedDocumentLineDocument>","<ram:LineID>","<ram:SpecifiedTradeProduct>","<ram:ID>","<ram:GlobalID>","<ram:Name>","<ram:Description >","<ram:IndividualTradeProductInstance>","<ram:BatchID>","<ram:ExpiryDateTime>","<ram:DesignatedProductClassification>","<ram:ClassCode>","<ram:ClassName>","<ram:OriginTradeCountry>","<ram:ID>","<ram:InformationNote >","<ram:Subject>","<ram:Content>","<ram:SpecifiedLineTradeAgreement>","<ram:GrossPriceProductTradePrice>","<ram:ChargeAmount>","<ram:AppliedTradeAllowanceCharge>","<ram:ChargeIndicator>","<ram:ActualAmount>","<ram:ReasonCode >","<ram:Reason>","<ram:TypeCode>","<ram:SpecifiedLineTradeDelivery>","<ram:BilledQuantity>","<ram:PerPackageUnitQuantity>","<ram:SpecifiedLineTradeSettlement>","<ram:ApplicableTradeTax>","<ram:TypeCode>","<ram:CalculatedRate>","<ram:BasisAmount>","<ram:CalculatedAmount>","<ram:SpecifiedTradeAllowanceCharge>","<ram:ChargeIndicator>","<ram:ActualAmount>","<ram:ReasonCode >","<ram:Reason>","<ram:TypeCode>","<ram:SpecifiedTradeSettlementLineMonetarySummation>","<ram:TaxTotalAmount>","<ram:NetLineTotalAmount>","<ram:NetIncludingTaxesLineTotal Amount>"
    };

    
    private String[] minTags = new String[]{
        "1","1","1","1","1","1","0","0","1","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"
    };

    private String[] maxTags = new String[]{
        "1","1","n","1","1","1","n","1","1","1","1","1","n","n","1","n","1","1","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","1","1","1","1","1","n","n","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","1","1","n","n","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","1","1","1","1","1","n","1","1","n","n","n","1","n","1","1","1","n","n","1","1","1","n","n","n","n","n","n","n","n","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","n","1","1","1","n","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","n","1","1","1","1","1","n","n","n","1","1","1","1","n","1","1","n","1","n","1","1","n","n","1","n","1","1","1","1","1","1","1","n","1","1","n","n","n","1","n","1","1","1","1","n","n","n"
    };

    // Type
    private String[] tagIdForTypes = new String[]{
        "1.1.1","2.1","2.2","2.3","2.4","2.5","2.6","2.7","2.8","2.9.1","2.9.2","3.1.1.1","3.1.1.2","3.1.1.3","3.1.1.4.1","3.1.1.5.1","3.1.1.5.2","3.1.1.5.3.1","3.1.1.5.4.1","3.1.1.6.1","3.1.1.6.2","3.1.1.6.3","3.1.1.6.4","3.1.1.6.5","3.1.1.6.6","3.1.1.6.7","3.1.1.6.8","3.1.1.6.9","3.1.1.6.10","3.1.1.6.11","3.1.1.6.12","3.1.1.6.13","3.1.2.1","3.1.2.2","3.1.2.3","3.1.2.4.1","3.1.2.5.1","3.1.2.5.2","3.1.2.5.3.1","3.1.2.5.4.1","3.1.2.6.1","3.1.2.6.2","3.1.2.6.3","3.1.2.6.4","3.1.2.6.5","3.1.2.6.6","3.1.2.6.7","3.1.2.6.8","3.1.2.6.9","3.1.2.6.10","3.1.2.6.11","3.3.6.6.12","3.1.2.6.13","3.1.3.1","3.1.4.1","3.1.4.2","3.1.4.3","3.1.5.1","3.1.5.2","3.1.5.3","3.2.1.1","3.2.1.2","3.2.1.3","3.2.1.4.1","3.2.1.4.2","3.2.1.4.3.1","3.2.1.4.4.1","3.2.1.5.1","3.2.1.5.2","3.2.1.5.3","3.2.1.5.4","3.2.1.5.5","3.2.1.5.6","3.2.1.5.7","3.2.1.5.8","3.2.1.5.9","3.2.1.5.10","3.2.1.5.11","3.2.1.5.12","3.2.1.5.13","3.2.2.1","3.2.2.2","3.2.2.3","3.2.2.4.1","3.2.2.4.2","3.2.2.4.3.1","3.2.2.4.4.1","3.2.2.5.1","3.2.2.5.2","3.2.2.5.3","3.2.2.5.4","3.2.2.5.5","3.2.2.5.6","3.2.2.5.7","3.2.2.5.8","3.2.2.5.9","3.2.2.5.10","3.2.2.5.11","3.2.2.5.12","3.2.2.5.13","3.2.3.1","3.3.1","3.3.2.1","3.3.2.2","3.3.2.3","3.3.2.4","3.3.3.1","3.3.3.2","3.3.3.3","3.3.3.4","3.3.3.5","3.3.4.1","3.3.4.2","3.3.4.3","3.3.5.1","3.3.5.2","3.3.5.3","3.3.5.4","3.3.5.5","3.3.5.6","3.3.5.7","3.3.5.8","3.3.6.1","3.3.6.2","3.3.6.3","3.3.6.4.1","3.3.6.5.1","3.3.6.5.2","3.3.6.5.3.1","3.3.6.5.4.1","3.3.6.6.1","3.3.6.6.2","3.3.6.6.3","3.3.6.6.4","3.3.6.6.5","3.3.6.6.6","3.3.6.6.7","3.3.6.6.8","3.3.6.6.9","3.3.6.6.10","3.3.6.6.11","3.3.6.6.12","3.3.6.6.13","3.3.7.1","3.3.7.2","3.3.7.3","3.3.7.4.1","3.3.7.5.1","3.3.7.5.2","3.3.7.5.3.1","3.3.7.5.4.1","3.3.7.6.1","3.3.7.6.2","3.3.7.6.3","3.3.7.6.4","3.3.7.6.5","3.3.7.6.6","3.3.7.6.7","3.3.7.6.8","3.3.7.6.9","3.3.7.6.10","3.3.7.6.11","3.3.7.6.12","3.3.7.6.13","3.3.8.1","3.3.8.2","3.3.8.3","3.3.8.4.1","3.3.8.5.1","3.3.8.5.2","3.3.8.5.3.1","3.3.8.5.4.1","3.3.8.6.1","3.3.8.6.2","3.3.8.6.3","3.3.8.6.4","3.3.8.6.5","3.3.8.6.6","3.3.8.6.7","3.3.8.6.8","3.3.8.6.9","3.3.8.6.10","3.3.8.6.11","3.3.8.6.12","3.3.8.6.13","3.3.9.1","3.3.9.2","3.3.9.3","3.3.9.4.1","3.3.9.5.1","3.3.9.5.2","3.3.9.5.3.1","3.3.9.5.4.1","3.3.9.6.1","3.3.9.6.2","3.3.9.6.3","3.3.9.6.4","3.3.9.6.5","3.3.9.6.6","3.3.9.6.7","3.3.9.6.8","3.3.9.6.9","3.3.9.6.10","3.3.9.6.11","3.3.9.6.12","3.3.9.6.13","3.4.1.1","3.4.2.1","3.4.2.2","3.4.2.3","3.4.2.4","3.4.2.5.1","3.4.2.5.2","3.4.2.6.1","3.4.2.6.2","3.4.2.7.1","3.4.2.8.1","3.4.2.8.2","3.4.3.1.1","3.4.3.1.2.1","3.4.3.1.2.2","3.4.3.1.2.3","3.4.3.1.2.4","3.4.3.1.2.5","3.4.4.1","3.4.4.2","3.4.5.1.1","3.4.5.1.2","3.4.5.1.3","3.4.5.1.4","3.4.5.2.1","3.4.5.2.2","3.4.5.2.3","3.4.5.2.4","3.4.5.2.5","3.4.5.3.1","3.4.5.3.2","3.4.5.3.3"
    };

    private String[] types = new String[]{
        "Max35Text","Max35Text","Max35Text","TypeCode","ISODateTime","Max256Text","TypeCode","Max70Text","ISODateTime","Max500Text","Max500Text","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","TypeCode","Max35Text","ISODateTime","TypeCode","Max35Text","ISODateTime","TypeCode","Max35Text","Max70Text","Max140Text","Max140Text","Max140Text","Max256Text","PhoneNumber","Max16Text","Max140Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max70Text","Max140Text","Max140Text","Max140Text","Max256Text","PhoneNumber","Max16Text","Max140Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","ISODateTime","TypeCode","TypeCode","PercentageRate","Amount","Amount","True or False","Amount","Max16Code","Max256Text","Max16Code","Max256Text","ISODateTime","TypeCode","Amount","Amount","Amount","Amount","Amount","Amount","Amount","Amount","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max70Text","Max256Text","Max35Text","Max140Text","Max140Text","Max256Text","Max35Text","Max16Text","Max70Text","Max256Text","Max256Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","Max70Text","CountryIDType","Max35Text","Max16Text","Max35Text","Max35Text","Max70Text","Max256Text","Max256Text","Max35Text","ISODateTime","Max35Text","Max256Text","TypeCode","Max500Text","Max500Text","Amount","True or False","Amount","Max16Code","Max256Text","Max16Code","Quantity  ","Quantity ","TypeCode","PercentageRate","Amount","Amount","True or False","Amount","Max16Code","Max256Text","Max16Code","Amount","Amount","Amount"
    };

    // Schema
    private String[] tagIdForSchemas = new String[]{
        "2.7","3.1.1.2","3.1.1.4.1","3.1.2.2","3.1.2.4.1","3.2.1.2","3.2.2.2","3.3.2.3","3.3.2.4","3.3.3.2","3.3.4.1","3.3.5.1","3.3.5.2","3.3.5.3","3.3.5.4","3.3.5.5","3.3.5.6","3.3.5.7","3.3.5.8","3.3.6.2","3.3.6.4.1","3.3.7.2","3.3.7.4.1","3.3.8.2","3.3.8.4.1","3.3.9.2","3.3.9.4.1","3.4.2.2","3.4.2.3","3.4.2.4","3.4.2.6.1","3.4.3.1.1","3.4.3.1.2.2","3.4.5.1.3","3.4.5.1.4","3.4.5.2.2","3.4.5.3.1","3.4.5.3.2","3.4.5.3.3"
    };

    private String[][] schemas = new String[][]{
        {"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID","schemeAgencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"languageID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"schemeID"},{"schemeID","schemeAgencyID"},{"languageID"},{"languageID"},{"listName","listVersionID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"},{"currencyID"}
    };

    // Rule
    private String[] tagIdForRules = new String[]{
        "1.1.1","2.2","2.3","2.4","2.5","2.6","2.7","2.8","3.1.1.1","3.1.1.2","3.1.1.4.1","3.1.1.6","3.1.1.6.9","3.1.1.6.10","3.1.1.6.11","3.1.1.6.12","3.1.2.1","3.1.2.2","3.1.2.4.1","3.1.2.6","3.1.2.6.9","3.1.2.6.10","3.1.2.6.11","3.3.6.6.12","3.1.3.1","3.1.4","3.1.4.2","3.1.5","3.1.5.2","3.2.1.1","3.2.1.2","3.2.1.5","3.2.1.5.9","3.2.1.5.10","3.2.1.5.11","3.2.1.5.12","3.2.2.1","3.2.2.2","3.2.2.5","3.2.2.5.9","3.2.2.5.10","3.2.2.5.11","3.2.2.5.12","3.2.3.1","3.3.1","3.3.2","3.3.2.3","3.3.2.4","3.3.3","3.3.4.2","3.3.4.3","3.3.5.1","3.3.5.2","3.3.5.3","3.3.5.4","3.3.5.5","3.3.5.6","3.3.5.7","3.3.5.8","3.3.6","3.3.6.1","3.3.6.2","3.3.6.4.1","3.3.6.6","3.3.6.6.9","3.3.6.6.10","3.3.6.6.11","3.3.6.6.12","3.3.7.1","3.3.7.2","3.3.7.4.1","3.3.7.6","3.3.7.6.9","3.3.7.6.10","3.3.7.6.11","3.3.7.6.12","3.3.8.1","3.3.8.2","3.3.8.4.1","3.3.8.6","3.3.8.6.9","3.3.8.6.10","3.3.8.6.11","3.3.8.6.12","3.3.9.1","3.3.9.2","3.3.9.4.1","3.3.9.6","3.3.9.6.9","3.3.9.6.10","3.3.9.6.11","3.3.9.6.12","3.4.2.1","3.4.2.2","3.4.2.5.2","3.4.2.6.1","3.4.2.6.2","3.4.2.7.1","3.4.3.1.1","3.4.3.1.2","3.4.4.1","3.4.4.2","3.4.5.1","3.4.5.2","3.4.5.3.2","3.4.5.3.3"
    };
    
    private String[] rules = new String[]{
        "8.2","8.3","8.3","8.5","8.4","8.4","8.22","8.5","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.12","8.13","8.5","8.13","8.5","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.5","8.14","8.15","8.19","8.19","8.16","8.5","8.17","8.19","8.19","8.19","8.19","8.19","8.19","8.19","8.19","8.23","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.6","8.6","8.6","8.7","8.9","8.8","8.11","8.10","8.20","8.20","8.5","8.21","8.21","8.11","8.19","8.16","8.18","8.18","8.15","8.16","8.19","8.19"
    };

    // Attribute Rules
    protected String[] attributeRules;

    private void setAttributeRules(String transactionType) {
        switch (transactionType) {
            case "Invoice":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","BD","BD","R","BD","BD","BD","NU","BD","BD","BD","R","R","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","C","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","C","C","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","NU","BD","NU","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","R","R","R","C","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD"
                };
                break;
            case "Receipt":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","R","R","R","BD","C","BD","R","BD","BD","BD","R","R","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","C","C","BD","BD","BD","BD","BD","BD","C","C","C","C","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","NU","BD","NU","BD","BD","BD","BD","R","C","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","BD","C","R","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","C","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","R","R","C","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","R","C","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD"
                };
                break;
            case "TaxInvoice":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","R","R","R","BD","C","BD","R","BD","BD","BD","R","R","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","BD","BD","BD","C","R","R","R","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","R","R","R","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","NU","R","NU","BD","BD","R","R","R","C","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","BD","C","R","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","C","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","R","R","C","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","R","C","BD","R","BD","R","R","R","R","BD","BD","BD","BD","BD","BD","R","BD","R","R"
                };
                break;
            case "DebitCreditNote":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","R","R","R","BD","R","BD","R","BD","BD","BD","R","R","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","BD","BD","BD","R","R","R","R","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","R","R","R","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","R","R","R","BD","BD","R","R","R","C","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","BD","C","R","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","C","C","C","BD","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","R","R","R","C","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","R","C","BD","R","BD","R","R","R","R","BD","BD","BD","BD","BD","BD","R","BD","R","BD"
                };
                break;
            case "CancellationNote":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","R","R","R","BD","NU","BD","R","BD","BD","BD","R","R","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","R","R","R","R","NU","NU","NU","NU","NU","NU","NU","NU","BD","BD","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","BD","BD","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","R","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","C","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","C","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU"
                };
                break;
            case "AbbreviatedTaxInvoice":
                attributeRules = new String[]{
                    "R","R","R","R","R","R","R","R","R","BD","C","BD","R","BD","BD","BD","R","R","R","BD","BD","R","C","R","BD","BD","BD","BD","BD","BD","BD","R","C","BD","C","C","BD","BD","BD","BD","C","C","R","C","C","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","NU","BD","NU","BD","BD","BD","BD","R","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","NU","R","R","R","C","BD","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","BD","BD","BD","BD","BD","BD","R","C","BD","R","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","BD","R","BD","R","R"
                };
                break;
            default:
                attributeRules = new String[]{};
                break;
        }
    }

    public Map<String, String> getTagIdToNameEng(){
        HashMap<String, String> tagIdToNameEng = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToNameEng.put(tagIds[idx], nameEngs[idx])
            );
        return tagIdToNameEng;
    }

    public Map<String, String> getTagIdToNameTh(){
        HashMap<String, String> tagIdToNameTh = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToNameTh.put(tagIds[idx], nameThs[idx])
            );
        return tagIdToNameTh;
    }

    public Map<String, String> getTagIdToTag(){
        HashMap<String, String> tagIdToTag = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToTag.put(tagIds[idx], tags[idx])
            );
        return tagIdToTag;
    }

    public Map<String, String> getTagIdToMinTag(){
        HashMap<String, String> tagIdToMinTag = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToMinTag.put(tagIds[idx], minTags[idx])
            );
        return tagIdToMinTag;
    }

    public Map<String, String> getTagIdToMaxTag(){
        HashMap<String, String> tagIdToMaxTag = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToMaxTag.put(tagIds[idx], maxTags[idx])
            );
        return tagIdToMaxTag;
    }

    public Map<String, String> getTagIdToAttributeRule(){
        HashMap<String, String> tagIdToAttributeRule = new HashMap<>();

        IntStream.range(0, tagIds.length - 1)
            .forEach(idx ->
                tagIdToAttributeRule.put(tagIds[idx], attributeRules[idx])
            );
        return tagIdToAttributeRule;
    }

    public Map<String, String> getTagIdForTypeToType(){
        HashMap<String, String> tagIdForTypeToType = new HashMap<>();

        IntStream.range(0, tagIdForTypes.length - 1)
            .forEach(idx ->
                tagIdForTypeToType.put(tagIdForTypes[idx], types[idx])
            );
        return tagIdForTypeToType;
    }

    public Map<String, String[]> getTagIdForSchemaToSchemas(){
        HashMap<String, String[]> tagIdForSchemaToSchemas = new HashMap<>();

        IntStream.range(0, tagIdForSchemas.length - 1)
            .forEach(idx ->
                tagIdForSchemaToSchemas.put(tagIdForSchemas[idx], schemas[idx])
            );
        return tagIdForSchemaToSchemas;
    }

    public Map<String, String> getTagIdForRulesToRule(){
        HashMap<String, String> tagIdForRulesToRule = new HashMap<>();

        IntStream.range(0, tagIdForRules.length - 1)
            .forEach(idx ->
                tagIdForRulesToRule.put(tagIdForRules[idx], rules[idx])
            );
        return tagIdForRulesToRule;
    }
}
