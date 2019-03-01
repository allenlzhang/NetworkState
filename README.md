# NetworkState
# 一个轻量级的网络状态监听库，方便实用。
# 1，引入库
    先在 build.gradle(Project:XXXX) 的 repositories 添加:
    allprojects {
    repositories {
        maven { url 'https://www.jitpack.io' }
        
    }
    }
    然后在 build.gradle(Module:app) 的 dependencies 添加:
    dependencies {
	        implementation 'com.github.allenlzhang:NetworkState:v1.0.0'
	}
# 2，在AndroidManifest中加入获取网络状态权限：

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    
# 3，在application中初始化：
    public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.getInstance().init(this);
    }
    }
# 4，在activity或者fragment中使用：
    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	//你也可以在这里初始化，对当前页面网络实时监听
	 NetworkManager.getInstance().init(this);
        //注册
        NetworkManager.getInstance().registerObserver(this);
       
    }

    @NetWork(netType = NetType.AUTO)
    public void network(NetType netType) {
        switch (netType) {
            case WIFI:
                Log.e(Constants.LOG_TAG, "wifi");
                break;
            case CMNET:
            case CMWAP:
                Log.e(Constants.LOG_TAG, "4G");
                break;
            case AUTO:
                break;
            case NONE:
                Log.e(Constants.LOG_TAG, "无网络");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        NetworkManager.getInstance().unRegisterObserver(this);
	//注销所有
	//NetworkManager.getInstance().unRegisterAllObserver();
    	}
    }
# 5，其他
  	NetworkUtils.isAvailable();//可以判断当前网络是否可用
  	NetworkUtils.getNetType();//可以获取当前网络类型
