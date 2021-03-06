package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/18.
 */
public class RechareActivity extends BaseActivity {
    ActionBar bar;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void prepareActivity() {
        bar = ActionBar.init(this);
        bar.setLeft(R.mipmap.fh, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @OnClick(R.id.btn_recharge)
    public void onClick() {
        startActivity(new Intent(this,PaymentActivity.class));
    }
}
