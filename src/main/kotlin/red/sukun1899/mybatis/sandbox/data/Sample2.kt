package red.sukun1899.mybatis.sandbox.data

data class Sample2(val id: String, val name: String, var childNames: List<String>) {
    constructor(id: String, name: String) : this(id, name, mutableListOf())
}
