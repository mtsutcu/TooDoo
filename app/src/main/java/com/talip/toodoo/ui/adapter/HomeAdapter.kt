package com.talip.toodoo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.databinding.HomeCardDesignBinding
import com.talip.toodoo.databinding.HomeRvDesignBinding
import com.talip.toodoo.ui.fragment.HomeFragmentDirections
import com.talip.toodoo.ui.viewmodel.HomeViewModel

class HomeAdapter(
    var mContext: Context,
    var rv_listesi: List<String>,
    val todoList: List<Todo>,
    val viewModel: HomeViewModel
) : RecyclerView.Adapter<HomeAdapter.RvTutucu>() {

    inner class RvTutucu(tasarim: HomeRvDesignBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: HomeRvDesignBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvTutucu {
        val tasarim = HomeRvDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return RvTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: RvTutucu, position: Int) {
        val rv_listesi = rv_listesi.sortedDescending().reversed()

        val newRvlist = ArrayList<String>()

        rv_listesi.forEach {
            if (!(newRvlist.contains(it))) {
                newRvlist.add(it)
            }
        }

        var rv = ""
        if (position < newRvlist.size) {
            rv = newRvlist.get(position)
        }


        val t = holder.tasarim

        t.textViewDateHome.text = rv

        val newTodoList = ArrayList<Todo>()
        todoList.forEach {
            if (it.todo_date == rv) {
                newTodoList.add(it)

            }
        }

        val newTodoAdapter = TodoAdapter(mContext, newTodoList,viewModel)

        t.rvTodo.adapter = newTodoAdapter

        t.rvTodo.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)


    }


    override fun getItemCount(): Int {
        return rv_listesi.size
    }
}