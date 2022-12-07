package nl.rabobank.customer.statement.domain.service

import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.adapters.csv.CsvCustomerStatementAdapter
import nl.rabobank.customer.statement.adapters.json.JsonCustomerStatementAdapter
import nl.rabobank.customer.statement.adapters.xml.XmlCustomerStatementAdapter
import nl.rabobank.customer.statement.domain.error.ErrorHandler
import nl.rabobank.customer.statement.validators.EndBalanceValidator
import nl.rabobank.customer.statement.validators.UniqueReferenceValidator
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class CustomerStatementService(
    private val validationService: ValidationService = ValidationService(
        listOf(
            EndBalanceValidator(),
            UniqueReferenceValidator()
        )
    ),
    private val converterService: ConverterService = ConverterService(
        listOf(
            XmlCustomerStatementAdapter(),
            CsvCustomerStatementAdapter(),
            JsonCustomerStatementAdapter()
        )
    )
) {

    fun processCustomerStatements(inputFile: String, outputDir: String, type: CustomerStatementInputType): Path {

        require(Files.exists(Paths.get(outputDir))) {
            "Specified report path does not exists"
        }

        return File(inputFile).inputStream()
            .use { inputStream ->
                converterService.getConverterForType(type).convert(inputStream)
                    .flatMap { customerStatement ->
                        validationService.validate(customerStatement)
                    }
                    .let { ErrorHandler.handleErrors(it, outputDir) }
            }
    }
}
