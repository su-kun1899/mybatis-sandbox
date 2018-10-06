package red.sukun1899.mybatis.sandbox.repository

import org.springframework.stereotype.Repository
import red.sukun1899.mybatis.sandbox.data.Sample
import red.sukun1899.mybatis.sandbox.data.Sample2
import red.sukun1899.mybatis.sandbox.mapper.SampleMapper

/**
 * @author su-kun1899
 */
@Repository
class SampleRepository(private val sampleMapper: SampleMapper) {
    fun findAll(): List<Sample> = sampleMapper.findAll()
    fun findSample2(): List<Sample2> = sampleMapper.findSample2()
}
