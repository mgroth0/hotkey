package matt.hotkey

import matt.collect.mapToSet
import matt.hotkey.ConsumeInstruction.Consume
import matt.hotkey.ConsumeInstruction.DoNotConsume
import matt.lang.function.Dsl
import matt.lang.function.Op
import matt.lang.function.Produce
import matt.lang.setall.setAll

fun buildHotkeys(op: Dsl<HotkeyDsl>): List<Hotkey> {
    val dsl = HotkeyDsl()
    dsl.apply(op)
    return dsl.buildHotkeys()
}

@DslMarker
annotation class HotKeyDslMarker

@HotKeyDslMarker
open class HotkeyDsl {


    val A get() = Key.A
    val B get() = Key.B
    val C get() = Key.C
    val D get() = Key.D
    val E get() = Key.E
    val F get() = Key.F
    val G get() = Key.G
    val H get() = Key.H
    val I get() = Key.I
    val J get() = Key.J
    val K get() = Key.K
    val L get() = Key.L
    val M get() = Key.M
    val N get() = Key.N
    val O get() = Key.O
    val P get() = Key.P
    val Q get() = Key.Q
    val R get() = Key.R
    val S get() = Key.S
    val T get() = Key.T
    val U get() = Key.U
    val V get() = Key.V
    val W get() = Key.W
    val X get() = Key.X
    val Y get() = Key.Y
    val Z get() = Key.Z


    val ESCAPE get() = Key.ESCAPE
    val BACK_SPACE get() = Key.BACK_SPACE
    val RETURN get() = Key.RETURN
    val COMMA get() = Key.COMMA
    val PERIOD get() = Key.PERIOD
    val DELETE get() = Key.DELETE
    val MINUS get() = Key.MINUS
    val EQUALS get() = Key.EQUALS
    val TAB get() = Key.TAB

    val OPEN_BRACKET get() = Key.LEFT_BRACKET
    val CLOSE_BRACKET get() = Key.RIGHT_BRACKET

    val LEFT get() = Key.LEFT_ARROW
    val RIGHT get() = Key.RIGHT_ARROW
    val UP get() = Key.UP_ARROW
    val DOWN get() = Key.DOWN_ARROW


    val DIGIT_1 get() = Key.DIGIT_1
    val DIGIT_2 get() = Key.DIGIT_2
    val DIGIT_3 get() = Key.DIGIT_3
    val DIGIT_4 get() = Key.DIGIT_4
    val DIGIT_5 get() = Key.DIGIT_5
    val DIGIT_6 get() = Key.DIGIT_6
    val DIGIT_7 get() = Key.DIGIT_7
    val DIGIT_8 get() = Key.DIGIT_8
    val DIGIT_9 get() = Key.DIGIT_9
    val DIGIT_0 get() = Key.DIGIT_0

    fun buildHotkeys() = mHotkeys.toList()

    private val mHotkeys = mutableListOf<Hotkey>()


    infix fun Key.op(setOp: () -> Unit) = bare(setOp)
    infix fun Key.meta(h: () -> Unit) = meta op { h() }
    infix fun Key.opt(h: () -> Unit) = opt op { h() }
    infix fun Key.ctrl(h: () -> Unit) = ctrl op { h() }
    infix fun Key.shift(h: () -> Unit) = shift op { h() }
    infix fun Key.bare(h: () -> Unit) = bare op { h() }

    infix fun KeyStroke.meta(h: () -> Unit) = meta op { h() }
    infix fun KeyStroke.opt(h: () -> Unit) = opt op { h() }
    infix fun KeyStroke.ctrl(h: () -> Unit) = ctrl op { h() }
    infix fun KeyStroke.shift(h: () -> Unit) = shift op { h() }

    infix fun KeyStroke.op(setOp: () -> Unit) =
        Hotkey(
            keyStroke = this, handler = {
                setOp()
                ConsumeInstruction.Consume
            }
        ).apply {
            mHotkeys.add(this)
        }

    fun KeyStroke.conditionalConsumption(
        condition: () -> Boolean,
        op: Op
    ) {
        this conditionalOp {
            if (condition()) {
                op()
                Consume
            } else DoNotConsume
        }
    }

    infix fun KeyStroke.conditionalOp(handler: () -> ConsumeInstruction) =
        Hotkey(
            keyStroke = this, handler = handler
        ).apply {
            mHotkeys.add(this)
        }

