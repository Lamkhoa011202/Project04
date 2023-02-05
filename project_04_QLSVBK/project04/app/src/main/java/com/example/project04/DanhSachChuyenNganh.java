package com.example.project04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project04.adapter.ChuyenNganhAdapter;
import com.example.project04.dao.DaoChuyenNganh;
import com.example.project04.dao.DaoSinhVien;
import com.example.project04.Login_Register_Activity.LoginActivity;
import com.example.project04.model.ChuyenNganh;
import com.example.project04.model.SinhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachChuyenNganh extends AppCompatActivity {

    FloatingActionButton fbadd;
    FloatingActionButton fab;
    FloatingActionButton fbHome;
    FloatingActionButton fabDangXuat;
    TextView tvanhien;
    EditText edtSearch;

    ArrayList<ChuyenNganh> dschuyenn = new ArrayList<>();
    ArrayList<ChuyenNganh> timKiem = new ArrayList<>();

    ArrayList<SinhVien> svlist;
    static ArrayList<SinhVien> svlistDuocLoc;
    public static boolean xetList = true;

    ListView listView;
    ChuyenNganhAdapter chuyenNganhAdapter;

    DaoChuyenNganh chuyenNganhDao;
    DaoSinhVien SinhVienDao;

    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chuyen_nganh);
        listView = findViewById(R.id.listviewLop);
        fbadd = findViewById(R.id.fbThemLop);
        tvanhien = findViewById(R.id.tvAnHien);
        fbHome = findViewById(R.id.fbHomeLop);
        fab = findViewById(R.id.fab1);
        fabDangXuat = findViewById(R.id.fbDangXuatLop);
        edtSearch = findViewById(R.id.edttennganh);
        fbAction();
        chuyenNganhDao = new DaoChuyenNganh(DanhSachChuyenNganh.this);
        dschuyenn = chuyenNganhDao.getAll();
        timKiem = chuyenNganhDao.getAll();
        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachChuyenNganh.this, ThemNganh.class));
            }
        });
        chuyenNganhAdapter = new ChuyenNganhAdapter(DanhSachChuyenNganh.this, R.layout.item_chuyen_nganh, dschuyenn);
        listView.setAdapter(chuyenNganhAdapter);

        if (dschuyenn.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            tvanhien.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            tvanhien.setVisibility(View.INVISIBLE);
        }



        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Search or Filter

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    chuyenNganhAdapter.resetData();

                } else {
                    chuyenNganhAdapter.getFilter().filter(s);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void fbAction() {
        fabDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachChuyenNganh.this, LoginActivity.class));

            }
        });
        fbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachChuyenNganh.this, MainActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }
        });
    }

    private void openMenu() {
        isOpen = true;
        fbHome.animate().translationY(-getResources().getDimension(R.dimen.stan_60));
        fbadd.animate().translationY(-getResources().getDimension(R.dimen.stan_105));
        fabDangXuat.animate().translationY(-getResources().getDimension(R.dimen.stan_155));
    }

    private void closeMenu() {
        isOpen = false;
        fbHome.animate().translationY(0);
        fbadd.animate().translationY(0);
        fabDangXuat.animate().translationY(0);
    }
}