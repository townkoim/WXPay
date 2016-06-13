package com.example.lb.weixin.pay.wxpay;

import com.example.lb.weixin.pay.wxpay.util.Signature;
import com.example.lb.weixin.pay.wxpay.util.WeiXinUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ${type_name}
 * @Description: ${todo}
 * @author: ${libiao}
 * @date: ${date} ${time}
 * ${tags}
 */
public class ScanPayReqData {
    //每个字段具体的意思请查看API文档
    private String appid = "";//微信分配的公众账号ID（企业号corpid即为此appId）
    private String mch_id = "";//微信支付分配的商户号
    private String nonce_str = "";//随机字符串
    private String sign = "";//签名
    private String body = "";//商品描述
    private String out_trade_no = "";//商户订单号
    private int total_fee = 0;//总金额
    private String spbill_create_ip = "";//终端ip

    private String notify_url = "";//接收微信支付异步通知回调地址,不能携带任何参数
    private String trade_type = "";//交易类型JSAPI，NATIVE，APP
    private String product_id = "";//商品ID,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。

    private String openid;//交易类型JSAPI,必填      用户在公众号下的唯一标识

    /**
     * 微信扫码支付模式二
     * @param body
     * @param total_fee
     * @param product_id
     * @param out_trade_no
     * @param spbill_create_ip
     */
    public ScanPayReqData( String body, int total_fee,
                           String product_id,String out_trade_no,String spbill_create_ip) {

        setAppid(Configure.appId);
        setMch_id(Configure.mchId);
        setNonce_str(WeiXinUtil.getRandomStringByLength(32));
        setBody(body);
        setOut_trade_no(out_trade_no);//商户订单号
        setTotal_fee(total_fee);
        setSpbill_create_ip(spbill_create_ip);
        setNotify_url(Configure.notifyUrl);
        setTrade_type("NATIVE");
        setProduct_id(product_id);

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中
    }



    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
