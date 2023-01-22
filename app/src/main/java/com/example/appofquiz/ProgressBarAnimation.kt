package com.example.appofquiz

import android.content.Context
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView

class ProgressBarAnimation: Animation {
    private var context: Context? = null
    private var progressBar: ProgressBar? = null
    private var textView: TextView? = null
    private var from: Float? = null
    private var to: Float? = null

    constructor(
        context: Context?,
        progressBar: ProgressBar?,
        textView: TextView?,
        from: Float?,
        to: Float?
    ) : super() {
        this.context = context
        this.progressBar = progressBar
        this.textView = textView
        this.from = from
        this.to = to
    }


    protected override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from!! + (to!! - from!!) * interpolatedTime
        progressBar!!.progress = value.toInt()
        textView!!.text = value.toInt().toString() + " %"
        if (value == to) {
            context!!.startActivity(Intent(context, CategoryActivity::class.java))
        }
    }
   }