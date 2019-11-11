package com.example.newstest;



import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class ShowWeatherActivity extends AppCompatActivity {
    String TAG="ShowWeather";
    @BindViews({R.id.tv_tianqi,R.id.tv_kongqi,R.id.tv_wind})
    List<TextView>textViewList;
    @BindView(R.id.img_weather)
    ImageView imageView;
    public AMapLocationClient mLocationClient=null;
    private static final int BAIDU_READ_PHONE_STATE = 100;//定位权限请求
    private static final int PRIVATE_CODE = 1315;//开启GPS权限
    //声明定位回调监听器
    public AMapLocationClientOption mLocationOption=null;
    //Location_Bean location_bean;
    private   String CityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_toolbar_base);
        showGPSContacts();

        initMap();
        HeConfig.init("HE1911061056431785","ae161412294f45aba420dd483a10c773");
        HeConfig.switchToFreeServerNode();
        ButterKnife.bind(this);
    }

    private void initMap() {
        //初始化定位
        mLocationClient=new AMapLocationClient(ShowWeatherActivity.this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，AMapLocationMode.Battery_Saving为低功耗模式，AMapLocationMode.Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setOnceLocation(false);//设置是否只定位一次,默认为false
        mLocationOption.setWifiActiveScan(true);//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setMockEnable(false);//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setInterval(15000);//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setOnceLocation(false);//可选，是否设置单次定位默认为false即持续定位
        mLocationOption.setOnceLocationLatest(false); //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mLocationOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mLocationOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }
    public AMapLocationListener mLocationListener= aMapLocation -> {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                // aMapLocation.getLatitude();//获取纬度
                // aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //  aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                //  aMapLocation.getCountry();//国家信息
                //  aMapLocation.getProvince();//省信息
                //  aMapLocation.getCity();//城市信息
                //   aMapLocation.getDistrict();//城区信息
                //    aMapLocation.getStreet();//街道信息
                //     aMapLocation.getStreetNum();//街道门牌号信息
                //    aMapLocation.getCityCode();//城市编码
                //     aMapLocation.getAdCode();//地区编码
                //获取经纬度
                double  LongitudeId = aMapLocation.getLongitude();
                double LatitudeId = aMapLocation.getLatitude();
                //获取定位城市定位的ID
                requestCityInfo(LongitudeId,LatitudeId);
                Toast.makeText(ShowWeatherActivity.this,"所在城市："+aMapLocation.getProvince()+aMapLocation.getCity()+aMapLocation.getDistrict(),Toast.LENGTH_SHORT).show();
                mLocationClient.stopLocation();//停止定位
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("info", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    };
    public void  requestCityInfo(double longitude,double latitude){
        //这里的key是webapi key
        String cityUrl = "https://search.heweather.net/find?location="+longitude+","+latitude+"&key=ef2642911e114642939dfc33049b48d0";
        sendRequestWithOkHttp(cityUrl);
    }
    //解析根据经纬度获取到的含有城市id的json数据，这里我使用原始的jsonObject与jsonArray来解析，网上也有使用jsonformat插件来，你们也可以那样做
    private void sendRequestWithOkHttp(final String cityUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(cityUrl).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    //返回城市列表json数据
                    String responseData = response.body().string();
                    System.out.println("变成json数据的格式："+responseData);
                    JSONObject jsonWeather = null;
                    try {
                        jsonWeather = new JSONObject(responseData);
                        JSONArray jsonArray = jsonWeather.getJSONArray("HeWeather6");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String jsonStatus = jsonObject.getString("status");
                        System.out.println("解析以后的内容："+jsonStatus);
                        if (jsonStatus.equals("ok")){
                            JSONArray jsonBasic = jsonObject.getJSONArray("basic");
                            JSONObject jsonCityId = jsonBasic.getJSONObject(0);
                            CityId = jsonCityId.getString("cid");
                            getWether();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getWether() {
        /**
         * 实况天气
         * 实况天气即为当前时间点的天气状况以及温湿风压等气象指数，具体包含的数据：体感温度、
         * 实测温度、天气状况、风力、风速、风向、相对湿度、大气压强、降水量、能见度等。
         *
         * @param context  上下文
         * @param location 地址详解
         * @param lang       多语言，默认为简体中文
         * @param unit        单位选择，公制（m）或英制（i），默认为公制单位
         * @param listener  网络访问回调接口
         */
        HeWeather.getWeatherNow(ShowWeatherActivity.this, CityId, Lang.CHINESE_SIMPLIFIED , Unit.METRIC , new HeWeather.OnResultWeatherNowBeanListener() {
            public static final String TAG="he_feng_now";
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ", e);
                System.out.println("Weather Now Error:"+new Gson());
            }
            @Override
            public void onSuccess(Now dataObject) {
                Log.i(TAG, " Weather Now onSuccess: " + new Gson().toJson(dataObject));
                String jsonData = new Gson().toJson(dataObject);
                System.out.println("返回的数据内容："+dataObject.getStatus());
                String tianqi = null,wendu = null, tianqicode = null,fengxiang=null;
                if (dataObject.getStatus().equals("ok")){
                    String JsonNow = new Gson().toJson(dataObject.getNow());
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(JsonNow);
                        tianqi = jsonObject.getString("cond_txt");
                        wendu = jsonObject.getString("tmp");
                        tianqicode = jsonObject.getString("cond_code");
                        fengxiang = jsonObject.getString("wind_dir");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(ShowWeatherActivity.this,"天气有错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                String wendu2 = wendu +"℃";
                textViewList.get(0).setText("当前天气:"+tianqi);
                textViewList.get(1).setText("当前温度:"+wendu2);
                textViewList.get(2).setText("当前风向:"+fengxiang);
                String tagurl = "https://cdn.heweather.com/cond_icon/" +tianqicode+".png";
                Glide.with(ShowWeatherActivity.this).load(tagurl).into(imageView);
            }
        });
        //无权限
//        HeWeather.getAirNow(ShowWeatherActivity.this,CityId, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultAirNowBeansListener() {
//            public static final String TAG2="he_feng_air";
//            @Override
//            public void onError(Throwable throwable) {
//                Log.i(TAG2,"ERROR IS:",throwable);
//            }
//
//            @Override
//            public void onSuccess(AirNow airNow) {
//                Log.i(TAG2,"Air Now onSuccess:"+new Gson().toJson(airNow));
//                String airStatus = airNow.getStatus();
//                if (airStatus.equals("ok")){
//                    String jsonData = new Gson().toJson(airNow.getAir_now_city());
//                    String aqi = null,qlty = null;
//                    JSONObject objectAir = null;
//                    try {
//                        objectAir = new JSONObject(jsonData);
//                        aqi = objectAir.getString("aqi");
//                        qlty = objectAir.getString("qlty");
//                        textViewList.get(2).setText("天气状况:"+qlty+" "+"空气质量:"+"("+aqi+")");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    Toast.makeText(ShowWeatherActivity.this,"空气质量有错误",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient !=null){
            mLocationClient.onDestroy();
        }
    }

    /**
     * 检测GPS、位置权限是否开启
     */

    public void showGPSContacts() {

        //得到系统的位置服务，判断GPS是否激活
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, LOCATIONGPS, BAIDU_READ_PHONE_STATE);
                } else {
                    //initLocationOption();//有权限，进行相应的处理
                }
            } else {
                //initLocationOption();//有权限，进行相应的处理
            }
        } else {
            Toast.makeText(this, "系统检测到未开启GPS定位服务,请开启", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, PRIVATE_CODE);
        }
    }

    /**
     * Android6.0申请权限的回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
//             requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                //如果用户取消，permissions可能为null.
                if (grantResults[0] == PERMISSION_GRANTED && grantResults.length > 0) { //有权限
                    // 获取到权限，作相应处理
                    //initLocationOption();//有权限，进行相应的处理
                } else {
                    /*
                     * 无权限
                     * */

                    Toast.makeText(this, "你未开启定位权限!", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }
    }

    static final String[] LOCATIONGPS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };




}

