package com.example.codingpeople.Fragment.ListFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codingpeople.Fragment.MarketInfo.MarketInfoActivity
import com.example.codingpeople.R
import com.example.codingpeople.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_market_info.*
import kotlinx.android.synthetic.main.fragment_first.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View=inflater.inflate(R.layout.fragment_first,container,false)

        val list_array=arrayListOf<ContentsListModel>(

            ContentsListModel( R.drawable.toiec, "Lang1", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang2", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang3", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang4", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang5", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang6", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang7", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang8", 1, "d"),
            ContentsListModel(R.drawable.toiec, "Lang9", 1, "d")
        )

        val list_adapter=
            FirstFragAdapter(
                requireContext(),
                list_array
            )

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->

                if(documentSnapshot.exists()==true){
                    //Data 필드가 있을 때

                }
                else{
                    //Data 필드가 없을 때

                    val lecture= hashMapOf(
                        "Lang1" to "",
                        "Lang2" to "",
                        "Lang3" to "",
                        "Lang4" to "",
                        "Lang5" to "",
                        "Lang6" to "",
                        "Lang7" to "",
                        "Lang8" to "",
                        "Lang9" to ""

                    )

                    FirebaseUtils.db
                        .collection("zzim")
                        .document(FirebaseUtils.getUid())
                        .set(lecture)
                        .addOnSuccessListener {  }
                        .addOnFailureListener {  }

            }

        }


        view.listview_first_fragment.adapter=list_adapter

        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->
            val intent= Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)
        }

        return view
    }

}