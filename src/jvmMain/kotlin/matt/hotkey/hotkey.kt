package matt.hotkey

import matt.model.obj.DSL


abstract class HotkeyDSL<H : Hotkey> : DSL {
    abstract val SPACE: H

    abstract val LEFT: H
    abstract val RIGHT: H
    abstract val UP: H
    abstract val DOWN: H


    abstract val A: H
    abstract val B: H
    abstract val C: H
    abstract val D: H
    abstract val E: H
    abstract val F: H
    abstract val G: H
    abstract val H: H
    abstract val I: H
    abstract val J: H
    abstract val K: H
    abstract val L: H
    abstract val M: H
    abstract val N: H
    abstract val O: H
    abstract val P: H
    abstract val Q: H
    abstract val R: H
    abstract val S: H
    abstract val T: H
    abstract val U: H
    abstract val V: H
    abstract val W: H
    abstract val X: H
    abstract val Y: H
    abstract val Z: H
}

interface Hotkey