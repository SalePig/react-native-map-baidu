package com.reactnative_firstdemo.baidu.map;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
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
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnative_firstdemo.R;

public class MapViewManager extends SimpleViewManager<MapView> {

    private final static String TAG = "MapView";

    @Override
    public String getName() {
        return "MapView";
    }


    @Override
    protected MapView createViewInstance(ThemedReactContext reactContext) {
        MapView view = new MapView(reactContext);
        initMap(view, reactContext);
        return view;
    }

    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.mipmap.ic_launcher);

    private void initMap(MapView mMapView, ThemedReactContext reactContext) {
        final BaiduMap mBaiduMap = mMapView.getMap();

//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
//        mBaiduMap.setMapStatus(msu);
        LatLng llA = new LatLng(39.963175, 116.400244);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA)
                .zIndex(9).draggable(true);
        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        Marker mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(llA).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        // 定位初始化
//        LocationClient mLocClient = new LocationClient(reactContext);
//        mLocClient.registerLocationListener(new BDLocationListener(){
//            @Override
//            public void onReceiveLocation(BDLocation bdLocation) {
//                LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            }
//        });
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true); // 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);
//        mLocClient.start();
    }

    @ReactProp(name = "test")
    public void setTest(View view, String test) {
    }
}
