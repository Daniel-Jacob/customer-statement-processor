package nl.rabobank.customer.statement.domain.error

import com.opencsv.CSVWriter
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import nl.rabobank.customer.statement.validators.CustomerStatementValidationError
import java.io.FileWriter
import java.nio.file.Path
import java.nio.file.Paths

class ErrorHandler(outputDir: String) : AutoCloseable {

    private val path = Paths.get(outputDir)

    val generatedReportPath: Path = Paths.get(path.toString(), "report-${System.currentTimeMillis()}.csv")

    private val writer = CSVWriter(FileWriter(generatedReportPath.toFile()))
    private val statefulBeanToCsv: StatefulBeanToCsv<CustomerStatementValidationError> = StatefulBeanToCsvBuilder<CustomerStatementValidationError>(writer).build()



    fun handleErrors(errors: List<CustomerStatementValidationError>) {
        statefulBeanToCsv.write(errors)

    }

    override fun close() {
        writer.close()
    }
}