package com.linxiangqu.shop.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linxiangqu on 2016/9/20.
 */
public class GetMallBbsReplyList implements Serializable {

    /**
     * stateCode : 0
     * msg : 获取成功
     * bbsId : 30
     * userBbsImg : /XRMall/user/user_img/201609/2009390553j7.jpg
     * userBbsName : fox
     * title : fff
     * content : cvf
     * createTime : 2016-09-14 20:42:43
     * isReplied : false
     * isAnonymity : false
     * bbsImg : [{"imgPath":"/XRMall/u/cms//BBS/img/201607/19151051gkn0.jpg"},{"imgPath":"/XRMall/u/cms//BBS/img/201607/191510510cyf.jpg"}]
     * mallBbsReplyDTOList : [{"replyId":29,"replyContent":"测试","repleTime":"2016-09-18 22:07:08","isReplied":false,"isAnonymity":false,"userName":"customer03211649","userImg":"/XRMall/user/user_img/201609/2009390553j7.jpg","userId":34,"mallBbsSecondReplyDTO":[{"replyId":30,"replyContent":"测试","repleTime":"2016-09-18 22:07:27","isReplied":false,"isAnonymity":false,"userName":"customer03211649","userImg":"/XRMall/user/user_img/201609/2009390553j7.jpg","userId":34,"parentId":29},{"replyId":31,"replyContent":"还是测试\n","repleTime":"2016-09-18 22:08:08","isReplied":false,"isAnonymity":false,"userName":"15706844133","userImg":"/XRMall/user/user_img/201608/2314210846ox.jpg","userId":108,"parentId":29}]},{"replyId":36,"replyContent":"adfasdfasdf","repleTime":"2016-09-20 10:57:16","isReplied":false,"isAnonymity":null,"userName":"salesMan03211647","userImg":"/XRMall/user/user_img/201607/15144232yfwe.jpg","userId":33,"mallBbsSecondReplyDTO":null}]
     */

    private int stateCode;
    private String msg;
    private int bbsId;
    private String userBbsImg;
    private String userBbsName;
    private String title;
    private String content;
    private String createTime;
    private boolean isReplied;
    private boolean isAnonymity;
    /**
     * imgPath : /XRMall/u/cms//BBS/img/201607/19151051gkn0.jpg
     */

    private List<BbsImgBean> bbsImg;
    /**
     * replyId : 29
     * replyContent : 测试
     * repleTime : 2016-09-18 22:07:08
     * isReplied : false
     * isAnonymity : false
     * userName : customer03211649
     * userImg : /XRMall/user/user_img/201609/2009390553j7.jpg
     * userId : 34
     * mallBbsSecondReplyDTO : [{"replyId":30,"replyContent":"测试","repleTime":"2016-09-18 22:07:27","isReplied":false,"isAnonymity":false,"userName":"customer03211649","userImg":"/XRMall/user/user_img/201609/2009390553j7.jpg","userId":34,"parentId":29},{"replyId":31,"replyContent":"还是测试\n","repleTime":"2016-09-18 22:08:08","isReplied":false,"isAnonymity":false,"userName":"15706844133","userImg":"/XRMall/user/user_img/201608/2314210846ox.jpg","userId":108,"parentId":29}]
     */

    private List<MallBbsReplyDTOListBean> mallBbsReplyDTOList;

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

    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public String getUserBbsImg() {
        return userBbsImg;
    }

    public void setUserBbsImg(String userBbsImg) {
        this.userBbsImg = userBbsImg;
    }

    public String getUserBbsName() {
        return userBbsName;
    }

    public void setUserBbsName(String userBbsName) {
        this.userBbsName = userBbsName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isIsReplied() {
        return isReplied;
    }

    public void setIsReplied(boolean isReplied) {
        this.isReplied = isReplied;
    }

    public boolean isIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(boolean isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public List<BbsImgBean> getBbsImg() {
        return bbsImg;
    }

    public void setBbsImg(List<BbsImgBean> bbsImg) {
        this.bbsImg = bbsImg;
    }

    public List<MallBbsReplyDTOListBean> getMallBbsReplyDTOList() {
        return mallBbsReplyDTOList;
    }

    public void setMallBbsReplyDTOList(List<MallBbsReplyDTOListBean> mallBbsReplyDTOList) {
        this.mallBbsReplyDTOList = mallBbsReplyDTOList;
    }

    public static class     BbsImgBean {
        private String imgPath;

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }
    }

    public static class MallBbsReplyDTOListBean {
        private int replyId;
        private String replyContent;
        private String repleTime;
        private boolean isReplied;
        private boolean isAnonymity;
        private String userName;
        private String userImg;
        private int userId;
        /**
         * replyId : 30
         * replyContent : 测试
         * repleTime : 2016-09-18 22:07:27
         * isReplied : false
         * isAnonymity : false
         * userName : customer03211649
         * userImg : /XRMall/user/user_img/201609/2009390553j7.jpg
         * userId : 34
         * parentId : 29
         */

        private List<MallBbsSecondReplyDTOBean> mallBbsSecondReplyDTO;

        public int getReplyId() {
            return replyId;
        }

        public void setReplyId(int replyId) {
            this.replyId = replyId;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public String getRepleTime() {
            return repleTime;
        }

        public void setRepleTime(String repleTime) {
            this.repleTime = repleTime;
        }

        public boolean isIsReplied() {
            return isReplied;
        }

        public void setIsReplied(boolean isReplied) {
            this.isReplied = isReplied;
        }

        public boolean isIsAnonymity() {
            return isAnonymity;
        }

        public void setIsAnonymity(boolean isAnonymity) {
            this.isAnonymity = isAnonymity;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<MallBbsSecondReplyDTOBean> getMallBbsSecondReplyDTO() {
            return mallBbsSecondReplyDTO;
        }

        public void setMallBbsSecondReplyDTO(List<MallBbsSecondReplyDTOBean> mallBbsSecondReplyDTO) {
            this.mallBbsSecondReplyDTO = mallBbsSecondReplyDTO;
        }

        public static class MallBbsSecondReplyDTOBean {
            private int replyId;
            private String replyContent;
            private String repleTime;
            private boolean isReplied;
            private boolean isAnonymity;
            private String userName;
            private String userImg;
            private int userId;
            private int parentId;

            public int getReplyId() {
                return replyId;
            }

            public void setReplyId(int replyId) {
                this.replyId = replyId;
            }

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }

            public String getRepleTime() {
                return repleTime;
            }

            public void setRepleTime(String repleTime) {
                this.repleTime = repleTime;
            }

            public boolean isIsReplied() {
                return isReplied;
            }

            public void setIsReplied(boolean isReplied) {
                this.isReplied = isReplied;
            }

            public boolean isIsAnonymity() {
                return isAnonymity;
            }

            public void setIsAnonymity(boolean isAnonymity) {
                this.isAnonymity = isAnonymity;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
