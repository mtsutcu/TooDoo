package com.talip.toodoo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.talip.toodoo.R
import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.databinding.FragmentHomeBinding
import com.talip.toodoo.ui.adapter.HomeAdapter
import com.talip.toodoo.ui.adapter.TodoAdapter
import com.talip.toodoo.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var  todoList : List<Todo>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.toolbarAnasayfaBaslik ="TooDoo"
        binding.homeFragment = this




        viewModel.todoList.observe(viewLifecycleOwner){
            todoList = it
        }

       viewModel.todoDateList.observe(viewLifecycleOwner){

           val homeAdapter = HomeAdapter(requireContext(),it,todoList,viewModel )
           binding.homeAdapter = homeAdapter

       }


        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)


        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.search_action)

                val searchView = item.actionView as SearchView
                
                
                searchView.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {


                return false


            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)




        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel

       todoList = mutableListOf()


        
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTodos()
    }

    fun fabOnClick(view: View){
        Navigation.findNavController(view).navigate(R.id.toSave)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }


}