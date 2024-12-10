package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Personal extends AppCompatActivity {

    private static final int REQUEST_CODE_CHARGE = 1; // CardCharge 액티비티 요청 코드
    private static final int REQUEST_CODE_MISSION = 2; // Mission 액티비티 요청 코드

    private int mileageBalance = 50000; // 기본 보유 포인트
    private int recentCharge = 0; // 최근 충전 금액
    private TextView mileageBalanceView, recentChargeView; // 포인트 및 최근 충전 금액 표시 TextView
    private Button btnCard, missionBtn; // 버튼 참조

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);

        // View 초기화
        mileageBalanceView = findViewById(R.id.mileage_balance);
        recentChargeView = findViewById(R.id.recent_charge);
        btnCard = findViewById(R.id.card_charge);
        missionBtn = findViewById(R.id.missionBtn);

        // 초기 포인트 및 최근 충전 금액 표시
        updateMileageBalance();
        updateRecentCharge();

        // CardCharge 액티비티로 이동
        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Personal.this, CardCharge.class);
                startActivityForResult(intent, REQUEST_CODE_CHARGE);
            }
        });

        // Mission 액티비티로 이동
        missionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Personal.this, Mission.class);
                startActivityForResult(intent, REQUEST_CODE_MISSION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_CHARGE) {
                // CardCharge에서 반환된 데이터 처리
                int chargedPoints = data.getIntExtra("chargedPoints", 0);
                recentCharge = chargedPoints;
                mileageBalance += chargedPoints;

                updateMileageBalance();
                updateRecentCharge();
            } else if (requestCode == REQUEST_CODE_MISSION) {
                // Mission에서 반환된 데이터 처리
                int updatedPoints = data.getIntExtra("updatedPoints", 0);
                mileageBalance += updatedPoints;

                updateMileageBalance();
            }
        }
    }

    private void updateMileageBalance() {
        mileageBalanceView.setText(mileageBalance + "P");
    }

    private void updateRecentCharge() {
        recentChargeView.setText("최근 충전 포인트: " + recentCharge + "P");
    }
}
