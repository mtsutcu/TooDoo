package com.talip.toodoo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.databinding.HomeCardDesignBinding
import com.talip.toodoo.ui.fragment.HomeFragmentDirections
import com.talip.toodoo.ui.viewmodel.HomeViewModel

class TodoAdapter(
    var mContext: Context,
    var todoListesi: List<Todo>,
    var viewModel: HomeViewModel
) : RecyclerView.Adapter<TodoAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: HomeCardDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: HomeCardDesignBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = HomeCardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val todo = todoListesi.get(position)
        val t = holder.tasarim
        t.textViewTodo.text = todo.todo_task

        t.homeCard.setOnClickListener {
            val gecis = HomeFragmentDirections.toDetail(todo = todo)
            Navigation.findNavController(it).navigate(gecis)
        }

        t.homeCard.setOnLongClickListener {

            val ad = AlertDialog.Builder(mContext)
            ad.setTitle("Uyarı ")
            ad.setMessage("${todo.todo_task} silinecek")
            ad.setPositiveButton("Evet"){s,d->
                viewModel.delete(todo.todo_id)

            }
            ad.setNegativeButton("Hayır"){s,d->

            }

            ad.create().show()



            return@setOnLongClickListener true

            
        }


    }


    override fun getItemCount(): Int {
        return todoListesi.size
    }


}