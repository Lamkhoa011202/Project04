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

import com.example.project04.adapter.MonHocAdapter;
import com.example.project04.dao.DaoMonHoc;
import com.example.project04.Login_Register_Activity.LoginActivity;
import com.example.project04.model.MonHoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachMonHoc extends AppCompatActivity {

    FloatingActionButton fbadd;
    FloatingActionButton fab;
    FloatingActionButton fbHome;
    FloatingActionButton fabDangXuat;
    TextView tvanhien;
    EditText edtSearch;

    ArrayList<MonHoc> dsmonhoc = new ArrayList<>();
    ArrayList<MonHoc> timKiem = new ArrayList<>();

    ListView listView;
    MonHocAdapter monHocAdapter;
    DaoMonHoc monHocDao;


    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_hoc);
        listView = findViewById(R.id.listviewLop);
        fbadd = findViewById(R.id.fbThemLop);
        tvanhien = findViewById(R.id.tvAnHien);
        fbHome = findViewById(R.id.fbHomeLop);
        fab = findViewById(R.id.fab1);
        fabDangXuat = findViewById(R.id.fbDangXuatLop);
        edtSearch = findViewById(R.id.edttennganh);
        fbAction();
        monHocDao = new DaoMonHoc(DanhSachMonHoc.this);

        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachMonHoc.this, ThemMonHoc.class));
            }
        });
        dsmonhoc = monHocDao.getAll();
        timKiem = monHocDao.getAll();

        final MonHocAdapter monHocAdapter= new MonHocAdapter(DanhSachMonHoc.this, R.layout.item_mon_hoc, dsmonhoc);
        listView.setAdapter(monHocAdapter);

        if (dsmonhoc.size() == 0) {
            listView.setVisibility(View.INVISIBLE);
            tvanhien.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            tvanhien.setVisibility(View.INVISIBLE);
        }

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    monHocAdapter.resetData();

                } else {
                    monHocAdapter.getFilter().filter(s);

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
                startActivity(new Intent(DanhSachMonHoc.this, LoginActivity.class));

            }
        });
        fbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachMonHoc.this, MainActivity.class));
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