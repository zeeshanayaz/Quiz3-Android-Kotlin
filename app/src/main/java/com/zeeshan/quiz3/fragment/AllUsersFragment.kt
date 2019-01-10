package com.zeeshan.quiz3.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zeeshan.quiz3.R
import com.zeeshan.quiz3.adapter.ListAdapter
import com.zeeshan.quiz3.model.User


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AllUsersFragment : Fragment() {

    var userList : ArrayList<User> = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_all_users, container, false)

        var listAdapter : ListAdapter? = null
        val recyclerView : RecyclerView = view.findViewById(R.id.all_user_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        listAdapter = ListAdapter(activity!!, userList)
        recyclerView.adapter = listAdapter

        return view
    }


}
