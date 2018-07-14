package com.svanegas.combiningobservables

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_merge.*


class MergeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MergeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_merge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        activity?.title = getString(R.string.merge)
    }

    private fun setupListeners() {
        val increaseObs = RxView
                .clicks(increase_button)
                .map { 1 }

        val decreaseObs = RxView
                .clicks(decrease_button)
                .map { -1 }

        Observable
                .merge(increaseObs, decreaseObs)
                .scan(0) { inc, dec ->
                    inc + dec
                }
                .subscribeBy {
                    counter_label.text = it.toString()
                }
    }
}
