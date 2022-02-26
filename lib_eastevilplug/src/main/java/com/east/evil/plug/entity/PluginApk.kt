package com.east.evil.plug.entity

import android.content.pm.PackageInfo
import android.content.res.AssetManager
import android.content.res.Resources
import dalvik.system.DexClassLoader


class PluginApk {
    //插件APK的实体对象
    var mClassLoader: DexClassLoader? = null
    var mResource: Resources? = null
    var mAssetManager: AssetManager? = null
    var mPackageInfo: PackageInfo? = null

    constructor(mClassLoader :DexClassLoader, mResource : Resources,mPackageInfo : PackageInfo, mAssetManager : AssetManager){
        this.mClassLoader = mClassLoader;
        this.mResource = mResource;
        this.mPackageInfo = mPackageInfo;
        this.mAssetManager = mAssetManager;
    }
}
