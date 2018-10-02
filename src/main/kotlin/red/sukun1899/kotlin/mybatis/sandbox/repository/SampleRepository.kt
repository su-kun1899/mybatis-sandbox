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
}

data class Sample(val id: String, val name: String, val childNames: List<String>)
