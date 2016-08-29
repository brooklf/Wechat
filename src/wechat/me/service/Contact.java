package wechat.me.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JulyLe on 2016/8/23.
 */
public class Contact {
    public static Map<String,Contact> contactsList = new HashMap<String,Contact>();
    public static int memberCount = 0;
    private static String myUsername;

    private String remarkPYInitial;
    private String contactFlag;
    private String snsFlag;
    private String verifyFlag;
    private String encryChatRoomId;
    private String nickName;  //昵称
    private String KeyWord;
    private String ChatRoomId;
    private String userName;  //用户名
    private String signature; //个性签名
    private String remarkName; //备注名
    private String sex;        //性别
    private String headImageUrl; //头像相对地址
    private String hideInputBarFlag;
    private String DisplayNamed;
    private String province;     //所在省份
    private String ownerUin;
    private String city;         //所在城市
    private String PYInitial;
    private String Uin;
    private String Alias;
    private String StarFriend;
    private String AttrStatus;
    private String UniFriend;
    private String PYQuanPin;
    private String MemberCountc;
    private String RemarkPYQuanPin;

    /**
     * 初始化联系人列表
     * @param bigJsonString
     * @param MyNickName  自己的昵称
     */
    public static void initContactList(String bigJsonString,String MyNickName){
        JSONObject bigJson = JSONObject.fromObject(bigJsonString);
        memberCount = Integer.parseInt(bigJson.getString("MemberCount"));
        JSONArray contactJsonArray = bigJson.getJSONArray("MemberList");
        for(int i=0;i<contactJsonArray.size();i++){
            JSONObject json = (JSONObject) contactJsonArray.get(i);
            Contact contact = new Contact();
            contact.setUserName(json.getString("UserName"));
            contact.setNickName(json.getString("NickName"));
            contact.setSignature(json.getString("Signature"));
            contact.setRemarkName(json.getString("RemarkName"));
            contact.setHeadImageUrl(json.getString("HeadImgUrl"));
            contact.setDisplayNamed(json.getString("DisplayName"));
            contact.setProvince(json.getString("Province"));
            contact.setCity(json.getString("City"));
            contactsList.put(contact.getNickName(),contact);
            if(contact.getNickName().equals(MyNickName)){
                setMyUsername(contact.getUserName());
            }
        }
    }

    public static String getUsername(String nickName){
        return contactsList.get(nickName).getUserName();
    }

    public Map<String,Contact> getContactList (){
        return contactsList;
    }


