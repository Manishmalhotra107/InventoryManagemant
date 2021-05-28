package com.poi.core

class ComponentContainer {

    companion object {
        private var sComponent: Any? = null
        fun init(component: Any?) {
            sComponent = component
        }

        fun <T> getComponent(): T {
            checkNotNull(sComponent) { "ComponentContainer wasn't initialized!" }
            return sComponent as T
        }

    }
}