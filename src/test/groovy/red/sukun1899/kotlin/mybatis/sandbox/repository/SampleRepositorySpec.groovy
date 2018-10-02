package red.sukun1899.kotlin.mybatis.sandbox.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author su-kun1899
 */
@SpringBootTest
class SampleRepositorySpec extends Specification {
    @Autowired
    SampleRepository sampleRepository

    def "Hello, mybatis"() {
        expect:
        sampleRepository.findAll().size() == 0
    }

}