    val KeyStroke.meta get() = copy(isMeta = true)
    val KeyStroke.opt get() = copy(isOpt = true)
    val KeyStroke.ctrl get() = copy(isCtrl = true)
    val KeyStroke.shift get() = copy(isShift = true)


    infix fun KeyStrokeSet.meta(h: () -> Unit) = meta op { h() }
    infix fun KeyStrokeSet.opt(h: () -> Unit) = opt op { h() }
    infix fun KeyStrokeSet.ctrl(h: () -> Unit) = ctrl op { h() }
    infix fun KeyStrokeSet.shift(h: () -> Unit) = shift op { h() }

    infix fun KeyStrokeSet.op(setOp: () -> Unit) {
        keyStrokes.forEach {
            it op setOp
        }
    }

    val KeyStrokeSet.meta get() = copy(keyStrokes = keyStrokes.mapToSet { it.meta })
    val KeyStrokeSet.opt get() = copy(keyStrokes = keyStrokes.mapToSet { it.opt })
    val KeyStrokeSet.ctrl get() = copy(keyStrokes = keyStrokes.mapToSet { it.ctrl })
    val KeyStrokeSet.shift get() = copy(keyStrokes = keyStrokes.mapToSet { it.shift })

    fun digit(number: Int) =
        when (number) {
            0 -> Key.DIGIT_0
            1 -> Key.DIGIT_1
            2 -> Key.DIGIT_2
            3 -> Key.DIGIT_3
            4 -> Key.DIGIT_4
            5 -> Key.DIGIT_5
            6 -> Key.DIGIT_6
            7 -> Key.DIGIT_7
            8 -> Key.DIGIT_8
            9 -> Key.DIGIT_9
            else -> error("no digit key for $number")
        }

    fun decorateAllOps(decorator: (Produce<ConsumeInstruction>) -> ConsumeInstruction) {
        mHotkeys.setAll(mHotkeys.map { it.copy(handler = { decorator(it.handler) }) })
    }
}


enum class ConsumeInstruction {
    Consume, DoNotConsume
}


/*Use Plural type-aliases like this all over! Such a good idea for concisely representing the preferred collection type for a type!!!*/
typealias Hotkeys = List<Hotkey>
data class Hotkey(
    val keyStroke: KeyStroke,
    val handler: Produce<ConsumeInstruction> /*todo: rename to 'action'?*/
) : KeyStrokeProps by keyStroke

operator fun Key.plus(otherKey: Key) = KeyStrokeSet(this, otherKey)
operator fun KeyStroke.plus(otherKey: KeyStroke) = KeyStrokeSet(this, otherKey)

data class KeyStrokeSet(
    val keyStrokes: Set<KeyStroke>
) {
    constructor(vararg keyStrokes: KeyStroke) : this(keyStrokes.toSet())
    constructor(keyStrokes: List<KeyStroke>) : this(keyStrokes.toSet())
    constructor(vararg keys: Key) : this(keys.map { it.bare })
}

interface KeyStrokeProps {
    val key: Key
    val isMeta: Boolean
    val isOpt: Boolean
    val isCtrl: Boolean
    val isShift: Boolean
}

data class KeyStroke(
    override val key: Key,
    override val isMeta: Boolean = false,
    override val isOpt: Boolean = false,
    override val isCtrl: Boolean = false,
    override val isShift: Boolean = false
) : KeyStrokeProps


enum class Key {

    ESCAPE,

    BACKTICK,

    DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4, DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9, DIGIT_0, MINUS, EQUALS, DELETE, BACK_SPACE,

    TAB, LEFT_BRACKET, RIGHT_BRACKET, BACK_SLASH,

    SEMICOLON, QUOTE, RETURN,

    SHIFT, COMMA, PERIOD, FORWARD_SLASH,

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,

    SPACE,

    UP_ARROW, LEFT_ARROW, DOWN_ARROW, RIGHT_ARROW

    ;


    val bare get() = KeyStroke(this)
    val ctrl get() = KeyStroke(this, isCtrl = true)
    val shift get() = KeyStroke(this, isShift = true)
    val opt get() = KeyStroke(this, isOpt = true)
    val meta get() = KeyStroke(this, isMeta = true)
}
