package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CardCharge extends AppCompatActivity {

    private RadioGroup radioGroupPoint;
    private Button btnCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_charge);

        // View 초기화
        radioGroupPoint = findViewById(R.id.radio_group_point);
        btnCharge = findViewById(R.id.btn_charge);

        // 충전 버튼 클릭 리스너
        btnCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupPoint.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(CardCharge.this, "충전 금액을 선택하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택된 금액 가져오기
                RadioButton selectedRadioButton = findViewById(selectedId);
                String[] pointText = selectedRadioButton.getText().toString().split(" ");
                int chargeAmount = Integer.parseInt(pointText[0].replace(",", "").replace("P", ""));

                // 데이터 반환
                Intent resultIntent = new Intent();
                resultIntent.putExtra("chargedPoints", chargeAmount);
                setResult(RESULT_OK, resultIntent);

                Toast.makeText(CardCharge.this, chargeAmount + "P 충전 완료!", Toast.LENGTH_SHORT).show();
                finish(); // 액티비티 종료
            }
        });
    }
}
