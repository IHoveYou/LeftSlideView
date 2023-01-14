# 左滑删除组件 ZTW_Engines_LeftSlide
> 导入依赖

```kotlin
Implementation 'com.github.IHoveYou:LeftSlideView:v1.2.0'

```

> 使用方式
LeftSlideHorizontalScrollView 滑动能力布局
LeftSlideView 左右分区布局 分区方式 包裹下第一个view为左侧内容 第二个view为展开后布局（ps:操作按钮布局）

```html
<com.ztocwst.scm.engines.leftslide.LeftSlideHorizontalScrollView
        android:scrollbars="none"
        android:id="@+id/leftSlideHorizontalScrollView"
        android:layout_width="match_parent"
        android:fillViewport="true"
        android:layout_height="40dp"
        >
        <com.ztocwst.scm.engines.leftslide.LeftSlideView
            android:id="@+id/leftSlideView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            >
            <!-- 左侧内容布局可以是任意View 或ViewGroup !-->
            <LinearLayout
                android:paddingHorizontal="15dp"
                android:orientation="vertical"
                android:layout_width="300dp"
                android:layout_height="match_parent">
                <TextView
                    android:gravity="center_vertical"
                    android:text="23333"
                    android:id="@+id/textView"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"/>
            </LinearLayout>
            <!-- 左侧内容布局 可以是任意View 或ViewGroup !-->
            <LinearLayout
                android:orientation="vertical"
                android:background="@color/teal_700"
                android:layout_width="50dp"
                android:layout_height="match_parent"/>
        </com.ztocwst.scm.engines.leftslide.LeftSlideView>

    </com.ztocwst.scm.engines.leftslide.LeftSlideHorizontalScrollView>
```

> 列表联合使用 提供了 LeftSlideProvider 控制类
使用 LeftSlideProvider.addLinkedView 添加需要联动的类
联动效果为当前只有一个View被展开，展开新的view之后老View自动折叠
将需要联动的VIew 添加入控制器，可以实现多View联动，多adapter联动等

```kotlin
//使用示例
class ListAdapter(val list: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder1>() {
    val  leftSlideUtils=LeftSlideProvider() //联动View公用一个控制器
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder1 {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(context), parent, false)
        //将需要联动的VIew 添加入控制器，可以实现多View联动，多adapter联动等
        leftSlideUtils.addLinkedView(binding.leftSlideHorizontalScrollView)
        return MainAdapter.ViewHolder1(binding.root)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder1, position: Int) {
        val binding = ItemMainBinding.bind(holder.itemView)
        binding.textView.text = list[position]
    }

    override fun onViewDetachedFromWindow(holder: MainAdapter.ViewHolder1) {
        //回收 回收复用布局时，建议将其手动折叠，防止复用布局后出现展开状态
        val binding = ItemMainBinding.bind(holder.itemView)
        
        //有动画关闭 二选一
        binding.leftSlideHorizontalScrollView.isRollOut = false
        //无动画关闭 二选一
        binding.leftSlideHorizontalScrollViewinitScrollow.initScrollow()
        
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = list.size

}
```

> **LeftSlideHorizontalScrollView api**

```kotlin
LeftSlideHorizontalScrollView
    /**
     * 触摸事件
     */
    var moveClickListener: MoveClickListener? = null
    /**
     * 按钮展开状态 更改值后自动变更布局状态
     */
    var isRollOut = false
```

> **MoveClickListener Api**

```kotlin
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
```
