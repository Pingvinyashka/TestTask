package ru.spb.niias.mrmplan

import com.agoda.kakao.screen.Screen

abstract class KScreen<out T : KScreen<T>> : Screen<T>() {

    abstract val layoutId: Int?
    abstract val viewClass: Class<*>?
}