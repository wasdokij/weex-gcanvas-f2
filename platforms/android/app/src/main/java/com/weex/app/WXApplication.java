package com.weex.app;

import android.app.Application;

import com.taobao.gcanvas.bridges.weex.GCanvasWeexModule;
import com.taobao.gcanvas.bridges.weex.WXGCanvasWeexComponent;
import com.weex.app.extend.ImageAdapter;
import com.weex.app.extend.WXEventModule;
import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.weex.app.util.AppConfig;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

public class WXApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    WXSDKEngine.addCustomOptions("appName", "WXSample");
    WXSDKEngine.addCustomOptions("appGroup", "WXApp");
    WXSDKEngine.initialize(this,
        new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
    );
    try {
      WXSDKEngine.registerModule("event", WXEventModule.class);

      WXSDKEngine.registerModule("gcanvas", GCanvasWeexModule.class);
      WXSDKEngine.registerComponent("gcanvas", WXGCanvasWeexComponent.class);
    } catch (WXException e) {
      e.printStackTrace();
    }
    AppConfig.init(this);
    WeexPluginContainer.loadAll(this);
  }
}
