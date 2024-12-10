package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Mission extends AppCompatActivity {

    private int totalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission);

        // 뒤로 가기 버튼 설정
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        // 각 미션의 체크박스와 포인트 연동
        setupMissionCheckBox(R.id.check1, 100); // 러닝 10km
        setupMissionCheckBox(R.id.check2, 200); // 10,000보 걷기
        setupMissionCheckBox(R.id.check3, 300); // 근력 운동 1시간
    }

    /**
     * 체크박스를 설정하고 포인트를 계산하는 메서드
     *
     * @param checkBoxId 체크박스 ID
     * @param pointValue 체크 시 추가할 포인트
     */
    private void setupMissionCheckBox(int checkBoxId, int pointValue) {
        CheckBox checkBox = findViewById(checkBoxId);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                totalPoints += pointValue;
            } else {
                totalPoints -= pointValue;
            }

            // 실시간으로 포인트 반영
            Intent intent = new Intent();
            intent.putExtra("updatedPoints", totalPoints);
            setResult(RESULT_OK, intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("updatedPoints", totalPoints);
        setResult(RESULT_OK, intent);

        // 부모 클래스의 기본 동작 호출
        super.onBackPressed();
    }
}
