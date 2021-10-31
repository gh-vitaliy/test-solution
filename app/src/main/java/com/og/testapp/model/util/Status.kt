package com.og.testapp.model.util

class LoadingStatus(val status: Status) {
    companion object {
        val LOADING = LoadingStatus(Status.LOADING)
        val ERROR = LoadingStatus(Status.ERROR)
        val LOADED = LoadingStatus(Status.LOADED)
    }

    enum class Status {
        LOADING,
        ERROR,
        LOADED
    }
}
