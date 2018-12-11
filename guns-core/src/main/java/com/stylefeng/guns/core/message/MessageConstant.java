package com.stylefeng.guns.core.message;

/**
 * Created by HH on 2017/2/20.
 */
public final class MessageConstant {
    private MessageConstant(){}

    public static final String DEFAULT_MESSAGE = "系统异常";

    public static class MessageCode{
        /**
         * 正常
         */
        public static final String SYS_OK = "000000";
        /**
         * 系统异常
         */
        public static final String SYS_EXCEPTION = "000099";
        /**
         * 校验码不正确
         */
        public static final String SYS_CAPTCHA_NOT_MATCH = "000001";
        /**
         * 校验码超时
         */
        public static final String SYS_CAPTCHA_TIME_OUT = "000002";
        /**
         * 对象不存在
         */
        public static final String SYS_SUBJECT_NOT_FOUND = "000003";
        /**
         * 对象重复
         */
        public static final String SYS_SUBJECT_DUPLICATE = "000004";
        /**
         * 缺少参数
         */
        public static final String SYS_MISSING_ARGUMENTS = "000005";
        /**
         * 状态异常
         */
        public static final String SYS_SUBJECT_STATE = "000006";
        /**
         * 无效字符
         */
        public static final String SYS_DATA_ILLEGAL = "000007";
        /**
         * 非法格式
         */
        public static final String SYS_ARGUMENTS_ILLEGAL_FORMAT = "000008";
        /**
         * 没有获取到有效的用户登录信息
         */
        public static final String SYS_CREDENTIAL_UNKNOW = "000101";
        /**
         * 用户令牌失效
         */
        public static final String SYS_CREDENTIAL_EXPIRED = "000102";
        /**
         * 签名验证失败
         */
        public static final String SYS_TOKEN_ERROR = "000111";
        /**
         * 上传失败
         */
        public static final String SYS_UPLOAD_ERROR = "000599";
        /**
         * 上传文件过大
         */
        public static final String SYS_UPLOAD_OUT_OF_SIZE = "000501";
        /**
         * 不支持的文件格式
         */
        public static final String SYS_UPLOAD_UNKNOW_FORMAT = "000502";
        /**
         * 账号不存在
         */
        public static final String LOGIN_ACCOUNT_NOT_FOUND = "010001";
        /**
         * 账号失效
         */
        public static final String LOGIN_ACCOUNT_EXPIRED = "010002";
        /**
         * 账号已锁定
         */
        public static final String LOGIN_ACCOUNT_LOCKED = "010003";
        /**
         * 登录失败
         */
        public static final String LOGIN_FAILED = "010099";

        /**
         * 不能重复报名
         */
        public static final String COURSE_SELECTED = "020001";

        /**
         * 报名已截止
         */
        public static final String COURSE_SELECT_OUTTIME = "020002";

        /**
         * 支付方式不支持
         */
        public static final String PAY_METHOD_NOT_FOUND = "030001";
    }
}
