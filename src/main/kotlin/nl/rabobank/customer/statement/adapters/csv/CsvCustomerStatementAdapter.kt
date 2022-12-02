package nl.rabobank.customer.statement.adapters.csv

import com.opencsv.CSVReader
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.CsvToBean
import nl.rabobank.customer.statement.adapters.CustomerStatementConverter
import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.domain.CustomerStatement
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class CsvCustomerStatementAdapter : CustomerStatementConverter {
    override fun convert(inputStream: InputStream): Sequence<CustomerStatement> {
        val csv = createCsvReader(inputStream)
        return csv.iterator().asSequence()
            .map(CsvCustomerStatement::toCustomerStatement)
    }

    override fun getType() = CustomerStatementInputType.CSV

    private fun createCsvReader(inputStream: InputStream): CsvToBean<CsvCustomerStatement> {
        val csvReader = CSVReader(BufferedReader(InputStreamReader(inputStream, Charsets.ISO_8859_1)))
        csvReader.skip(1)
        val mappingStrategy = ColumnPositionMappingStrategy<CsvCustomerStatement>()
        mappingStrategy.type = CsvCustomerStatement::class.java
        val csv: CsvToBean<CsvCustomerStatement> = CsvToBean<CsvCustomerStatement>()
        csv.setMappingStrategy(mappingStrategy)
        csv.setCsvReader(csvReader)
        return csv
    }
}