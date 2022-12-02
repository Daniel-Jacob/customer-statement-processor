package nl.rabobank.customer.statement.domain.validators

import nl.rabobank.customer.statement.domain.CustomerStatement
import nl.rabobank.customer.statement.validators.CustomerStatementValidationError
import nl.rabobank.customer.statement.validators.EndBalanceValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

internal class EndBalanceValidatorTest {
    private val defaultCustomerStatement = CustomerStatement(
        reference = "1234",
        accountNumber = "NL50ABNA123456789",
        description = "this is a description of the mutation",
        mutation = "0",
        startBalance = ZERO,
        endBalance = ZERO
    )

    @Test
    fun `should add error when balance does not match mutation on addition`() {

        assertShouldAddErrorForEndBalance(
            start = BigDecimal(2.50),
            end = BigDecimal(5.00),
            mutationValue = "+5.01")
    }

    @Test
    fun `should add error when balance does not match mutation on deduction`() {
        assertShouldAddErrorForEndBalance(
            start = BigDecimal(2.50),
            end = ZERO,
            mutationValue = "-5"
       )
    }

    @Test
    fun `should add error on unknown mutation type`() {
        assertShouldAddErrorForEndBalance(start = BigDecimal(2.50), end = BigDecimal(2.50), mutationValue = "*5.00")
    }

    private fun assertShouldAddErrorForEndBalance(start: BigDecimal, end: BigDecimal, mutationValue: String) {
        val customerStatement = defaultCustomerStatement.copy(startBalance = start, endBalance = end, mutation = mutationValue)

        val expectedReason = "Could not save customer reference since there is a discrepancy between the mutation and balance"

        val expected = CustomerStatementValidationError(defaultCustomerStatement.reference, defaultCustomerStatement.description, expectedReason)

        val result = EndBalanceValidator().validate(
            statement = customerStatement
        )

        Assertions.assertEquals(1, result.size)
        Assertions.assertEquals(listOf(expected), result)

    }
}