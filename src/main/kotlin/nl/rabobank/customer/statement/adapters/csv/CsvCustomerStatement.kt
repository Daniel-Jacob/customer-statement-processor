package nl.rabobank.customer.statement.adapters.csv

import com.opencsv.bean.CsvBindByPosition
import nl.rabobank.customer.statement.domain.CustomerStatement
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class CsvCustomerStatement(
    @CsvBindByPosition(position = 0)
    val reference: String,
    @CsvBindByPosition(position = 1)
    val accountNumber: String,
    @CsvBindByPosition(position = 2)
    val description: String,
    @CsvBindByPosition(position = 3)
    val startBalance: BigDecimal,
    @CsvBindByPosition(position = 4)
    val mutation: String,
    @CsvBindByPosition(position = 5)
    val endBalance: BigDecimal
){
    constructor() : this("", "", "", ZERO, "", ZERO)  // mandatory no-args for opencsv lib

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