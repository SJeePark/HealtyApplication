package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {



    protected void setPersonalButtonClickListener(int imageViewId) {
        ImageView perB = findViewById(imageViewId);
        perB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Personal.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupDropdownMenu(String screenType) {
        ImageView dropdown = findViewById(R.id.dropdown_menu);

        dropdown = findViewById(R.id.dropdown_menu);

        // 드롭다운 메뉴가 특정 화면에서만 보이도록 설정
        if (dropdown != null) {
            dropdown.setVisibility(View.VISIBLE);  // 헬스장, 필라테스, 수영장, 테니스 화면에서만 보이게
            dropdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(BaseActivity.this, v);

                    // 화면에 따라 해당 항목을 제외한 나머지 메뉴 추가
                    switch (screenType) {
                        case "gym":
                            popupMenu.getMenu().add("필라테스");
                            popupMenu.getMenu().add("수영");
                            popupMenu.getMenu().add("테니스");
                            break;
                        case "pila":
                            popupMenu.getMenu().add("헬스장");
                            popupMenu.getMenu().add("수영");
                            popupMenu.getMenu().add("테니스");
                            break;
                        case "swim":
                            popupMenu.getMenu().add("헬스장");
                            popupMenu.getMenu().add("필라테스");
                            popupMenu.getMenu().add("테니스");
                            break;
                        case "tennis":
                            popupMenu.getMenu().add("헬스장");
                            popupMenu.getMenu().add("필라테스");
                            popupMenu.getMenu().add("수영");
                            break;
                    }

                    popupMenu.show();
                }
            });
        }
    }
}