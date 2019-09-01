package lanyao.springsecurity.oauth;

public enum SystemConstant {

    encode_UTF_8(Constants.encode_UTF_8),
    //    時間格式
    GMT_8(Constants.GMT_8),
    DATE_ALL_FORMAT(Constants.DATE_ALL_FORMAT),



    ADMIN_INDEX(Constants.ADMIN_INDEX),



    //會員狀態
    //正常
    USER_STATUS_NORMAL(Constants.USER_STATUS_NORMAL),
    //鎖住
    USER_STATUS_LOCKED(Constants.USER_STATUS_LOCKED),
    //停用
    USER_STATUS_ENABLE(Constants.USER_STATUS_ENABLE),

//   狀態
    NORMAL(Constants.SHOW),
    HIDE(Constants.HIDE),
//圖片輪播最大數量
    CAROUSEL_LINK_COUNT(Constants.CAROUSEL_LINK_COUNT),
    CAROUSEL_URL(Constants.CAROUSEL_URL),
    CAROUSEL_PATH(Constants.CAROUSEL_PATH),

//    預設密碼
    USER_DEFAULT_PASSWORD(Constants.USER_DEFAULT_PASSWORD),


    //   老師狀態
    TEACHER_STATUS_NORMAL(Constants.TEACHER_STATUS_NORMAL),
    TEACHER_STATUS_RETIREMENT(Constants.TEACHER_STATUS_RETIREMENT),

//    功能種類
    FUNCTION_KIND_ADMIN(Constants.FUNCTION_KIND_USER),
//子功能狀態
    SUB_FUNCTION_STATUS_HIDE(Constants.SUB_FUNCTION_STATUS_HIDE),
    SUB_FUNCTION_STATUS_SHOW(Constants.SUB_FUNCTION_STATUS_SHOW)



    ;


    private String value;
    SystemConstant(final String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }

    public static class Constants {
        public static final String encode_UTF_8 = "UTF-8";
        public static final String GMT_8 = "GMT+8";
        public static final String DATE_ALL_FORMAT ="yyyy/MM/dd  HH：mm";
        public static final String ADMIN_INDEX ="/admin/index";



        public static final String USER_STATUS_NORMAL = "1";
        public static final String USER_STATUS_LOCKED = "2";
        public static final String USER_STATUS_ENABLE = "3";


        public static final String SHOW = "1";
        public static final String HIDE = "2";

        public static final String FUNCTION_KIND_USER = "2";


        public static final String TEACHER_STATUS_NORMAL = "1";
        public static final String TEACHER_STATUS_RETIREMENT = "2";




        public static final String CAROUSEL_LINK_COUNT = "10";
        public static final String CAROUSEL_URL = "carousel/img/";
        public static final String CAROUSEL_PATH = "D:\\sec\\master_program\\file\\carousel\\";

        public static final String FILE_PATH_NEWS="D:\\sec\\master_program\\file\\news\\";
        public static final String FILE_PATH_NEWS_URL="news/file/";
        public static final String FILE_PATH_SUB_FUNCTION="D:\\sec\\master_program\\file\\subFunction\\";
        public static final String FILE_PATH_SUB_FUNCTION_URL="subFunction/file/";

        public static final String FILE_PATH_DOWNLOAD_MAIN="D:\\sec\\master_program\\file\\downloadMain\\";
        public static final String FILE_PATH_DOWNLOAD_MAIN_URL="downloadMain/file/";
        public static final String FILE_PATH_NEWS_BIGDATA="D:\\sec\\master_program\\file\\newsBigdata\\";
        public static final String FILE_PATH_NEWS_BIGDATA_URL="newsBigdata/file/";


        public static final String USER_DEFAULT_PASSWORD="abc12345";

        public static final String SUB_FUNCTION_STATUS_HIDE="0";
        public static final String SUB_FUNCTION_STATUS_SHOW="1";


    }












}