    public static void main(String args[]){
        String jsonString ="{\n" +
                "\"BaseResponse\": {\n" +
                "\"Ret\": 0,\n" +
                "\"ErrMsg\": \"\"\n" +
                "}\n" +
                ",\n" +
                "\"MemberCount\": 257,\n" +
                "\"MemberList\": [{\n" +
                "\"Uin\": 0,\n" +
                "\"UserName\": \"weixin\",\n" +
                "\"NickName\": \"微信团队\",\n" +
                "\"HeadImgUrl\": \"/cgi-bin/mmwebwx-bin/webwxgeticon?seq=440001&username=weixin&skey=\",\n" +
                "\"ContactFlag\": 3,\n" +
                "\"MemberCount\": 0,\n" +
                "\"MemberList\": [],\n" +
                "\"RemarkName\": \"\",\n" +
                "\"HideInputBarFlag\": 0,\n" +
                "\"Sex\": 0,\n" +
                "\"Signature\": \"\",\n" +
                "\"VerifyFlag\": 56,\n" +
                "\"OwnerUin\": 0,\n" +
                "\"PYInitial\": \"WXTD\",\n" +
                "\"PYQuanPin\": \"weixintuandui\",\n" +
                "\"RemarkPYInitial\": \"\",\n" +
                "\"RemarkPYQuanPin\": \"\",\n" +
                "\"StarFriend\": 0,\n" +
                "\"AppAccountFlag\": 0,\n" +
                "\"Statues\": 0,\n" +
                "\"AttrStatus\": 4,\n" +
                "\"Province\": \"\",\n" +
                "\"City\": \"\",\n" +
                "\"Alias\": \"\",\n" +
                "\"SnsFlag\": 0,\n" +
                "\"UniFriend\": 0,\n" +
                "\"DisplayName\": \"\",\n" +
                "\"ChatRoomId\": 0,\n" +
                "\"KeyWord\": \"wei\",\n" +
                "\"EncryChatRoomId\": \"\"\n" +
                "}\n" +
                ",{\n" +
                "\"Uin\": 0,\n" +
                "\"UserName\": \"@156aae2742a2adae07c6933181bdd07fdf49752e449247e2c98361bf1fba6958\",\n" +
                "\"NickName\": \"无怨无悔\",\n" +
                "\"HeadImgUrl\": \"/cgi-bin/mmwebwx-bin/webwxgeticon?seq=620080065&username=@156aae2742a2adae07c6933181bdd07fdf49752e449247e2c98361bf1fba6958&skey=\",\n" +
                "\"ContactFlag\": 3,\n" +
                "\"MemberCount\": 0,\n" +
                "\"MemberList\": [],\n" +
                "\"RemarkName\": \"\",\n" +
                "\"HideInputBarFlag\": 0,\n" +
                "\"Sex\": 1,\n" +
                "\"Signature\": \"倒带\",\n" +
                "\"VerifyFlag\": 0,\n" +
                "\"OwnerUin\": 0,\n" +
                "\"PYInitial\": \"WYWH\",\n" +
                "\"PYQuanPin\": \"wuyuanwuhui\",\n" +
                "\"RemarkPYInitial\": \"\",\n" +
                "\"RemarkPYQuanPin\": \"\",\n" +
                "\"StarFriend\": 0,\n" +
                "\"AppAccountFlag\": 0,\n" +
                "\"Statues\": 0,\n" +
                "\"AttrStatus\": 4129,\n" +
                "\"Province\": \"安徽\",\n" +
                "\"City\": \"芜湖\",\n" +
                "\"Alias\": \"\",\n" +
                "\"SnsFlag\": 16,\n" +
                "\"UniFriend\": 0,\n" +
                "\"DisplayName\": \"\",\n" +
                "\"ChatRoomId\": 0,\n" +
                "\"KeyWord\": \"\",\n" +
                "\"EncryChatRoomId\": \"\"\n" +
                "}\n" +
                ",{\n" +
                "\"Uin\": 0,\n" +
                "\"UserName\": \"@8e9c84d68156fb44942ae7f745630ddca14f85fa878d22c27de2dcc67b5a3803\",\n" +
                "\"NickName\": \"汪军\",\n" +
                "\"HeadImgUrl\": \"/cgi-bin/mmwebwx-bin/webwxgeticon?seq=620080096&username=@8e9c84d68156fb44942ae7f745630ddca14f85fa878d22c27de2dcc67b5a3803&skey=\",\n" +
                "\"ContactFlag\": 3,\n" +
                "\"MemberCount\": 0,\n" +
                "\"MemberList\": [],\n" +
                "\"RemarkName\": \"\",\n" +
                "\"HideInputBarFlag\": 0,\n" +
                "\"Sex\": 1,\n" +
                "\"Signature\": \"万里无云\",\n" +
                "\"VerifyFlag\": 0,\n" +
                "\"OwnerUin\": 0,\n" +
                "\"PYInitial\": \"WJ\",\n" +
                "\"PYQuanPin\": \"wangjun\",\n" +
                "\"RemarkPYInitial\": \"\",\n" +
                "\"RemarkPYQuanPin\": \"\",\n" +
                "\"StarFriend\": 0,\n" +
                "\"AppAccountFlag\": 0,\n" +
                "\"Statues\": 0,\n" +
                "\"AttrStatus\": 4129,\n" +
                "\"Province\": \"陕西\",\n" +
                "\"City\": \"西安\",\n" +
                "\"Alias\": \"\",\n" +
                "\"SnsFlag\": 16,\n" +
                "\"UniFriend\": 0,\n" +
                "\"DisplayName\": \"\",\n" +
                "\"ChatRoomId\": 0,\n" +
                "\"KeyWord\": \"\",\n" +
                "\"EncryChatRoomId\": \"\"\n" +
                "}],\n" +
                "\"Seq\": 0\n" +
                "}";
        /*JSONObject getobj = JSONObject.fromObject(jsonString);
        System.out.println(getobj.getString("MemberCount"));
        System.out.println(getobj.getJSONArray("MemberList").get(1));
        JSONObject obj = JSONObject.fromObject(getobj.getJSONArray("MemberList").get(1));*/
         Contact.initContactList(jsonString,"");
        System.out.println(Contact.getMemberCount());

    }

