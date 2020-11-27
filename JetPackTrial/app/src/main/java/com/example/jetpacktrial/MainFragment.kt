package com.example.jetpacktrial

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacktrial.data.NoteEntity
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

        requireActivity().title = getString(R.string.app_name)

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

            val selectedNotes =savedInstanceState?.getParcelableArrayList<NoteEntity>(SELECTED_NOTES_KEY)

            adapter.selectedNotes.addAll(selectedNotes ?: emptyList())

        })

        binding.floatingActionButton2.setOnClickListener {
            editNote(NEW_NOTE_ID)
        }

        return binding.root
        //return inflater.inflate(R.layout.main_fragment, container, false)
    }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            val menuId =
                    //cannot do null check cause it is lateinit variable
                    if(this::adapter.isInitialized
                            && adapter.selectedNotes.isNotEmpty()) {
                    R.menu.menu_main_selected_items
                    }
                    else
                    {
                        R.menu.menu_main
                    }


            inflater.inflate(menuId, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_sample_data -> addSampleData()
                R.id.action_delete_all -> deleteDataAll()
                R.id.action_delete -> deleteSelectedNotes()
                else -> return super.onOptionsItemSelected(item)
            }
        }

        private fun deleteDataAll(): Boolean {
            viewModel.delteAll()
            Handler(Looper.getMainLooper()).postDelayed({
                adapter.selectedNotes.clear()
                requireActivity().invalidateOptionsMenu()
            }, 100)
            //database operation is running in a background thread and is asynchronous
            //need to wait a moment before taking this action

            return true
        }

        private fun deleteSelectedNotes(): Boolean {
            viewModel.deleteNotes(adapter.selectedNotes)
            Handler(Looper.getMainLooper()).postDelayed({
                adapter.selectedNotes.clear()
                requireActivity().invalidateOptionsMenu()
            }, 100)
            //database operation is running in a background thread and is asynchronous
            //need to wait a moment before taking this action

            return true
        }

        private fun addSampleData(): Boolean {
            viewModel.addSampleData()
            return true
        }

        override fun editNote(notedId: Int) {
            Log.i(TAG, "onItemClick: received note id $notedId")
            val action = MainFragmentDirections.actionEditNote((notedId)) //comming from navigation graph xml file
            findNavController().navigate(action)
        }

        override fun onItemSelectionChanged() {
            requireActivity().invalidateOptionsMenu()
        }

        override fun onSaveInstanceState(outState: Bundle) {
            if(this::adapter.isInitialized) {
                outState.putParcelableArrayList(SELECTED_NOTES_KEY, adapter.selectedNotes)
            }

            super.onSaveInstanceState(outState)
        }

}