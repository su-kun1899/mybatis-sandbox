package red.sukun1899.mybatis.sandbox.repository

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

    // 入れ子だけvarにする逃げの一手
    fun findSample2(): List<Sample2>
}

data class Sample(val id: String, val name: String, val childNames: List<String>)

data class Sample2(val id: String, val name: String, var childNames: List<String>) {
    constructor(id: String, name: String) : this(id, name, mutableListOf())
}
