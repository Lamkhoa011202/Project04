package com.example.project04.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "project04_db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //tạo table taikhoan
        String sql = "CREATE TABLE taiKhoan(tenTaiKhoan text primary key, matKhau text)";
        db.execSQL(sql);
        sql = "INSERT INTO taiKhoan VALUES('admin','admin')";
        db.execSQL(sql);

        //table tạo chuyên ngành
        sql = " CREATE TABLE CHUYENNGANH(maChuyenNganh TEXT PRIMARY KEY, tenChuyenNganh TEXT)";
        db.execSQL(sql);
        sql = " INSERT INTO CHUYENNGANH VALUES ('KHMT','Khoa hoc may tinh')";
        db.execSQL(sql);
        sql = " INSERT INTO CHUYENNGANH VALUES ('CNTT','Cong nghe thong tin')";
        db.execSQL(sql);
        sql = " INSERT INTO CHUYENNGANH VALUES ('KHVL','Khoa hoc vat lieu')";
        db.execSQL(sql);

        //table tao bang mon hoc
        sql = " CREATE TABLE MONHOC(maMH TEXT PRIMARY KEY, tenmonhoc TEXT)";
        db.execSQL(sql);
        sql = " INSERT INTO MONHOC VALUES ('TA1','Tieng Anh')";
        db.execSQL(sql);
        sql = " INSERT INTO MONHOC VALUES ('TOAN1','Toan Cao Cap')";
        db.execSQL(sql);
        sql = " INSERT INTO MONHOC VALUES ('DOHOA1','Do hoa may tinh')";
        db.execSQL(sql);

        // Tạo bảng lớp
        sql = " CREATE TABLE LOP(maLop TEXT PRIMARY KEY, tenLop TEXT)";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('TiengAnh','Tieng Anh')";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('Laptrinh','Lap trinh may tinh')";
        db.execSQL(sql);
        sql = " INSERT INTO LOP VALUES ('Toan','Toan Cao Cap')";
        db.execSQL(sql);

        // tạo bảng sinh viên
        sql = " CREATE TABLE SINHVIEN(maSv TEXT PRIMARY KEY, tenSV TEXT ," +
                " email TEXT ,maLop TEXT REFERENCES LOP(maLop), maChuyenNganh TEXT REFERENCES CHUYENNGANH(maChuyenNganh)) ";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('20206560','Nguyen lam Khoa','nguyenlamkhoa@gmail.com','TiengAnh','KHVL')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('20206558','Nguyen Thanh Long','nguyentl@gmail.com','Laptrinh','KHMT')";
        db.execSQL(sql);
        sql = " INSERT INTO SINHVIEN VALUES ('20206557','La Van Hop','Lavanhop@gmail.com','Toan','KHMT')";
        db.execSQL(sql);
        // tao bang diem
        sql = "CREATE TABLE DIEM(maSv TEXT REFERENCES SINHVIEN(maSv),maMH TEXT REFERENCES MONHOC(maMH)," +
                "diem REAL, PRIMARY KEY (maSv, maMH))";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

