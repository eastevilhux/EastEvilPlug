package com.east.evil.plug.view

import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import com.east.evil.plug.commons.BaseEastEvilActivity
import com.east.evil.plug.commons.IEastEvilPlugin
import com.east.evil.plug.commons.PluginManager
import com.east.evil.plug.entity.PluginApk

//代理Activity，管理插件Activity的生命周期
class ProxyActivity : BaseEastEvilActivity() {
    private var mClassName : String? = null;
    private var mPluginApk : PluginApk? = null;
    private var iEastEvilPlugin : IEastEvilPlugin? = null;


    companion object{
        private const val TAG = "ProxyActivity==>"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mClassName = intent.getStringExtra("className")
        mPluginApk = PluginManager.instance.getPluginApk();
        launchPluginActivity();
    }

    private fun launchPluginActivity(){
        if (mPluginApk == null) {
            return
        }
        try {
            val clazz: Class<*>? = mPluginApk!!.mClassLoader?.loadClass(mClassName)
            clazz?.let {
                val objetc = clazz.newInstance()
                if(objetc is IEastEvilPlugin){
                    objetc.attach(this)
                    val bundle = Bundle();
                    bundle.putInt("FROM", IEastEvilPlugin.FORM_EXTERNAL)
                    objetc.plugOnCreate(bundle);
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun getResources(): Resources? {
        return if (mPluginApk != null) mPluginApk!!.mResource else super.getResources()
    }

    override fun getAssets(): AssetManager? {
        return if (mPluginApk != null) mPluginApk!!.mAssetManager else super.getAssets()
    }

    override fun getClassLoader(): ClassLoader? {
        return if (mPluginApk != null) mPluginApk!!.mClassLoader else super.getClassLoader()
    }
}
