package com.lanxi.weixin.codeMapper;


import com.lanxi.weixin.bean.BranchActInfoBean;
import com.lanxi.weixin.bean.ExchangeCodeBean;
import org.apache.ibatis.annotations.Param;

public interface ExchangeCodeMapper {
    /** 通过串码id获取 */
    public abstract ExchangeCodeBean getByCmid(String cmid);
    /** 更新状态 根据串码id 状态 */
    public abstract int updateCmztByCmidCmzt(ExchangeCodeBean exchangeCodeBean);

    /**
     * 查询规定时间段内, 单个手机号已兑换的次数
     * @param dhsjhm 手机号
     * @param beginDate 起始日期
     * @param endDate 结束日期
     * @return
     */
    public abstract int getExchangeCountByDhsjDhsjhm(@Param("dhsjhm") String dhsjhm,
                                                     @Param("beginDate") String beginDate,
                                                     @Param("endDate") String endDate);

    /**
     * 插入短信发送表
     * @param xtrq 系统日期
     * @param fsxh 发送序号
     * @param clbz 处理标志 0:未处理（或失败） 1:处理成功
     * @param sjh 手机号
     * @param zdcs 最大次数
     * @param yfcs 已发次数
     * @param fsrq 发送日期
     * @param fssj 发送时间
     * @param fsxx 发送信息
     * @return
     */
    public abstract int insertSMSSend(@Param("xtrq") String xtrq, @Param("fsxh") String fsxh,
                                      @Param("clbz") String clbz, @Param("sjh") String sjh,
                                      @Param("zdcs") int zdcs, @Param("yfcs") int yfcs,
                                      @Param("fsrq") String fsrq, @Param("fssj") String fssj,
                                      @Param("fsxx") String fsxx);

    /**
     * 查询规定时间段内, 指定机构号 手机号已兑换的次数
     * @param branchid 机构号
     * @param dhsjhm 手机号
     * @param beginDate 起始日期
     * @param endDate 结束日期
     * @return
     */
    public abstract int getExchangeCountByDhsjBranchidDhsjhm(@Param("branchid") String branchid,
                                                             @Param("dhsjhm") String dhsjhm,
                                                             @Param("beginDate") String beginDate,
                                                             @Param("endDate") String endDate);

}
