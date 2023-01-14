package com.example.leftslideview

/**
 * 作者 lxy on Time 2022/7/2916:18.
 * 上有天，下有地，中间站着你自己，做一天人，尽一天人事儿
 * 人生是一个永不停息的工厂，那里没有懒人的位置。工作吧！创造吧！
 * 多视图联动
 */
class LeftSlideUtils {
    var view:LeftSlideHorizontalScrollView? = null
    var listener = object : LeftSlideHorizontalScrollView.MoveClickListener {
        override fun onMoveClickListener(
            leftSlideHorizontalScrollView: LeftSlideHorizontalScrollView?,
            moveX: Int
        ) {
        }

        override fun onUpClickListener(
            leftSlideHorizontalScrollView: LeftSlideHorizontalScrollView?,
            isRollOut: Boolean
        ) {
            if (isRollOut && view != leftSlideHorizontalScrollView){
                view?.isRollOut=false
                view = leftSlideHorizontalScrollView
            }
        }

    }
    fun addView(view:LeftSlideHorizontalScrollView?){
        view?.isRollOut=false
        view?.moveClickListener = listener
    }




}