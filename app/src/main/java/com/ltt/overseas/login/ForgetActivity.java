package com.ltt.overseas.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.core.ActionBar;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/17.
 */
public class ForgetActivity extends BaseActivity {
    ActionBar bar;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_foget_password;
    }

    @Override
    protected void prepareActivity() {
        bar = ActionBar.init(this);
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bar.setTitle("FORGET PASSWORD");
    }

    @OnClick(R.id.btn_forget)
    public void onClick() {
        startActivity(new Intent(this,EmailSendActivity.class));
        finish();
    }
}
