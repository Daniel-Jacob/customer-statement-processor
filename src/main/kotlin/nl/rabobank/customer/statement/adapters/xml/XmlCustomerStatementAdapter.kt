package nl.rabobank.customer.statement.adapters.xml

import nl.rabobank.customer.statement.adapters.CustomerStatementConverter
import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.domain.CustomerStatement
import java.io.InputStream

class XmlCustomerStatementAdapter : CustomerStatementConverter {

    override fun convert(inputStream: InputStream): Sequence<CustomerStatement> {
        return XmlCustomerStatementReader.readFromXML(inputStream)
            .map(XmlCustomerStatement::toCustomerStatement)
    }

    override fun getType() = CustomerStatementInputType.XML
}