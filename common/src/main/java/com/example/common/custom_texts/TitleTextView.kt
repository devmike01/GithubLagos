package com.example.common.custom_texts

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.common.R
import java.util.jar.Attributes

class TitleTextView @JvmOverloads constructor(context: Context, attributesSet: AttributeSet? = null,
                                                  defStyle: Int =0)
    : AppCompatTextView(context, attributesSet, defStyle)  {

        private var titleText : String? = null

        init {
            init(attributesSet)
        }

    private fun init(attributesSet: AttributeSet? ){
        context.theme.obtainStyledAttributes(
            attributesSet,
            R.styleable.TitleTextView,
            0, 0).apply {
            titleText = getString(R.styleable.TitleTextView_titleText)
            try {
                text = titleText
            } finally {
                recycle()
            }
        }

    }

    fun setTitleText(text: CharSequence?) {
        text?.run {
            setText(this[0].uppercase().plus(subSequence(1, length)))
        }
    }
}