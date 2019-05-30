package com.amansingh.foxfire.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amansingh.foxfire.APIS.APIClient;
import com.amansingh.foxfire.APIS.APIInterface;
import com.amansingh.foxfire.APIS.MasterData.MasterData;
import com.amansingh.foxfire.Models.Utils;
import com.amansingh.foxfire.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomePageActivity extends AppCompatActivity {

    private final String TAG = "WelcomeActivity";
    @BindView(R.id.userNameLayout)
    TextInputLayout userNameLayout;
    @BindView(R.id.userPassLayout)
    TextInputLayout userPassLayout;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String mid = "", pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page_);
        ButterKnife.bind(this);

        pref = getApplicationContext().getSharedPreferences("MyPrefMaster", MODE_PRIVATE);
        checkLogin();
    }

    private void checkLogin() {
        boolean userFirstLogin = pref.getBoolean("login", false);  // getting boolean
        if (userFirstLogin) {
            Log.e(TAG, "checkLogin: please Wait!!");
            userPassLayout.setEnabled(false);
            userNameLayout.setEnabled(false);
            //github.com/iRahulGaur
            Utils.setIntent(WelcomePageActivity.this, HomeActivity.class);
        } else {
            userPassLayout.setEnabled(true);
            userNameLayout.setEnabled(true);
        }
        Log.e(TAG, "checkLogin: login found in pref " + userFirstLogin);
    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {
        Utils.showMessage(WelcomePageActivity.this, "Please Wait....");
        if (checkFields()) {
            apiCall();
        } else {
            Utils.showMessage(WelcomePageActivity.this, "Please fill both fields.");
        }
    }

    private void apiCall() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<MasterData> call = apiInterface.getMasterData(Integer.parseInt(mid));
        call.enqueue(new Callback<MasterData>() {
            @Override
            public void onResponse(@NonNull Call<MasterData> call, @NonNull Response<MasterData> response) {
                MasterData data = response.body();
                String password = Objects.requireNonNull(data).master_password;
                String master_id = data.master_id + "";
                String user_id = data.user_id + "";

                Log.e(TAG, "onResponse: user_id " + user_id + " master_id " + master_id + " password " + password);

                if (pass.equals(password)) {

                    editor = pref.edit();
                    editor.putBoolean("login", true);
                    editor.putString("master", master_id);
                    editor.putString("user", user_id);
                    editor.apply();
                    Utils.setIntentNoBackLog(WelcomePageActivity.this, HomeActivity.class);
                } else
                    Utils.showMessage(WelcomePageActivity.this, "Password not correct");
            }

            @Override
            public void onFailure(@NonNull Call<MasterData> call, @NonNull Throwable t) {
                Utils.showLog(TAG, "exception ", t.getMessage());
                Utils.showMessage(WelcomePageActivity.this, "Details did not match with server");
            }
        });
    }

    private boolean checkFields() {
        mid = Objects.requireNonNull(userNameLayout.getEditText()).getText().toString();
        pass = Objects.requireNonNull(userPassLayout.getEditText()).getText().toString();
        return !mid.isEmpty() || !pass.isEmpty();
    }
}
