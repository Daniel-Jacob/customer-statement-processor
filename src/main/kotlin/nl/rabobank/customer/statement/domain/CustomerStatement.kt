package nl.rabobank.customer.statement.domain

import java.math.BigDecimal

data class CustomerStatement(
    val reference: String,
    val accountNumber: String,
    val description: String,
    val startBalance: BigDecimal,
    val mutation: String,
    val endBalance: BigDecimal
)