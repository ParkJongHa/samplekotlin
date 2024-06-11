package sample03_designpattern.demo04_etc_delegate_pattern.property_delegate

import kotlin.reflect.KProperty

class CustomString {

    private var value = ""

    /*
    Property delegation operators
    provideDelegate, getValue and setValue operator functions are described in Delegated properties.
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue thisRef[ $thisRef ]") // null
        println("getValue property[ $property ]") // var customString: kotlin.String
        return "[CustomString] $value"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue thisRef[ $thisRef ]") // null
        println("setValue property[ $property ]") // var customString: kotlin.String
        println("setValue value[ $value ]") // Bye, world.
        this.value = value
    }

}

fun main() {
//*
// CustomString 클래스에서
//    getValue 와 setValue 를 operator fun 으로 정의 해주고 있고,
//    이 함수들이 delegate 로 작동된다.

    var customString by CustomString() // property delegate 지원
    customString = "Bye, world." // setValue 호출
// */
/*
    var customString: CustomString = CustomString()
    customString = CustomString()
//    customString = "Bye, world" // type mismatch / Required CustomString / Found String
// */
    println( customString ) // getValue 호출
}

