package com.hyl.mis.hyl.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by mis on 2016/10/18.
 */
public class BeanDetailspage {



    private int code;
    private String msg;

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private GoodsBean goods;
        private boolean collected;
        private boolean reserved;
        private int commentNumber;


        private MatchPriceRuleBean matchPriceRule;


        private List<CommentsBean> comments;


        private List<ActivityBean> activity;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public boolean isCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

        public int getCommentNumber() {
            return commentNumber;
        }

        public void setCommentNumber(int commentNumber) {
            this.commentNumber = commentNumber;
        }

        public MatchPriceRuleBean getMatchPriceRule() {
            return matchPriceRule;
        }

        public void setMatchPriceRule(MatchPriceRuleBean matchPriceRule) {
            this.matchPriceRule = matchPriceRule;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public static class GoodsBean  {
            private String id;
            private String goods_name;
            private int stock_number;
            private int collect_count;
            private double market_price;
            private double shop_price;
            private int shipping_fee;
            private String goods_desc;
            private String goods_img;
            private String is_on_sale;
            private double quality;
            private double valueformoney;
            private double desmatch;
            private int sales_volume;
            private boolean reservable;
            private String createuser;
            private String lastupdateuser;
            private int sort;
            private int restriction;
            private int restrict_purchase_num;
            private String is_activity_goods;
            private boolean is_coupon_allowed;
            private int allocated_stock;
            private String efficacy;
            private int is_gift;
            private String goods_source;
            private String redeem_goods_restrict_flag;
            private String is_allow_credit;
            private double commission_scale;
            private String goods_type;





            @Override
            public String toString() {
                return "GoodsBean{" +
                        "id='" + id + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", stock_number=" + stock_number +
                        ", collect_count=" + collect_count +
                        ", market_price=" + market_price +
                        ", shop_price=" + shop_price +
                        ", shipping_fee=" + shipping_fee +
                        ", goods_desc='" + goods_desc + '\'' +
                        ", goods_img='" + goods_img + '\'' +
                        ", is_on_sale='" + is_on_sale + '\'' +
                        ", quality=" + quality +
                        ", valueformoney=" + valueformoney +
                        ", desmatch=" + desmatch +
                        ", sales_volume=" + sales_volume +
                        ", reservable=" + reservable +
                        ", createuser='" + createuser + '\'' +
                        ", lastupdateuser='" + lastupdateuser + '\'' +
                        ", sort=" + sort +
                        ", restriction=" + restriction +
                        ", restrict_purchase_num=" + restrict_purchase_num +
                        ", is_activity_goods='" + is_activity_goods + '\'' +
                        ", is_coupon_allowed=" + is_coupon_allowed +
                        ", allocated_stock=" + allocated_stock +
                        ", efficacy='" + efficacy + '\'' +
                        ", is_gift=" + is_gift +
                        ", goods_source='" + goods_source + '\'' +
                        ", redeem_goods_restrict_flag='" + redeem_goods_restrict_flag + '\'' +
                        ", is_allow_credit='" + is_allow_credit + '\'' +
                        ", commission_scale=" + commission_scale +
                        ", goods_type='" + goods_type + '\'' +
                        ", gallery=" + gallery +
                        ", attributes=" + attributes +
                        '}';
            }



            private List<GalleryBean> gallery;


            private List<AttributesBean> attributes;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public int getStock_number() {
                return stock_number;
            }

            public void setStock_number(int stock_number) {
                this.stock_number = stock_number;
            }

            public int getCollect_count() {
                return collect_count;
            }

            public void setCollect_count(int collect_count) {
                this.collect_count = collect_count;
            }

            public double getMarket_price() {
                return market_price;
            }

            public void setMarket_price(double market_price) {
                this.market_price = market_price;
            }

            public double getShop_price() {
                return shop_price;
            }

            public void setShop_price(double shop_price) {
                this.shop_price = shop_price;
            }

            public int getShipping_fee() {
                return shipping_fee;
            }

            public void setShipping_fee(int shipping_fee) {
                this.shipping_fee = shipping_fee;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(String is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public double getQuality() {
                return quality;
            }

            public void setQuality(int quality) {
                this.quality = quality;
            }

            public double getValueformoney() {
                return valueformoney;
            }

            public void setValueformoney(int valueformoney) {
                this.valueformoney = valueformoney;
            }

            public double getDesmatch() {
                return desmatch;
            }

            public void setDesmatch(int desmatch) {
                this.desmatch = desmatch;
            }

            public int getSales_volume() {
                return sales_volume;
            }

            public void setSales_volume(int sales_volume) {
                this.sales_volume = sales_volume;
            }

            public boolean isReservable() {
                return reservable;
            }

            public void setReservable(boolean reservable) {
                this.reservable = reservable;
            }

            public String getCreateuser() {
                return createuser;
            }

            public void setCreateuser(String createuser) {
                this.createuser = createuser;
            }

            public String getLastupdateuser() {
                return lastupdateuser;
            }

            public void setLastupdateuser(String lastupdateuser) {
                this.lastupdateuser = lastupdateuser;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getRestriction() {
                return restriction;
            }

            public void setRestriction(int restriction) {
                this.restriction = restriction;
            }

            public int getRestrict_purchase_num() {
                return restrict_purchase_num;
            }

            public void setRestrict_purchase_num(int restrict_purchase_num) {
                this.restrict_purchase_num = restrict_purchase_num;
            }

            public String getIs_activity_goods() {
                return is_activity_goods;
            }

            public void setIs_activity_goods(String is_activity_goods) {
                this.is_activity_goods = is_activity_goods;
            }

            public boolean isIs_coupon_allowed() {
                return is_coupon_allowed;
            }

            public void setIs_coupon_allowed(boolean is_coupon_allowed) {
                this.is_coupon_allowed = is_coupon_allowed;
            }

            public int getAllocated_stock() {
                return allocated_stock;
            }

            public void setAllocated_stock(int allocated_stock) {
                this.allocated_stock = allocated_stock;
            }

            public String getEfficacy() {
                return efficacy;
            }

            public void setEfficacy(String efficacy) {
                this.efficacy = efficacy;
            }

            public int getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(int is_gift) {
                this.is_gift = is_gift;
            }

            public String getGoods_source() {
                return goods_source;
            }

            public void setGoods_source(String goods_source) {
                this.goods_source = goods_source;
            }

            public String getRedeem_goods_restrict_flag() {
                return redeem_goods_restrict_flag;
            }

            public void setRedeem_goods_restrict_flag(String redeem_goods_restrict_flag) {
                this.redeem_goods_restrict_flag = redeem_goods_restrict_flag;
            }

            public String getIs_allow_credit() {
                return is_allow_credit;
            }

            public void setIs_allow_credit(String is_allow_credit) {
                this.is_allow_credit = is_allow_credit;
            }

            public double getCommission_scale() {
                return commission_scale;
            }

            public void setCommission_scale(double commission_scale) {
                this.commission_scale = commission_scale;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public List<GalleryBean> getGallery() {
                return gallery;
            }

            public void setGallery(List<GalleryBean> gallery) {
                this.gallery = gallery;
            }

            public List<AttributesBean> getAttributes() {
                return attributes;
            }

            public void setAttributes(List<AttributesBean> attributes) {
                this.attributes = attributes;
            }

            public static class GalleryBean {
                @Override
                public String toString() {
                    return "GalleryBean{" +
                            "id='" + id + '\'' +
                            ", sort=" + sort +
                            ", goods_id='" + goods_id + '\'' +
                            ", normal_url='" + normal_url + '\'' +
                            ", thumb_url='" + thumb_url + '\'' +
                            ", original_url='" + original_url + '\'' +
                            ", enable=" + enable +
                            '}';
                }

                private String id;
                private int sort;
                private String goods_id;
                private String normal_url;
                private String thumb_url;
                private String original_url;
                private boolean enable;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getNormal_url() {
                    return normal_url;
                }

                public void setNormal_url(String normal_url) {
                    this.normal_url = normal_url;
                }

                public String getThumb_url() {
                    return thumb_url;
                }

                public void setThumb_url(String thumb_url) {
                    this.thumb_url = thumb_url;
                }

                public String getOriginal_url() {
                    return original_url;
                }

                public void setOriginal_url(String original_url) {
                    this.original_url = original_url;
                }

                public boolean isEnable() {
                    return enable;
                }

                public void setEnable(boolean enable) {
                    this.enable = enable;
                }
            }

            public static class AttributesBean  {
                private String id;
                private String goods_id;
                private String attr_name;
                private String attr_value;

                protected AttributesBean(Parcel in) {
                    id = in.readString();
                    goods_id = in.readString();
                    attr_name = in.readString();
                    attr_value = in.readString();
                }



                @Override
                public String toString() {
                    return "AttributesBean{" +
                            "id='" + id + '\'' +
                            ", goods_id='" + goods_id + '\'' +
                            ", attr_name='" + attr_name + '\'' +
                            ", attr_value='" + attr_value + '\'' +
                            '}';
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getAttr_name() {
                    return attr_name;
                }

                public void setAttr_name(String attr_name) {
                    this.attr_name = attr_name;
                }

                public String getAttr_value() {
                    return attr_value;
                }

                public void setAttr_value(String attr_value) {
                    this.attr_value = attr_value;
                }
            }
        }

        public static class MatchPriceRuleBean {
            private String id;
            private String createtime;
            private String title;
            private String subtitle;
            private double amount;
            private int rebate_money;
            private int restriction;
            private String begin_date;
            private String end_date;
            private int state;
            private String descript;
            private String createuser;
            private String actStatus;
            private String matchPriceRuleEnable;

            @Override
            public String toString() {
                return "MatchPriceRuleBean{" +
                        "id='" + id + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", title='" + title + '\'' +
                        ", subtitle='" + subtitle + '\'' +
                        ", amount=" + amount +
                        ", rebate_money=" + rebate_money +
                        ", restriction=" + restriction +
                        ", begin_date='" + begin_date + '\'' +
                        ", end_date='" + end_date + '\'' +
                        ", state=" + state +
                        ", descript='" + descript + '\'' +
                        ", createuser='" + createuser + '\'' +
                        ", actStatus='" + actStatus + '\'' +
                        ", matchPriceRuleEnable='" + matchPriceRuleEnable + '\'' +
                        ", goodsList=" + goodsList +
                        '}';
            }

            /**
             * id : 14
             * goods_name : 矿物泥浆鼻膜60g
             * stock_number : 0
             * collect_count : 0
             * shop_price : 55
             * goods_img : http://image.hmeili.com/yunifang/images/goods/14/goods_img/160819084841216186223194195.jpg
             * quality : 5
             * valueformoney : 5
             * desmatch : 5
             * sales_volume : 0
             * reservable : false
             * restriction : 0
             * restrict_purchase_num : 0
             * is_coupon_allowed : true
             * allocated_stock : 0
             * is_gift : 0
             * is_allow_credit : 1
             */

            private List<GoodsListBean> goodsList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getRebate_money() {
                return rebate_money;
            }

            public void setRebate_money(int rebate_money) {
                this.rebate_money = rebate_money;
            }

            public int getRestriction() {
                return restriction;
            }

            public void setRestriction(int restriction) {
                this.restriction = restriction;
            }

            public String getBegin_date() {
                return begin_date;
            }

            public void setBegin_date(String begin_date) {
                this.begin_date = begin_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public String getCreateuser() {
                return createuser;
            }

            public void setCreateuser(String createuser) {
                this.createuser = createuser;
            }

            public String getActStatus() {
                return actStatus;
            }

            public void setActStatus(String actStatus) {
                this.actStatus = actStatus;
            }

            public String getMatchPriceRuleEnable() {
                return matchPriceRuleEnable;
            }

            public void setMatchPriceRuleEnable(String matchPriceRuleEnable) {
                this.matchPriceRuleEnable = matchPriceRuleEnable;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public static class GoodsListBean {
                private String id;
                private String goods_name;
                private int stock_number;
                private int collect_count;
                private double shop_price;
                private String goods_img;
                private int quality;
                private int valueformoney;
                private int desmatch;
                private int sales_volume;
                private boolean reservable;
                private int restriction;
                private int restrict_purchase_num;
                private boolean is_coupon_allowed;
                private int allocated_stock;
                private int is_gift;
                private String is_allow_credit;

                @Override
                public String toString() {
                    return "GoodsListBean{" +
                            "id='" + id + '\'' +
                            ", goods_name='" + goods_name + '\'' +
                            ", stock_number=" + stock_number +
                            ", collect_count=" + collect_count +
                            ", shop_price=" + shop_price +
                            ", goods_img='" + goods_img + '\'' +
                            ", quality=" + quality +
                            ", valueformoney=" + valueformoney +
                            ", desmatch=" + desmatch +
                            ", sales_volume=" + sales_volume +
                            ", reservable=" + reservable +
                            ", restriction=" + restriction +
                            ", restrict_purchase_num=" + restrict_purchase_num +
                            ", is_coupon_allowed=" + is_coupon_allowed +
                            ", allocated_stock=" + allocated_stock +
                            ", is_gift=" + is_gift +
                            ", is_allow_credit='" + is_allow_credit + '\'' +
                            '}';
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public int getStock_number() {
                    return stock_number;
                }

                public void setStock_number(int stock_number) {
                    this.stock_number = stock_number;
                }

                public int getCollect_count() {
                    return collect_count;
                }

                public void setCollect_count(int collect_count) {
                    this.collect_count = collect_count;
                }

                public double getShop_price() {
                    return shop_price;
                }

                public void setShop_price(double shop_price) {
                    this.shop_price = shop_price;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public int getQuality() {
                    return quality;
                }

                public void setQuality(int quality) {
                    this.quality = quality;
                }

                public int getValueformoney() {
                    return valueformoney;
                }

                public void setValueformoney(int valueformoney) {
                    this.valueformoney = valueformoney;
                }

                public int getDesmatch() {
                    return desmatch;
                }

                public void setDesmatch(int desmatch) {
                    this.desmatch = desmatch;
                }

                public int getSales_volume() {
                    return sales_volume;
                }

                public void setSales_volume(int sales_volume) {
                    this.sales_volume = sales_volume;
                }

                public boolean isReservable() {
                    return reservable;
                }

                public void setReservable(boolean reservable) {
                    this.reservable = reservable;
                }

                public int getRestriction() {
                    return restriction;
                }

                public void setRestriction(int restriction) {
                    this.restriction = restriction;
                }

                public int getRestrict_purchase_num() {
                    return restrict_purchase_num;
                }

                public void setRestrict_purchase_num(int restrict_purchase_num) {
                    this.restrict_purchase_num = restrict_purchase_num;
                }

                public boolean isIs_coupon_allowed() {
                    return is_coupon_allowed;
                }

                public void setIs_coupon_allowed(boolean is_coupon_allowed) {
                    this.is_coupon_allowed = is_coupon_allowed;
                }

                public int getAllocated_stock() {
                    return allocated_stock;
                }

                public void setAllocated_stock(int allocated_stock) {
                    this.allocated_stock = allocated_stock;
                }

                public int getIs_gift() {
                    return is_gift;
                }

                public void setIs_gift(int is_gift) {
                    this.is_gift = is_gift;
                }

                public String getIs_allow_credit() {
                    return is_allow_credit;
                }

                public void setIs_allow_credit(String is_allow_credit) {
                    this.is_allow_credit = is_allow_credit;
                }
            }
        }

        public static class CommentsBean {
            private String id;
            private String createtime;
            private String content;
            /**
             * id : 3408313
             * nick_name : ynf_9584
             * icon : http://image.hmeili.com/yunifang/web/assets/images/app/no-icon-2.png
             */

            private UserBean user;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                private String id;
                private String nick_name;
                private String icon;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNick_name() {
                    return nick_name;
                }

                public void setNick_name(String nick_name) {
                    this.nick_name = nick_name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }

        public static class ActivityBean {
            private String id;
            private String title;
            private String description;

            @Override
            public String toString() {
                return "ActivityBean{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
