package nl.rabobank.customer.statement.validators

open class ValidationError
/**
 *
 * @param I is the input argument to validate
 * @param E is the list of errors returned
 */
interface Validator<I, E : ValidationError> {
    fun validate(statement: I): List<E>
}