    public static Map<String,Contact> getContactsList() {
        return contactsList;
    }


    public String getRemarkPYInitial() {
        return remarkPYInitial;
    }

    public void setRemarkPYInitial(String remarkPYInitial) {
        this.remarkPYInitial = remarkPYInitial;
    }

    public String getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(String contactFlag) {
        this.contactFlag = contactFlag;
    }

    public String getSnsFlag() {
        return snsFlag;
    }

    public void setSnsFlag(String snsFlag) {
        this.snsFlag = snsFlag;
    }

    public String getVerifyFlag() {
        return verifyFlag;
    }

    public void setVerifyFlag(String verifyFlag) {
        this.verifyFlag = verifyFlag;
    }

    public String getEncryChatRoomId() {
        return encryChatRoomId;
    }

    public void setEncryChatRoomId(String encryChatRoomId) {
        this.encryChatRoomId = encryChatRoomId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getChatRoomId() {
        return ChatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        ChatRoomId = chatRoomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getHideInputBarFlag() {
        return hideInputBarFlag;
    }

    public void setHideInputBarFlag(String hideInputBarFlag) {
        this.hideInputBarFlag = hideInputBarFlag;
    }

    public String getDisplayNamed() {
        return DisplayNamed;
    }

    public void setDisplayNamed(String displayNamed) {
        DisplayNamed = displayNamed;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getOwnerUin() {
        return ownerUin;
    }

    public void setOwnerUin(String ownerUin) {
        this.ownerUin = ownerUin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPYInitial() {
        return PYInitial;
    }

    public void setPYInitial(String PYInitial) {
        this.PYInitial = PYInitial;
    }

    public String getUin() {
        return Uin;
    }

    public void setUin(String uin) {
        Uin = uin;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getStarFriend() {
        return StarFriend;
    }

    public void setStarFriend(String starFriend) {
        StarFriend = starFriend;
    }

    public String getAttrStatus() {
        return AttrStatus;
    }

    public void setAttrStatus(String attrStatus) {
        AttrStatus = attrStatus;
    }

    public String getUniFriend() {
        return UniFriend;
    }

    public void setUniFriend(String uniFriend) {
        UniFriend = uniFriend;
    }

    public String getPYQuanPin() {
        return PYQuanPin;
    }

    public void setPYQuanPin(String PYQuanPin) {
        this.PYQuanPin = PYQuanPin;
    }


    public String getRemarkPYQuanPin() {
        return RemarkPYQuanPin;
    }

    public void setRemarkPYQuanPin(String remarkPYQuanPin) {
        RemarkPYQuanPin = remarkPYQuanPin;
    }

    public static void setMemberCount(int memberCount) {
        Contact.memberCount = memberCount;

    }

    public static int getMemberCount() {
        return memberCount;
    }

    public String getMemberCountc() {
        return MemberCountc;
    }

    public void setMemberCountc(String memberCountc) {
        MemberCountc = memberCountc;
    }

    public static String getMyUsername() {
        return myUsername;
    }

    public static void setMyUsername(String myUsername) {
        Contact.myUsername = myUsername;
    }
}
