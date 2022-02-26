package com.east.evil.plug.commons

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

abstract class EastEvilActivity : BaseEastEvilActivity(), IEastEvilPlugin {

    companion object{
        private const val TAG = "EastEvilActivity==>";
    }
    private var mFrom: Int = IEastEvilPlugin.FROM_INTERNAL

    private var mProxyActivity: BaseEastEvilActivity? = null

    override fun attach(activity: BaseEastEvilActivity) {
        this.mProxyActivity = activity;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun plugOnCreate(saveInstanceState: Bundle) {
        if (saveInstanceState != null) {
            mFrom = saveInstanceState.getInt("FROM")
        }
        Log.d(TAG,"plugOnCreate==>FROM=>${mFrom}");
        if (mFrom == IEastEvilPlugin.FROM_INTERNAL) {
            mProxyActivity = this
            onCreate(saveInstanceState);
        }
    }

    override fun setContentView(layoutResID: Int) {
        Log.d(TAG,"setContentView==>FROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FORM_EXTERNAL){
            mProxyActivity?.setContentView(layoutResID);
        }else{
            super.setContentView(layoutResID)
        }
    }

    override fun onRestart() {
        Log.d(TAG,"onRestart==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onRestart()
        }else{
            plugOnStart();
        }
    }

    override fun onStop() {
        Log.d(TAG,"onStop==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onStop();
        }else{
            plugOnStop();
        }
    }

    override fun onResume() {
        Log.d(TAG,"onResume==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onResume();
        }else{
            plugOnResume();
        }
    }

    override fun onPause() {
        Log.d(TAG,"onPause==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onPause();
        }else{
            plugOnPause();
        }
    }

    override fun onBackPressed() {
        Log.d(TAG,"onBackPressed==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onBackPressed()
        }else{
            plugOnBackPressed();
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG,"onTouchEvent==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            return super.onTouchEvent(event)
        }else{
            return plugOnTouchEvent(event);
        }
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onDestroy()
        }else{
            plugOnDestroy();
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG,"onSaveInstanceState==>MFROM=>${mFrom}");
        if(mFrom == IEastEvilPlugin.FROM_INTERNAL){
            super.onSaveInstanceState(outState);
        }else{
            plugOnSaveInstanceState(outState);
        }
    }

    override fun plugOnStart() {
        Log.d(TAG,"plugOnStart");
    }

    override fun plugOnResume() {
        Log.d(TAG,"plugOnResume");
    }

    override fun plugOnPause() {
        Log.d(TAG,"plugOnPause");
    }

    override fun plugOnStop() {
        Log.d(TAG,"plugOnStop");
    }

    override fun plugOnDestroy() {
        Log.d(TAG,"plugOnDestroy");
    }

    override fun plugOnSaveInstanceState(outState: Bundle?) {
        Log.d(TAG,"plugOnSaveInstanceState");
    }

    override fun plugOnTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG,"plugOnTouchEvent");
        return super.onTouchEvent(event);
    }

    override fun plugOnBackPressed() {
        Log.d(TAG,"plugOnBackPressed");
    }

}
