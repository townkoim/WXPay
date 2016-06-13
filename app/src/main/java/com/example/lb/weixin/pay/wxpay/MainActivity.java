package com.example.lb.weixin.pay.wxpay;

import android.app.Activity;
import android.os.Bundle;

import com.example.lb.weixin.pay.wxpay.util.HttpUtil;
import com.example.lb.weixin.pay.wxpay.util.WeiXinUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //微信扫码模式2
    public void wxPayModel2() {
        String body = "测试商户的名称";
        int money = 100;//交易金额，以分为单位100=1元。
        String productId= WeiXinUtil.getTimeStamp();//获取商品订单号
        String outTradeNo=WeiXinUtil.getOrderNo();//获取该笔交易的订单号
        String ip=WeiXinUtil.getIp();//获取发起请求的IP，我这里就直接获取了本机的IP了，实际开发需要获取实际的请求IP
        ScanPayReqData data  = new ScanPayReqData(body,money,productId,outTradeNo,ip);//组成要发送给微信的数据为一个实体类
        String reuslt= HttpUtil.sendPost(Configure.orderApi,data);//将组装好的数据发送到微信，并获取微信的返回值

        //接下来的操作就是解析result。 将xml格式的result解析成json或者实体类。然后将数据保存到数据库...一下步骤省略...在改xml中有一个参数code_url，这个就是二维码的链接，
        //将该二维码的链接生成图形二维码，给用户扫码。会进入到Configure.notifyUrl的回调接口当中。现在假设回调的为下面的方法notifyUrl();

    }

    //微信支付成功之后的回调,注意，只有在成功支付，微信才会进入回调,注意
    public void notiyUrl(HttpServletRequest req,HttpServletResponse response){
        ServletInputStream input =  req.getInputStream();//这里得到输入流，这就是微信在回调该接口的时候，会把参数传到这里，下一步就是将流解析成xml。然后再将xml解析成我们需要的格式
        //得到数据之后，通过outTradeNo来修改wxPayModel2（）中已经插入数据库中的数据
        //然后发送推送给APP。说交易成功。
    }

}
