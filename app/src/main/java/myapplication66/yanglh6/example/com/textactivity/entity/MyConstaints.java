package myapplication66.yanglh6.example.com.textactivity.entity;

/**
 * @author LinkBasic 常量
 */
public interface MyConstaints {

    /**
     * 保存的缓存文件名 ()
     */
    String ALL_SPFILE_NAME = "lbk";

    /**
     * 保存的用户数据
     */
    String USER_DATA = "userdata";

    /**
     * 保存到sd卡的文件夹名称
     */
    String ALL_SDFILE_NAME = "/lbk/";

    /**
     * 保存到sd卡的文件夹名称
     */
    String ALL_SDFILE_NAME02 = "/";

    /*
     * 登录界面
	 */

    /**
     * 是否保存用户登陆信息(账号，密码)
     */
    String LOGIN_SAVE_USERINFO = "issaveuserinfo";

    /**
     * 用户标识
     */
    String TOKEN = "token";

    /**
     * 用户标识
     */
    String PHONE = "phone";


    /**
     * 用户标识
     */
    String USER_ID = "userid";

    /**
     * 用户标识
     */
    String FLOWCOUNT = "flowcount";

    /**
     * 用户名
     */
    String LOGIN_USERNAME = "username";

    /**
     * 用户密码
     */
    String LOGIN_USERPWD = "userpwd";

    /**地图类型------------------------------------------------------------------------------------------------------------------*/
    /**
     * 保存地图类型
     */
    String MAP_TYPE = "maptype";

    /**
     * 卫星地图
     */
    int SATELLITE_MAP = 1;
    /**
     * 平面地图
     */
    int PLANE_MAP = 2;
    /**
     * 混合地图
     */
    int BLEND_MAP = 3;

    /**
     * 发音人
     */
    String INFORMANT = "informant";
    /**
     * 发音人名字
     */
    String INFORMANT_NAME = "informant_name";
    /**
     * 发音人位置
     */
    String INFORMANT_POS = "informant_pos";

    /**
     * PM2.5的TOKEN
     * 临时的,需要申请
     */
    String WEATH_PM_TOKEN = "5j1znBVAsnSf5xQyNQyq";
    /**
     * 手表标识
     */
    String WRIST_WATCH_TYPE = "1";
    /**
     * 手环标识
     */
    String BRACELET_TYPE = "2";
    /**
     * 心率表标识
     */
    String HEART_RATE_MONITOR_TYPE = "3";
    /**
     * 骑行踏频器标识
     */
    String FREQUENCY_DIVIDER_TYPE = "4";
    /**
     * -------------------------------------------------------------运动的几个类型-----------------------------------------------------
     */
    /**
     * 走路
     */
    String MOTION_TYPE_WALK = "1381038";
    /**
     * 跑步
     */
    String MOTION_TYPE_RUN = "1381040";
    /**
     * 游泳
     */
    String MOTION_TYPE_SWIMMING = "1381041";
    /**
     * 登山
     */
    String MOTION_TYPE_MOUNTAINEERING = "1381039";
    /**
     * 骑行
     */
    String MOTION_TYPE_RIDING = "1381042";
    /**
     * 铁三
     */
    String MOTION_TYPE_IRON_THREE = "1381044";


}


