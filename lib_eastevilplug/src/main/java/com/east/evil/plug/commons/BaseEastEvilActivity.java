package com.east.evil.plug.commons;

import androidx.databinding.ViewDataBinding;

import com.east.evil.huxlyn.commons.BaseActivity;
import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.huxlyn.entity.VMData;

abstract public class BaseEastEvilActivity<V extends ViewDataBinding, D extends EastViewModel<? extends VMData>>
        extends BaseActivity<V, D> {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
