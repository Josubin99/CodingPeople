package com.example.codingpeople.Fragment.MarketInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.codingpeople.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewFragment : Fragment() {

    private lateinit var auth:FirebaseAuth

    private val db=FirebaseFirestore.getInstance()

    private val text_array=ArrayList<String>()

    private val nickname_array=ArrayList<String>()

    private val rating_array=ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        auth= FirebaseAuth.getInstance()

        val view:View=inflater.inflate(R.layout.fragment_review, container, false)

        //adapter 연결

        val review_adapter=ReviewListAdapter(requireContext(),nickname_array,text_array,rating_array)
        view.listview_review.adapter=review_adapter

        //

        db.collection("reviews")
            .get()
            .addOnSuccessListener { result->
                for (document in result) {
                    rating_array.add(document.get("rating") as String)
                    text_array.add(document.get("text") as String)
                    nickname_array.add(document.get("writer") as String)
                }

                review_adapter.notifyDataSetChanged()

            }

            .addOnFailureListener { exception ->  }

       view.write_button.setOnClickListener {

            if(auth.currentUser==null)
                Toast.makeText(requireContext(), "회원가입 or 로그인 해주세요", Toast.LENGTH_LONG).show()

            val intent= Intent(requireContext(),WriteActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}