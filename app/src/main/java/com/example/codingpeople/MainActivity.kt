package com.example.codingpeople

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.codingpeople.Auth.LoginActivity
import com.example.codingpeople.Auth.MyCominActivity
import com.example.codingpeople.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        val img=arrayOf(
            R.drawable.interview,
            R.drawable.opic,
            R.drawable.contest,
            R.drawable.toiec,
            R.drawable.ncs,
            R.drawable.license
        )

        val text=arrayOf(
            "면접",
            "오픽",
            "공모전",
            "토익",
            "NCS",
            "기사"
        )

        val gridviewAdapter=
            GridviewAdapter(
                this,
                img,
                text
            )
        gridview.adapter=gridviewAdapter

        gridview.setOnItemClickListener { adapterView, view, i, l ->
            val intent= Intent(this,LectureActivity::class.java)
            startActivity(intent)
        }

        viewpager=findViewById(R.id.viewpager) as ViewPager

        val adapter=ViewPagerAdapter(this)
        viewpager.adapter=adapter

        zzim_icon.setOnClickListener{
            val intent=Intent(this, ZzimActivity::class.java)
            startActivity(intent)
        }


        my_page.setOnClickListener {

            if(auth.currentUser == null) {

                val intent=Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else{

                val intent=Intent(this, MyCominActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
