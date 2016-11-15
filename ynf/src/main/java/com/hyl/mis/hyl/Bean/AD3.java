package com.hyl.mis.hyl.Bean;

/**
 * Created by mis on 2016/10/10.
 */
public class AD3 {
    /**
     *  "id": "156",
     "prize_name": "第86期「iPad mini 4」",
     "lottery_img": "http://image.hmeili.com/yunifang/images/lottery/156/lottery_img/161008223059616988404599628.png",
     "start_seconds": "-56286",
     "end_seconds": "440514",
     "status": "1"

     */
    public String id;
    public String prize_name;
    public String lottery_img;
    public String start_seconds;
    public String end_seconds;
    public String status;

    @Override
    public String toString() {
        return "AD3{" +
                "id='" + id + '\'' +
                ", prize_name='" + prize_name + '\'' +
                ", lottery_img='" + lottery_img + '\'' +
                ", start_seconds='" + start_seconds + '\'' +
                ", end_seconds='" + end_seconds + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
