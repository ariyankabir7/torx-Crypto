package com.troxcryptocoin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.troxcryptocoin.R
import com.troxcryptocoin.modal.HomeModal

class HomeAdapter(private val quizQuestions: List<HomeModal>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.tv_title)
        val option1TextView: TextView = itemView.findViewById(R.id.tv_subtitle)
        val option2TextView: TextView = itemView.findViewById(R.id.tv_balance)
        val option3TextView: TextView = itemView.findViewById(R.id.tv_trending_percent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_home_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = quizQuestions[position]
      //  holder.questionTextView.text = question.question
      //  holder.option1TextView.text = question.op1
        holder.option2TextView.text = "$"+question.op2
       // holder.option3TextView.text = question.op3+"%"
    }

    override fun getItemCount(): Int {
        return quizQuestions.size
    }
}