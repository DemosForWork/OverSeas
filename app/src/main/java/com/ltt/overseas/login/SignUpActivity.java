package com.ltt.overseas.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ltt.overseas.MainActivity;
import com.ltt.overseas.R;
import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.base.Constants;
import com.ltt.overseas.base.RecyclerAdapter;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.login.adapter.PhoneListAdapter;
import com.ltt.overseas.main.tab.fragment.adapter.NotificationAdapter;
import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.PhoneBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.utils.PreferencesUtils;
import com.ltt.overseas.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/1/17.
 */
public class SignUpActivity extends BaseActivity {
    ActionBar bar;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_area_code)
    TextView tvAreaCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_referral_code)
    EditText etReferralCode;

    private String mFirstName, mLastName, mEmail, mPwd, mAreaCode, mPhone, mReferralCode;

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_sign_up;
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
        bar.setTitle("SIGN UP");
    }

    @OnClick({R.id.btn_done})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
            //case R.id.tv_area_code:
              //  getPhoneList();
              //  break;
            case R.id.iv_google_signup:
                ToastUtils.showToast("google+ signup");
                break;
            case R.id.iv_fb_signup:
                ToastUtils.showToast("facebook signup");
                break;*/
            case R.id.btn_done:
//                goHomePage();
                if(judgeInput()){
                    signup();
                }
                break;

        }
    }

    private void signup(){
        showLoadingView();
        UserBean signParam = new UserBean();
        signParam.setEmail(mEmail);
        signParam.setPassword(mPwd);
        signParam.setCountry_id(mAreaCode);
        signParam.setFirstname(mFirstName);
        signParam.setLastname(mLastName);
        signParam.setPhone(mPhone);
        Call<GsonUserBean> loginCall = RetrofitUtil.getAPIService().register(signParam);
        loginCall.enqueue(new CustomerCallBack<GsonUserBean>() {
            @Override
            public void onResponseResult(GsonUserBean response) {
                Log.d("register:",""+response.getMsg());
                ToastUtils.showToast(response.getMsg());
                dismissLoadingView();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private void goHomePage(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.putExtra(Constants.FROM_REGISTER, true);
        startActivity(intent);
        finish();
    }

    private void getProfile(){
        showLoadingView();
        Call<GsonUserBean> call = RetrofitUtil.getAPIService().getProfile();
        call.enqueue(new CustomerCallBack<GsonUserBean>() {
            @Override
            public void onResponseResult(GsonUserBean response) {
                dismissLoadingView();
                goHomePage();
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private boolean judgeInput(){
        boolean canSignUp = true;
        mFirstName = etFirstName.getText().toString();
        mLastName = etLastName.getText().toString();
        mEmail = etEmail.getText().toString();
        mPwd = etPassword.getText().toString();
        mAreaCode = tvAreaCode.getText().toString();
        mPhone = etPhone.getText().toString();
        mReferralCode = etReferralCode.getText().toString();

        if (mFirstName.trim().isEmpty()){
            ToastUtils.showToast("FirstName Can Not Be Empty");
            canSignUp = false;
        }
        if (mLastName.trim().isEmpty()){
            ToastUtils.showToast("LastName Can Not Be Empty");
            canSignUp = false;
        }
        if (mEmail.trim().isEmpty()){
            ToastUtils.showToast("Email Can Not Be Empty");
            canSignUp = false;
        }
        if (mPwd.trim().isEmpty()){
            ToastUtils.showToast("Password Can Not Be Empty");
            canSignUp = false;
        }
        if (mAreaCode.trim().isEmpty()){
            ToastUtils.showToast("AreaCode Can Not Be Empty");
            canSignUp = false;
        }
        if (mPhone.trim().isEmpty()){
            ToastUtils.showToast("Phone Can Not Be Empty");
            canSignUp = false;
        }
        return canSignUp;
    }

    private void getPhoneList(){
        showLoadingView();
        Call<PhoneListBean> call = RetrofitUtil.getAPIService().getCountryIds();
        call.enqueue(new CustomerCallBack<PhoneListBean>() {
            @Override
            public void onResponseResult(PhoneListBean response) {
                dismissLoadingView();
                showPhoneListDialog(response);
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private void showPhoneListDialog(PhoneListBean response){
        final Dialog shareDialog = new Dialog(this, R.style.BottomDialogStyle);
        View shareView = getLayoutInflater().inflate(R.layout.layout_dialog_list, null);
        RecyclerView rvDialogList = (RecyclerView) shareView.findViewById(R.id.rvDialogList);
        rvDialogList.setLayoutManager(new LinearLayoutManager(getContext()));
        PhoneListAdapter adapter = new PhoneListAdapter();
        adapter.addAll(response.getData());
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, View view, int position) {
                shareDialog.dismiss();
                String text = ((PhoneBean) object).getText();
                tvAreaCode.setText(text.substring(text.indexOf("(") + 1, text.indexOf(")")));
            }
        });
        rvDialogList.setAdapter(adapter);

        shareDialog.setContentView(shareView);
//        shareDialog.getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = shareDialog.getWindow().getAttributes();
        lp.width = XApplication.SCREEN_WIDTH/3*2;
        shareDialog.getWindow().setAttributes(lp);
        shareDialog.show();//显示对话框
    }
}
