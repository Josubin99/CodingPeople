package com.example.codingpeople.Fragment.ListFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0-> {
                FirstFragment()
            }
            1-> {
                SecondFragment()
            }
            else-> {
                return ThirdFragment()
            }
        }
    }
    override fun getCount(): Int {
        return 3
    }

}