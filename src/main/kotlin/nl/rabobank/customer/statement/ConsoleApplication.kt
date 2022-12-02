package nl.rabobank.customer.statement

import nl.rabobank.customer.statement.adapters.CustomerStatementInputType
import nl.rabobank.customer.statement.domain.service.CustomerStatementService

class ConsoleApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            require(args.size == 3) { "Expected 3 command-line arguments: <input file> <output directory> <type: CSV|XML>" }
            val service = CustomerStatementService()
            service.processCustomerStatements(args[0], args[1], CustomerStatementInputType.valueOf(args[2]))
        }
    }
}
