package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.main.tab.fragment.adapter.InboxAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.NotificationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 */
public class NotificationActivity extends BaseActivity {
    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.iv_notify)
    ImageView ivNotify;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.action_bar)
    LinearLayout actionBar;
    @BindView(R.id.toprecycle)
    SwipeRecyclerView toprecycle;
    @BindView(R.id.bottomrecycle)
    SwipeRecyclerView bottomrecycle;
    private NotificationAdapter adapter;
    ActionBar bar;
    @Override
    protected int bindLayoutID() {
        return R.layout.activity_notification_center;
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
        bar.showcenter();

        toprecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NotificationAdapter();
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
//                Intent intent = new Intent(NotificationActivity.this, ChatsActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
        toprecycle.setAdapter(adapter);
        bottomrecycle.setAdapter(adapter);
    }


}
