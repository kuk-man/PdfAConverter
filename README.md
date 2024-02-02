
# PdfAConvertor

Simple program to convert PDF to PDFA-3 using PDFbox

## Build
```sh
$ git clone 
$ mvn package 
```

## Execute
```sh
$ java -jar target/PdfAConverter-0.0.1-SNAPSHOT.jar <input file name> <embed file name> <color profile name> <output filename> <document type> <document file name> <document version> <pkcs type>
```
**input file name** = PDF's input file path <br/>
**embed file name** = XML's input file path <br/>
**color profile name** = .icm's input file path <br/>
**output filename** = PDF's output file path <br/>
**document type** = Document type <br/>
**document file name** = XML Template's input file name <br/>
**document version** = 2.0 <br/>
**pkcs type** = "PKCS11" or "PKCS12" <br/>

| Document Type | Descrtiption |
| :---:   | :---: |
|   80  | ใบเพิ่มหนี้ (Debit note)                                          |
|   81  | ใบลดหนี้ (Credit note)                                          |
|   380 | ใบแจ้งหนี้ (Invoice)                                             |
|   388 | ใบกากับภาษี (Tax Invoice)                                       |
|   T01 | ใบรับ (Receipt)                                                 |
|   T02 | ใบแจ้งหนี้/ใบกากับภาษี (Invoice/ Tax Invoice)                      |
|   T03 | ใบเสร็จรับเงิน/ใบกากับภาษี (Receipt/ Tax Invoice)                   |
|   T04 | ใบส่งของ/ใบกากับภาษี (Delivery order/ Tax Invoice)               |
|   T05 | ใบกากับภาษีอย่างย่อ (Abbreviated Tax Invoice)                     |
|   T06 | ใบเสร็จรับเงิน/ใบกากับภาษีอย่างย่อ (Receipt/Abbreviated Tax Invoice)  |
|   T07 | ใบแจ้งยกเลิก (Cancellation note)                                 |

#### Example
```
java -jar target/PdfAConverter-0.0.1-SNAPSHOT.jar "./src/main/resources/sample.pdf" "./src/main/resources/ETDA-invoice.xml" "./src/main/resources/sRGB Color Space Profile.icm" "./target/success.pdf" "388" "ETDA-invoice.xml" "2.0" "./src/main/resources/xmpTemplate.xml" "PKCS12"
```

## Self-Certificate
```sh
openssl req -x509 -nodes -sha512 -days 365 -subj "/CN=Local" -newkey rsa:4096 -keyout privatekey.pem -out cert.pem
openssl pkcs12 -export -out keystore.p12 -inkey privatekey.pem -in cert.pem
openssl pkcs12 -export -name server-cert -in cert.pem -inkey privatekey.pem -out keystore.p12
keytool -importkeystore -destkeystore keystore.jks -srckeystore keystore.p12 -srcstoretype pkcs12 -alias server-cert
keytool -import -alias server-cert -file cert.pem -keystore truststore.jks
```
copy 'keystore.p12' to 'src/main/resources/pkcs12/keystore.p12' <br/>
copy 'truststore.jks' to 'src/main/resources/trustStore/trustStore.jks'

## XADES Configuration
PKCS11_LIB_PATH must be absolute path.

## Before Sign with PKCS11
You have to insall eToken driver before run program
