package sample03_designpattern.demo04_etc_delegate_pattern.class_delegate

/*
직접 구현하는게 아닌, Speaker 구현체를 인자로 받고 이를 통해 구현을 해주고 있다
이런 방식의 delegate 는 많은 코드의 보일러플레이트를 유발
 */
class Bella(private val presentation: Speaker): Speaker {

    override val subject = presentation.subject
    override val script = presentation.script

    override fun say() {
        presentation.say()
    }

}


/*
위의 방식의 delegate 는 많은 코드의 보일러플레이트를 유발
따라서 이를 language level 에서 지원하는것이 바로 by 키워드
 */
class BellaBy(presentation: Speaker): Speaker by presentation