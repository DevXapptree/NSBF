package com.conduent.hcesdk.room

interface OnDataBaseQueryListener {
    fun onDataFetched(singleFile: Object)
    fun onCountFetched(count: Int) {

    }
}