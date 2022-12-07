package nl.rabobank.customer.statement.adapters.json

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import nl.rabobank.customer.statement.adapters.CustomerStatementConverter
import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.adapters.CustomerStatementInputType.JSON
import nl.rabobank.customer.statement.domain.CustomerStatement
import java.io.InputStream
import java.io.InputStreamReader

class JsonCustomerStatementAdapter : CustomerStatementConverter {

    override fun convert(inputStream: InputStream): Sequence<CustomerStatement> {
        val reader = JsonReader(InputStreamReader(inputStream))
        return sequence {
            reader.beginArray()
            while (reader.hasNext()) {
                val gson = Gson()
                yield(
                    gson.fromJson<JsonCustomerStatement>(reader, JsonCustomerStatement::class.java)
                        .toCustomerStatement()
                )
            }
        }
    }

    override fun getType(): CustomerStatementInputType {
        return JSON
    }
}