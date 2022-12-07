package nl.rabobank.customer.statement

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Files
import java.nio.file.Path

internal class ConsoleApplicationTest {

    companion object {
        private const val outputFile = "build/tmp/test/"
        private const val inputXmlPath = "src/test/resources/records.xml"
        private const val inputCsvPath = "src/test/resources/records.csv"
        private const val inputJsonPath = "src/test/resources/records.json"
    }

    @Test
    fun `should process csv`() {
        val outputPath = Path.of(outputFile)

        ConsoleApplication.main(arrayOf(inputCsvPath, outputFile, "CSV"))

        assertTrue(Files.exists(outputPath))

    }

    @Test
    fun `should process XML`() {
        val outputPath = Path.of(outputFile)

        ConsoleApplication.main(arrayOf(inputXmlPath, outputFile, "XML"))

        assertTrue(Files.exists(outputPath))
    }

    @Test
    fun `should process JSON`() {
        ConsoleApplication.main(arrayOf(inputJsonPath, outputFile, "JSON"))
        val outputPath = Path.of(outputFile)
        assertTrue { Files.exists(outputPath) }
    }

    @Test
    fun `should throw illegal argument exception on unknown adapter`() {
        assertThrows<IllegalArgumentException> { ConsoleApplication.main(arrayOf("some path", outputFile, "PDF")) }
    }

    @Test
    fun `incorrect number of arguments`() {
        assertThrows<IllegalArgumentException> { ConsoleApplication.main(arrayOf(outputFile, "PDF")) }
    }
}