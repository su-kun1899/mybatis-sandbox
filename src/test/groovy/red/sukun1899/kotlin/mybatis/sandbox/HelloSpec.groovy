package red.sukun1899.kotlin.mybatis.sandbox

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author su-kun1899
 */
@SpringBootTest
class HelloSpec extends Specification {
    def "Hello"() {
        expect:
        true
    }
}
