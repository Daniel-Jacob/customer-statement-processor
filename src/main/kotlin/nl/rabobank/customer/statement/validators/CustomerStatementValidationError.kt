package nl.rabobank.customer.statement.validators

data class CustomerStatementValidationError(val reference: String, val description: String, val reason: String) : ValidationError()
