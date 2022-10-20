package com.talip.toodoo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.talip.toodoo.R
import com.talip.toodoo.databinding.FragmentSaveBinding
import com.talip.toodoo.ui.viewmodel.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveFragment : Fragment() {

    private lateinit var binding : FragmentSaveBinding
    private lateinit var viewModel : SaveViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_save,container,false)

        binding.toolbarSaveBaslik = "New Todo"
        binding.saveFragment = this

        






        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : SaveViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun save(todo_task:String,todo_date : String){
        viewModel.save(todo_task,todo_date).runCatching {
            activity?.onBackPressed()
        }

    }

}