package com.example.jetpacktrial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacktrial.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    /**
     * When using navigation component the new instance function will not be called
    companion object {
        fun newInstance() = MainFragment()
    }*/

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding

    private lateinit var adapter: NotesListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //have references to all the child view objects within the layout
        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //recyclerView component uses an adaptor class to manage its data at runtime
        //the adapter breaks down its tasks into a number of different function
        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.noteList.observe(viewLifecycleOwner, Observer {

            Log.i("noteLogging", it.toString())

            adapter = NotesListAdapter(it)

            binding.recyclerView.adapter = adapter

            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

            //As the data is received in the fragment for the view model
            //it is passed into an instance of the adpater
            //the adapter is passed to the recycler view
            //recylcer view to display itself as a list

        })

        return binding.root
        //return inflater.inflate(R.layout.main_fragment, container, false)
    }

    /** won't need anymore
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    } */

}