package com.example.jetpacktrial

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacktrial.databinding.MainFragmentBinding

class MainFragment : Fragment(),
    NotesListAdapter.ListItemListener

    {
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

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)

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

        //need ? or !! to manage nullable data
        viewModel.noteList?.observe(viewLifecycleOwner, Observer {

            Log.i("noteLogging", it.toString())

            adapter = NotesListAdapter(it, this@MainFragment)

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

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

            inflater.inflate(R.menu.menu_main, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_sample_data -> addSampleData()
                else -> return super.onOptionsItemSelected(item)
            }
        }

        private fun addSampleData(): Boolean {
            viewModel.addSampleData()
            return true
        }

        override fun onItemClick(notedId: Int) {
            Log.i(TAG, "onItemClick: received note id $notedId")
            val action = MainFragmentDirections.actionEditNote((notedId))
            findNavController().navigate(action)
        }

        /** won't need anymore
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    } */

}