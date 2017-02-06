package com.lanxi.weixin.bean;


public class ExchangeCodeBean {
    /** 串码id */
    private String cmid;
    /** 生成时间 */
    private String scsj;
    /** 串码批次 */
    private String cmpc;
    /** 商品编号 */
    private String spbh;
    /** 商品名称 */
    private String spmc;
    /** 商品数量 */
    private String spsl;
    /** 串码状态 */
    private String cmzt;
    /** 有效期 */
    private String yxq;
    /** 兑换时间 */
    private String dhsj;
    /** 兑换手机 */
    private String dhsjhm;
    /** 备用1 */
    private String beiy1;
    /** 备用2 */
    private String beiy2;

    /** 设置状态 */
    private String newCmzt;

    public String getNewCmzt() {
        return newCmzt;
    }

    public void setNewCmzt(String newCmzt) {

        if (newCmzt != null) {
            this.newCmzt = newCmzt.trim();
        } else {
            this.newCmzt = newCmzt;
        }
    }

    public String getCmid() {
        return cmid;
    }

    public void setCmid(String cmid) {

        if (cmid != null) {
            this.cmid = cmid.trim();
        } else {
            this.cmid = cmid;
        }
    }

    public String getScsj() {
        return scsj;
    }

    public void setScsj(String scsj) {

        if (scsj != null) {
            this.scsj = scsj.trim();
        } else {
            this.scsj = scsj;
        }
    }

    public String getCmpc() {
        return cmpc;
    }

    public void setCmpc(String cmpc) {

        if (cmpc != null) {
            this.cmpc = cmpc.trim();
        } else {
            this.cmpc = cmpc;
        }
    }

    public String getSpbh() {
        return spbh;
    }

    public void setSpbh(String spbh) {

        if (spbh != null) {
            this.spbh = spbh.trim();
        } else {
            this.spbh = spbh;
        }
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {

        if (spmc != null) {
            this.spmc = spmc.trim();
        } else {
            this.spmc = spmc;
        }
    }

    public String getSpsl() {
        return spsl;
    }

    public void setSpsl(String spsl) {

        if (spsl != null) {
            this.spsl = spsl.trim();
        } else {
            this.spsl = spsl;
        }
    }

    public String getCmzt() {
        return cmzt;
    }

    public void setCmzt(String cmzt) {

        if (cmzt != null) {
            this.cmzt = cmzt.trim();
        } else {
            this.cmzt = cmzt;
        }
    }

    public String getYxq() {
        return yxq;
    }

    public void setYxq(String yxq) {

        if (yxq != null) {
            this.yxq = yxq.trim();
        } else {
            this.yxq = yxq;
        }
    }

    public String getDhsj() {
        return dhsj;
    }

    public void setDhsj(String dhsj) {

        if (dhsj != null) {
            this.dhsj = dhsj.trim();
        } else {
            this.dhsj = dhsj;
        }
    }

    public String getDhsjhm() {
        return dhsjhm;
    }

    public void setDhsjhm(String dhsjhm) {

        if (dhsjhm != null) {
            this.dhsjhm = dhsjhm.trim();
        } else {
            this.dhsjhm = dhsjhm;
        }
    }

    public String getBeiy1() {
        return beiy1;
    }

    public void setBeiy1(String beiy1) {

        if (beiy1 != null) {
            this.beiy1 = beiy1.trim();
        } else {
            this.beiy1 = beiy1;
        }
    }

    public String getBeiy2() {
        return beiy2;
    }

    public void setBeiy2(String beiy2) {

        if (beiy2 != null) {
            this.beiy2 = beiy2.trim();
        } else {
            this.beiy2 = beiy2;
        }
    }
}
