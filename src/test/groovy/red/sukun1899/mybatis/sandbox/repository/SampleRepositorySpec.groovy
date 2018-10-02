package red.sukun1899.mybatis.sandbox.repository

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

    def "MyBatisだけでconstrucotインジェクションさせる"() {
        given:
        def parentId1 = UUID.randomUUID().toString()
        def parentId2 = UUID.randomUUID().toString()
        def insertParent = Operations.insertInto("parent")
                .columns("id", "name")
                .values(parentId1, "parent1")
                .values(parentId2, "parent2")
                .build()
        def insertChild = Operations.insertInto("child")
                .columns("id", "parent_id", "name")
                .values(UUID.randomUUID().toString(), parentId1, "child1")
                .values(UUID.randomUUID().toString(), parentId1, "child2")
                .build()
        def operations = Operations.sequenceOf(insertParent, insertChild)
        new DbSetup(dataSourceDestination, operations).launch()

        when:
        def actual = sampleRepository.findAll()

        then:
        actual.size() == 2

        and:
        def sample = actual.first()
        sample.id == parentId1
        sample.name == "parent1"
        sample.childNames.size() == 2
        sample.childNames.first() == "child1"
        sample.childNames[1] == "child2"

        cleanup:
        new DbSetup(dataSourceDestination, Operations.deleteAllFrom("child", "parent")).launch()
    }

    def "ネストしたプロパティだけvarにすれば逃げられる"() {
        given:
        def parentId1 = UUID.randomUUID().toString()
        def parentId2 = UUID.randomUUID().toString()
        def insertParent = Operations.insertInto("parent")
                .columns("id", "name")
                .values(parentId1, "parent1")
                .values(parentId2, "parent2")
                .build()
        def insertChild = Operations.insertInto("child")
                .columns("id", "parent_id", "name")
                .values(UUID.randomUUID().toString(), parentId1, "child1")
                .values(UUID.randomUUID().toString(), parentId1, "child2")
                .values(UUID.randomUUID().toString(), parentId2, "child3")
                .values(UUID.randomUUID().toString(), parentId2, "child4")
                .values(UUID.randomUUID().toString(), parentId2, "child5")
                .build()
        def operations = Operations.sequenceOf(insertParent, insertChild)
        new DbSetup(dataSourceDestination, operations).launch()

        when:
        def actual = sampleRepository.findSample2()

        then:
        actual.size() == 2

        and:
        def sample = actual.first()
        sample.id == parentId1
        sample.name == "parent1"
        sample.childNames.size() == 2
        sample.childNames.containsAll(["child1", "child2"])

        and:
        def sample2 = actual[1]
        sample2.id == parentId2
        sample2.name == "parent2"
        sample2.childNames.size() == 3
        sample2.childNames.containsAll(["child3", "child4", "child5"])

        cleanup:
        new DbSetup(dataSourceDestination, Operations.deleteAllFrom("child", "parent")).launch()
    }
}
