package matt.hotkey.test


import matt.hotkey.Hotkey
import matt.hotkey.HotkeyDSL
import matt.test.assertions.JupiterTestAssertions.assertRunsInOneMinute
import kotlin.test.Test

class HotkeyTests() {
    @Test
    fun constructClasses() = assertRunsInOneMinute {
        object : HotkeyDSL<Hotkey>() {
            override val SPACE: Hotkey
                get() = TODO("Not yet implemented")
            override val LEFT: Hotkey
                get() = TODO("Not yet implemented")
            override val RIGHT: Hotkey
                get() = TODO("Not yet implemented")
            override val UP: Hotkey
                get() = TODO("Not yet implemented")
            override val DOWN: Hotkey
                get() = TODO("Not yet implemented")
            override val A: Hotkey
                get() = TODO("Not yet implemented")
            override val B: Hotkey
                get() = TODO("Not yet implemented")
            override val C: Hotkey
                get() = TODO("Not yet implemented")
            override val D: Hotkey
                get() = TODO("Not yet implemented")
            override val E: Hotkey
                get() = TODO("Not yet implemented")
            override val F: Hotkey
                get() = TODO("Not yet implemented")
            override val G: Hotkey
                get() = TODO("Not yet implemented")
            override val H: Hotkey
                get() = TODO("Not yet implemented")
            override val I: Hotkey
                get() = TODO("Not yet implemented")
            override val J: Hotkey
                get() = TODO("Not yet implemented")
            override val K: Hotkey
                get() = TODO("Not yet implemented")
            override val L: Hotkey
                get() = TODO("Not yet implemented")
            override val M: Hotkey
                get() = TODO("Not yet implemented")
            override val N: Hotkey
                get() = TODO("Not yet implemented")
            override val O: Hotkey
                get() = TODO("Not yet implemented")
            override val P: Hotkey
                get() = TODO("Not yet implemented")
            override val Q: Hotkey
                get() = TODO("Not yet implemented")
            override val R: Hotkey
                get() = TODO("Not yet implemented")
            override val S: Hotkey
                get() = TODO("Not yet implemented")
            override val T: Hotkey
                get() = TODO("Not yet implemented")
            override val U: Hotkey
                get() = TODO("Not yet implemented")
            override val V: Hotkey
                get() = TODO("Not yet implemented")
            override val W: Hotkey
                get() = TODO("Not yet implemented")
            override val X: Hotkey
                get() = TODO("Not yet implemented")
            override val Y: Hotkey
                get() = TODO("Not yet implemented")
            override val Z: Hotkey
                get() = TODO("Not yet implemented")

        }
    }
}