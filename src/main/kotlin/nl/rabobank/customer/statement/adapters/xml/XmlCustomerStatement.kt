package nl.rabobank.customer.statement.adapters.xml

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import nl.rabobank.customer.statement.domain.CustomerStatement
import java.math.BigDecimal
import java.math.BigDecimal.ZERO


@JacksonXmlRootElement(localName = "record")
data class XmlCustomerStatement(
    @field:JacksonXmlProperty(localName = "reference")
    var reference: String = "",
    @field:JacksonXmlProperty(localName = "accountNumber")
    var accountNumber: String = "",
    @field:JacksonXmlProperty(localName = "description")
    var description: String = "",
    @field:JacksonXmlProperty(localName = "startBalance")
    var startBalance: BigDecimal = ZERO,
    @field:JacksonXmlProperty(localName = "endBalance")
    var endBalance: BigDecimal = ZERO,
    @field:JacksonXmlProperty(localName = "mutation")
    var mutation: String = ""
) {
    fun toCustomerStatement(): CustomerStatement =
        CustomerStatement(
            reference = reference,
            description = description,
            mutation = mutation,
            startBalance = startBalance,
            endBalance = endBalance,
            accountNumber = accountNumber
        )
}
