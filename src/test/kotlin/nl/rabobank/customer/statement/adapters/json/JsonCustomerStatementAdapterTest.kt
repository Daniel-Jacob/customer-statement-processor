package nl.rabobank.customer.statement.adapters.json

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class JsonCustomerStatementAdapterTest {
    private val resourcePath = Paths.get("src", "test", "resources", "records.json")
    @Test
    fun `should deserialize json to customer statement sequence`() {
        val inputStream = Files.newInputStream(resourcePath)
        val jsonCustomerStatementAdapter = JsonCustomerStatementAdapter()
        val customerStatements = jsonCustomerStatementAdapter.convert(inputStream).toList()
        assertEquals(10, customerStatements.size)
    }
}