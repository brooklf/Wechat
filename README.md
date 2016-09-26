##Wechat
网页版微信API，微信机器人JAVA版。

###微信验证过程

```
       +--------------+     +---------------+   +---------------+
       |              |     |               |   |               |
       |   Get UUID   |     |  Get Contact  |   | Status Notify |
       |              |     |               |   |               |
       +-------+------+     +-------^-------+   +-------^-------+
               |                    |                   |
               |                    +-------+  +--------+
               |                            |  |
       +-------v------+               +-----+--+------+      +--------------+
       |              |               |               |      |              |
       |  Get QRCode  |               |  Weixin Init  +------>  Sync Check  <----+
       |              |               |               |      |              |    |
       +-------+------+               +-------^-------+      +-------+------+    |
               |                              |                      |           |
               |                              |                      +-----------+
               |                              |                      |
       +-------v------+               +-------+--------+     +-------v-------+
       |              | Confirm Login |                |     |               |
+------>    Login     +---------------> New Login Page |     |  Weixin Sync  |
|      |              |               |                |     |               |
|      +------+-------+               +----------------+     +---------------+
|             |
|QRCode Scaned|
+-------------+
```
####1、获取UUID

API |  获取UUID
----|-------------------------------------
URL |https://login.weixin.qq.com/jslogin
参数|appid、fun、lang、_
方法|post

appid为微信网页接口appid，现在为"wx782c26e4c19acffb",fun="new",lang="zh_CN"，_为时间戳。

####2、获取二维码
API |获取二维码
--- |-------------------------------------
URL |https://login.weixin.qq.com/qrcode/
参数|uuid,t,_
方法|post
uuid|就是上一步获取的uuid

t="webwx","_"为时间戳

####3、等待扫描

API |轮询二维码扫描状态，等待扫描
----|-------------------------------------------------
URL |https://login.weixin.qq.com/cgi-bin/mmwebwx-bin
参数|tip,uuid,&_
方法|get

tip是扫描标记位，1代表未扫描，0代表扫描

####4、获取初始化参数
API  |获取初始化参数、以后后面访问需要用到的Webwx_data_ticket，skey,wxsid,wxuin,pass_ticket
-----|---------------------------------------------------
URL  |扫描后会有一个redirectUrl
参数 |fun="new"
方法 |get

####5、初始化微信，获取同步密钥
API  |初始化卫星获取同步刷新时候的密钥
-----|--------------------------------------------------------
URL  |https://login.weixin.qq.com/cgi-bin/mmwebwx-bin
参数 |pass_ticket、skey、&r
方法 |post

获取同步刷新时需要的同步密钥，也就是几个时间戳。返回Json,Key字段。

####6、获取联系人信息
API  |获取联系人信息，返回Json
-----|---------------------------------------------------
URL  |https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact
参数 |pass_ticket，带cookie访问
方法 |post

获取联系人信息，Json数组。

####7、发送消息

API  |发送微信消息
-----|-------------------------------------------------------------
URL  |https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg
参数 |pass_ticket
方法 |post & json，带cookie访问

####8、同步刷新
API  |同步刷新，探知微信状态
-----|----------------------------------------------------------------------
URL  |https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck
参数 |r,skey,sid,uin,deviceid,synckey,_
方法 |get

其中r和_均为时间戳，devicedid为"e312495766220816"也就是e+15位随机数，synckey为初始化时获取的密钥
返回数据
	window.synccheck={retcode:"xxx",selector:"xxx"}

	retcode:
    	0 正常
    	1100 失败/登出微信
	selector:
    	0 正常
    	2 新的消息
    	7 进入/离开聊天界面
####9、收取消息
API  |收取微信消息
-----|-------------------------------------------------------------
URL  |https://webpush.wx.qq.com/cgi-bin/mmwebwx-bin/synccheck
参数 |sid，skey，pass_ticket，BaseRequest，SyncKey，rr
方法 |post 带cookie访问

该方法用于收取消息，也用于更新同步密钥Sysnckey





