package com.talip.toodoo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.talip.toodoo.R
import com.talip.toodoo.databinding.FragmentDetailBinding
import com.talip.toodoo.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        binding.todoDetailFragment = this
        binding.toolbarDetailBaslik = "Detail"

        val bundle : DetailFragmentArgs by navArgs()
        val newTodo = bundle.todo

        binding.todo = newTodo
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel

    }


    fun updateClick(todo_id : Int, todo_task : String,todo_date : String){
        viewModel.update(todo_id,todo_task,todo_date)
        activity?.onBackPressed()

    }


}