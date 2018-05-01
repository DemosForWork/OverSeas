package com.ltt.overseas.main.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseFragment;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.main.tab.fragment.activity.NotificationActivity;
import com.ltt.overseas.main.tab.fragment.activity.TaskDetailActivity;
import com.ltt.overseas.main.tab.fragment.adapter.MyResponseAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.MyTaskAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/18.
 */
public class TaskFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.container)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.id_lv)
    ListView listview;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.action_bar)
    View actionBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitle;
    private MyResponseAdapter myResponseAdapter;
//    private TaskFinishedAdapter finishedAdapter;
//    private TaskUnlockedAdapter taskUnlockedadapter;
    private MyTaskAdapter myTaskAdapter;
    ActionBar bar;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_task;
    }

    @Override
    protected void prepareFragment() {
        bar = ActionBar.init(actionBar);
        bar.showNotify();
        bar.setTitle("My Requests");
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myTaskAdapter = new MyTaskAdapter();
        recyclerView.setAdapter(myTaskAdapter);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_text, getData());
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawers();
                tvTitle.setText(list.get(i));
                changeUi(i);
            }
        });
        myTaskAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                startActivity(new Intent(getActivity(), TaskDetailActivity.class));
            }
        });
    }

    private void changeUi(int i) {
        switch (i) {
            case 0:
                myResponseAdapter = new MyResponseAdapter();
                recyclerView.setAdapter(myResponseAdapter);
                break;
            case 1:
                myTaskAdapter = new MyTaskAdapter();
                recyclerView.setAdapter(myTaskAdapter);
                break;
//            case 2:
//                taskUnlockedadapter = new TaskUnlockedAdapter();
//                recyclerView.setAdapter(taskUnlockedadapter);
//                break;
//            case 3:
//                finishedAdapter = new TaskFinishedAdapter();
//                recyclerView.setAdapter(finishedAdapter);
//                break;
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
    }


    @OnClick({R.id.iv_menu, R.id.iv_left, R.id.iv_notify})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(getActivity(),NotificationActivity.class));
                break;
            case R.id.iv_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_left:
                drawerLayout.closeDrawers();
                break;
        }

    }

    private ArrayList<String> getData() {
//        list.add("All My Task");
//        list.add("Pin");
        list.add("My Response");
        list.add("My Task");
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
