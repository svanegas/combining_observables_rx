package com.svanegas.combiningobservables

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class Concat {

    fun concat() {
        val source1 = Observable.just("A1", "A2", "A3")
        val source2 = Observable.just("B1", "B2")

        Observable
                .concat(source1, source2)
                .subscribeBy {
                    println(it)
                }
    }

    fun concatWith() {
        val source1 = Observable.just("A1", "A2", "A3")
        val source2 = Observable.just("B1", "B2")

        source1
                .concatWith(source2)
                .subscribeBy {
                    println(it)
                }
    }

    fun concatInfinite() {
        val everySecondObs = Observable.interval(1, TimeUnit.SECONDS)
        val neverCalledObs = Observable.just("Done")

        Observable
                .concat(everySecondObs, neverCalledObs)
                .subscribeBy {
                    println(it)
                }
    }
}