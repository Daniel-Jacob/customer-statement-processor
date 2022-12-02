package nl.rabobank.customer.statement.adapters.xml

import nl.rabobank.customer.statement.domain.CustomerStatement
import nl.rabobank.customer.statement.adapters.xml.XmlCustomerStatementAdapter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigDecimal

internal class XmlCustomerStatementAdapterTest {

    @Test
    fun `should deserialize xml to customerStatementDto`() {
        val xmlCustomerStatementAdapter = XmlCustomerStatementAdapter()
        val result = xmlCustomerStatementAdapter.convert(File("src/test/resources/records.xml").inputStream()).toList()
        assertEquals(result.size, 10)
        assertEquals(expected(), result)
    }

    private fun expected(): List<CustomerStatement> = listOf(
        CustomerStatement(
            reference = "138932",
            description = "Flowers for Richard Bakker",
            mutation = "+14.63",
            startBalance = BigDecimal("94.9"),
            endBalance = BigDecimal("109.53"),
            accountNumber = "NL90ABNA0585647886"
        ),
        CustomerStatement(
            reference = "131254",
            description = "Candy from Vincent de Vries",
            mutation = "-939",
            startBalance = BigDecimal("5429"), endBalance = BigDecimal("6368"),
            accountNumber = "NL93ABNA0585619023"
        ),
        CustomerStatement(
            reference = "101339",
            description = "Tickets from Rik King",
            mutation = "+40.45",
            startBalance = BigDecimal("84.46"),
            endBalance = BigDecimal("124.91"),
            accountNumber = "NL27SNSB0917829871"
        ),
        CustomerStatement(
            reference = "119582",
            description = "Subscription from Vincent de Vries",
            mutation = "+28.77",
            startBalance = BigDecimal("38.86"),
            endBalance = BigDecimal("67.63"),
            accountNumber = "NL32RABO0195610843"
        ),
        CustomerStatement(
            reference = "180148",
            description = "Candy for Rik de Vries",
            mutation = "-25.59",
            startBalance = BigDecimal("51.01"),
            endBalance = BigDecimal("25.42"),
            accountNumber = "NL32RABO0195610843"
        ),
        CustomerStatement(
            reference = "152977",
            description = "Flowers from Richard Bakker",
            mutation = "+20.55",
            startBalance = BigDecimal("62.17"),
            endBalance = BigDecimal("82.72"),
            accountNumber = "NL69ABNA0433647324"
        ),
        CustomerStatement(
            reference = "167188",
            description = "Subscription for Jan Theuß",
            mutation = "-0.3",
            startBalance = BigDecimal("10.1"),
            endBalance = BigDecimal("9.8"),
            accountNumber = "NL91RABO0315273637"
        ),
        CustomerStatement(
            reference = "154771",
            description = "Clothes from Rik Theuß",
            mutation = "-17.57",
            startBalance = BigDecimal("21.54"),
            endBalance = BigDecimal("3.97"),
            accountNumber = "NL43AEGO0773393871"
        ),
        CustomerStatement(
            reference = "192480",
            description = "Subscription for Erik de Vries",
            mutation = "+1000",
            startBalance = BigDecimal("3980"),
            endBalance = BigDecimal("4981"),
            accountNumber = "NL43AEGO0773393871"
        ),
        CustomerStatement(
            reference = "181688",
            description = "Flowers for Jan Theuß",
            mutation = "-32.75",
            startBalance = BigDecimal("75.39"),
            endBalance = BigDecimal("42.64"),
            accountNumber = "NL90ABNA0585647886"
        )
    )
}
