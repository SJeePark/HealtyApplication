package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Join extends AppCompatActivity {
    EditText mainId, mainPw;
    Button mainLoginB, mainJoinB;
    JoinDetail.myDBHelper myHelper; // Join 클래스의 myDBHelper 사용
    SQLiteDatabase sqlDB;
    int idFlag = 0;
    int pwFlag = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        // myDBHelper 초기화
        myHelper = new JoinDetail.myDBHelper(this);

        mainId = findViewById(R.id.mainId);
        mainPw = findViewById(R.id.mainPw);
        mainJoinB = findViewById(R.id.mainJoinB);
        mainLoginB = findViewById(R.id.mainLoginB);

        // 회원가입 화면 이동
        mainJoinB.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), JoinDetail.class);
            startActivity(intent);
        });

        // 로그인 버튼 클릭 이벤트
        mainLoginB.setOnClickListener(view -> {
            String edtId = mainId.getText().toString();
            String edtPw = mainPw.getText().toString();

            if (edtId.isEmpty() || edtPw.isEmpty()) {
                Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            sqlDB = myHelper.getReadableDatabase();
            Cursor cursor = null;

            try {
                // 아이디로 데이터 검색
                cursor = sqlDB.rawQuery("SELECT * FROM JoinInfo WHERE uId = ?", new String[]{edtId});
                if (cursor.moveToFirst()) {
                    String dbPw = cursor.getString(2); // 비밀번호는 세 번째 열
                    if (edtPw.equals(dbPw)) {
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();

                        //메인 화면으로 클래스 이름 변경하기!
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "오류 발생: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                if (cursor != null) cursor.close();
                sqlDB.close();
            }
        });
    }
}