package nl.rabobank.customer.statement.domain.service

import nl.rabobank.customer.statement.domain.CustomerStatement
import nl.rabobank.customer.statement.validators.EndBalanceValidator
import nl.rabobank.customer.statement.validators.UniqueReferenceValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ValidationServiceTest {

    @Test
    fun `should return error for the incorrect mutation`() {
        val validationService = ValidationService(listOf(UniqueReferenceValidator(), EndBalanceValidator()))

        val customerStatement = CustomerStatement(
            reference = "1234",
            accountNumber = "NL50ABNA123456789",
            description = "this is a description of the mutation",
            startBalance = BigDecimal.TEN,
            endBalance = BigDecimal.ZERO,
            mutation = "-5"
        )

        val result = validationService.validate(customerStatement)
        assertEquals(1, result.size, "Should return exactly 1 error for the incorrect mutation")
    }
}