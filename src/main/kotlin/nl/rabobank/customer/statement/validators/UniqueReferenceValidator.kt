package nl.rabobank.customer.statement.validators

import nl.rabobank.customer.statement.domain.CustomerStatement

class UniqueReferenceValidator : Validator<CustomerStatement, CustomerStatementValidationError> {
    private val repository: MutableSet<String> = mutableSetOf()

    override fun validate(statement: CustomerStatement): List<CustomerStatementValidationError> {
        val errors = mutableListOf<CustomerStatementValidationError>()
        val duplicateReference = repository.contains(statement.reference)
        if (duplicateReference) {
            errors.add(
                CustomerStatementValidationError(
                    reference = statement.reference,
                    description = statement.description,
                    reason = "could not save customer reference since it already exists."
                )
            )
        } else repository.add(statement.reference)

        return errors.toList()
    }
}