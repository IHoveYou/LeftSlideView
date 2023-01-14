package com.example.leftslideview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * 作者 lxy on Time 2022/7/2914:16.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
class LeftSlideView : ViewGroup {
    var wSize = 0// 水平方向的测量大小
    var tWidth = 0//实际需要宽度
    var tHeight = 0//实际需要高度
    fun getContentView()= getChildAt(0) //获取内容区域
    fun getBtnView() = getChildAt(1) //获取按钮区域
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        tWidth = 0
        val wMode = MeasureSpec.getMode(widthMeasureSpec) // 水平方向的测量模式
        wSize = MeasureSpec.getSize(widthMeasureSpec) // 水平方向的测量大小
        val hMode = MeasureSpec.getMode(heightMeasureSpec) // 竖直方向的测量模式
        var hSize = MeasureSpec.getSize(heightMeasureSpec) // 竖直方向的测量大小

        var contentView = getChildAt(0) //获取内容区域控件
        tHeight =contentView.measuredHeight  //实际高度以左侧布局高度为准
        tWidth = getDefaultSize(wSize,wMode) //获取控件实际可用最大位置
        contentView.measure(tWidth, tHeight)  //设置内容区域布局属性
        contentView.layoutParams.width = tWidth //设置内容区域 layoutParams
        var btnView = getChildAt(1) //获取按钮区域控件
        tWidth += btnView.measuredWidth ?: 0 //计算内容区域+按钮区域 = 实际宽度
        btnView.measure(btnView.measuredWidth,tHeight) //设置按钮布局属性
        measureChildren(widthMeasureSpec, heightMeasureSpec)//重新测量子View
        setMeasuredDimension(tWidth, tHeight)//设置整体布局属性
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var contentView = getContentView()//获取内容区域
        var btnView = getBtnView() //获取按钮区域

        contentView?.layout(0, 0, wSize, tHeight)//设置内容区域显示位置
        btnView?.layout(wSize, 0, tWidth, tHeight)//设置按钮区域显示位置

    }

}