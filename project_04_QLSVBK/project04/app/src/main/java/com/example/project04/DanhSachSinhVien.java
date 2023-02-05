package com.example.project04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.project04.adapter.SinhVienAdapter;
import com.example.project04.dao.DaoSinhVien;
import com.example.project04.Login_Register_Activity.LoginActivity;
import com.example.project04.model.SinhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DanhSachSinhVien extends AppCompatActivity {
    ListView listView;
    EditText edtSearch;
    public static ArrayList<SinhVien> ds = new ArrayList<>();
    ArrayList<SinhVien> timKiem = new ArrayList<>();

    SinhVienAdapter sinhVienAdapter;
    FloatingActionButton fbadd;
    FloatingActionButton fab;
    FloatingActionButton fbHome;
    FloatingActionButton fabDangXuat;
    DaoSinhVien sinhVienDao;
    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien);
        edtSearch = findViewById(R.id.edtsearch);
        listView = findViewById(R.id.listSV);
        fbadd = findViewById(R.id.fbsearch);
        fab = findViewById(R.id.fab);
        fbHome = findViewById(R.id.fbHome);
        fabDangXuat = findViewById(R.id.fbDangXuat);

        sinhVienDao = new DaoSinhVien(DanhSachSinhVien.this);
        if (DanhSachLopActivity.xetList == true) {
            ds = DanhSachLopActivity.svlistDuocLoc;
        } else {
            ds = sinhVienDao.getALL();
        }
        timKiem = sinhVienDao.getALL();
        sinhVienAdapter = new SinhVienAdapter(DanhSachSinhVien.this, ds);
        listView.setAdapter(sinhVienAdapter);

        fbadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachSinhVien.this, ThemSinhVienActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.ani_intent, R.anim.ani_intenexit);
            }
        });
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sinhVien = ds.get(position);
                Intent intent = new Intent(DanhSachSinhVien.this,ThongTinDiem.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SINHVIEN",sinhVien);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < before) {
                    sinhVienAdapter.resetData();
                } else {
                    sinhVienAdapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        fabDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachSinhVien.this, LoginActivity.class));

            }
        });
        fbHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DanhSachSinhVien.this, MainActivity.class));
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

    @Override
    protected void onResume() {

        if (DanhSachLopActivity.xetList == true) {
            ds = DanhSachLopActivity.svlistDuocLoc;
        } else {
            ds.clear();
            ds.addAll(sinhVienDao.getALL());
        }

        sinhVienAdapter.notifyDataSetChanged();
        super.onResume();


    }
}
