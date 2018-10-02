package red.sukun1899.kotlin.mybatis.sandbox.repository

import com.ninja_squad.dbsetup.DbSetup
import com.ninja_squad.dbsetup.Operations
import com.ninja_squad.dbsetup.destination.DataSourceDestination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.sql.DataSource

/**
 * @author su-kun1899
 */
@SpringBootTest
class SampleRepositorySpec extends Specification {
    @Autowired
    SampleRepository sampleRepository

    @Autowired
    DataSource dataSource

    DataSourceDestination dataSourceDestination

    def setup() {
        dataSourceDestination = new DataSourceDestination(dataSource)
    }

    def "Hello, mybatis"() {
        given:
        def parentId = UUID.randomUUID().toString()
        def insertParent = Operations.insertInto("parent")
                .columns("id", "name")
                .values(parentId, "parent1")
                .build()
        def operations = Operations.sequenceOf(insertParent)
        new DbSetup(dataSourceDestination, operations).launch()

        expect:
        sampleRepository.findAll().size() == 1

        cleanup:
        new DbSetup(dataSourceDestination, Operations.deleteAllFrom("parent")).launch()
    }
}
