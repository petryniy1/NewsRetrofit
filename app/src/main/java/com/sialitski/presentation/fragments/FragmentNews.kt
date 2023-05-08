package com.sialitski.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sialitski.R
import com.sialitski.databinding.FragmentNewsBinding
import com.sialitski.domain.OnNewsClickListener
import com.sialitski.presentation.recyclerView.NewsAdapter
import com.sialitski.presentation.viewModels.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentNews : Fragment(R.layout.fragment_news) {
    private val binding: FragmentNewsBinding
            by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter(newsClickListener) }

    private val newsClickListener: OnNewsClickListener = object :
        OnNewsClickListener {
        override fun onIconClickListener(position: Int) {
            viewModel.onNewsItemClicked(position)
            viewModel.news.value?.get(position)?.let { viewModel.saveNews(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        initObservers()

        initRecycler()

        viewModel.loadRetrofitNews()
    }

    private fun initObservers() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }

    private fun initRecycler() {
        binding.run {
            recyclerNews.adapter = adapter
            recyclerNews.layoutManager = LinearLayoutManager(context)
        }
    }
}