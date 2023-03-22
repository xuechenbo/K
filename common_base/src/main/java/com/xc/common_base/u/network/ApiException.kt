package com.xc.common_base.u.network

class ApiException(var code: Int, override var message: String) : RuntimeException()