package com.east.evil.plug.commons;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.databinding.ViewDataBinding;

import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.huxlyn.entity.VMData;

public interface IEastEvilPlugin {
    public static final int FROM_INTERNAL = 0;
    public static final int FORM_EXTERNAL = 1;

    public <V extends ViewDataBinding, D extends EastViewModel<? extends VMData>> void attach(BaseEastEvilActivity<V, D> activity);

    public void plugOnCreate(Bundle saveInstanceState);

    public void plugOnStart();

    public void plugOnResume();

    public void plugOnPause();

    public void plugOnStop();

    public void plugOnDestroy();

    public void plugOnSaveInstanceState(Bundle outState);

    public boolean plugOnTouchEvent(MotionEvent event);

    public void plugOnBackPressed();

}
