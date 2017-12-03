package com.reactnative_firstdemo;

import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.facebook.react.ReactActivity;
import com.facebook.react.uimanager.ThemedReactContext;

public class MainActivity extends ReactActivity {

    private final static String TAG = "WalkView";
    private WalkNavigateHelper mNaviHelper;
    ThemedReactContext reactContext;
    WalkNaviLaunchParam walkParam;
    private LatLng startPt,endPt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPt = new LatLng(40.047416,116.312143);
        endPt = new LatLng(40.048424, 116.313513);
        walkParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
        mNaviHelper = WalkNavigateHelper.getInstance();
        startWalkNavi();
    }

    private void startWalkNavi() {
        Log.e(TAG, "startBikeNavi");
        try {
            mNaviHelper.initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    Log.e(TAG, "engineInitSuccess");
                    routePlanWithWalkParam();
                }

                @Override
                public void engineInitFail() {
                    Log.e(TAG, "engineInitFail");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    private void routePlanWithWalkParam() {
        mNaviHelper.routePlanWithParams(walkParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.e(TAG, "onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
//                boolean startResult = mNaviHelper.startWalkNavi(this);
//                Log.e(TAG, "startWalkNavi result : " + startResult);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
                Log.e(TAG, "onRoutePlanFail");
            }

        });
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "ReactNative_FirstDemo";
    }
}
