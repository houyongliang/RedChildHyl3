package com.hhzmy.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.base.BaseViewHolder;
import com.hhzmy.bean.ShopBean;
import com.hhzmy.mis.redchildhyl.R;

import butterknife.BindView;

import static com.baidu.location.b.g.R;
import static com.baidu.location.b.g.S;

/**
 * Created by mis on 2016/11/20.
 */

public class ViewHolderShop extends BaseViewHolder {
    @BindView(com.hhzmy.mis.redchildhyl.R.id.cbox_checked)
    CheckBox cboxChecked;
    @BindView(com.hhzmy.mis.redchildhyl.R.id.shop_goodimg)
    ImageView shopGoodimg;
    @BindView(com.hhzmy.mis.redchildhyl.R.id.shop_goodname)
    TextView shopGoodname;
    @BindView(com.hhzmy.mis.redchildhyl.R.id.shop_price)
    TextView shopPrice;
    @BindView(com.hhzmy.mis.redchildhyl.R.id.shop_count)
    TextView shopCount;
    public ViewHolderShop(View itemView, Context context) {
        super(itemView, context);
//        itemView.findViewById(R.id.);

    }


    public void initData(Object data, int index) {
        super.initData(data, index);
        ShopBean sb= (ShopBean)data;

        shopGoodname.setText( sb.goodName);

    }
}
