package com.example.project04.Login_Register_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.project04.R;
import com.example.project04.dao.DaoTaiKhoan;
import com.example.project04.model.TaikhoanMatKhau;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private RelativeLayout rlayout;
    private Animation animation;
    EditText txtRegTk, txtRegMk, txtRegMkM;
    Button btDangKy, btNhapLai;
    ArrayList<TaikhoanMatKhau> listTk = new ArrayList<>();
    DaoTaiKhoan tkDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);
        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRegMk.setText("");
                txtRegMkM.setText("");
                txtRegTk.setText("");
            }
        });
        btDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tkDao = new DaoTaiKhoan(RegisterActivity.this);
                String tk = txtRegTk.getText().toString();
                String mk = txtRegMk.getText().toString();
                String mkm = txtRegMkM.getText().toString();
                boolean xetTk = true, xetMk = false;
                TaikhoanMatKhau tkmk = new TaikhoanMatKhau(tk, mk);
                listTk = tkDao.getALl();
                if (mk.matches(mkm)) {
                    xetMk = true;
                } else {
                    xetMk = false;
                }
                for (int i = 0; i < listTk.size(); i++) {
                    TaikhoanMatKhau tkx = listTk.get(i);
                    if (tkx.getTenTaiKhoan().matches(tk)) {
                        xetTk = false;
                        break;
                    }
                }
                if (tk.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Tên tài khoản không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (mk.isEmpty() || mkm.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (xetTk == true) {
                            if (xetMk == true) {
                                tkDao.Insert(tkmk);
                                Toast.makeText(RegisterActivity.this, "Thêm tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent();
                                i.putExtra("taikhoan",tk);
                                i.putExtra("matkhau",mk);
                                setResult(RESULT_OK,i);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp nhau!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Tên tài khoản k được trùng!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    private void init() {
        txtRegTk = findViewById(R.id.edtRegUser);
        txtRegMk = findViewById(R.id.edtRegPassword);
        txtRegMkM = findViewById(R.id.edtRePassword);
        btDangKy = findViewById(R.id.btnReg);
        btNhapLai = findViewById(R.id.btnRelay);
    }
}
