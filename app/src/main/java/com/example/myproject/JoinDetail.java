package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinDetail extends AppCompatActivity {
    EditText jId, jPw, jName;
    Button jRegistration, jLoginBtn;
    myDBHelper myHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_detail);

        jId = findViewById(R.id.jId);
        jPw = findViewById(R.id.jPw);
        jName = findViewById(R.id.jName);
        jRegistration = findViewById(R.id.jRegistrationB);
        jLoginBtn = findViewById(R.id.jLoginBtn);
        myHelper = new myDBHelper(this);


        //회원가입 버튼
        jRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = jId.getText().toString();
                String userPw = jPw.getText().toString();
                String userName = jName.getText().toString();

                //공란일 경우 회원가입 불가
                if (userId.isEmpty() || userPw.isEmpty() || userName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase sqlDB = myHelper.getWritableDatabase();


                // 아이디 중복 확인
                Cursor cursor = sqlDB.rawQuery("SELECT COUNT(*) FROM JoinInfo WHERE uId = ?", new String[]{userId});
                cursor.moveToFirst();
                int count = cursor.getInt(0);
                cursor.close();

                if (count > 0) {
                    Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    // 데이터 삽입
                    ContentValues values = new ContentValues();
                    values.put("uId", userId);
                    values.put("uPassword", userPw);
                    values.put("uName", userName);
                    sqlDB.insert("JoinInfo", null, values);

                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Join.class);
                    startActivity(intent);
                }
                sqlDB.close();
            }
        });//jRegistration

        jLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });//jLoginBtn



    }//Join


    //SQL DB 생성
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "LoginDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE JoinInfo (" +
                    "uId TEXT PRIMARY KEY, " +
                    "uPassword TEXT, " +
                    "uName TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS JoinInfo");
            onCreate(db);
        }
    }
}