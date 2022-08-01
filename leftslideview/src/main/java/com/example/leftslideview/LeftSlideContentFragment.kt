package com.example.leftslideview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 * 作者 lxy on Time 2022/7/2915:36.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
class LeftSlideContentFragment : FrameLayout {
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val wMode = MeasureSpec.getMode(widthMeasureSpec) // 水平方向的测量模式
//        val wSize = MeasureSpec.getSize(widthMeasureSpec) // 水平方向的测量大小
//        val hMode = MeasureSpec.getMode(heightMeasureSpec) // 竖直方向的测量模式
//        val hSize = MeasureSpec.getSize(heightMeasureSpec) // 竖直方向的测量大小
//        // 测量所有子View
//
//        var childView = getChildAt(0)
//        val whidthSize = MeasureSpec.makeMeasureSpec(wSize, MeasureSpec.EXACTLY)
//
//        childView?.measure(whidthSize,heightMeasureSpec)
//
//
//        setMeasuredDimension(widthMeasureSpec, getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//    }
//
//
//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        for (i in 0..childCount - 1) {
//            var v = getChildAt(i)
//            v?.layout(0, 0, measuredWidth, getChildAt(0)?.measuredHeight ?: 0)
//        }
//
//    }

}