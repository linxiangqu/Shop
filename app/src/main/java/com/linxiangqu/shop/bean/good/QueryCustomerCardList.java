package com.linxiangqu.shop.bean.good;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/14.
 */
public class QueryCustomerCardList implements Serializable {

    /**
     * stateCode : 0
     * msg : 成功
     * mallCustomerCardDTOs : [{"cardId":50,"cashOutType":1,"cardNo":"5369","cardName":"苏杰","cardCompany":"深圳发展银行","isUse":false},{"cardId":54,"cashOutType":2,"cardNo":"njj****om","cardName":"mmkk","cardCompany":null,"isUse":false},{"cardId":102,"cashOutType":2,"cardNo":"755****ht","cardName":"不知道","cardCompany":null,"isUse":false},{"cardId":103,"cashOutType":2,"cardNo":"arg****58","cardName":"十多个","cardCompany":null,"isUse":false},{"cardId":104,"cashOutType":1,"cardNo":"5123","cardName":"不咋地","cardCompany":"中国建设银行","isUse":false},{"cardId":105,"cashOutType":1,"cardNo":"4568","cardName":"肝硬化","cardCompany":"中国农业银行","isUse":false},{"cardId":106,"cashOutType":2,"cardNo":"hjh****88","cardName":"hello","cardCompany":null,"isUse":false},{"cardId":107,"cashOutType":2,"cardNo":"****","cardName":"chdf","cardCompany":null,"isUse":false},{"cardId":108,"cashOutType":2,"cardNo":"****","cardName":"个","cardCompany":null,"isUse":false},{"cardId":109,"cashOutType":2,"cardNo":"****","cardName":"","cardCompany":null,"isUse":false},{"cardId":110,"cashOutType":2,"cardNo":"che****en","cardName":"chen","cardCompany":null,"isUse":false},{"cardId":111,"cashOutType":2,"cardNo":"che****en","cardName":"chenchenchen","cardCompany":null,"isUse":false},{"cardId":112,"cashOutType":2,"cardNo":"****","cardName":"asd","cardCompany":null,"isUse":false},{"cardId":113,"cashOutType":2,"cardNo":"wow****wo","cardName":"wowowo","cardCompany":null,"isUse":false},{"cardId":114,"cashOutType":2,"cardNo":"qqq****qq","cardName":"qqqqqqqqq","cardCompany":null,"isUse":false},{"cardId":115,"cashOutType":2,"cardNo":"jkk****kk","cardName":"fgggggh","cardCompany":null,"isUse":false},{"cardId":116,"cashOutType":2,"cardNo":"304****57","cardName":"许贺","cardCompany":"上海浦东发展银行","isUse":false},{"cardId":117,"cashOutType":2,"cardNo":"337****76","cardName":"斯柯达就","cardCompany":"中国银行","isUse":false},{"cardId":118,"cashOutType":1,"cardNo":"6786","cardName":"成","cardCompany":"招商银行","isUse":false}]
     */

    private int stateCode;
    private String msg;
    /**
     * cardId : 50
     * cashOutType : 1
     * cardNo : 5369
     * cardName : 苏杰
     * cardCompany : 深圳发展银行
     * isUse : false
     */

    private List<MallCustomerCardDTOsBean> mallCustomerCardDTOs;

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

    public List<MallCustomerCardDTOsBean> getMallCustomerCardDTOs() {
        return mallCustomerCardDTOs;
    }

    public void setMallCustomerCardDTOs(List<MallCustomerCardDTOsBean> mallCustomerCardDTOs) {
        this.mallCustomerCardDTOs = mallCustomerCardDTOs;
    }

    public static class MallCustomerCardDTOsBean {
        private int cardId;
        private int cashOutType;
        private String cardNo;
        private String cardName;
        private String cardCompany;
        private boolean isUse;

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public int getCashOutType() {
            return cashOutType;
        }

        public void setCashOutType(int cashOutType) {
            this.cashOutType = cashOutType;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardCompany() {
            return cardCompany;
        }

        public void setCardCompany(String cardCompany) {
            this.cardCompany = cardCompany;
        }

        public boolean isIsUse() {
            return isUse;
        }

        public void setIsUse(boolean isUse) {
            this.isUse = isUse;
        }
    }
}
