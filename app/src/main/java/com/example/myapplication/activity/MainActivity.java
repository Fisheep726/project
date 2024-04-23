package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.MyfragmentPagerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.fragment.BubbleFragment;
import com.example.myapplication.fragment.ContactFragment;
import com.example.myapplication.fragment.PersonalFragment;
import com.example.myapplication.fragment.PondFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager2 viewPager;
    private LinearLayout llPond,llContacts,llBubble,llPersonal;
    private ImageView ivPond,ivContacts,ivBubble,ivPersonal,ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initTabView();


    }

    private void initPager(){

        viewPager = findViewById(R.id.id_viewpager);
        ArrayList<androidx.fragment.app.Fragment> fragments = new ArrayList<>();

        fragments.add(PondFragment.newInstance());
        fragments.add(ContactFragment.newInstance());
        fragments.add(BubbleFragment.newInstance());
        fragments.add(PersonalFragment.newInstance());
        MyfragmentPagerAdapter pagerAdapter = new MyfragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void initTabView(){
        llPond = findViewById(R.id.tab_pond);
        llContacts = findViewById(R.id.tab_contacts);
        llBubble = findViewById(R.id.tab_bubble);
        llPersonal = findViewById(R.id.tab_personal);
        llPond.setOnClickListener(this);
        llContacts.setOnClickListener(this);
        llBubble.setOnClickListener(this);
        llPersonal.setOnClickListener(this);

        ivPond = findViewById(R.id.tab_img_pond);
        ivContacts = findViewById(R.id.tab_img_contacts);
        ivBubble = findViewById(R.id.tab_img_bubble);
        ivPersonal = findViewById(R.id.tab_img_personal);

        ivPond.setSelected(true);
        ivCurrent = ivPond;
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);

        if (position == 0) {
            ivPond.setSelected(true);
            ivCurrent = ivPond;
        }else if (position == 1) {
            ivContacts.setSelected(true);
            ivCurrent = ivContacts;
        } else if (position == 2) {
            ivBubble.setSelected(true);
            ivCurrent = ivBubble;
        } else if (position == 3) {
            ivPersonal.setSelected(true);
            ivCurrent = ivPersonal;
        }

        if (position == R.id.tab_pond) {
            viewPager.setCurrentItem(0);
        }else if (position == R.id.tab_contacts) {
            viewPager.setCurrentItem(1);
        }else if (position == R.id.tab_bubble) {
            viewPager.setCurrentItem(2);
        }else if (position == R.id.tab_personal) {
            viewPager.setCurrentItem(3);
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    //    public void changeFragment(View v){
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment1 fgm1 = new Fragment1();
//        Fragment2 fgm2 = new Fragment2();
//        Fragment3 fgm3 = new Fragment3();
//        View pond = findViewById(R.id.imagePond);
//        View bubble = findViewById(R.id.imageBubble);
//        View personal = findViewById(R.id.imagePersonal);
//
//        int id = v.getId();
//        pond.setSelected(false);
//        bubble.setSelected(false);
//        personal.setSelected(false);
//        if (id == R.id.textPond) {
//            ft.replace(R.id.main, fgm1);
//        } else if (id == R.id.textBubble) {
//            ft.replace(R.id.main, fgm2);
//        } else if (id == R.id.textPersonal) {
//            ft.replace(R.id.main, fgm3);
//        }
//
//        if (id == R.id.imagePond) {
//            ft.replace(R.id.main, fgm1);
//            pond.setSelected(true);
//        } else if (id == R.id.imageBubble) {
//            ft.replace(R.id.main, fgm2);
//            bubble.setSelected(true);
//        } else if (id == R.id.imagePersonal) {
//            ft.replace(R.id.main, fgm3);
//            personal.setSelected(true);
//        }
//        ft.commit();
//    }

}