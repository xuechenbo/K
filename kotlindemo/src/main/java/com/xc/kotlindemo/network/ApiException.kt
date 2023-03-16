package com.xc.kotlindemo.network

class ApiException(var code: Int, override var message: String) : RuntimeException()