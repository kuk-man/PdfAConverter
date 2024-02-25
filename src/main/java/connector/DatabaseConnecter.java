package connector;

import java.util.Map;
import static java.util.Map.entry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List; 

public class DatabaseConnecter {
    public DatabaseConnecter(String conntion) {
        // setup conection 
    }

    public Map<String, String> getTransactionNameToType(){
        HashMap<String, String> transactionNameToType = new HashMap<>();
        transactionNameToType.put("Invoice", "INV");
        transactionNameToType.put("Receipt", "RCT");
        transactionNameToType.put("TaxInvoice", "TIV");
        transactionNameToType.put("DebitCreditNote", "DCN");
        transactionNameToType.put("CancellationNote", "CLN");
        transactionNameToType.put("AbbreviatedTaxInvoice", "ABB");
        return transactionNameToType;
    }

    // this value must get from database
    // rule 8.3
    public Map<String, String> getInvoiceTypeCodeToName() {
        return Map.ofEntries(
            entry("82","Metered services invoice"),
            entry("83","Credit note related to financial adjustments"),
            entry("84","Debit note related to financial adjustments"),
            entry("261","Self billed credit note"),
            entry("262","Consolidated credit note - goods and services"),
            entry("325","Proforma invoice"),
            entry("380","Commercial invoice"),
            entry("381","Credit note"),
            entry("383","Debit note"),
            entry("384","Corrected invoice"),
            entry("385","Consolidated invoice"),
            entry("386","Prepayment invoice"),
            entry("389","Self-billed invoice"),
            entry("395","Consignment invoice"),
            entry("396","Factored credit note")
        );
    }

    public Map<String, String> getTaxInvoiceTypeCodeToName() {
        return Map.ofEntries(
            entry("80","ใบเพิ่มหนี้ (Debit note)"),
            entry("81","ใบลดหนี้ (Credit note)"),
            entry("380","ใบแจ้งหนี้ (Invoice)"),
            entry("388","ใบกากับภาษี (Tax Invoice)"),
            entry("T01","ใบรับ (Receipt)"),
            entry("T02","ใบแจ้งหนี้/ใบกากับภาษี (Invoice/ Tax Invoice)"),
            entry("T03","ใบเสร็จรับเงิน/ใบกากับภาษี (Receipt/ Tax Invoice)"),
            entry("T04","ใบส่งของ/ใบกากับภาษี (Delivery order/ Tax Invoice)"),
            entry("T05","ใบกากับภาษีอย่างย่อ (Abbreviated Tax Invoice )"),
            entry("T06","ใบเสร็จรับเงิน/ใบกากับภาษีอย่างย่อ (Receipt/Abbreviated Tax Invoice)"),
            entry("T07","ใบแจ้งยกเลิก (Cancellation note)")
        );
    }

    // rule 8.4
    public Map<String, String> getPurposeCodeToDescriptionForDebitNoteOnGoods() {
        return Map.ofEntries(
            entry("DBNG01","มีการเพิ่มราคาค่าสินค้า (สินค้าเกินกว่าจานวนที่ตกลงกัน)"),
            entry("DBNG02","คานวณราคาสินค้า ผิดพลาดต่ากว่าที่เป็นจริง"),
            entry("DBNG99","เหตุอื่น (ระบุสาเหตุ)")
        ); 
    }

    public Map<String, String> getPurposeCodeToDescriptionForDebitNoteOnService() {
        return Map.ofEntries(
            entry("DBNS01","การเพิ่มราคาค่าบริการ (บริการเกินกว่าข้อกาหนดที่ตกลงกัน)"),
            entry("DBNS02","คานวณราคาค่าบริการ ผิดพลาดต่ากว่าที่เป็นจริง"),
            entry("DBNS99","เหตุอื่น (ระบุสาเหตุ)")
        );
    }
    public Map<String, String> getPurposeCodeToDescriptionForCreditNoteOnGoods() {
        return Map.ofEntries(
            entry("CDNG01","ลดราคาสินค้าที่ขาย (สินค้าผิดข้อกาหนดที่ตกลงกัน)"),
            entry("CDNG02","สินค้าชารุดเสียหาย"),
            entry("CDNG03","สินค้าขาดจานวนตามที่ตกลงซื้อขาย"),
            entry("CDNG04","คานวณราคาสินค้าผิดพลาดสูงกว่าที่เป็นจริง"),
            entry("CDNG05","รับคืนสินค้า (ไม่ตรงตามคาพรรณนา)"),
            entry("CDNG99","เหตุอื่น (ระบุสาเหตุ) รหัสสาเหตุของการออกใบลดหนี้ (กรณีบริการ)")
        );
    }

    public Map<String, String> getPurposeCodeToDescriptionForCreditNoteOnService() {
        return Map.ofEntries(
            entry("CDNS01","ลดราคาค่าบริการ (บริการผิดข้อกาหนดที่ตกลงกัน)"),
            entry("CDNS02","ค่าบริการขาดจานวน"),
            entry("CDNS03","คานวณราคาค่าบริการผิดพลาดสูงกว่าที่เป็นจริง"),
            entry("CDNS04","บอกเลิกสัญญาบริการ"),
            entry("CDNS99","เหตุอื่น (ระบุสาเหตุ)")
        );
    }
    public Map<String, String> getPurposeCodeToDescriptionForCancellationCauseOnNewTaxInvoice() {
        return Map.ofEntries(
            entry("TIVC01","ชื่อผิด"),
            entry("TIVC02","ที่อยู่ผิด"),
            entry("TIVC99","เหตุอื่น (ระบุสาเหตุ)")
        );
    }
    public Map<String, String> getPurposeCodeToDescriptionForCancellationCauseOnNewReceipt() {
        return Map.ofEntries(
            entry("RCTC01","ชื่อผิด"),
            entry("RCTC02","ที่อยู่ผิด"),
            entry("RCTC03","รับคืนสินค้า / ยกเลิกบริการ ทั้งจานวน (ระบุจานวนเงิน) บาท"),
            entry("RCTC04","รับคืนสินค้า / ยกเลิกบริการ บางส่วนจานวน (ระบุจานวนเงิน) บาท"),
            entry("RCTC99","เหตุอื่น (ระบุสาเหตุ)")
        );
    }

    // rule 8.7 - 8.11
    public List<String> getCitySubDivisionNames() {
        return Arrays.asList("100701", "100702"); 
    }

    public List<String> getCityNames() {
        return Arrays.asList("1007");
    }

    public List<String> getCountrySubDivisionIDs() {
        return Arrays.asList("37", "15", "S");
    }

    public List<String> getCountryIDs() {
        return Arrays.asList("AF","AX","AL","DZ","AS","AD","AO","AI","AQ","AG","AR","AM","AW","AU","AT","AZ","BS","BH","BD",
            "BB","BY","BE","BZ","BJ","BM","BT","BO","BQ","BA","BW","BV","BR","IO","BN","BG","BF","BI","CV","KH","CM","CA","KY","CF","TD","CL","CN",
            "CX","CC","CO","KM","CD","CG","CK","CR","CI","HR","CU","CW","CY","CZ","DK","DJ","DM","DO","EC","EG","SV","GQ","ER","EE","SZ","ET","FK",
            "FO","FJ","FI","FR","GF","PF","TF","GA","GM","GE","DE","GH","GI","GR","GL","GD","GP","GU","GT","GG","GN","GW","GY","HT","HM","VA","HN",
            "HK","HU","IS","IN","ID","IR","IQ","IE","IM","IL","IT","JM","JP","JE","JO","KZ","KE","KI","KP","KR","KW","KG","LA","LV","LB","LS","LR",
            "LY","LI","LT","LU","MO","MG","MW","MY","MV","ML","MT","MH","MQ","MR","MU","YT","MX","FM","MD","MC","MN","ME","MS","MA","MZ","MM","NA",
            "NR","NP","NL","NC","NZ","NI","NE","NG","NU","NF","MK","MP","NO","OM","PK","PW","PS","PA","PG","PY","PE","PH","PN","PL","PT","PR","QA",
            "RE","RO","RU","RW","BL","SH","KN","LC","MF","PM","VC","WS","SM","ST","SA","SN","RS","SC","SL","SG","SX","SK","SI","SB","SO","ZA","GS",
            "SS","ES","LK","SD","SR","SJ","SE","CH","SY","TW","TJ","TZ","TH","TL","TG","TK","TO","TT","TN","TR","TM","TC","TV","UG","UA","AE","GB",
            "UM","US","UY","UZ","VU","VE","VN","VG","VI","WF","EH","YE","ZM","ZW"
        );
    }
    // rule 8.12
    public List<String> getIncotermCodes() {
        return Arrays.asList("EXW","DAT","FCA","DAP","CPT","DDP","CIP","FAS","CFR","FOB","CIF");
    }

    // rule 8.13
    public List<String> getReferencedTypeCodes() {
        return Arrays.asList("IV","LC","LS","ON","SE","ALT","DL","CD","ZZZ");
    }

