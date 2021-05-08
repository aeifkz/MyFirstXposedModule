package com.example.myfirstxposedmodule;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MyModule implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);
        XposedBridge.log("I am author aeifkz???");

        if ("com.example.myfirstapp".equals(loadPackageParam.packageName) ) {
            XposedHelpers.findAndHookMethod(
                    "com.example.myfirstapp.MainActivity",
                    loadPackageParam.classLoader,
                    "checkPassword",
                    String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("I get Object:" + param.thisObject.getClass().getName());

                            //param.getResult() 能夠取得 function 的回傳值
                            XposedBridge.log("I get Result:" + param.getResult());

                            //竄改回傳值
                            param.setResult(true);
                        }
                    }
            );
        }



    }



}
