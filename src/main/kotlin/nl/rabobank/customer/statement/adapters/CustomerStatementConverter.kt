package nl.rabobank.customer.statement.adapters

import nl.rabobank.customer.statement.domain.CustomerStatement
import java.io.InputStream

enum class CustomerStatementInputType {
    UNKNOWN, CSV, XML, JSON
}

interface CustomerStatementConverter {
    fun convert(inputStream: InputStream): Sequence<CustomerStatement>
    fun getType(): CustomerStatementInputType
}