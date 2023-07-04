package com.example.yg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.yg.pagerdelegatAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class tabdelegates extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabdelegates);

        TabLayout tabLayout = findViewById(R.id.tabLayout_delegates);
        setContentView(tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager_delegates);

        Utilsdelegates.FillData();

        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < Utilsdelegates.categories.size(); i++) {
            fragments.add(delegatesitms.newInstance(Utilsdelegates.categories.get(i)));
        }

        pagerdelegatAdapter adapter = new pagerdelegatAdapter(this, fragments);
        viewPager.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                tab.setText(Utilsdelegates.categories.get(position));
            }

            @Override
            public boolean equals(@Nullable Object obj) {
                return super.equals(obj);
            }
        });
        mediator.attach();
    }
}