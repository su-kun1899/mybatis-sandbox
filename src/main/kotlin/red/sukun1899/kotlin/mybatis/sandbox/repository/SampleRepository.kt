package red.sukun1899.kotlin.mybatis.sandbox.repository

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * @author su-kun1899
 */
@Mapper
@Repository
interface SampleRepository {
    // N+1は起きるが、MyBatisのconstructorで解決できる
    fun findAll(): List<Sample>

    fun findSample2(): List<Sample2>
}

data class Sample(val id: String, val name: String, val childNames: List<String>)

data class Sample2(val id: String, val name: String) {
    var childNames = mutableListOf<String>()
}
