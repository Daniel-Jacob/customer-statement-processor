package nl.rabobank.customer.statement.adapters.json

import nl.rabobank.customer.statement.domain.CustomerStatement
import java.math.BigDecimal

data class JsonCustomerStatement(
    var reference: String = "",
    var accountNumber: String = "",
    var description: String = "",
    var startBalance: BigDecimal = BigDecimal.ZERO,
    var endBalance: BigDecimal = BigDecimal.ZERO,
    var mutation: String = ""
) {
    fun toCustomerStatement(): CustomerStatement =
        CustomerStatement(
            reference = reference,
            description = description,
            mutation = mutation,
            startBalance = startBalance,
            endBalance = endBalance,
            accountNumber = accountNumber
        )
}