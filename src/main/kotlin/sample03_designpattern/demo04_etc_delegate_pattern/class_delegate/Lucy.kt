package sample03_designpattern.demo04_etc_delegate_pattern.class_delegate

class Lucy: Speaker {

    override val subject: String
        get() {

            return "Jetpack Compose 작동원리"
        }

    override val script: String
        get() {
            return "안녕하세요 이것은 $subject 발표 입니다."
        }

    override fun say() {
        println("[$subject] $script")
    }

}