package nl.rabobank.customer.statement.adapters.xml

import java.io.InputStream
import java.math.BigDecimal
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamException
import javax.xml.stream.XMLStreamReader

object XmlCustomerStatementReader {

    private const val record = "record"
    private const val records = "records"
    private const val reference = "reference"
    private const val accountNumber = "accountNumber"
    private const val description = "description"
    private const val startBalance = "startBalance"
    private const val mutation = "mutation"
    private const val endBalance = "endBalance"

    fun readFromXML(a: InputStream?): Sequence<XmlCustomerStatement> {
        val inputFactory: XMLInputFactory = XMLInputFactory.newInstance()
        var reader: XMLStreamReader? = null
        return try {
            reader = inputFactory.createXMLStreamReader(a)
            readDocument(reader)
        } finally {
            reader?.close()
        }
    }

    private fun readDocument(reader: XMLStreamReader): Sequence<XmlCustomerStatement> {
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamReader.START_ELEMENT -> {
                    val elementName = reader.localName
                    if (elementName == records) return readCustomerStatements(reader)
                }
            }
        }
        throw XMLStreamException("Premature end of file")
    }

    private fun readCustomerStatements(reader: XMLStreamReader): Sequence<XmlCustomerStatement> {
        var customerStatements = sequenceOf<XmlCustomerStatement>()
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamReader.START_ELEMENT -> {
                    val elementName = reader.localName
                    if (elementName == record) customerStatements += readCustomerStatement(reader)

                }

                XMLStreamReader.END_ELEMENT -> return customerStatements
            }
        }
        throw XMLStreamException("Premature end of file")
    }

    private fun readCharacters(reader: XMLStreamReader): String {
        val result = StringBuilder()
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamReader.CHARACTERS, XMLStreamReader.CDATA -> result.append(reader.text)
                XMLStreamReader.END_ELEMENT -> return result.toString()
            }
        }
        throw XMLStreamException("Premature end of file")
    }

    private fun readCustomerStatement(reader: XMLStreamReader): XmlCustomerStatement {
        val customerStatementDto = XmlCustomerStatement()
        customerStatementDto.reference = reader.getAttributeValue(null, reference)
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamReader.START_ELEMENT -> {
                    when (reader.localName) {
                        accountNumber -> customerStatementDto.apply {
                            this.accountNumber = readCharacters(reader)
                        }

                        description -> customerStatementDto.apply {
                            this.description = readCharacters(reader)
                        }

                        startBalance -> customerStatementDto.apply {
                            this.startBalance = BigDecimal(readCharacters(reader))
                        }

                        endBalance -> customerStatementDto.apply { this.endBalance = BigDecimal(readCharacters(reader)) }

                        mutation -> customerStatementDto.apply { this.mutation = readCharacters(reader) }
                    }
                }

                XMLStreamReader.END_ELEMENT -> return customerStatementDto
            }
        }
        throw XMLStreamException("Premature end of file")
    }
}