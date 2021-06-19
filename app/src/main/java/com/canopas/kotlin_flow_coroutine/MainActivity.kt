package com.canopas.kotlin_flow_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.canopas.kotlin_flow_coroutine.data.Repository
import com.canopas.kotlin_flow_coroutine.databinding.ActivityMainBinding
import com.canopas.kotlin_flow_coroutine.databinding.ListItemSearchRepoBinding
import com.canopas.kotlin_flow_coroutine.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    private var adapter: GitRepoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = GitRepoAdapter()
        viewModel.fetchData()
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.repo.observe(this, Observer {
            adapter?.setItems(it.items)
        })
    }

    private fun initView() {
        binding.recyclerViewRepo.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewRepo.adapter = adapter
    }


    inner class GitRepoAdapter : RecyclerView.Adapter<GitRepoAdapter.RepoViwHolder>() {
        private val items = ArrayList<Repository>()

        fun setItems(list: List<Repository>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViwHolder {
            val binding = ListItemSearchRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return RepoViwHolder(binding)
        }

        override fun onBindViewHolder(holder: RepoViwHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return items.size
        }


        inner class RepoViwHolder(itemView: ListItemSearchRepoBinding) :
            RecyclerView.ViewHolder(itemView.root) {

            fun bind(item: Repository) {

            }
        }
    }
}