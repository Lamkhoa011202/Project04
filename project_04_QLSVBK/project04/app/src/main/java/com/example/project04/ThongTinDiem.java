package com.example.project04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project04.adapter.DiemAdapter;
import com.example.project04.dao.DaoChuyenNganh;
import com.example.project04.dao.DaoLop;
import com.example.project04.dao.DaoDiem;
import com.example.project04.dao.DaoMonHoc;
import com.example.project04.model.Diem;
import com.example.project04.model.MonHoc;
import com.example.project04.model.SinhVien;

import java.util.List;

public class ThongTinDiem extends AppCompatActivity {
    private TextView tvMa,tvTen,tvEmail,tvtenLop,tvTenNganh;
    private Spinner spinnerMH;
    private EditText etDiem;
    private ListView listView;
    private Button btnThem;
    List<Diem> list;

    //GET DAO

    private DaoLop lopDao;
    private DaoChuyenNganh chuyenNganhDao;
    private DaoMonHoc monHocDao;
    private DaoDiem diemDAO;


    //Adapter

    private DiemAdapter diemAdapter;

    SinhVien sinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_diem);
        Intent intent = getIntent();
        sinhVien = (SinhVien) intent.getExtras().getSerializable("SINHVIEN");
        init();

        tvMa.setText("Mã sinh viên : "+ sinhVien.getMaSv());
        tvTen.setText("Tên sinh viên : "+ sinhVien.getTenSv());
        tvEmail.setText("Email : "+ sinhVien.getEmail());
        String tenlop = lopDao.getLop(sinhVien.getMaLop()).getTenLop();
        String tennganh = chuyenNganhDao.getChuyenNganh(sinhVien.getMaChuyenNganh()).getTenChuyenNganh();

        tvtenLop.setText("Tên lớp : "+ tenlop);
        tvTenNganh.setText("Tên ngành : "+ tennganh);

        List<MonHoc> lsList = monHocDao.getAll();
        final ArrayAdapter adapter = new ArrayAdapter(ThongTinDiem.this, android.R.layout.simple_spinner_item, lsList);
        spinnerMH.setAdapter(adapter);
        List<Diem> list = diemDAO.getAll(sinhVien.getMaSv());
        diemAdapter = new DiemAdapter(ThongTinDiem.this,R.layout.item_diem,list,sinhVien.getMaSv());
        listView.setAdapter(diemAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etDiemS = etDiem.getText().toString();
                if(etDiemS.isEmpty()){
                    Toast.makeText(ThongTinDiem.this,"Không được bỏ trống thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    Float diem = Float.valueOf(etDiem.getText().toString());
                    MonHoc monHoc = (MonHoc) spinnerMH.getSelectedItem();
                    if(diem>=0 || diem<=10){
                        if(diemDAO.insert(new Diem(sinhVien.getMaSv(),monHoc.getMaMH(),diem))){
                            Toast.makeText(ThongTinDiem.this,"Thêm điểm thành công",Toast.LENGTH_SHORT).show();
                            diemAdapter.reset(diemDAO.getAll(sinhVien.getMaSv()));
                        }else{
                            Toast.makeText(ThongTinDiem.this,"Môn học này đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }

    private void init() {
        lopDao = new DaoLop(ThongTinDiem.this);
        chuyenNganhDao = new DaoChuyenNganh(ThongTinDiem.this);
        monHocDao = new DaoMonHoc(ThongTinDiem.this);
        diemDAO = new DaoDiem(ThongTinDiem.this);
        tvMa = findViewById(R.id.tvMaSinhVien);
        tvTen = findViewById(R.id.tvTenSV);
        tvEmail = findViewById(R.id.tvEmail);
        tvtenLop = findViewById(R.id.tvTenLop);
        tvTenNganh = findViewById(R.id.tvTenNganh);
        spinnerMH = findViewById(R.id.spMonHoc);
        etDiem = findViewById(R.id.etDiem);
        listView = findViewById(R.id.listDiem);
        btnThem = findViewById(R.id.btnThemDiem);
    }
}