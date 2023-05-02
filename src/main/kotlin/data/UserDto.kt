package data

class UserDto (

    var id: Long? = null,

    var name: String? = null,

    var age: Int? = 0

) {
    override fun toString(): String {
        return super.toString() + "{" +
            "id:" + id +
            ", name:" + name +
            ", age:" + age +
        "}"
    }
}