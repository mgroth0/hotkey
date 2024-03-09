package matt.hotkey.js

import matt.lang.anno.SeeURL
import org.w3c.dom.events.KeyboardEvent

@SeeURL("https://stackoverflow.com/questions/24386354/execute-js-code-after-pressing-the-spacebar")
val KeyboardEvent.isSpace get() = key == " " || code == "Space" || keyCode == 32

