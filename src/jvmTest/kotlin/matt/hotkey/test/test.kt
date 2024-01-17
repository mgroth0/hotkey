package matt.hotkey.test


import matt.hotkey.HotkeyDsl
import matt.test.Tests
import kotlin.test.Test

class HotkeyTests : Tests() {
    @Test
    fun constructClasses() {
        HotkeyDsl()
    }
}