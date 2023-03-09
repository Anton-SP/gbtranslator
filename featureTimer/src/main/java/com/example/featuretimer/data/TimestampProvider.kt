package com.example.featuretimer.data

interface TimestampProvider {
    fun getMilliseconds(): Long
}