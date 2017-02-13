package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/7.
 */
public class GetGoodsInfo implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * goodsId : 25
     * goodsName : 2016新款睡衣女夏天短袖开衫碎花针织纯棉薄款全棉家居服套装
     * goodsSubtitle : asd
     * gcName : 男女服装 &gt; 内衣睡衣
     * goodsImage : /XRMall/u/cms/www/201606/28090203xrp1.png
     * goodsStorePrice : 129
     * goodsStorePriceInterval : 0
     * goodsSerial : 1
     * goodsClick : 0
     * goodsDescription : asd
     * goodsBody : asd
     * goodsImageText : <ul id="J_AttrUL">
     <li title="&nbsp;2016年春季">
     上市时间:&nbsp;2016年春季</li>
     <li title="&nbsp;睡衣">
     适用场景:&nbsp;睡衣</li>
     <li title="&nbsp;青年">
     适用对象:&nbsp;青年</li>
     <li title="&nbsp;商场同款(线上线下都销售)">
     销售渠道类型:&nbsp;商场同款(线上线下都销售)</li>
     <li title="&nbsp;棉100%">
     面料材质成分:&nbsp;棉100%</li>
     <li id="J_attrBrandName" title="&nbsp;芬腾">
     品牌:&nbsp;芬腾</li>
     <li title="&nbsp;J9623016">
     款号:&nbsp;J9623016</li>
     <li title="&nbsp;J9623016米黄">
     颜色分类:&nbsp;J9623016米黄</li>
     <li title="&nbsp;M&nbsp;L&nbsp;XL&nbsp;XXL">
     尺码:&nbsp;M&nbsp;L&nbsp;XL&nbsp;XXL</li>
     <li title="&nbsp;2件">
     件数:&nbsp;2件</li>
     <li title="&nbsp;针织棉面料">
     面料俗称:&nbsp;针织棉面料</li>
     <li title="&nbsp;棉">
     面料主材质:&nbsp;棉</li>
     <li title="&nbsp;95%以上">
     成分含量:&nbsp;95%以上</li>
     <li title="&nbsp;200g">
     主面料克重:&nbsp;200g</li>
     <li title="&nbsp;薄款">
     厚薄:&nbsp;薄款</li>
     <li title="&nbsp;碎花">
     图案:&nbsp;碎花</li>
     <li title="&nbsp;印花">
     服装款式细节:&nbsp;印花</li>
     <li title="&nbsp;圆领">
     领型:&nbsp;圆领</li>
     <li title="&nbsp;对襟">
     衣门襟:&nbsp;对襟</li>
     <li title="&nbsp;短袖">
     袖长:&nbsp;短袖</li>
     <li title="&nbsp;五分裤">
     裤长:&nbsp;五分裤</li>
     <li title="&nbsp;皮筋">
     裤门襟:&nbsp;皮筋</li>
     <li title="&nbsp;夏季">
     适用季节:&nbsp;夏季</li>
     <li title="&nbsp;女">
     适用性别:&nbsp;女</li>
     <li title="&nbsp;甜美">
     家居服风格:&nbsp;甜美</li>
     <li title="&nbsp;甜美">
     <img alt="" src="/XRMall/u/cms/www/201606/28090303m0qu.png" style="width: 322px; height: 417px;" /></li>
     </ul>

     * goodsSpec : null
     * goodsColImg : /XRMall/u/cms/www/201606/28090230dmwp.png;/XRMall/u/cms/www/201606/28090217cusz.png;/XRMall/u/cms/www/201606/28090224o978.png;
     * kdPrice : 0
     * commentnum : 0
     * salenum : 1
     * buysNumber : 0
     * songName : asd
     * goodsIsbuy : false
     * goodsImageMore : ["/XRMall/u/cms/www/201606/28090230dmwp.png","/XRMall/u/cms/www/201606/28090217cusz.png","/XRMall/u/cms/www/201606/28090224o978.png"]
     * goodsSpecDTO : [{"specId":61,"specName":"[{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":129,"specGoodsStorage":193,"specSalenum":0,"specGoodsColor":"/XRMall/u/cms/www/201606/28090203xrp1.png","specGoodsSpec":"[{\"spValue\":\"L\",\"spValueId\":17}]"},{"specId":62,"specName":"[{\"spName\":\"尺码\",\"spId\":2}]","specGoodsPrice":129,"specGoodsStorage":145,"specSalenum":0,"specGoodsColor":"/XRMall/u/cms/www/201606/28090209t665.png","specGoodsSpec":"[{\"spValue\":\"M\",\"spValueId\":16}]"}]
     * goodsSpecListDTO : [{"spId":2,"spName":"尺码","goodsSpecValueListDTO":[{"spValueId":16,"spValue":"M"},{"spValueId":17,"spValue":"L"}]}]
     * goodsEvaluationDTO : [{"id":26,"desF":"未作出评价","replyF":"bbb","flag":1,"time":"2016-7-8","userName":"Gfgg","userImg":"/XRMall/user/user_img/201607/080939047pd0.png"},{"id":26,"desF":"未作出评价","replyF":"bbb","flag":1,"time":"2016-7-8","userName":"Gfgg","userImg":"/XRMall/user/user_img/201607/080939047pd0.png"}]
     * goodsIssueAttributeDTO : [{"attrName":"面料","attrValueName":"全棉"}]
     */

    private int stateCode;
    private String msg;
    private int goodsId;
    private String goodsName;
    private String goodsSubtitle;
    private String gcName;
    private String goodsImage;
    private int goodsStorePrice;
    private String goodsStorePriceInterval;
    private String goodsSerial;
    private int goodsClick;
    private String goodsDescription;
    private String goodsBody;
    private String goodsImageText;
    private Object goodsSpec;
    private String goodsColImg;
    private int kdPrice;
    private int commentnum;
    private int salenum;
    private int buysNumber;
    private String songName;
    private boolean goodsIsbuy;
    private List<String> goodsImageMore;
    /**
     * specId : 61
     * specName : [{"spName":"尺码","spId":2}]
     * specGoodsPrice : 129
     * specGoodsStorage : 193
     * specSalenum : 0
     * specGoodsColor : /XRMall/u/cms/www/201606/28090203xrp1.png
     * specGoodsSpec : [{"spValue":"L","spValueId":17}]
     */

    private List<GoodsSpecDTOBean> goodsSpecDTO;
    /**
     * spId : 2
     * spName : 尺码
     * goodsSpecValueListDTO : [{"spValueId":16,"spValue":"M"},{"spValueId":17,"spValue":"L"}]
     */

    private List<GoodsSpecListDTOBean> goodsSpecListDTO;
    /**
     * id : 26
     * desF : 未作出评价
     * replyF : bbb
     * flag : 1
     * time : 2016-7-8
     * userName : Gfgg
     * userImg : /XRMall/user/user_img/201607/080939047pd0.png
     */

    private List<GoodsEvaluationDTOBean> goodsEvaluationDTO;
    /**
     * attrName : 面料
     * attrValueName : 全棉
     */

    private List<GoodsIssueAttributeDTOBean> goodsIssueAttributeDTO;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle;
    }

    public String getGcName() {
        return gcName;
    }

    public void setGcName(String gcName) {
        this.gcName = gcName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getGoodsStorePrice() {
        return goodsStorePrice;
    }

    public void setGoodsStorePrice(int goodsStorePrice) {
        this.goodsStorePrice = goodsStorePrice;
    }

    public String getGoodsStorePriceInterval() {
        return goodsStorePriceInterval;
    }

    public void setGoodsStorePriceInterval(String goodsStorePriceInterval) {
        this.goodsStorePriceInterval = goodsStorePriceInterval;
    }

    public String getGoodsSerial() {
        return goodsSerial;
    }

    public void setGoodsSerial(String goodsSerial) {
        this.goodsSerial = goodsSerial;
    }

    public int getGoodsClick() {
        return goodsClick;
    }

    public void setGoodsClick(int goodsClick) {
        this.goodsClick = goodsClick;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsBody() {
        return goodsBody;
    }

    public void setGoodsBody(String goodsBody) {
        this.goodsBody = goodsBody;
    }

    public String getGoodsImageText() {
        return goodsImageText;
    }

    public void setGoodsImageText(String goodsImageText) {
        this.goodsImageText = goodsImageText;
    }

    public Object getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(Object goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsColImg() {
        return goodsColImg;
    }

    public void setGoodsColImg(String goodsColImg) {
        this.goodsColImg = goodsColImg;
    }

    public int getKdPrice() {
        return kdPrice;
    }

    public void setKdPrice(int kdPrice) {
        this.kdPrice = kdPrice;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public int getSalenum() {
        return salenum;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public int getBuysNumber() {
        return buysNumber;
    }

    public void setBuysNumber(int buysNumber) {
        this.buysNumber = buysNumber;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public boolean isGoodsIsbuy() {
        return goodsIsbuy;
    }

    public void setGoodsIsbuy(boolean goodsIsbuy) {
        this.goodsIsbuy = goodsIsbuy;
    }

    public List<String> getGoodsImageMore() {
        return goodsImageMore;
    }

    public void setGoodsImageMore(List<String> goodsImageMore) {
        this.goodsImageMore = goodsImageMore;
    }

    public List<GoodsSpecDTOBean> getGoodsSpecDTO() {
        return goodsSpecDTO;
    }

    public void setGoodsSpecDTO(List<GoodsSpecDTOBean> goodsSpecDTO) {
        this.goodsSpecDTO = goodsSpecDTO;
    }

    public List<GoodsSpecListDTOBean> getGoodsSpecListDTO() {
        return goodsSpecListDTO;
    }

    public void setGoodsSpecListDTO(List<GoodsSpecListDTOBean> goodsSpecListDTO) {
        this.goodsSpecListDTO = goodsSpecListDTO;
    }

    public List<GoodsEvaluationDTOBean> getGoodsEvaluationDTO() {
        return goodsEvaluationDTO;
    }

    public void setGoodsEvaluationDTO(List<GoodsEvaluationDTOBean> goodsEvaluationDTO) {
        this.goodsEvaluationDTO = goodsEvaluationDTO;
    }

    public List<GoodsIssueAttributeDTOBean> getGoodsIssueAttributeDTO() {
        return goodsIssueAttributeDTO;
    }

    public void setGoodsIssueAttributeDTO(List<GoodsIssueAttributeDTOBean> goodsIssueAttributeDTO) {
        this.goodsIssueAttributeDTO = goodsIssueAttributeDTO;
    }

    public static class GoodsSpecDTOBean {
        private int specId;
        private String specName;
        private int specGoodsPrice;
        private int specGoodsStorage;
        private int specSalenum;
        private String specGoodsColor;
        private String specGoodsSpec;

        public int getSpecId() {
            return specId;
        }

        public void setSpecId(int specId) {
            this.specId = specId;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public int getSpecGoodsPrice() {
            return specGoodsPrice;
        }

        public void setSpecGoodsPrice(int specGoodsPrice) {
            this.specGoodsPrice = specGoodsPrice;
        }

        public int getSpecGoodsStorage() {
            return specGoodsStorage;
        }

        public void setSpecGoodsStorage(int specGoodsStorage) {
            this.specGoodsStorage = specGoodsStorage;
        }

        public int getSpecSalenum() {
            return specSalenum;
        }

        public void setSpecSalenum(int specSalenum) {
            this.specSalenum = specSalenum;
        }

        public String getSpecGoodsColor() {
            return specGoodsColor;
        }

        public void setSpecGoodsColor(String specGoodsColor) {
            this.specGoodsColor = specGoodsColor;
        }

        public String getSpecGoodsSpec() {
            return specGoodsSpec;
        }

        public void setSpecGoodsSpec(String specGoodsSpec) {
            this.specGoodsSpec = specGoodsSpec;
        }
    }

    public static class GoodsSpecListDTOBean {
        private int spId;
        private String spName;
        /**
         * spValueId : 16
         * spValue : M
         */

        private List<GoodsSpecValueListDTOBean> goodsSpecValueListDTO;

        public int getSpId() {
            return spId;
        }

        public void setSpId(int spId) {
            this.spId = spId;
        }

        public String getSpName() {
            return spName;
        }

        public void setSpName(String spName) {
            this.spName = spName;
        }

        public List<GoodsSpecValueListDTOBean> getGoodsSpecValueListDTO() {
            return goodsSpecValueListDTO;
        }

        public void setGoodsSpecValueListDTO(List<GoodsSpecValueListDTOBean> goodsSpecValueListDTO) {
            this.goodsSpecValueListDTO = goodsSpecValueListDTO;
        }

        public static class GoodsSpecValueListDTOBean {
            private int spValueId;
            private String spValue;

            public int getSpValueId() {
                return spValueId;
            }

            public void setSpValueId(int spValueId) {
                this.spValueId = spValueId;
            }

            public String getSpValue() {
                return spValue;
            }

            public void setSpValue(String spValue) {
                this.spValue = spValue;
            }
        }
    }

    public static class GoodsEvaluationDTOBean {
        private int id;
        private String desF;
        private String replyF;
        private int flag;
        private String time;
        private String userName;
        private String userImg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDesF() {
            return desF;
        }

        public void setDesF(String desF) {
            this.desF = desF;
        }

        public String getReplyF() {
            return replyF;
        }

        public void setReplyF(String replyF) {
            this.replyF = replyF;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }
    }

    public static class GoodsIssueAttributeDTOBean {
        private String attrName;
        private String attrValueName;

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public String getAttrValueName() {
            return attrValueName;
        }

        public void setAttrValueName(String attrValueName) {
            this.attrValueName = attrValueName;
        }
    }
}
