package com.east.evil.plug.commons;

import android.app.Application;

import com.east.evil.huxlyn.commons.EastViewModel;
import com.east.evil.plug.entity.ProxyData;

import org.jetbrains.annotations.NotNull;

public class ProxyViewModel extends EastViewModel<ProxyData> {


    public ProxyViewModel(@NotNull Application application) {
        super(application);
    }

    @NotNull
    @Override
    public ProxyData initData() {
        return new ProxyData();
    }
}
