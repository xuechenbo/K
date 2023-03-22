package com.xc.common_base.u.network

class BaseResp<T> {
    var errorCode = -1
    var errorMsg: String? = null
    var data: T? = null
        private set
}