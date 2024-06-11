package com.troxcryptocoin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.troxcryptocoin.R
import com.troxcryptocoin.modal.HomeModal

class InvestAdapter(private val quizQuestions: List<HomeModal>) : RecyclerView.Adapter<InvestAdapter.ViewHolder1>() {

    inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.tv_profit)
        val option1TextView: TextView = itemView.findViewById(R.id.tv_maturity)
        val option2TextView: TextView = itemView.findViewById(R.id.tv_plan)
        val option3TextView: TextView = itemView.findViewById(R.id.tv_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_invest_design, parent, false)
        return ViewHolder1(view)
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
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