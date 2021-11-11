package com.example.android.futureworks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.futureworks.databinding.ArticleItemBinding
import com.example.android.futureworks.models.Article

class ArticleAdapter(private val listener: ArticlesListener): ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    var articles: List<Article> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    class ArticleViewHolder(private var binding: ArticleItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, listener: ArticlesListener) {
            binding.listener = listener
            binding.article = article
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ArticleItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position], listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(article: List<Article>?) {
        if (article != null) {
            this.articles = article
        }
    }

    interface ArticlesListener {
        fun onClick(article: Article) = Unit
    }

}