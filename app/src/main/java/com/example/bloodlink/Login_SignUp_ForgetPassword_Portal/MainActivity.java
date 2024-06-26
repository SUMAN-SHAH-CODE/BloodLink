package com.example.bloodlink.Login_SignUp_ForgetPassword_Portal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodlink.R;
import com.example.bloodlink.dashboard.dashboard;
import com.example.bloodlink.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Ensure textView5 retains its initial text
        binding.titleTextView.setText("BloodLink");
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });

//        binding.emailEditText.setLongClickable(false);
//        binding.emailEditText.setTextIsSelectable(false);
        emailFocusListener();
        passwordFocusListener();
//        binding.textView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intend = new Intent(MainActivity.this, forgetPassword.class);
//                startActivity(intend);
//            }
//        });
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, dashboard.class);
//                startActivity(intent);
//            }
//        });
    }


    private void emailFocusListener() {
        binding.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {


                String result = validEmail();
                if (result != null) {
                    binding.emailContainer.setHelperText(result);

                } else {
                    binding.emailContainer.setHelperText("");
                   // Clear error text if email is valid
                }
            }
        });
    }

    private String validEmail() {
        String emailText = binding.emailEditText.getText().toString().trim();
        if (emailText.isEmpty()) {
            return "Email cannot be empty";
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address";
        }
        return null; // Return null if email is valid
    }
    private void passwordFocusListener() {
        binding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = validPassword();
                if (result != null) {
                    binding.passwordContainer.setHelperText(result);

                } else {
                    binding.passwordContainer.setHelperText("");
                    // Clear error text if email is valid
                }
            }
        });
    }

    private String validPassword() {
        String passwordText = binding.passwordEditText.getText().toString().trim();
      if(passwordText.length()<8){
          return "Minimum 8 Character Password";
      }
      if(!passwordText.matches(".*[A-Z].*")){
          return "Must Contain 1 Upper-case Character ";
      }
        if(!passwordText.matches(".*[a-z].*")){
            return "Must Contain 1 Lower-case Character ";
        }
        if(!passwordText.matches(".*[@#/$^&=+].*")){
            return "Must Contain 1 Special Character [@#/$^&=+] ";
        }
        return null; // Return null if email is valid
    }
    private  void login() {

        final String[] Token = new String[1];

        String url = "http://192.168.1.69:8085/api/v1/user/login";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", (binding.emailEditText.getText().toString()));
            jsonRequest.put("password", binding.passwordEditText.getText().toString());
        } catch (JSONException e) {
           Log.d("JsonException",e.toString());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Token[0] = response.getString("accessToken");
                    Intent intent=new Intent(MainActivity.this, dashboard.class);
                    Log.d("mainToken","hello"+ Token[0]);  // This Token has null value but why??
                    intent.putExtra("Token", Token[0]);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    Log.v("LoginResponse",response.toString());

                } catch (JSONException e) {
                   Log.d("ResponseError",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                Log.d("volleyError", error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);



    }

}