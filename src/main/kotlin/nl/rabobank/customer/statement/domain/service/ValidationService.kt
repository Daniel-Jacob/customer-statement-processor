package nl.rabobank.customer.statement.domain.service

import nl.rabobank.customer.statement.domain.CustomerStatement
import nl.rabobank.customer.statement.validators.CustomerStatementValidationError
import nl.rabobank.customer.statement.validators.Validator

class ValidationService(private val validators: List<Validator<CustomerStatement, CustomerStatementValidationError>>) {

    fun validate(statement: CustomerStatement): List<CustomerStatementValidationError> {
        return validators.flatMap { it.validate(statement) }
    }
}