package com.east.evil.plug.commons

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import com.east.evil.plug.entity.PluginApk
import dalvik.system.DexClassLoader
import java.io.File

class PluginManager private constructor(){
    private var mPluginApk : PluginApk? = null;
    companion object{
        val instance: PluginManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { PluginManager() }
    }

    /**
     * 加载apk
     * create by Administrator at 2021/9/2 13:19
     * @author Administrator
     * @param apkPath
     *      apk路径
     * @return
     *      void
     */
    fun loadApk(context : Context,apkPath : String){
        if (TextUtils.isEmpty(apkPath)) {
            return
        }
        val packageInfo: PackageInfo? = context?.getPackageManager()?.getPackageArchiveInfo(apkPath,
            PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES
        )
        packageInfo?.let {
            //创建dexClassLoader
            val classLoader: DexClassLoader? = createDexClassLoader(context,apkPath);
            classLoader?.let { dex->
                //创建Resource
                val am: AssetManager? = createAssetManager(apkPath)
                val resources: Resources? = am?.let { it1 -> createResource(context,it1) }
                mPluginApk = resources?.let { it1 ->
                    PluginApk(classLoader,
                        it1, packageInfo, am!!)
                }
            }
        }
    }

    fun getPluginApk(): PluginApk? {
        return mPluginApk
    }


    //获取插件dex的DexClassLoader，用来加载插件dex的任何一个类
    private fun createDexClassLoader(context : Context,apkPath: String): DexClassLoader? {
        val file: File? = context?.getDir("dex", Context.MODE_PRIVATE)
        return DexClassLoader(apkPath, file?.absolutePath, null, context?.getClassLoader())
    }

    private fun createAssetManager(apkPath: String): AssetManager? {
        try {
            val am = AssetManager::class.java.newInstance()
            val method =
                AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            method.invoke(am, apkPath)
            return am
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun createResource(context : Context,am: AssetManager): Resources? {
        val res: Resources? = context?.getResources()
        return Resources(am, res?.displayMetrics, res?.configuration)
    }
}
