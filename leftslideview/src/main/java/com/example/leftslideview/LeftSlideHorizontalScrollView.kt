package com.example.leftslideview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_left_slide.view.*


/**
 * 作者 lxy on Time 2022/7/2617:45.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 */
class LeftSlideHorizontalScrollView : HorizontalScrollView {
    /**
     * 按下x
     */
    private var dowX = 0
    /**
     * 抬起x
     */
    private var upX = 0
    /**
     * 右侧按钮布局宽度
     */
    var widthPixels: Int = 0
    /**
     * 触摸事件
     */
    var moveClickListener: MoveClickListener? = null
    /**
     * 按钮展开状态
     */
    var isRollOut = false
    set(value) {
        field = value
        post {
            smoothScrollTo(if (value) widthPixels else 0, 0)
        }
    }


    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {//事件按下
            MotionEvent.ACTION_DOWN -> {
                //记录点击位置
                dowX = ev.x.toInt()
                var size = childCount
                if (size!=0&&getChildAt(0) is LeftSlideView){
                    var leftSlideView= getChildAt(0) as LeftSlideView
                    if (leftSlideView.childCount ==2){
                        //获取右侧布局宽度
                        widthPixels = leftSlideView.getChildAt(1).llLeftSlidButton?.width?:0
                    }
                }

            }
            MotionEvent.ACTION_MOVE -> {//移动
                if (moveClickListener != null && dowX > ev.x) {
                    moveClickListener!!.onMoveClickListener(
                        this@LeftSlideHorizontalScrollView,
                        (dowX - ev.x).toInt()
                    )
                }
            }
            MotionEvent.ACTION_UP -> {//抬起
                upX = ev.x.toInt()//记录抬起位置
                val move = Math.abs(upX - dowX)//记录移动距离绝对值
                if (move > widthPixels / 3) {//判断移动距离是否超过右侧布局1/3
                    if (upX > dowX) { //往右移动 判断移动方向
                        post { smoothScrollTo(0, 0) }//执行关闭动画
                        isRollOut = false
                    } else { //left移动
                        post { smoothScrollTo(widthPixels, widthPixels) }//执行打开动画
                        isRollOut = true
                    }
                } else {//未超过 1/3 放弃事件
                    post { smoothScrollTo(if (isRollOut) widthPixels else 0, 0) }//执行动画回归原位
                }
                if (moveClickListener != null) moveClickListener!!.onUpClickListener(
                    this@LeftSlideHorizontalScrollView,
                    isRollOut
                )
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun fling(velocityX: Int) { //滑动惯性设为0
        super.fling(0)
    }
    /**
     * 触摸回调
     */
    interface MoveClickListener {
        /**
         * 移动中回调
         * @param leftSlideHorizontalScrollView
         * @param moveX x轴移动距离
         */
        fun onMoveClickListener(leftSlideHorizontalScrollView: LeftSlideHorizontalScrollView?, moveX: Int)
        /**
         * 移动结束
         * @param leftSlideHorizontalScrollView
         * @param isRollOut 当前状态  true 展开  false 收起
         */
        fun onUpClickListener(leftSlideHorizontalScrollView: LeftSlideHorizontalScrollView?, isRollOut: Boolean)
    }

    //初始化布局 执行关闭无动画
    fun initScrollow() {
        isRollOut = false
        scrollTo(0,0)
        if (moveClickListener != null) moveClickListener!!.onUpClickListener(
            this@LeftSlideHorizontalScrollView,
            isRollOut
        )
    }
}