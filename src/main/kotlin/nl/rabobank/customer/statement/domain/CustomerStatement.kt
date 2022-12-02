package nl.rabobank.customer.statement.domain

import java.math.BigDecimal

data class CustomerStatement(
    val reference: String,
    val description: String,
    val mutation: String,
    val startBalance: BigDecimal,
    val endBalance: BigDecimal,
    val accountNumber: String
)