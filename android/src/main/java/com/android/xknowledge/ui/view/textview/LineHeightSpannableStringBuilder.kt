package com.android.xknowledge.ui.view.textview

import android.graphics.Paint.FontMetricsInt
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.LineHeightSpan

class LineHeightSpannableStringBuilder : SpannableStringBuilder() {
    private var mVVLineHeightSpan: VVLineHeightSpan? = null
    fun setContent(sequence: CharSequence, lineHeight: Float) {
        clear()
        clearSpans()
        if (mVVLineHeightSpan == null) {
            mVVLineHeightSpan = VVLineHeightSpan(lineHeight)
        } else {
            mVVLineHeightSpan!!.setHeight(lineHeight)
        }
        append(sequence)
        setSpan(mVVLineHeightSpan, 0, sequence.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    class VVLineHeightSpan internal constructor(height: Float) : LineHeightSpan {
        var height: Int
            private set

        fun setHeight(height: Float) {
            this.height = Math.ceil(height.toDouble()).toInt()
        }

        override fun chooseHeight(
            text: CharSequence,
            start: Int,
            end: Int,
            spanstartv: Int,
            v: Int,
            fm: FontMetricsInt
        ) { // This is more complicated that I wanted it to be. You can find a good explanation of what the
// FontMetrics mean here: http://stackoverflow.com/questions/27631736.
// The general solution is that if there's not enough height to show the full line height, we
// will prioritize in this order: descent, ascent, bottom, top
            if (fm.descent > height) { // Show as much descent as possible
                fm.descent = Math.min(height, fm.descent)
                fm.bottom = fm.descent
                fm.ascent = 0
                fm.top = fm.ascent
            } else if (-fm.ascent + fm.descent > height) { // Show all descent, and as much ascent as possible
                fm.bottom = fm.descent
                fm.ascent = -height + fm.descent
                fm.top = fm.ascent
            } else if (-fm.ascent + fm.bottom > height) { // Show all ascent, descent, as much bottom as possible
                fm.top = fm.ascent
                fm.bottom = fm.ascent + height
            } else if (-fm.top + fm.bottom > height) { // Show all ascent, descent, bottom, as much top as possible
                fm.top = fm.bottom - height
            } else { // Show proportionally additional ascent / top & descent / bottom
                val additional = height - (-fm.top + fm.bottom)
                // Round up for the negative values and down for the positive values  (arbritary choice)
// So that bottom - top equals additional even if it's an odd number.
                fm.top -= Math.ceil(additional / 2.0f.toDouble()).toInt()
                fm.bottom += Math.floor(additional / 2.0f.toDouble()).toInt()
                fm.ascent = fm.top
                fm.descent = fm.bottom
            }
        }

        init {
            this.height = Math.ceil(height.toDouble()).toInt()
        }
    }
}