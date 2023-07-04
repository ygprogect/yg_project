package com.example.yg;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.yg.adapters.pagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
public class deliveryman_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryman);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        pagerAdapter adapter = new pagerAdapter(this);
        viewPager.setAdapter(adapter);

        // تعيين عدد الصفحات في TabLayout
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("قائمة الطلبات");
                    break;
                case 1:
                    tab.setText("قائمة طلباتي");
                    break;
            }
        });
        mediator.attach();
    }
}








