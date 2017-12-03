package com.reactnative_firstdemo.baidu.walknavi;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWNaviStatusListener;
import com.baidu.mapapi.walknavi.adapter.IWRouteGuidanceListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.adapter.IWTTSPlayer;
import com.baidu.mapapi.walknavi.model.RouteGuideKind;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.platform.comapi.walknavi.WalkNaviModeSwitchListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class WalkNaviManager extends SimpleViewManager<View> implements LifecycleEventListener{

    private final static String TAG = "WalkView";
    private WalkNavigateHelper mNaviHelper;
    ThemedReactContext reactContext;
    WalkNaviLaunchParam walkParam;
    private LatLng startPt,endPt;
    @Override
    public String getName() {
        return "WalkView";
    }


    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        this.reactContext = reactContext;
        reactContext.addLifecycleEventListener(this);
        startPt = new LatLng(40.047416,116.312143);
        endPt = new LatLng(40.048424, 116.313513);
        walkParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
        mNaviHelper = WalkNavigateHelper.getInstance();
        View view = mNaviHelper.onCreate(reactContext.getCurrentActivity());
        return view;
    }

    @ReactProp(name = "test")
    public void setTest(View view, String test) {
    }

    @Override
    public void onHostResume() {
//        startWalkNavi();
        setmNaviHelperListener();
        mNaviHelper.resume();
    }

    @Override
    public void onHostPause() {
        mNaviHelper.pause();
    }

    @Override
    public void onHostDestroy() {
        mNaviHelper.quit();
    }


    private void startWalkNavi() {
        Log.e(TAG, "startBikeNavi");
        try {
            mNaviHelper.initNaviEngine(reactContext.getCurrentActivity(), new IWEngineInitListener() {
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
                boolean startResult = mNaviHelper.startWalkNavi(reactContext.getCurrentActivity());
                Log.e(TAG, "startWalkNavi result : " + startResult);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
                Log.e(TAG, "onRoutePlanFail");
            }

        });
    }

    private void setmNaviHelperListener(){
        mNaviHelper.setWalkNaviStatusListener(new IWNaviStatusListener() {
            @Override
            public void onWalkNaviModeChange(int mode, WalkNaviModeSwitchListener listener) {
                Log.e(TAG, "onWalkNaviModeChange : " + mode);
                mNaviHelper.switchWalkNaviMode(reactContext.getCurrentActivity(), mode, listener);
            }

            @Override
            public void onNaviExit() {
                Log.e(TAG, "onNaviExit");
            }
        });

        boolean startResult = mNaviHelper.startWalkNavi(reactContext.getCurrentActivity());

        mNaviHelper.setTTsPlayer(new IWTTSPlayer() {
            @Override
            public int playTTSText(final String s, boolean b) {
                Log.e(TAG, s);
                return 0;
            }
        });

        mNaviHelper.setRouteGuidanceListener(reactContext.getCurrentActivity(), new IWRouteGuidanceListener() {
            @Override
            public void onRouteGuideIconUpdate(Drawable icon) {

            }

            @Override
            public void onRouteGuideKind(RouteGuideKind routeGuideKind) {

            }

            @Override
            public void onRoadGuideTextUpdate(CharSequence charSequence, CharSequence charSequence1) {

            }

            @Override
            public void onRemainDistanceUpdate(CharSequence charSequence) {

            }

            @Override
            public void onRemainTimeUpdate(CharSequence charSequence) {

            }

            @Override
            public void onGpsStatusChange(CharSequence charSequence, Drawable drawable) {


            }

            @Override
            public void onRouteFarAway(CharSequence charSequence, Drawable drawable) {

            }

            @Override
            public void onRoutePlanYawing(CharSequence charSequence, Drawable drawable) {

            }

            @Override
            public void onReRouteComplete() {

            }

            @Override
            public void onArriveDest() {

            }

            @Override
            public void onVibrate() {

            }
        });
    }
//    //
//    @Override
//    protected void addEventEmitters(
//            final ThemedReactContext reactContext,
//            final View view) {
//    }
//
//    @Override
//    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
//        return MapBuilder.<String, Object>builder()
//                .put("onDidMoveByUser", MapBuilder.of("registrationName", "onDidMoveByUser"))//registrationName 后的名字,RN中方法也要是这个名字否则不执行
//                .build();
//    }

}
