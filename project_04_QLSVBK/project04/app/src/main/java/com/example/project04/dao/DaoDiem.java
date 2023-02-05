package com.example.project04.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project04.database.DatabaseHelper;
import com.example.project04.model.Diem;

import java.util.ArrayList;
public class DaoDiem {
     DatabaseHelper db;
    public DaoDiem(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<Diem> getAll(String maSV) {
        ArrayList<Diem> lsList = new ArrayList<>();
        SQLiteDatabase dtb = db.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM DIEM WHERE maSV="+maSV, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            String masv = cs.getString(0);
            String mamh = cs.getString(1);
            Float diem = cs.getFloat(2);
            Diem s = new Diem(masv, mamh,diem);
            lsList.add(s);
            cs.moveToNext();
        }
        cs.close();
        return lsList;
    }
    public boolean insert(Diem diem) {
        try{
            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("maSV", diem.getMaSV());
            contentValues.put("maMH", diem.getMaMH());
            contentValues.put("diem", diem.getDiem());
            long r = sqLiteDatabase.insert("DIEM", null, contentValues);
            return r > 0;
        }catch (Exception E){
            return  false;
        }
    }
    public boolean update(Diem diem) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("diem", diem.getDiem());
        long r = sqLiteDatabase.update("DIEM", contentValues, "maSv=? AND maMH=?", new String[]{diem.getMaSV(),diem.getMaMH()});
        return r > 0;
    }
    public boolean Delete(Diem diem) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        int r = sqLiteDatabase.delete("DIEM", "maSv=? AND maMH=?", new String[]{diem.getMaSV(),diem.getMaMH()});
        return r > 0;
    }
}