    // rule 8.14
    public List<String> getCurrencyCodes() {
        return Arrays.asList("AMD","ANG","THB");
    }

    // rule 8.15
    public List<String> getTaxCodes() {
        return Arrays.asList("AAA","AAB","AAC","AAD","AAE","AAF","ADD","BOL","CAP","CAR","COC","CST","CUD","CVD","ENV","EXC",
            "EXP","FET","FRE","GCN","GST","ILL","IMP","IND","LAC","LCN","LDP","LOC","LST","MCA","MCD","OTH","PDB","PDC","PRF","SCN","SSS","STT","SUP",
            "SUR","SWT","TAC","TOT","TOX","TTA","VAD","VAT"
        );
    }

    // rule 8.16
    public List<String> getAllowanceChargeTypeCodes() {
        return Arrays.asList("28","30","52","55","57","58","65","66","77","82","85","95","96");
    }

    // rule 8.17
    public List<String> getTradePaymentCodes() {
        return Arrays.asList("CREDOC","CREDOC/nM","CONCREDOC","CONCREDOC/nM","RECREDOC","RECREDOC/nM","COLLECT",
            "DOCOLLECT","DOCOLLECT/nM","INNO","INPA","PARTS","AAAA/MM/JJ","ANTICIP","CASH","REMBURS","ULTIMO","ULTIMO/nM","DEL/nM","CASHFAC; CASHINV",
            "FACULTIMO; INVULTIMO","FACULTIMO/nM; INVULTIMO/nM","FAC/nM; INV/nM","DIS n% nM; ESC n% nM","INT nM n%","COMPENS","CONSIGN","AGREE","HABIT","NOPAY"
        );
    }
    // rule 8.18
    public List<String> getUnitCodes() {
        return Arrays.asList("lift","small_spray","heat_lot","group","outfit","ration","shot","stick_military","hundred_fifteen_kg_drum",
            "hundred_lb_drum","fiftyfive_gallon_(US)_drum","tank_truck","twenty_foot_container","forty_foot_container","decilitre_per_gram","gram_per_cubic_centimetre",
            "theoretical_pound","gram_per_square_centimetre","actual_ton","theoretical_ton","kilogram_per_square_metre","pound_per_thousand_square_foot",
            "horse_power_day_per_air_dry_metric_ton","catch_weight","kilogram_per_air_dry_metric_ton","kilopascal_square_metre_per_gram","kilopascal_per_millimetre",
            "millilitre_per_square_centimetre_second","cubic_foot_per_minute_per_square_foot","ounce_per_square_foot","ounce_per_square_foot_per_0,01inch",
            "millilitre_per_second","millilitre_per_minute","super_bulk_bag","fivehundred_kg_bulk_bag","threehundred_kg_bulk_bag","fifty_lb_bulk_bag","fifty_lb_bag",
            "bulk_car_load","theoretical_kilogram","theoretical_tonne","sitas","mesh","net_kilogram","part_per_million","percent_weight","part_per_billion_(US)",
            "percent_per_1000_hour","failure_rate_in_time","pound_per_square_inch_gauge","oersted","test_specific_scale","volt_ampere_per_pound","watt_per_pound",
            "ampere_tum_per_centimetre","millipascal","gauss","milli-inch","kilogauss","pound_per_square_inch_absolute","henry","kilopound-force_per_square_inch",
            "foot_pound-force","pound_per_cubic_foot","poise","Saybold_universal_second","stokes","calorie_per_cubic_centimetre","calorie_per_gram","curl_unit",
            "twenty_thousand_gallon_(US)_tankcar","ten_thousand_gallon_(US)_tankcar","ten_kg_drum","fifteen_kg_drum","car_mile","car_count","locomotive_count",
            "caboose_count","empty_car","train_mile","fuel_usage_gallon_(US)","caboose_mile","fixed_rate","ton_mile","locomotive_mile","total_car_count","total_car_mile",
            "quarter_mile","radian_per_second","radian_per_second_squared","roentgen","volt_AC","volt_DC","British_thermal_unit_(international_table)_per_hour",
            "cubic_centimetre_per_second","cubic_foot_per_hour","cubic_foot_per_minute","centimetre_per_second","decibel","kilobyte","kilobecquerel","kilocurie",
            "megagram","megagram_per_hour","bin","metre_per_minute","milliroentgen","millivolt","megajoule","manmonth","pound_per_pound_of_product",
            "pound_per_piece_of_product","kilogram_per_kilogram_of_product","kilogram_per_piece_of_product","bobbin","cap","centistokes","twenty_pack",
            "microlitre","micrometre_(micron)","milliampere","megabyte","milligram_per_hour","megabecquerel","microfarad","newton_per_metre","ounce_inch",
            "ounce_foot","picofarad","pound_per_hour","ton_(US)_per_hour","kilolitre_per_hour","barrel_(US)_per_minute","batch","gallon(US)_per_thousand",
            "MMSCF/day","pound_per_thousand","pump","stage","standard_cubic_foot","hydraulic_horse_power","count_per_minute","seismic_level","seismic_line",
            "15_°C_calorie","ampere_square_metre_per_joule_second","angstrom","astronomical_unit","attojoule","barn","barn_per_electronvolt",
            "barn_per_steradian_electronvolt","barn_per_steradian","becquerel_per_kilogram","becquerel_per_cubic_metre","ampere_per_centimetre",
            "British_thermal_unit_(international_table)_per_second_square_foot_degree_Rankine","British_thermal_unit_(international_table)_per_pound_degree_Rankine",
            "British_thermal_unit_(international_table)_per_second_foot_degree_Rankine","British_thermal_unit_(international_table)_per_hour_square_foot_degree_Rankine",
            "candela_per_square_metre","cheval_vapeur","coulomb_metre","coulomb_metre_squared_per_volt","coulomb_per_cubic_centimetre","coulomb_per_cubic_metre",
            "ampere_per_millimetre","coulomb_per_cubic_millimetre","coulomb_per_kilogram_second","coulomb_per_mole","coulomb_per_square_centimetre",
            "coulomb_per_square_metre","coulomb_per_square_millimetre","cubic_centimetre_per_mole","cubic_decimetre_per_mole","cubic_metre_per_coulomb",
            "cubic_metre_per_kilogram","ampere_per_square_centimetre","cubic_metre_per_mole","ampere_per_square_metre","curie_per_kilogram","deadweight_tonnage",
            "decalitre","decametre","decitex","degree_Rankine","denier_A49","ampere_square_metre","dyne_second_per_cubic_centimetre","dyne_second_per_centimetre",
            "dyne_second_per_centimetre_to_the_fifth_power","electronvolt","electronvolt_per_metre","electronvolt_square_metre","electronvolt_square_metre_per_kilogram",
            "erg","erg_per_centimetre","8-part_cloud_cover","ampere_per_square_metre_kelvin_squared","erg_per_cubic_centimetre","erg_per_gram","erg_per_gram_second",
            "erg_per_second","erg_per_second_square_centimetre","erg_per_square_centimetre_second","erg_square_centimetre","erg_square_centimetre_per_gram","exajoule",
            "farad_per_metre","ampere_per_square_millimetre","femtojoule","femtometre","foot_per_second_squared","foot_pound-force_per_second","freight_ton","gal",
            "Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_displacement","Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_electric_current",
            "Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_electric_charge",
            "ampere_second","Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_electric_field_strength",
            "Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_electric_polarization","Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_electric_potential",
            "Gaussian_CGS_(Centimetre-Gram-Second_system)_unit_of_magnetization","gigacoulomb_per_cubic_metre","gigaelectronvolt","gigahertz","gigaohm","gigaohm_metre",
            "gigapascal","rate","gigawatt","gon","gram_per_cubic_metre","gram_per_mole","gray","gray_per_second","hectopascal","henry_per_metre","bit","ball","bulk_pack",
            "acre","activity","byte","ampere_per_metre","additional_minute","average_minute_per_call","cop","fathom","access_line","ampoule","ampere_hour","ampere","year",
            "aluminium_pound_only","troy_ounce_or_apothecary_ounce","anti-hemophilic_factor_(AHF)_unit","suppository","are","assortment","alcoholic_strength_by_mass",
            "alcoholic_strength_by_volume","standard_atmosphere","technical_atmosphere","capsule","powder_filled_vial","american_wire_gauge","assembly",
            "British_thermal_unit_(international_table)_per_pound","Btu_per_cubic_foot","barrel_(US)_per_day","bit_per_second","joule_per_kilogram_kelvin",
            "joule_per_metre","joule_per_square_metre","joule_per_metre_to_the_fourth_power","joule_per_mole","joule_per_mole_kelvin","credit","joule_second","digit",
            "bunk","joule_square_metre_per_kilogram","kelvin_per_watt","kiloampere","kiloampere_per_square_metre","kiloampere_per_metre","kilobecquerel_per_kilogram",
            "kilocoulomb","kilocoulomb_per_cubic_metre","kilocoulomb_per_square_metre","kiloelectronvolt","batting_pound","gibibit","kilogram_metre_per_second",
            "kilogram_metre_squared","kilogram_metre_squared_per_second","kilogram_per_cubic_decimetre","kilogram_per_litre","calorie_(thermochemical)_per_gram",
            "kilogram-force","kilogram-force_metre","kilogram-force_metre_per_second","barrel_imperial","kilogram-force_per_square_metre","kilojoule_per_kelvin",
            "kilojoule_per_kilogram","kilojoule_per_kilogram_kelvin","kilojoule_per_mole","kilomole","kilomole_per_cubic_metre","kilonewton","kilonewton_metre",
            "kiloohm","billet","kiloohm_metre","kilopond","kilosecond","kilosiemens","kilosiemens_per_metre","kilovolt_per_metre","kiloweber_per_metre","light_year",
            "litre_per_mole","lumen_hour","bun","lumen_per_square_metre","lumen_per_watt","lumen_second","lux_hour","lux_second","maxwell","megaampere_per_square_metre",
            "megabecquerel_per_kilogram","gigabit","megacoulomb_per_cubic_metre","cycle","megacoulomb_per_square_metre","megaelectronvolt","megagram_per_cubic_metre",
            "meganewton","meganewton_metre","megaohm","megaohm_metre","megasiemens_per_metre","megavolt","megavolt_per_metre","joule_per_cubic_metre","gigabit_per_second",
            "reciprocal_metre_squared_reciprocal_second","inch_per_linear_foot","metre_to_the_fourth_power","microampere","microbar","microcoulomb",
            "microcoulomb_per_cubic_metre","microcoulomb_per_square_metre","microfarad_per_metre","batt","microhenry","microhenry_per_metre","micronewton",
            "micronewton_metre","microohm","microohm_metre","micropascal","microradian","microsecond","microsiemens","bar_[unit_of_pressure]","base_box","board",
            "bundle","board_foot","bag","brush","brake_horse_power","billion_(EUR)","bucket","basket","bale","dry_barrel_(US)","barrel_(US)","bottle","hundred_board_foot",
            "beats_per_minute","becquerel","bar_[unit_of_packaging]","bolt","British_thermal_unit_(international_table)","bushel_(US)","bushel_(UK)","base_weight","box",
            "million_BTUs","call","composite_product_pound_(total_weight)","millifarad","milligal","milligram_per_metre","milligray","millihenry","millijoule",
            "millimetre_per_second","millimetre_squared_per_second","millimole","mole_per_kilogram","carset","millinewton","kibibit","millinewton_per_metre",
            "milliohm_metre","millipascal_second","milliradian","millisecond","millisiemens","millisievert","millitesla","microvolt_per_metre","millivolt_per_metre",
            "milliwatt","milliwatt_per_square_metre","milliweber","mole","mole_per_cubic_decimetre","mole_per_cubic_metre","kilobit","mole_per_litre","nanoampere",
            "carload","nanocoulomb","nanofarad","nanofarad_per_metre","nanohenry","nanohenry_per_metre","nanometre","nanoohm_metre","nanosecond","nanotesla","nanowatt",
            "cost","neper","neper_per_second","picometre","newton_metre_second","newton_metre_squared_per_kilogram_squared","newton_per_square_metre",
            "newton_per_square_millimetre","newton_second","newton_second_per_metre","octave","cell","ohm_centimetre","ohm_metre","one","parsec","pascal_per_kelvin",
            "pascal_second","pascal_second_per_cubic_metre","pascal_second_per_metre","petajoule","phon","centipoise","picoampere","picocoulomb","picofarad_per_metre",
            "picohenry","kilobit_per_second","picowatt","picowatt_per_square_metre","pound_gage","pound-force","kilovolt_ampere_hour","millicoulomb_per_kilogram",
            "rad","radian","radian_square_metre_per_mole","radian_square_metre_per_kilogram","radian_per_metre","reciprocal_angstrom","reciprocal_cubic_metre",
            "reciprocal_cubic_metre_per_second","reciprocal_electron_volt_per_cubic_metre","reciprocal_henry","coil_group","reciprocal_joule_per_cubic_metre",
            "reciprocal_kelvin_or_kelvin_to_the_power_minus_one","reciprocal_metre","reciprocal_square_metre","reciprocal_minute","reciprocal_mole",
            "reciprocal_pascal_or_pascal_to_the_power_minus_one","reciprocal_second","reciprocal_second_per_cubic_metre","reciprocal_second_per_metre_squared",
            "can","carrying_capacity_in_metric_ton","candela","degree_Celsius","hundred","card","centigram","container","cone","connector","coulomb_per_kilogram",
            "coil","hundred_leave","centilitre","square_centimetre","cubic_centimetre","centimetre","hundred_pack","cental_(UK)","carboy","coulomb","cartridge",
            "crate","case","carton","content_gram","metric_carat","content_ton_(metric)","cup","curie","cover","hundred_pound_(cwt)_/_hundred_weight_(US)",
            "hundred_weight_(UK)","cylinder","combo","kilowatt_hour_per_hour","lot_[unit_of_weight]","reciprocal_second_per_steradian","siemens_per_metre",
            "mebibit","siemens_square_metre_per_mole","sievert","thousand_linear_yard","sone","square_centimetre_per_erg","square_centimetre_per_steradian_erg",
            "metre_kelvin","square_metre_kelvin_per_watt","reciprocal_second_per_steradian_metre_squared","square_metre_per_joule","square_metre_per_kilogram",
            "square_metre_per_mole","pen_gram_(protein)","square_metre_per_steradian","square_metre_per_steradian_joule","square_metre_per_volt_second","steradian",
            "syphon","terahertz","terajoule","terawatt","terawatt_hour","tesla","tex","calorie_(thermochemical)","megabit","calorie_(thermochemical)_per_gram_kelvin",
            "calorie_(thermochemical)_per_second_centimetre_kelvin","calorie_(thermochemical)_per_second_square_centimetre_kelvin","thousand_litre",
            "tonne_per_cubic_metre","tropical_year","unified_atomic_mass_unit","var","volt_squared_per_kelvin_squared","volt_-_ampere","volt_per_centimetre",
            "volt_per_kelvin","millivolt_per_kelvin","kilogram_per_square_centimetre","volt_per_metre","volt_per_millimetre","watt_per_kelvin","watt_per_metre_kelvin",
            "watt_per_square_metre","watt_per_square_metre_kelvin","watt_per_square_metre_kelvin_to_the_fourth_power","watt_per_steradian",
            "watt_per_steradian_square_metre","weber_per_metre","roentgen_per_second","weber_per_millimetre","minute_[unit_of_angle]","second_[unit_of_angle]",
            "book","block","round","cassette","dollar_per_hour","number_of_words","inch_to_the_fourth_power","sandwich","calorie_(international_table)",
            "calorie_(international_table)_per_second_centimetre_kelvin","calorie_(international_table)_per_second_square_centimetre_kelvin","joule_square_metre",
            "kilogram_per_mole","calorie_(international_table)_per_gram","calorie_(international_table)_per_gram_kelvin","megacoulomb","megajoule_per_second","beam",
            "draize_score","microwatt","microtesla","microvolt","millinewton_metre","microwatt_per_square_metre","millicoulomb","millimole_per_kilogram",
            "millicoulomb_per_cubic_metre","millicoulomb_per_square_metre","dyne_per_square_centimetre","cubic_metre_(net)","rem","band","second_per_cubic_metre",
            "second_per_cubic_metre_radian","joule_per_gram","pound_gross","pallet/unit_load","mass_pound","sleeve","decare","ten_day","day","dry_pound",
            "disk_(disc)","degree_[unit_of_angle]","deal","decade","decigram","dispenser","decagram","decilitre","cubic_decametre","square_decimetre",
            "standard_kilolitre","cubic_decimetre","decimetre","decinewton_metre","dozen_piece","dozen_pair","displacement_tonnage","data_record","drum","dram_(US)",
            "dram_(UK)","dozen_roll","drachm_(UK)","display","dry_ton","decitonne","dyne","pennyweight","dyne_per_centimetre","directory_book","dozen","dozen_pack",
            "newton_per_square_centimetre","megawatt_hour_per_hour","megawatt_per_hertz","milliampere_hour","degree_day","gigacalorie","mille",
            "kilocalorie_(international_table)","kilocalorie_(thermochemical)_per_hour","million_Btu(IT)_per_hour","cubic_foot_per_second","tonne_per_hour","ping",
            "belt","megabit_per_second","shares","TEU","tyre","active_unit","dose","air_dry_ton","trailer","strand","square_metre_per_litre","litre_per_hour",
            "foot_per_thousand","gigabyte","terabyte","petabyte","pixel","megapixel","dots_per_inch","gross_kilogram","part_per_hundred_thousand",
            "kilogram-force_per_square_millimetre","kilogram-force_per_square_centimetre","joule_per_square_centimetre","kilogram-force_metre_per_square_centimetre",
            "milliohm","kilowatt_hour_per_cubic_metre","kilowatt_hour_per_kelvin","service_unit","working_day","metric_long_ton","accounting_unit","job","run_foot",
            "test","trip","use","well","zone","exabit_per_second","exbibyte","pebibyte","tebibyte","gibibyte","mebibyte","kibibyte","exbibit_per_metre",
            "exbibit_per_square_metre","exbibit_per_cubic_metre","gigabyte_per_second","gibibit_per_metre","gibibit_per_square_metre","gibibit_per_cubic_metre",
            "kibibit_per_metre","kibibit_per_square_metre","kibibit_per_cubic_metre","mebibit_per_metre","mebibit_per_square_metre","mebibit_per_cubic_metre",
            "petabit","petabit_per_second","pebibit_per_metre","pebibit_per_square_metre","pebibit_per_cubic_metre","terabit","terabit_per_second","tebibit_per_metre",
            "tebibit_per_cubic_metre","tebibit_per_square_metre","bit_per_metre","bit_per_square_metre","reciprocal_centimetre","reciprocal_day",
            "cubic_decimetre_per_hour","kilogram_per_hour","kilomole_per_second","mole_per_second","degree_per_second","millimetre_per_degree_Celcius_metre",
            "degree_Celsius_per_kelvin","hectopascal_per_bar","each","electronic_mail_box","each_per_month","eleven_pack","equivalent_gallon","envelope",
            "bit_per_cubic_metre","kelvin_per_kelvin","kilopascal_per_bar","millibar_per_bar","megapascal_per_bar","poise_per_bar","pascal_per_bar",
            "milliampere_per_inch","thousand_cubic_foot_per_day","kelvin_per_hour","kelvin_per_minute","kelvin_per_second","slug","gram_per_kelvin","kilogram_per_kelvin",
            "milligram_per_kelvin","pound-force_per_foot","kilogram_square_centimetre","kilogram_square_millimetre","pound_inch_squared","pound-force_inch",
            "pound-force_foot_per_ampere","gram_per_cubic_decimetre","kilogram_per_kilomol","gram_per_hertz","gram_per_day","gram_per_hour","gram_per_minute",
            "gram_per_second","kilogram_per_day","kilogram_per_minute","milligram_per_day","milligram_per_minute","milligram_per_second","gram_per_day_kelvin",
            "gram_per_hour_kelvin","gram_per_minute_kelvin","gram_per_second_kelvin","kilogram_per_day_kelvin","kilogram_per_hour_kelvin","kilogram_per_minute_kelvin",
            "kilogram_per_second_kelvin","milligram_per_day_kelvin","milligram_per_hour_kelvin","milligram_per_minute_kelvin","milligram_per_second_kelvin",
            "newton_per_millimetre","pound-force_per_inch","rod_[unit_of_distance]","micrometre_per_kelvin","centimetre_per_kelvin","metre_per_kelvin",
            "millimetre_per_kelvin","milliohm_per_metre","ohm_per_mile_(statute_mile)","ohm_per_kilometre","milliampere_per_pound-force_per_square_inch","reciprocal_bar",
            "milliampere_per_bar","degree_Celsius_per_bar","kelvin_per_bar","gram_per_day_bar","gram_per_hour_bar","gram_per_minute_bar","gram_per_second_bar",
            "kilogram_per_day_bar","kilogram_per_hour_bar","kilogram_per_minute_bar","kilogram_per_second_bar","milligram_per_day_bar","milligram_per_hour_bar",
            "milligram_per_minute_bar","milligram_per_second_bar","gram_per_bar","milligram_per_bar","milliampere_per_millimetre","pascal_second_per_kelvin",
            "inch_of_water","inch_of_mercury","water_horse_power","bar_per_kelvin","hectopascal_per_kelvin","kilopascal_per_kelvin","millibar_per_kelvin",
            "megapascal_per_kelvin","poise_per_kelvin","volt_per_litre_minute","newton_centimetre","newton_metre_per_degree","fibre_per_cubic_centimetre_of_air",
            "newton_metre_per_ampere","bar_litre_per_second","bar_cubic_metre_per_second","hectopascal_litre_per_second","hectopascal_cubic_metre_per_second",
            "millibar_litre_per_second","millibar_cubic_metre_per_second","megapascal_litre_per_second","megapascal_cubic_metre_per_second","pascal_litre_per_second",
            "degree_Fahrenheit","farad","field","fibre_metre","thousand_cubic_foot","million_particle_per_cubic_foot","track_foot","hundred_cubic_metre",
            "transdermal_patch","micromole","failures_in_time","flake_ton","million_cubic_foot","foot","pound_per_square_foot","foot_per_minute","foot_per_second",
            "square_foot","cubic_foot","pascal_cubic_metre_per_second","centimetre_per_bar","metre_per_bar","millimetre_per_bar","square_inch_per_second",
            "square_metre_per_second_kelvin","stokes_per_kelvin","gram_per_cubic_centimetre_bar","gram_per_cubic_decimetre_bar","gram_per_litre_bar",
            "gram_per_cubic_metre_bar","gram_per_millilitre_bar","kilogram_per_cubic_centimetre_bar","kilogram_per_litre_bar","kilogram_per_cubic_metre_bar",
            "newton_metre_per_kilogram","US_gallon_per_minute","pound-force_foot_per_pound","cup_[unit_of_volume]","peck","tablespoon_(US)","teaspoon_(US)",
            "stere","cubic_centimetre_per_kelvin","litre_per_kelvin","cubic_metre_per_kelvin","Imperial_gallon_per_minute","millilitre_per_kelvin",
            "kilogram_per_cubic_centimetre","ounce_(avoirdupois)_per_cubic_yard","gram_per_cubic_centimetre_kelvin","gram_per_cubic_decimetre_kelvin",
            "gram_per_litre_kelvin","gram_per_cubic_metre_kelvin","gram_per_millilitre_kelvin","kilogram_per_cubic_centimetre_kelvin","kilogram_per_litre_kelvin",
            "kilogram_per_cubic_metre_kelvin","square_metre_per_second_bar","microsiemens_per_centimetre","microsiemens_per_metre","nanosiemens_per_centimetre",
            "nanosiemens_per_metre","stokes_per_bar","cubic_centimetre_per_day","cubic_centimetre_per_hour","cubic_centimetre_per_minute","gallon_(US)_per_hour",
            "litre_per_second","cubic_metre_per_day","cubic_metre_per_minute","millilitre_per_day","millilitre_per_hour","cubic_inch_per_hour","cubic_inch_per_minute",
            "cubic_inch_per_second","milliampere_per_litre_minute","volt_per_bar","cubic_centimetre_per_day_kelvin","cubic_centimetre_per_hour_kelvin",
            "cubic_centimetre_per_minute_kelvin","cubic_centimetre_per_second_kelvin","litre_per_day_kelvin","litre_per_hour_kelvin","litre_per_minute_kelvin",
            "litre_per_second_kelvin","cubic_metre_per_day_kelvin","microfiche_sheet","cubic_metre_per_hour_kelvin","cubic_metre_per_minute_kelvin",
            "cubic_metre_per_second_kelvin","millilitre_per_day_kelvin","millilitre_per_hour_kelvin","millilitre_per_minute_kelvin","millilitre_per_second_kelvin",
            "millimetre_to_the_fourth_power","cubic_centimetre_per_day_bar","cubic_centimetre_per_hour_bar","cubic_centimetre_per_minute_bar",
            "cubic_centimetre_per_second_bar","litre_per_day_bar","litre_per_hour_bar","litre_per_minute_bar","litre_per_second_bar","cubic_metre_per_day_bar",
            "cubic_metre_per_hour_bar","cubic_metre_per_minute_bar","cubic_metre_per_second_bar","millilitre_per_day_bar","millilitre_per_hour_bar",
            "millilitre_per_minute_bar","millilitre_per_second_bar","cubic_centimetre_per_bar","litre_per_bar","cubic_metre_per_bar","millilitre_per_bar",
            "microhenry_per_kiloohm","microhenry_per_ohm","gallon_(US)_per_day","gigabecquerel","gram_per_100_gram","gross_barrel","gram_dry_weight",
            "pound_per_gallon_(US)","gram_per_metre_(gram_per_100_centimetres)","gram_of_fissile_isotope","great_gross","half_gallon_(US)","gill_(US)",
            "gram_including_container","gill_(UK)","gram_including_inner_packaging","gram_per_millilitre","gram_per_kilogram","gram_per_litre","dry_gallon_(US)",
            "gallon_(UK)","gallon_(US)","gram_per_square_metre","gross_gallon","milligram_per_square_metre","milligram_per_cubic_metre","microgram_per_cubic_metre",
            "gram","grain","gross","gross_register_ton","gross_ton","gigajoule","gallon_per_thousand_cubic_foot","gigawatt_hour","gross_yard","gage_system",
            "henry_per_kiloohm","henry_per_ohm","millihenry_per_kiloohm","millihenry_per_ohm","pascal_second_per_bar","microbecquerel","reciprocal_year",
            "half_page_–_electronic","reciprocal_hour","reciprocal_month","degree_Celsius_per_hour","degree_Celsius_per_minute","degree_Celsius_per_second",
            "square_centimetre_per_gram","square_decametre","square_hectometre","cubic_hectometre","half_litre","cubic_kilometre","blank",
            "volt_square_inch_per_pound-force","volt_per_inch","volt_per_microsecond","percent_per_kelvin","ohm_per_metre","degree_per_metre",
            "microfarad_per_kilometre","microgram_per_litre","square_micrometre_(square_micron)","ampere_per_kilogram","ampere_squared_second",
            "farad_per_kilometre","hertz_metre","kelvin_metre_per_watt","megaohm_per_kilometre","megaohm_per_metre","megaampere","megahertz_kilometre",
            "newton_per_ampere","newton_metre_watt_to_the_power_minus_0,5","pascal_per_metre","siemens_per_centimetre","teraohm","volt_second_per_metre",
            "volt_per_second","watt_per_cubic_metre","attofarad","centimetre_per_hour","reciprocal_cubic_centimetre","decibel_per_kilometre","decibel_per_metre",
            "kilogram_per_bar","kilogram_per_cubic_decimetre_kelvin","kilogram_per_cubic_decimetre_bar","kilogram_per_square_metre_second","inch_per_two_pi_radiant",
            "metre_per_volt_second","square_metre_per_newton","cubic_metre_per_cubic_metre","millisiemens_per_centimetre","millivolt_per_minute",
            "milligram_per_square_centimetre","milligram_per_gram","millilitre_per_cubic_metre","millimetre_per_year","millimetre_per_hour","millimole_per_gram",
            "picopascal_per_kilometre","picosecond","percent_per_month","percent_per_hectobar","percent_per_decakelvin","watt_per_metre","decapascal",
            "gram_per_millimetre","module_width","conventional_centimetre_of_water","French_gauge","rack_unit","millimetre_per_minute","big_point","litre_per_kilogram",
            "gram_millimetre","reciprocal_week","piece","megaohm_kilometre","percent_per_ohm","percent_per_degree","percent_per_ten_thousand",
            "percent_per_one_hundred_thousand","percent_per_hundred","percent_per_thousand","percent_per_volt","percent_per_bar","percent_per_inch",
            "percent_per_metre","hank","hectare","hectobar","hundred_boxes","hundred_count","half_dozen","hundred_kilogram_dry_weight","hundredth_of_a_carat","head",
            "hundred_foot","hectogram","hundred_cubic_foot","hundred_sheet","hundred_international_unit","metric_horse_power","hundred_kilogram",
            "hundred_kilogram_net_mass","hundred_foot_(linear)","hectolitre","mile_per_hour_(statute_mile)","million_cubic_metre","hectometre",
            "conventional_millimetre_of_mercury","hundred_troy_ounce","conventional_millimetre_of_water","hectolitre_of_pure_alcohol","hundred_square_foot",
            "half_hour","hertz","hour","hundred_yard","inch_pound_(pound_inch)","count_per_inch","person","inches_of_water","column_inch","inch_per_minute_IL",
            "impression","inch","square_inch","cubic_inch","insurance_policy","international_sugar_degree","count_per_centimetre","inch_per_second",
            "international_unit_per_gram","inch_per_second_squared","percent_per_millimetre","per_mille_per_psi","degree_API","degree_Baume_(origin_scale)",
            "degree_Baume_(US_heavy)","degree_Baume_(US_light)","degree_Balling","degree_Brix",
            "degree_Fahrenheit_hour_square_foot_per_British_thermal_unit_(thermochemical)","joule_per_kilogram","degree_Fahrenheit_per_kelvin",
            "degree_Fahrenheit_per_bar","degree_Fahrenheit_hour_square_foot_per_British_thermal_unit_(international_table)","degree_Fahrenheit_per_hour",
            "degree_Fahrenheit_per_minute","degree_Fahrenheit_per_second","reciprocal_degree_Fahrenheit","degree_Oechsle","degree_Rankine_per_hour",
            "degree_Rankine_per_minute","degree_Rankine_per_second","degree_Twaddell","micropoise","microgram_per_kilogram","microgram_per_cubic_metre_kelvin",
            "microgram_per_cubic_metre_bar","microlitre_per_litre","baud","British_thermal_unit_(mean)",
            "British_thermal_unit_(international_table)_foot_per_hour square_foot_degree_Fahrenheit",
            "British_thermal_unit_(international_table)_inch_per_hour_square foot_degree_Fahrenheit",
            "British_thermal_unit_(international_table)_inch_per_second_square foot_degree_Fahrenheit",
            "British_thermal_unit_(international_table)_per_pound_degree_Fahrenheit","British_thermal_unit_(international_table)_per_minute",
            "British_thermal_unit_(international_table)_per_second","British_thermal_unit_(thermochemical)_foot_per_hour_square foot_degree_Fahrenheit",
            "British_thermal_unit_(thermochemical)_per_hour","British_thermal_unit_(thermochemical)_inch_per_hour_square foot_degree_Fahrenheit",
            "British_thermal_unit_(thermochemical)_inch_per_second square_foot_degree_Fahrenheit","British_thermal_unit_(thermochemical)_per_pound_degree_Fahrenheit",
            "British_thermal_unit_(thermochemical)_per_minute","British_thermal_unit_(thermochemical)_per_second","coulomb_square_metre_per_kilogram","megabaud",
            "watt_second","bar_per_bar","barrel_(UK_petroleum)","barrel_(UK_petroleum)_per_minute","barrel_(UK_petroleum)_per_day","barrel_(UK_petroleum)_per_hour",
            "barrel_(UK_petroleum)_per_second","barrel_(US_petroleum)_per_hour","barrel_(US_petroleum)_per_second","bushel_(UK)_per_day","bushel_(UK)_per_hour",
            "bushel_(UK)_per_minute","bushel_(UK)_per_second","bushel_(US_dry)_per_day","bushel_(US_dry)_per_hour","bushel_(US_dry)_per_minute",
            "bushel_(US_dry)_per_second","centinewton_metre","centipoise_per_kelvin","centipoise_per_bar","calorie_(mean)",
            "calorie_(international_table)_per_gram_degree_Celsius","calorie_(thermochemical)_per_centimetre_second_degree_Celsius",
            "calorie_(thermochemical)_per_gram_degree_Celsius","calorie_(thermochemical)_per_minute","calorie_(thermochemical)_per_second","clo",
            "centimetre_per_second_kelvin","centimetre_per_second_bar","cubic_centimetre_per_cubic_metre","centimetre_of_mercury",
            "cubic_decimetre_per_day","cubic_decimetre_per_cubic_metre","cubic_decimetre_per_minute","cubic_decimetre_per_second","dyne_centimetre",
            "ounce_(UK_fluid)_per_day","ounce_(UK_fluid)_per_hour","ounce_(UK_fluid)_per_minute","ounce_(UK_fluid)_per_second","ounce_(US_fluid)_per_day","jumbo",
            "joule_per_kelvin","jug","megajoule_per_kilogram","megajoule_per_cubic_metre","pipeline_joint","joint","joule","hundred_metre","jar","number_of_jewels",
            "kilowatt_demand","ounce_(US_fluid)_per_hour","ounce_(US_fluid)_per_minute","ounce_(US_fluid)_per_second","foot_per_degree_Fahrenheit","foot_per_hour",
            "foot_pound-force_per_hour","foot_pound-force_per_minute","foot_per_psi","foot_per_second_degree_Fahrenheit","foot_per_second_psi",
            "kilovolt_ampere_reactive_demand","reciprocal_cubic_foot","cubic_foot_per_degree_Fahrenheit","cubic_foot_per_day","cubic_foot_per_psi","foot_of_water",
            "foot_of_mercury","gallon_(UK)_per_day","gallon_(UK)_per_hour","gallon_(UK)_per_second","kilovolt_ampere_reactive_hour","gallon_(US_liquid)_per_second",
            "gram-force_per_square_centimetre","gill_(UK)_per_day","gill_(UK)_per_hour","gill_(UK)_per_minute","gill_(UK)_per_second","gill_(US)_per_day",
            "gill_(US)_per_hour","gill_(US)_per_minute","gill_(US)_per_second","standard_acceleration_of_free_fall","grain_per_gallon_(US)","horsepower_(boiler)",
            "horsepower_(electric)","inch_per_degree_Fahrenheit","inch_per_psi","inch_per_second_degree_Fahrenheit","inch_per_second_psi","reciprocal_cubic_inch",
            "kilovolt_ampere_(reactive)","kilobaud","kilocalorie_(mean)","kilocalorie_(international_table)_per_hour_metre_degree_Celsius","kilocalorie_(thermochemical)",
            "kilocalorie_(thermochemical)_per_minute","kilocalorie_(thermochemical)_per_second","kilomole_per_hour","kilomole_per_cubic_metre_kelvin","kilolitre",
            "kilomole_per_cubic_metre_bar","kilomole_per_minute","litre_per_litre","reciprocal_litre","pound_(avoirdupois)_per_degree_Fahrenheit",
            "pound_(avoirdupois)_square_foot","pound_(avoirdupois)_per_day","pound_per_foot_hour","pound_per_foot_second",
            "pound_(avoirdupois)_per_cubic_foot_degree_Fahrenheit","pound_(avoirdupois)_per_cubic_foot_psi","pound_(avoirdupois)_per_gallon_(UK)",
            "pound_(avoirdupois)_per_hour_degree_Fahrenheit","pound_(avoirdupois)_per_hour_psi","pound_(avoirdupois)_per_cubic_inch_degree_Fahrenheit",
            "pound_(avoirdupois)_per_cubic_inch_psi","pound_(avoirdupois)_per_psi","pound_(avoirdupois)_per_minute","pound_(avoirdupois)_per_minute_degree_Fahrenheit",
            "pound_(avoirdupois)_per_minute_psi","pound_(avoirdupois)_per_second","pound_(avoirdupois)_per_second_degree_Fahrenheit","pound_(avoirdupois)_per_second_psi",
            "pound_per_cubic_yard","pound-force_per_square_foot","pound-force_per_square_inch_degree_Fahrenheit","psi_cubic_inch_per_second","psi_litre_per_second",
            "psi_cubic_metre_per_second","psi_cubic_yard_per_second","pound-force_second_per_square_foot","pound-force_second_per_square_inch","reciprocal_psi",
            "quart_(UK_liquid)_per_day","quart_(UK_liquid)_per_hour","quart_(UK_liquid)_per_minute","quart_(UK_liquid)_per_second","quart_(US_liquid)_per_day",
            "quart_(US_liquid)_per_hour","cake","katal","kilocharacter","kilobar","kilogram_of_choline_chloride","kilogram_decimal","kilogram_drained_net_weight",
            "kelvin","kilopacket","keg","kilogram","kilogram_per_second","kilogram_of_hydrogen_peroxide","kilohertz","kilogram_per_millimetre_width",
            "kilogram_including_container","kilogram_including_inner_packaging","kilosegment","kilojoule","kilogram_per_metre","lactic_dry_material_percentage",
            "kilolux","kilogram_of_methylamine","kilometre_per_hour","square_kilometre","kilogram_per_cubic_metre","kilometre_KMT","kilogram_of_nitrogen",
            "kilonewton_per_square_metre","kilogram_named_substance","knot","milliequivalence_caustic_potash_per_gram_of_product","kilopascal",
            "kilogram_of_potassium_hydroxide_(caustic_potash)","kilogram_of_potassium_oxide","kilogram_of_phosphorus_pentoxide_(phosphoric_anhydride)","kiloroentgen",
            "thousand_pound_per_square_inch","kilogram_of_substance_90_%_dry","kilogram_of_sodium_hydroxide_(caustic_soda)","kit","kilometre_KTM","kilotonne",
            "kilogram_of_uranium","kilovolt_-_ampere","kilovar","kilovolt","kilogram_per_millimetre","kilowatt_hour","kilowatt_year",
            "Kilowatt_hour_per_normalized_cubic_metre","kilogram_of_tungsten_trioxide","Kilowatt_hour_per_standard_cubic_metre","kilowatt","millilitre_per_kilogram",
            "quart_(US_liquid)_per_minute","quart_(US_liquid)_per_second","metre_per_second_kelvin","metre_per_second_bar",
            "square_metre_hour_degree_Celsius_per_kilocalorie_(international_table)","millipascal_second_per_kelvin","millipascal_second_per_bar",
            "milligram_per_cubic_metre_kelvin","milligram_per_cubic_metre_bar","millilitre_per_litre","litre_per_minute","reciprocal_cubic_millimetre",
            "cubic_millimetre_per_cubic_metre","mole_per_hour","mole_per_kilogram_kelvin","mole_per_kilogram_bar","mole_per_litre_kelvin","mole_per_litre_bar",
            "mole_per_cubic_metre_kelvin","mole_per_cubic_metre_bar","mole_per_minute","milliroentgen_aequivalent_men","nanogram_per_kilogram",
            "ounce_(avoirdupois)_per_day","ounce_(avoirdupois)_per_hour","ounce_(avoirdupois)_per_minute","ounce_(avoirdupois)_per_second",
            "ounce_(avoirdupois)_per_gallon_(UK)","ounce_(avoirdupois)_per_gallon_(US)","ounce_(avoirdupois)_per_cubic_inch","ounce_(avoirdupois)-force",
            "ounce_(avoirdupois)-force_inch","picosiemens_per_metre","peck_(UK)","peck_(UK)_per_day","peck_(UK)_per_hour","peck_(UK)_per_minute","peck_(UK)_per_second",
            "peck_(US_dry)_per_day","peck_(US_dry)_per_hour","peck_(US_dry)_per_minute","peck_(US_dry)_per_second","psi_per_psi","pint_(UK)_per_day","pint_(UK)_per_hour",
            "pint_(UK)_per_minute","pint_(UK)_per_second","pint_(US_liquid)_per_day","pint_(US_liquid)_per_hour","pint_(US_liquid)_per_minute",
            "pint_(US_liquid)_per_second","pint_(US_dry)","quart_(US_dry)","slug_per_day","slug_per_foot_second","slug_per_cubic_foot","slug_per_hour","slug_per_minute",
            "slug_per_second","tonne_per_kelvin","tonne_per_bar","tonne_per_day","tonne_per_day_kelvin","tonne_per_day_bar","tonne_per_hour_kelvin","tonne_per_hour_bar",
            "tonne_per_cubic_metre_kelvin","tonne_per_cubic_metre_bar","tonne_per_minute","tonne_per_minute_kelvin","tonne_per_minute_bar","tonne_per_second",
            "tonne_per_second_kelvin","tonne_per_second_bar","ton_(UK_shipping)","ton_long_per_day","ton_(US_shipping)","ton_short_per_degree_Fahrenheit",
            "ton_short_per_day","ton_short_per_hour_degree_Fahrenheit","ton_short_per_hour_psi","ton_short_per_psi","ton_(UK_long)_per_cubic_yard",
            "ton_(US_short)_per_cubic_yard","ton-force_(US_short)","common_year","sidereal_year","yard_per_degree_Fahrenheit","yard_per_psi","pound_per_cubic_inch",
            "lactose_excess_percentage","pound","troy_pound_(US)","linear_centimetre","litre_per_day","lite","leaf","linear_foot","labour_hour","linear_inch",
            "large_spray","link","linear_metre","length","lot_[unit_of_procurement]","liquid_pound","litre_of_pure_alcohol","layer","lump_sum",
            "ton_(UK)_or_long_ton_(US)","litre","metric_ton_lubricating_oil","lumen","lux","linear_yard_per_pound","linear_yard","magnetic_tape","milligram_per_litre",
            "reciprocal_cubic_yard","cubic_yard_per_degree_Fahrenheit","cubic_yard_per_day","cubic_yard_per_hour","cubic_yard_per_psi","cubic_yard_per_minute",
            "cubic_yard_per_second","kilohertz_metre","gigahertz_metre","Beaufort","reciprocal_megakelvin_or_megakelvin_to_the_power_minus_one",
            "reciprocal_kilovolt_-_ampere_reciprocal_hour","millilitre_per_square_centimetre_minute","newton_per_centimetre","ohm_kilometre","percent_per_degree_Celsius",
            "gigaohm_per_metre","megahertz_metre","kilogram_per_kilogram","reciprocal_volt_-_ampere_reciprocal_second","kilogram_per_kilometre","pascal_second_per_litre",
            "millimole_per_litre","newton_metre_per_square_metre","millivolt_-_ampere","30-day_month","actual/360","kilometre_per_second_squared",
            "centimetre_per_second_squared","monetary_value","yard_per_second_squared","millimetre_per_second_squared","mile_(statute_mile)_per_second_squared","mil",
            "revolution","degree_[unit_of_angle]_per_second_squared","revolution_per_minute","circular_mil","square_mile_(based_on_U.S._survey_foot)",
            "chain_(based_on_U.S._survey_foot)","microcurie","furlong","foot_(U.S._survey)","mile_(based_on_U.S._survey_foot)","metre_per_pascal","metre_per_radiant",
            "shake","mile_per_minute","mile_per_second","metre_per_second_pascal","metre_per_hour","inch_per_year","kilometre_per_second","inch_per_minute_M63",
            "yard_per_second","yard_per_minute","yard_per_hour","acre-foot_(based_on_U.S._survey_foot)","cord_(128_ft3)","cubic_mile_(UK_statute)","micro-inch",
            "ton_register","cubic_metre_per_pascal","bel","kilogram_per_cubic_metre_pascal","kilogram_per_pascal","kilopound-force","poundal",
            "kilogram_metre_per_second_squared","pond","square_foot_per_hour","stokes_per_pascal","square_centimetre_per_second","square_metre_per_second_pascal",
            "denier_M83","pound_per_yard","ton_assay","pfund","kilogram_per_second_pascal","tonne_per_month","tonne_per_year","million_Btu_per_1000_cubic_foot",
            "kilopound_per_hour","pound_per_pound","pound-force_foot","newton_metre_per_radian","kilogram_metre","poundal_foot","poundal_inch","dyne_metre",
            "kilogram_centimetre_per_second","gram_centimetre_per_second","machine_per_unit","megavolt_ampere_reactive_hour","megalitre","megametre","megavar",
            "megawatt","thousand_standard_brick_equivalent","thousand_board_foot","millibar","microgram","millicurie","air_dry_metric_ton",
            "milligram_per_square_foot_per_side","milligram","megahertz","square_mile_(statute_mile)","thousand","minute_[unit_of_time]","million",
            "million_international_unit","milligram_per_square_inch","milliard","millilitre","square_millimetre","cubic_millimetre","millimetre","kilogram_dry_weight",
            "month","megapascal","thousand_metre","cubic_metre_per_hour","cubic_metre_per_second","metre_per_second_squared","mat","square_metre","cubic_metre","metre",
            "metre_per_second","number_of_mults","megavolt_-_ampere","megawatt_hour_(1000 kW.h)","pen_calorie","pound_foot_per_second","pound_inch_per_second",
            "Pferdestaerke","centimetre_of_mercury_(0_ºC)","centimetre_of_water_(4_ºC)","foot_of_water_(39.2_ºF)","inch_of_mercury_(32_ºF)","inch_of_mercury_(60_ºF)",
            "inch_of_water_(39.2_ºF)","inch_of_water_(60_ºF)","number_of_lines","kip_per_square_inch","poundal_per_square_foot","ounce_(avoirdupois)_per_square_inch",
            "conventional_metre_of_water","gram_per_square_millimetre","pound_per_square_yard","poundal_per_square_inch","foot_to_the_fourth_power",
            "cubic_decimetre_per_kilogram","cubic_foot_per_pound","print_point","cubic_inch_per_pound","kilonewton_per_metre","poundal_per_inch","pound-force_per_yard",
            "poundal_second_per_square_foot","poise_per_pascal","newton_second_per_square_metre","kilogram_per_metre_second","kilogram_per_metre_minute",
            "kilogram_per_metre_day","kilogram_per_metre_hour","gram_per_centimetre_second","poundal_second_per_square_inch","pound_per_foot_minute","pound_per_foot_day",
            "cubic_metre_per_second_pascal","foot_poundal","inch_poundal","watt_per_square_centimetre","watt_per_square_inch",
            "British_thermal_unit_(international_table)_per_square_foot_hour","British_thermal_unit_(thermochemical)_per_square_foot_hour",
            "British_thermal_unit_(thermochemical)_per_square_foot_minute","British_thermal_unit_(international_table)_per_square_foot_second",
            "British_thermal_unit_(thermochemical)_per_square_foot_second","British_thermal_unit_(international_table)_per_square_inch_second",
            "calorie_(thermochemical)_per_square_centimetre_minute","calorie_(thermochemical)_per_square_centimetre_second",
            "British_thermal_unit_(international_table)_per_cubic_foot","British_thermal_unit_(thermochemical)_per_cubic_foot",
            "British_thermal_unit_(international_table)_per_degree_Fahrenheit","British_thermal_unit_(thermochemical)_per_degree_Fahrenheit",
            "British_thermal_unit_(international_table)_per_degree_Rankine","British_thermal_unit_(thermochemical)_per_degree_Rankine",
            "British_thermal_unit_(thermochemical)_per_pound_degree_Rankine","kilocalorie_(international_table)_per_gram_kelvin","British_thermal_unit_(39_ºF)",
            "British_thermal_unit_(59_ºF)","British_thermal_unit_(60_ºF)","calorie_(20_ºC)","quad_(1015_BtuIT)","therm_(EC)","therm_(U.S.)",
            "British_thermal_unit_(thermochemical)_per_pound","British_thermal_unit_(international_table)_per_hour_square_foot_degree_Fahrenheit",
            "British_thermal_unit_(thermochemical)_per_hour_square_foot_degree_Fahrenheit",
            "British_thermal_unit_(international_table)_per_second_square_foot_degree_Fahrenheit",
            "British_thermal_unit_(thermochemical)_per_second_square_foot_degree_Fahrenheit","kilowatt_per_square_metre_kelvin","kelvin_per_pascal",
            "watt_per_metre_degree_Celsius","kilowatt_per_metre_kelvin","kilowatt_per_metre_degree_Celsius","metre_per_degree_Celcius_metre",
            "degree_Fahrenheit_hour_per_British_thermal_unit_(international_table)","degree_Fahrenheit_hour_per_British_thermal_unit_(thermochemical)",
            "degree_Fahrenheit_second_per_British_thermal_unit_(international_table)","degree_Fahrenheit_second_per_British_thermal_unit_(thermochemical)",
            "degree_Fahrenheit_hour_square_foot_per_British_thermal_unit_(international_table)_inch",
            "degree_Fahrenheit_hour_square_foot_per_British_thermal_unit_(thermochemical)_inch","kilofarad","reciprocal_joule","picosiemens","ampere_per_pascal",
            "franklin","ampere_minute","biot","gilbert","volt_per_pascal","picovolt","milligram_per_kilogram","number_of_articles","barge","number_of_bobbins","car",
            "number_of_cells","net_barrel","net_litre","newton","message","net_gallon_(us)","message_hour","net_imperial_gallon","nil","number_of_international_units",
            "number_of_screens","load","Normalised_cubic_metre","nautical_mile","number_of_packs","train","number_of_parcels","number_of_pairs","number_of_parts","mho",
            "micromho","number_of_rolls","net_ton","net_register_ton","newton_metre","vehicle","part_per_thousand","pound_per_air_dry_metric_ton","panel",
            "ozone_depletion_equivalent","ODS_Grams","ODS_Kilograms","ODS_Milligrams","ohm","ounce_per_square_yard","ounce_(avoirdupois)","two_pack",
            "oscillations_per_minute","overtime_hour","ounce_av","fluid_ounce_(US)","fluid_ounce_(UK)","page_-_electronic","percent","coulomb_per_metre","kiloweber",
            "gamma","kilotesla","joule_per_second","joule_per_minute","joule_per_hour","joule_per_day","kilojoule_per_second","kilojoule_per_minute","pound_per_foot",
            "kilojoule_per_hour","kilojoule_per_day","nanoohm","ohm_circular-mil_per_foot","kilohenry","lumen_per_square_foot","phot","footcandle",
            "candela_per_square_inch","footlambert","three_pack","lambert","stilb","candela_per_square_foot","kilocandela","millicandela","Hefner-Kerze",
            "international_candle","British_thermal_unit_(international_table)_per_square_foot","British_thermal_unit_(thermochemical)_per_square_foot",
            "calorie_(thermochemical)_per_square_centimetre","four_pack","langley","decade_(logarithmic)","pascal_squared_second","bel_per_metre","pound_mole",
            "pound_mole_per_second","pound_mole_per_minute","kilomole_per_kilogram","pound_mole_per_pound","newton_square_metre_per_ampere","five_pack","weber_metre",
            "mol_per_kilogram_pascal","mol_per_cubic_metre_pascal","unit_pole","milligray_per_second","microgray_per_second","nanogray_per_second","gray_per_minute",
            "milligray_per_minute","microgray_per_minute","six_pack","nanogray_per_minute","gray_per_hour","milligray_per_hour","microgray_per_hour","nanogray_per_hour",
            "sievert_per_second","millisievert_per_second","microsievert_per_second","nanosievert_per_second","rem_per_second","seven_pack","sievert_per_hour",
            "millisievert_per_hour","microsievert_per_hour","nanosievert_per_hour","sievert_per_minute","millisievert_per_minute","microsievert_per_minute",
            "nanosievert_per_minute","reciprocal_square_inch","pascal_square_metre_per_kilogram","eight_pack","millipascal_per_metre","kilopascal_per_metre",
            "hectopascal_per_metre","standard_atmosphere_per_metre","technical_atmosphere_per_metre","torr_per_metre","psi_per_inch","cubic_metre_per_second_square_metre",
            "rhe","pound-force_foot_per_inch","nine_pack","pound-force_inch_per_inch","perm_(0_ºC)","perm_(23_ºC)","byte_per_second","kilobyte_per_second",
            "megabyte_per_second","reciprocal_volt","reciprocal_radian","pascal_to_the_power_sum_of_stoichiometric_numbers",
            "mole_per_cubiv_metre_to_the_power_sum_of_stoichiometric_numbers","packet","pascal","pair_inch","pad","pound_equivalent","pallet_(lift)","proof_litre",
            "plate","proof_gallon","pitch","pack","pail","degree_Plato","pound_percentage","pound_net","pound_per_inch_of_length","page_per_inch","pair",
            "pound-force_per_square_inch","pint_(US)","dry_pint_(US)","pint_(UK)","liquid_pint_(US)","portion","tray_/_tray_pack","half_pint_(US)",
            "pound_per_inch_of_width","peck_dry_(US)","peck_dry_(UK)","joule_per_tesla","erlang","octet","octet_per_second","shannon","hartley",
            "natural_unit_of_information","shannon_per_second","hartley_per_second","natural_unit_of_information_per_second","second_per_kilogramm","watt_square_metre",
            "second_per_radian_cubic_metre","weber_to_the_power_minus_one","reciprocal_inch","dioptre","one_per_one","newton_metre_per_metre",
            "kilogram_per_square_metre_pascal_second","microgram_per_hectogram","pH_(potential_of_Hydrogen)","kilojoule_per_gram","femtolitre","picolitre","nanolitre",
            "megawatts_per_minute","square_metre_per_cubic_metre","Standard_cubic_metre_per_day","Standard_cubic_metre_per_hour","Normalized_cubic_metre_per_day",
            "Normalized_cubic_metre_per_hour","Joule_per_normalised_cubic_metre","Joule_per_standard_cubic_metre","meal","page_-_facsimile","quarter_(of_a_year)",
            "page_-_hardcopy","quarter_dozen","quarter_hour","quarter_kilogram","quire","quart_(US)","dry_quart_(US)","quart_(UK)","liquid_quart_(US)","quarter_(UK)",
            "pica","calorie","thousand_cubic_metre","rack","rod","ring","running_or_operating_hour","roll_metric_measure","reel","ream","ream_metric_measure","roll",
            "room","pound_per_ream","revolutions_per_minute","revolutions_per_second","reset","revenue_ton_mile","run","square_foot_per_second","square_metre_per_second",
            "sixty_fourths_of_an_inch","session","storage_unit","standard_advertising_unit","sack","half_year_(6_months)","score","scruple","solid_pound","section",
            "second_[unit_of_time]","set","segment","shipping_ton","siemens","split_tank_truck","slipsheet","Standard_cubic_metre","mile_(statute_mile)","square_rod",
            "spool","shelf_package","square","square_roofing","strip","sheet_metric_measure","short_standard_(7200_matches)","sheet","stick","stone_(UK)",
            "stick_cigarette","standard_litre","ton_(US)_or_short_ton_(UK/US)","straw","skid","skein","shipment","syringe","telecommunication_line_in_service",
            "thousand_pound_gross","thousand_piece","thousand_bag","thousand_casing","thousand_gallon_(US)","thousand_impression","thousand_linear_inch",
            "tenth_cubic_foot","kiloampere_hour_(thousand_ampere_hour)","total_acid_number","truckload","therm","tote","ten_square_yard","thousand_square_inch",
            "metric_ton_including_container","metric_ton_including_inner_packaging","thousand_square_centimetre","tank_rectangular","tonne_kilometre",
            "thousand_foot_(linear)","kilogram_of_imported_meat_less_offal","tin","tonne_(metric_ton)","ten_pack","teeth_per_inch","ten_pair","thousand_foot",
            "thousand_cubic_metre_per_day","ten_square_foot","trillion_(EUR)","thousand_square_foot","tonne_of_substance_90_%_dry","ton_of_steam_per_hour","ten_set",
            "thousand_linear_metre","ten_thousand_sticks","tube","thousand_kilogram","thousand_sheet","tank_cylindrical","treatment","tablet","torr",
            "telecommunication_line_in_service_average","telecommunication_port","tenth_minute","tenth_hour","usage_per_telecommunication_line_average",
            "ten_thousand_yard","million_unit","volt_-_ampere_per_kilogram","vial","volt","percent_volume","bulk","visit","wet_kilo","two_week","watt_per_kilogram",
            "wet_pound","cord","wet_ton","weber","week","wine_gallon","wheel","watt_hour","weight_per_square_inch","working_month","wrap","standard","watt",
            "millilitre_of_water","Gunter's_chain","square_yard","cubic_yard","hundred_linear_yard","yard","ten_yard","lift_van","hanging_container","chest","cask",
            "hogshead","lug","conference_point","newspage_agate_line","page","mutually_defined","Metre_Week","Square_Metre_Week","Cubic_Metre_Week","Piece_Week",
            "Metre_Day","Square_Metre_Day","Cubic_Metre_Day","Piece_Day","Metre_Month","Square_Metre_Month","Cubic_Metre_Month","Piece_Month","Decibel_watt",
            "Decibel-milliwatts","Formazin_nephelometric_unit","Nephelometric_turbidity_unit"
        );
    }
    
