package com.ltt.overseas.main.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lin.widget.SwipeRecyclerView;
import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.BaseFragment;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.activity.ChatsActivity;
import com.ltt.overseas.main.tab.fragment.activity.NotificationActivity;
import com.ltt.overseas.main.tab.fragment.adapter.InboxAdapter;
import com.ltt.overseas.model.MessageListBean;
import com.ltt.overseas.utils.L;
import com.ltt.overseas.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/18.
 */
public class InboxFragment extends BaseFragment {
    @BindView(R.id.action_bar)
    View actionBar;
    @BindView(R.id.container)
    SwipeRecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    ActionBar bar;
    private InboxAdapter adapter;
    private List<MessageListBean.DataBean> mMessageLists = new ArrayList<>();

    //Existing chat personnel information.
    private List<String> chatUserName = new ArrayList<>();
    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_inbox;
    }

    @Override
    protected void prepareFragment() {
        bar = ActionBar.init(actionBar);
        bar.setTitle("My Message");
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bar.showNotify();
        setRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new InboxAdapter(mMessageLists);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                MessageListBean.DataBean dataBean = mMessageLists.get(position);
                Intent intent = new Intent(getActivity(), ChatsActivity.class);
                intent.putExtra("username", dataBean.getUser());
                intent.putExtra("request_category", dataBean.getRequest_category());
                intent.putExtra("conversation_id", dataBean.getConversation_id());
                intent.putExtra("request_id", dataBean.getRequest_id());
                intent.putExtra("date_created", dataBean.getDate_created());
                startActivity(intent);
            }
        });
        initData();
    }

    /**ˢ�½�����Ϣ*/
    private void setRefresh() {
        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);

        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        ToastUtils.showToast("Refresh the data");

                        refreshLayout.setRefreshing(false);
                    }
                }, 1200);
            }
        });
    }


    protected void initData() {
        Call<MessageListBean> messageLists = RetrofitUtil.getAPIService().getMessageLists(1);
        messageLists.enqueue(new CustomerCallBack<MessageListBean>() {
            @Override
            public void onResponseResult(MessageListBean messageListBean) {
                L.e(TAG + "---" + messageListBean.getTotal_message() + "--" + messageListBean.getCode());
                List<MessageListBean.DataBean> data = messageListBean.getData();
                if (data == null) {
                    adapter.notifyDataSetChanged();
                    ToastUtils.showToast("No Data");
                } else {
                    mMessageLists.addAll(data);
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < data.size(); i++) {
                        chatUserName.add(data.get(i).getUser());
                    }
                }
            }
            @Override
            public void onResponseError(BaseBean errorMessage, boolean isNetError) {

            }
        });
    }

    @OnClick({R.id.iv_notify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
        }
    }
}