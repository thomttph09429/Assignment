package com.example.thomttph09429;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thomttph09429.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialActivity extends AppCompatActivity {
//    private CallbackManager callbackManager;
//    private LoginButton loginButton;
//    LoginManager loginManager;
//    private static final String EMAIL = "email";
//    AppEventsLogger logger;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_social);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        callbackManager = CallbackManager.Factory.create();
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        loginButton.setReadPermissions(EMAIL);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Toast.makeText(SocialActivity.this,"LOGIN THANH CONG",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
//        loginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                    }
//                });
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//                Log.i("TAG", "printHashKey() Hash Key: " + hashKey);
//            }
////        } catch (NoSuchAlgorithmException e) {
////            Log.e("TAG", "printHashKey()", e);
////        } catch (Exception e) {
////            Log.e("TAG", "printHashKey()", e);
////        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }

ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Button btndangxuat,btnchucnang;
    TextView tvname,tvemail;
    CallbackManager callbackManager;
    String email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setTitle("xã hội");
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_social);

        profilePictureView = findViewById(R.id.imageprofilesocial);
        loginButton = findViewById(R.id.login_button);
        btndangxuat = findViewById(R.id.btndangxuatsocial);
        btnchucnang = findViewById(R.id.btnchucnangfbsocial);
        tvname = findViewById(R.id.tvnamesocial);
        tvemail = findViewById(R.id.tvemailsocial);

        btnchucnang.setVisibility(View.INVISIBLE);
        btndangxuat.setVisibility(View.INVISIBLE);
        tvname.setVisibility(View.INVISIBLE);
        tvemail.setVisibility(View.INVISIBLE);

        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_Button();
        setLogout_Button();
        chuyenmanhinh();

    }

    private void chuyenmanhinh() {
        btnchucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialActivity.this, ChucNangFBActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setLogout_Button() {
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btndangxuat.setVisibility(View.INVISIBLE);
                btnchucnang.setVisibility(View.INVISIBLE);
                tvname.setVisibility(View.INVISIBLE);
                tvemail.setVisibility(View.INVISIBLE);
                tvname.setText("");
                tvemail.setText("");
                profilePictureView.setProfileId(null);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setLogin_Button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setVisibility(View.INVISIBLE);
                btnchucnang.setVisibility(View.VISIBLE);
                btndangxuat.setVisibility(View.VISIBLE);
                tvname.setVisibility(View.VISIBLE);
                tvemail.setVisibility(View.VISIBLE);

                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");

                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());

                    tvemail.setText(email);
                    tvname.setText(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "name,email");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}