package com.east.evil.plug.commons;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.huxlyn.entity.VMData;

import org.jetbrains.annotations.Nullable;

abstract public class PlugActivity<V extends ViewDataBinding, D extends EastViewModel<? extends VMData>>
        extends BasePlugActivity<V,D> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void plugOnCreate(Bundle saveInstanceState) {
        super.plugOnCreate(saveInstanceState);
        setContentView(dataBinding.getRoot());
    }
}
