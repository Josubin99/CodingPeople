package com.example.codingpeople.Fragment.MarketInfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.example.codingpeople.R
import com.example.codingpeople.Utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_market_info.*

class MarketInfoActivity : AppCompatActivity() {

    private var auth:FirebaseAuth= FirebaseAuth.getInstance()
    private val db:FirebaseFirestore= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        FirebaseUtils.getUid()
        FirebaseUtils.db

        lecture_text.text=intent.getStringExtra("title")

        //찜 여부 확인

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot.get(intent.getStringExtra("title"))==true){
                    header_zzim.text="하트뿅뿅 찜 되었습니다"
                    header_zzim.setTextColor(Color.BLUE)
                }
            }
            .addOnFailureListener {  }

        zzim.setOnClickListener {

            //이미 찜 되어 있을 때
            if(header_zzim.text.equals("하트뿅뿅 찜 되었습니다")){
                header_zzim.text="하뜨뿅뿅 찜"
                header_zzim.setTextColor(Color.RED)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), "")
                    .addOnSuccessListener {
                        Toast.makeText(this, "저장 취소", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "다시 눌러 주세요", Toast.LENGTH_LONG).show()
                    }

            }else{
                //이미 찜 되어 있지 않을 때때
                header_zzim.text="하뜨뿅뿅 찜 되었습니다"
                header_zzim.setTextColor(Color.BLUE)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title"), true)
                    .addOnSuccessListener {
                        Toast.makeText(this, "저장", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "다시 눌러 주세요", Toast.LENGTH_LONG).show()
                    }

            }


        }



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area,ContentFragment())
            .commit()

        figure_1.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,ContentFragment())
                .commit()
        }
        figure_2.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,InfoFragment())
                .commit()
        }
        figure_3.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,ReviewFragment())
                .commit()
        }
    }
}