package nl.rabobank.customer.statement.domain.service

import nl.rabobank.customer.statement.adapters.CustomerStatementConverter
import nl.rabobank.customer.statement.adapters.CustomerStatementInputType

class ConverterService(private val converters: List<CustomerStatementConverter>) {
    fun getConverterForType(type: CustomerStatementInputType): CustomerStatementConverter {
        return converters.firstOrNull { it.getType() == type }
            ?: throw IllegalArgumentException("Could not find implementation for message type: $type")
    }
}
