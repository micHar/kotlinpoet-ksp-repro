package com.example.repro

import com.example.annotation.LeAnnotation

@LeAnnotation
class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}