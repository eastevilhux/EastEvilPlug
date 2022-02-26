package com.east.evil.plug.commons

import android.os.Bundle
import android.view.MotionEvent


/**
 * 定义插件规范
 */
interface IEastEvilPlugin{


    /**
     *   害，kotlin 为生么不能继承重新方法和接口实现方法一样呢。。。。
     *   这。。。。
     *   定义接口的接口方法和实现类方法不能一样，不然会编译报错！！！！！！！
     *  出此下策加上个前缀
     */

    companion object{
        const val FROM_INTERNAL = 0
        const val FORM_EXTERNAL = 1;
    }

    fun attach(activity : BaseEastEvilActivity);

    fun plugOnCreate(saveInstanceState : Bundle);

    fun plugOnStart()

    fun plugOnResume()

    fun plugOnPause()

    fun plugOnStop()

    fun plugOnDestroy()

    fun plugOnSaveInstanceState(outState: Bundle?)

    fun plugOnTouchEvent(event: MotionEvent?): Boolean

    fun plugOnBackPressed()
}
