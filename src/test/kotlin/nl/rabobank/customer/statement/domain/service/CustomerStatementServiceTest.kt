package nl.rabobank.customer.statement.domain.service

import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.adapters.csv.CsvCustomerStatementAdapter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.io.path.inputStream

class CustomerStatementServiceTest {
    private val inputFile = "src/main/resources/records.csv"
    private val outputFile = "build/tmp/test/"

    @Test
    fun `should validate statement`() {
        val customerStatementService = CustomerStatementService()
        val report = customerStatementService.processCustomerStatements(inputFile, outputFile, CustomerStatementInputType.CSV)

        report.inputStream().use {
            val adapter = CsvCustomerStatementAdapter().convert(it)
            assertEquals(2, adapter.toList().size)
        }
    }
}