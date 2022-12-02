package nl.rabobank.customer.statement.domain.error

import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsvBuilder
import nl.rabobank.customer.statement.validators.CustomerStatementValidationError
import java.io.FileWriter
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.asStream

object ErrorHandler {
    fun handleErrors(errors: Sequence<CustomerStatementValidationError>, outputDir: String): Path {
        val path = Paths.get(outputDir)

        val generatedReportPath = Paths.get(path.toString(), "report-${System.currentTimeMillis()}.csv")
        CSVWriter(FileWriter(generatedReportPath.toFile())).use { csvWriter ->
            StatefulBeanToCsvBuilder<CustomerStatementValidationError>(csvWriter).build().write(errors.asStream())
        }
        return generatedReportPath
    }
}