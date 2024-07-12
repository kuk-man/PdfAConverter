package rd.checker.format;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormatTest {
    String object;

    @BeforeEach
    private void setBeforEach() {
        object = "Test";
    }

    @AfterEach
    private void setAfterEach() {
        object = "";
    }

    @Test
    void testCheckPercentageRate() {
        // Arrange
        Format format;
        String validPercentageRage = "10.00";
        String invalidPercentageRage = "abc";

        // Act & Assert
        format = new Format();
        format.checkPercentageRate(validPercentageRage, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkPercentageRate(invalidPercentageRage, "Test");
        assertEquals("Check " + object + ": Invalid PercentageRage (Decimal). Current PercentageRage = " + invalidPercentageRage + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckAmount() {
        // Arrange
        Format format;
        String validAmount = "10.50";
        String invalidAmount = "abc";

        // Act & Assert
        format = new Format();
        format.checkAmount(validAmount, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkAmount(invalidAmount, "Test");
        assertEquals("Check " + object + ": Invalid Amount (Decimal). Current Amount = " + invalidAmount + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckQuantity() {
        // Arrange
        Format format;
        String validQuantity = "10";
        String invalidQuantity = "abc";

        // Act & Assert
        format = new Format();
        format.checkQuantity(validQuantity, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkQuantity(invalidQuantity, "Test");
        assertEquals("Check " + object + ": Invalid Quantity (Decimal). Current Quantity = " + invalidQuantity + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckIsoDateTime() {
        // Arrange
        Format format;
        String validIsoDateTime = "2023-03-08T10:15:30";
        String invalidIsoDateTime = "2023-03-08T10:15:30:55";

        // Act & Assert
        format = new Format();
        format.checkIsoDateTime(validIsoDateTime, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkIsoDateTime(invalidIsoDateTime, "Test");
        assertEquals("Check " + object + ": Invalid DateTime (YYYY-mm-DDTHH:MM:SS). Current DateTime = " + invalidIsoDateTime + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckTrueFalse() {
        // Arrange
        Format format;
        String validTrueFalse1 = "true";
        String validTrueFalse2 = "false";
        String invalidTrueFalse = "abc";

        // Act & Assert
        format = new Format();
        format.checkTrueFalse(validTrueFalse1, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkTrueFalse(validTrueFalse2, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkTrueFalse(invalidTrueFalse, "Test");
        assertEquals("Check " + object + ": Invalid true/false. Current true/false = " + invalidTrueFalse + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckPhoneNumber() {
        // Arrange
        Format format;
        String validPhoneNumber = "true";
        String invalidPhoneNumber = "abc";

        // Act & Assert
        format = new Format();
        format.checkTrueFalse(validPhoneNumber, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkTrueFalse(invalidPhoneNumber, "Test");
        assertEquals("Check " + object + ": Invalid true/false. Current true/false = " + invalidPhoneNumber + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheckText() {
        // Arrange
        Format format;
        int maxLength = 5;
        String validText = "a1234";
        String invalidText1 = "a12345";
        String invalidText2 = "";

        // Act & Assert
        format = new Format();
        format.checkText(validText, maxLength, "Test");
        assertEquals("", format.getError().getErrorMessage());

        format = new Format();
        format.checkText(invalidText1, maxLength, "Test");
        assertEquals("Check " + object + ": Invalid Text (> 0 & <= " + maxLength + " characters). Current Text = " + invalidText1 + "\n", format.getError().getErrorMessage());

        format = new Format();
        format.checkText(invalidText2, maxLength, "Test");
        assertEquals("Check " + object + ": Invalid Text (> 0 & <= " + maxLength + " characters). Current Text = " + invalidText2 + "\n", format.getError().getErrorMessage());
    }

    @Test
    void testCheck16Text() {
        // Arrange
        Format format;
        String validText = "ABCdEFghIJKLMNO?";

        // Act & Assert
        format = new Format();
        format.check16Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck35Text() {
        // Arrange
        Format format;
        String validText = "ABCDEFGHIJKLMNOPQRST123456789012345";

        // Act & Assert
        format = new Format();
        format.check35Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck70Text() {
        // Arrange
        Format format;
        String validText = "ABCDEFGHIJKLMNOPQRST12345678901234567890123456789012345678901234567890";

        // Act & Assert
        format = new Format();
        format.check70Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck140Text() {
        // Arrange
        Format format;
        String validText = "ABCDEFGHIJKLMNOPQRST123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        
        // Act & Assert
        format = new Format();
        format.check140Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck256Text() {
        // Arrange
        Format format;
        String validText = "ABCDEFGHIJKLMNOPQRST123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890ABCDEFGHIJKLMNOPQRST123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456";
        
        // Act & Assert
        format = new Format();
        format.check256Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck500Text() {
        // Arrange
        Format format;
        String validText = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        
        // Act & Assert
        format = new Format();
        format.check500Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }

    @Test
    void testCheck2048Text() {
        // Arrange
        Format format;
        String validText = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890ABCDEFGHIJKLMNOPQRST1234567890123451111111111111";
        
        // Act & Assert
        format = new Format();
        format.check2048Text(validText, "Test");
        assertEquals("", format.getError().getErrorMessage());
    }
}