package red.sukun1899.mybatis.sandbox.mapper

import org.apache.ibatis.annotations.Mapper
import red.sukun1899.mybatis.sandbox.data.Sample
import red.sukun1899.mybatis.sandbox.data.Sample2

/**
 * @author su-kun1899
 */
@Mapper
interface SampleMapper {
    // N+1は起きるが、MyBatisのconstructorで解決できる
    fun findAll(): List<Sample>

    // 入れ子だけvarにする逃げの一手
    fun findSample2(): List<Sample2>
}
