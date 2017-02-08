package com.lanxi.weixin.codeMapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by shenzhiqiang on 16-10-10.
 */
public interface SmsModMapper {

    /**
     * 根据短信名称获取短信模板
     * @param smsname
     * @return
     */
    public abstract String getSmsmodBySmsname(@Param("smsname") String smsname);

}
