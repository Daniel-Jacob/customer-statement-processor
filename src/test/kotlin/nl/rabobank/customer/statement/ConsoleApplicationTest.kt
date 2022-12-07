package nl.rabobank.customer.statement

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import kotlin.io.path.appendLines

internal class ConsoleApplicationTest {

    companion object {
        private const val outputFile = "build/tmp/test/"
        private const val inputXmlPath = "src/test/resources/records.xml"
        private const val inputCsvPath = "src/test/resources/records.csv"
        private const val inputJsonPath = "src/test/resources/records.json"
        private const val inputCsvWithoutHeaderPath = "src/test/resources/records-without-header.csv"
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

    @Test
    @Disabled
    fun `generate big file and process`() {

        ConsoleApplication.main(arrayOf("build/tmp/test/huge-2022-12-07T21:50:35.316665.csv", outputFile, "CSV"))
    }

    private fun generateBigFile(size: Long): Path {
        val requestedSizeInBytes = size * 1073741824
        val bytes = Files.size(Paths.get(inputCsvWithoutHeaderPath))
        val amountOfDataToGenerate = (requestedSizeInBytes / bytes) + 1
        val bigFile = Files.createFile(Paths.get("build/tmp/test/huge-${LocalDateTime.now()}.csv"))
        for (i in 0..amountOfDataToGenerate) {
            bigFile.appendLines(Files.readAllLines(Paths.get(inputCsvWithoutHeaderPath), Charsets.ISO_8859_1))
        }
        return bigFile
    }
}