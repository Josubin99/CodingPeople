package com.example.codingpeople

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.example.codingpeople.Fragment.ListFragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.custom_tab.view.*

class LectureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture)

        val fragmentAdapter=
            FragmentAdapter(
                supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        list_viewpager.adapter=fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("면접")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("오픽")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("공모전")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("토익")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("NCS")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("기사")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?){

            }
            override fun onTabUnselected(p0: TabLayout.Tab?){

            }

            override fun onTabSelected(p0: TabLayout.Tab?){
                if (p0 != null) {
                    list_viewpager.currentItem = p0.position
                }
            }
        })

    }

    private fun createTabView(tabName:String) : View {
        val tabView= LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.txt_name.text=tabName
        return tabView
    }
}