    // rule 8.21
    public List<String> getUnspscCodes() {
        return Arrays.asList("10100000", "10101500");
    }

    public List<String> getLanguageCodes() {
        return Arrays.asList("aar","abk","ace","ach","ada","ady","afa","afh","afr","ain","aka","akk","ale","alg","alt","amh","ang","anp",
            "apa","ara","arc","arg","arn","arp","art","arw","asm","ast","ath","aus","ava","ave","awa","aym","aze","bad","bai","bak","bal","bam","ban","bas","bat","bej",
            "bel","bem","ben","ber","bho","bih","bik","bin","bis","bla","bnt","bod","tib","bos","bra","bre","btk","bua","bug","bul","byn","cad","cai","car","cat","cau",
            "ceb","cel","ces","cze","cha","chb","che","chg","chk","chm","chn","cho","chp","chr","chu","chv","chy","cmc","cnr","cop","cor","cos","cpe","cpf","cpp","cre",
            "crh","crp","csb","cus","cym","wel","dak","dan","dar","day","del","den","deu","ger","dgr","din","div","doi","dra","dsb","dua","dum","dyu","dzo","efi","egy",
            "eka","ell","gre","elx","eng","enm","epo","est","eus","baq","ewe","ewo","fan","fao","fas","per","fat","fij","fil","fin","fiu","fon","fra","fre","frm","fro",
            "frr","frs","fry","ful","fur","gaa","gay","gba","gem","gez","gil","gla","gle","glg","glv","gmh","goh","gon","gor","got","grb","grc","grn","gsw","guj","gwi",
            "hai","hat","hau","haw","heb","her","hil","him","hin","hit","hmn","hmo","hrv","hsb","hun","hup","hye","arm","iba","ibo","ido","iii","ijo","iku","ile","ilo",
            "ina","inc","ind","ine","inh","ipk","ira","iro","isl","ice","ita","jav","jbo","jpn","jpr","jrb","kaa","kab","kac","kal","kam","kan","kar","kas","kat","geo",
            "kau","kaw","kaz","kbd","kha","khi","khm","kho","kik","kin","kir","kmb","kok","kom","kon","kor","kos","kpe","krc","krl","kro","kru","kua","kum","kur","kut",
            "lad","lah","lam","lao","lat","lav","lez","lim","lin","lit","lol","loz","ltz","lua","lub","lug","lui","lun","luo","lus","mad","mag","mah","mai","mak","mal",
            "man","map","mar","mas","mdf","mdr","men","mga","mic","min","mis","mkd","mac","mkh","mlg","mlt","mnc","mni","mno","moh","mon","mos","mri","mao","msa","may",
            "mul","mun","mus","mwl","mwr","mya","bur","myn","myv","nah","nai","nap","nau","nav","nbl","nde","ndo","nds","nep","new","nia","nic","niu","nld","dut","nno",
            "nob","nog","non","nor","nqo","nso","nub","nwc","nya","nym","nyn","nyo","nzi","oci","oji","ori","orm","osa","oss","ota","oto","paa","pag","pal","pam","pan",
            "pap","pau","peo","phi","phn","pli","pol","pon","por","pra","pro","pus","qaa","qtz","que","raj","rap","rar","roa","roh","rom","ron","rum","run","rup","rus",
            "sad","sag","sah","sai","sal","sam","san","sas","sat","scn","sco","sel","sem","sga","sgn","shn","sid","sin","sio","sit","sla","slk","slo","slv","sma","sme",
            "smi","smj","smn","smo","sms","sna","snd","snk","sog","som","son","sot","spa","sqi","alb","srd","srn","srp","srr","ssa","ssw","suk","sun","sus","sux","swa",
            "swe","syc","syr","tah","tai","tam","tat","tel","tem","ter","tet","tgk","tgl","tha","tig","tir","tiv","tkl","tlh","tli","tmh","tog","ton","tpi","tsi","tsn",
            "tso","tuk","tum","tup","tur","tut","tvl","twi","tyv","udm","uga","uig","ukr","umb","und","urd","uzb","vai","ven","vie","vol","vot","wak","wal","war","was",
            "wen","wln","wol","xal","xho","yao","yap","yid","yor","ypk","zap","zbl","zen","zgh","zha","zho","chi","znd","zul","zun","zxx","zza"
        );
    }
}
