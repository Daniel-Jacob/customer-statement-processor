package nl.rabobank.customer.statement.adapters.csv

import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigDecimal

internal class CsvCustomerStatementAdapterTest {
    @Test
    fun `should convert csv file to CustomerStatementDto`() {
        val file = File("src/test/resources/records.csv")
        val adapter = CsvCustomerStatementAdapter()

        val result = adapter.convert(file.inputStream()).toList()

        assertEquals(expected().map { it.toCustomerStatement() }, result)
        assertEquals(adapter.getType(), CustomerStatementInputType.CSV)
    }

    private fun expected(): List<CsvCustomerStatement> {
        return listOf(
            CsvCustomerStatement(
                reference = "183398",
                accountNumber = "NL56RABO0149876948",
                description = "Clothes from Richard de Vries",
                mutation = "+5.55",
                startBalance = BigDecimal("33.34"),
                endBalance = BigDecimal("38.89"),
            ),
            CsvCustomerStatement(
                reference = "112806",
                accountNumber = "NL27SNSB0917829871",
                description = "Subscription from Jan Dekker",
                mutation = "-19.44",
                startBalance = BigDecimal("28.95"),
                endBalance = BigDecimal("9.51")
            ),
            CsvCustomerStatement(
                reference = "110784",
                accountNumber = "NL93ABNA0585619023",
                description = "Subscription from Richard Bakker",
                mutation = "-46.18",
                startBalance = BigDecimal("13.89"),
                endBalance = BigDecimal("-32.29"),
            ),
            CsvCustomerStatement(
                reference = "137758",
                accountNumber = "NL93ABNA0585619023",
                description = "Tickets for Daniël King",
                mutation = "+46.41",
                startBalance = BigDecimal("97.56"),
                endBalance = BigDecimal("143.97"),
            ), CsvCustomerStatement(
                reference = "112806",
                accountNumber = "NL43AEGO0773393871",
                description = "Subscription from Daniël Theuß",
                mutation = "+11.49",
                startBalance = BigDecimal("102.33"),
                endBalance = BigDecimal("113.82"),
            ),
            CsvCustomerStatement(
                reference = "112806",
                accountNumber = "NL74ABNA0248990274",
                description = "Subscription for Rik Dekker",
                mutation = "-4.25",
                startBalance = BigDecimal("48.2"),
                endBalance = BigDecimal("43.95"),
            ),
            CsvCustomerStatement(
                reference = "118757",
                accountNumber = "NL32RABO0195610843",
                description = "Candy for Willem King",
                mutation = "-7.85",
                startBalance = BigDecimal("98.99"),
                endBalance = BigDecimal("91.14"),
            ),
            CsvCustomerStatement(
                reference = "146294",
                accountNumber = "NL90ABNA0585647886",
                description = "Tickets for Vincent Dekker",
                mutation = "-15.08",
                startBalance = BigDecimal("13.62"),
                endBalance = BigDecimal("-1.46"),
            ),
            CsvCustomerStatement(
                reference = "128470",
                accountNumber = "NL91RABO0315273637",
                description = "Tickets for Erik Dekker",
                mutation = "-15.85",
                startBalance = BigDecimal("53.31"),
                endBalance = BigDecimal("37.46"),
            ),
            CsvCustomerStatement(
                reference = "141007",
                accountNumber = "NL32RABO0195610843",
                description = "Tickets for Richard de Vries",
                mutation = "+44.27",
                startBalance = BigDecimal("66.35"),
                endBalance = BigDecimal("110.62"),
            )
        )
    }
}