package nl.rabobank.customer.statement.validators

import nl.rabobank.customer.statement.domain.CustomerStatement
import java.math.BigDecimal

class EndBalanceValidator : Validator<CustomerStatement, CustomerStatementValidationError> {

    override fun validate(statement: CustomerStatement): List<CustomerStatementValidationError> {
        val errors = mutableListOf<CustomerStatementValidationError>()
        statement.takeIf { doesNotMatch(it) }
            ?.let {
                errors.add(
                    CustomerStatementValidationError(
                        reference = it.reference,
                        description = it.description,
                        reason = "Could not save customer reference since there is a discrepancy between the mutation and balance"
                    )
                )
            }
        return errors.toList()
    }

    private fun doesNotMatch(customerStatement: CustomerStatement): Boolean {
        val mutationType = customerStatement.mutation.first()
        val amount = customerStatement.mutation.drop(1)
        return when (mutationType) {
            '+' -> customerStatement.startBalance + BigDecimal(amount) != customerStatement.endBalance
            '-' -> customerStatement.startBalance - BigDecimal(amount) != customerStatement.endBalance
            // also add an error if we get an unknown mutation type
            else -> true
        }
    }
}