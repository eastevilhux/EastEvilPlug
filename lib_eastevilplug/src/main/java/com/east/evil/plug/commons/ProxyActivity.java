package com.east.evil.plug.commons;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ViewDataBinding;

import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.huxlyn.entity.VMData;
import com.east.evil.plug.entity.PluginApk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProxyActivity<V extends ViewDataBinding, D extends EastViewModel<? extends VMData>>
        extends BaseEastEvilActivity<V,D>{

    private static final String TAG = "ProxyActivity==>";

    private String mClassName = null;
    private PluginApk mPluginApk = null;
    private IEastEvilPlugin iEastEvilPlugin = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.Companion.getInstance().getPluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity(){
        if(mPluginApk == null){
            return;
        }
        try {
            Class<?> clazz = mPluginApk.getMClassLoader().loadClass(mClassName);
            if(clazz != null){
                Object obj = clazz.newInstance();
                if(obj instanceof IEastEvilPlugin){
                    ((IEastEvilPlugin) obj).attach(this);
                    Bundle bundle = new Bundle();
                    bundle.putInt("FROM",IEastEvilPlugin.FORM_EXTERNAL);
                    ((IEastEvilPlugin) obj).plugOnCreate(bundle);
                }
            }else{
                throw new IllegalArgumentException("load plugapk class error");
            }
        }catch (Exception e){
            Log.e(TAG,"pluginapk not fond");
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk != null ? mPluginApk.getMResource() : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.getMAssetManager() : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.getMClassLoader() : super.getClassLoader();
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @NotNull
    @Override
    public Class<D> getVMClass() {
        return null;
    }
}
