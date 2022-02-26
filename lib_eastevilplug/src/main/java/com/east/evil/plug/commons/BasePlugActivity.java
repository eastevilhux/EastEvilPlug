package com.east.evil.plug.commons;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.huxlyn.entity.VMData;

import org.jetbrains.annotations.Nullable;

abstract public class BasePlugActivity<V extends ViewDataBinding, D extends EastViewModel<? extends VMData>>
        extends BaseEastEvilActivity<V,D> implements IEastEvilPlugin{

    private static final String TAG = "EastEvilActivity==>";

    private int mFrom = IEastEvilPlugin.FROM_INTERNAL;

    private BaseEastEvilActivity mProxyActivity;

    @Override
    public <V extends ViewDataBinding, D extends EastViewModel<? extends VMData>>
        void attach(BaseEastEvilActivity<V, D> activity) {
        this.mProxyActivity = activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void plugOnCreate(Bundle saveInstanceState) {
        if(saveInstanceState != null){
            mFrom = saveInstanceState.getInt("FROM");
        }
        Log.d(TAG,"plugOnCreate==>FROM=>${mFrom}");
        if (mFrom == IEastEvilPlugin.FROM_INTERNAL) {
            mProxyActivity = this;
            onCreate(saveInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        Log.d(TAG,"setContentView==>FROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FORM_EXTERNAL){
            mProxyActivity.setContentView(layoutResID);
        }else{
            super.setContentView(layoutResID);
        }
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"onRestart==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onRestart();
        }else{
            plugOnStart();
        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onStop();
        }else{
            plugOnStop();
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"onResume==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onResume();
        }else{
            plugOnResume();
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"onPause==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onPause();
        }else{
            plugOnPause();
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onBackPressed();
        }else{
            plugOnBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"onTouchEvent==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            return super.onTouchEvent(event);
        }else{
            return plugOnTouchEvent(event);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onDestroy();
        }else{
            plugOnDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG,"onSaveInstanceState==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onSaveInstanceState(outState);
        }else{
            plugOnSaveInstanceState(outState);
        }
    }

    @Override
    public void plugOnStart() {
        Log.d(TAG,"plugOnStart");
    }

    @Override
    public void plugOnResume() {
        Log.d(TAG,"plugOnResume");
    }

    @Override
    public void plugOnPause() {
        Log.d(TAG,"plugOnPause");
    }

    @Override
    public void plugOnStop() {
        Log.d(TAG,"plugOnStop");
    }

    @Override
    public void plugOnDestroy() {
        Log.d(TAG,"plugOnDestroy");
    }

    @Override
    public void plugOnSaveInstanceState(Bundle outState) {
        Log.d(TAG,"plugOnSaveInstanceState");
    }

    @Override
    public boolean plugOnTouchEvent(MotionEvent event) {
        Log.d(TAG,"plugOnTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void plugOnBackPressed() {
        Log.d(TAG,"plugOnBackPressed");
    }
}
