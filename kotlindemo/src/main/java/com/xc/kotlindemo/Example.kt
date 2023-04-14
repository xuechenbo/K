package com.xc.kotlindemo

interface IUserAction {

    fun attack()

    fun defense()
}

class UserActionImpl : IUserAction {

    override fun attack() {
//        YYLogUtils.w("默认操作-开始执行攻击")
    }

    override fun defense() {
//        YYLogUtils.w("默认操作-开始执行防御")
    }
}

class UserDelegate1(private val action: IUserAction) : IUserAction {
    override fun attack() {
//        YYLogUtils.w("UserDelegate1-需要自己实现攻击")
    }

    override fun defense() {
//        YYLogUtils.w("UserDelegate1-需要自己实现防御")
    }
}

class UserDelegate2(private val action: IUserAction) : IUserAction by action

class UserDelegate3(private val action: IUserAction) : IUserAction by action {

    override fun attack() {
//        YYLogUtils.w("UserDelegate3 - 只重写了攻击")
    }

    override fun defense() {
        TODO("Not yet implemented")
    }
}


class Main {
    val actionImpl = UserActionImpl()

    fun main() {
        UserDelegate1(actionImpl).run {
            attack()
            defense()
        }

        UserDelegate2(actionImpl).run {
            attack()
            defense()
        }

        UserDelegate3(actionImpl).run {
            attack()
            defense()
        }
    }
}


// 约束类  业务
interface IGamePlayer {
    // 打排位赛
    fun rank()

    // 升级
    fun upgrade()
}

// 被委托对象，本场景中的游戏代练                 业务单据
class RealGamePlayer(private val name: String) : IGamePlayer {
    override fun rank() {
        println("$name 开始排位赛")
    }

    override fun upgrade() {
        println("$name 升级了")
    }
}

// 委托对象
class DelegateGamePlayer(private val player: IGamePlayer) : IGamePlayer by player

fun main() {
    val realGamePlayer = RealGamePlayer("张三")
    val delegateGamePlayer = DelegateGamePlayer(realGamePlayer)
    delegateGamePlayer.rank()
    delegateGamePlayer.upgrade()
}

//-------------
//想干的事
interface Kill {
    fun killPeople()
}

//干专业事情的人
class killer(private val name: String) : Kill {
    override fun killPeople() {
        TODO("Not yet implemented")
    }
}

//想干事的人
class Mine(private val k: Kill) : Kill by k

fun mainKill() {
    val killer = killer("张三")
    Mine(killer).killPeople()
}



//委托属性
class DelegateProp{
//    operator fun getValue(thisRef:Any?,prop:KProperty<*>):String{
//        return "getValue ref is $thisRef,prop is ${prop.name}"
//    }
//
//    operator fun setValue(thisRef:Any?,prop:KProperty<*>,value:String){
//        println("setValue ref is $thisRef,prop is ${prop.name}")
//    }
}

class TestProp{
//    var myProp:String by DelegateProp()
}

fun main111(){
//    val testProp = TestProp()
//    testProp.myProp = "1"
//    val a = testProp.myProp
}


// 55000


//139360


























