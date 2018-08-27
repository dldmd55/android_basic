package com.example.dldmd.globalsns.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.dldmd.globalsns.R;
import com.example.dldmd.globalsns.pid.PidList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText editId;
    EditText editPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lay);

        editId= (EditText)findViewById(R.id.editId);
        editPwd= (EditText)findViewById(R.id.editPwd);

        Button loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(editId.getText().toString());

                if(editId.getText().toString()==null||editPwd.getText().toString()==null||("").equals(editId.getText().toString())||("").equals(editPwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"빈칸이 존재합니다.",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sf = getSharedPreferences("USER_KEY",MODE_PRIVATE);

                    if(sf.getString(editId.getText().toString(),"")==null||sf.getString(editId.getText().toString(),"").equals(null)){
                        Toast.makeText(getApplicationContext(),"ID가 없습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        String loginCheck = sf.getString(editId.getText().toString(),"");

                        StringBuffer sb;
                        JSONArray jsonArray;

                        {
                            try {
                                sb = new StringBuffer();
                                jsonArray = new JSONArray(loginCheck);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String userPwd = jsonObject.getString("userPwd");

                                    sb.append(userPwd);
                                }
                                if(editPwd.getText().toString().equals(sb.toString())){
                                    Intent intent = new Intent(getApplicationContext(), PidList.class);
                                    intent.putExtra("userId",editId.getText().toString());
                                    //세션값 저장
                                    SharedPreferences saveSf = getSharedPreferences("USER_SESSION",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = saveSf.edit();
                                    editor.putString("SESSION_ID",editId.getText().toString());
                                    editor.commit();
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"ID와 PASSWORD가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                                    System.out.println("틀림");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        });

        Button signUpBtn = (Button)findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
