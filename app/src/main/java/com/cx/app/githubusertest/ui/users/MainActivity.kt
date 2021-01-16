package com.cx.app.githubusertest.ui.users

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cx.app.githubusertest.R
import com.cx.app.githubusertest.model.Items
import com.cx.app.githubusertest.model.UsersModel
import com.cx.app.githubusertest.utils.DefaultItemDecoration
import com.cx.app.githubusertest.utils.dp
import com.cx.app.githubusertest.utils.isNotNull
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchUserView {
    private lateinit var presenter: SearchUserPresenter
    private lateinit var adapter: SearchUserAdapter
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private var data: MutableList<Items> = mutableListOf()
    private var page: Int = 1
    private var enableEndless: Boolean = false
    private var q: String? = null
    private var error: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        init()
    }

    override fun onSuccess(data: UsersModel?) {
        if (data == null) return
        if (this.data.size < data.total_count) {
            enableEndless = true
            page++
        } else {
            enableEndless = false
        }
        data.items.let { this.data.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    override fun onAttachView() {
        presenter.attachView(this)
    }

    override fun onDetachView() {
        presenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )

        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = "Username"
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                q = query
                data.clear()
                query?.let { presenter.searchUsers(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onDataEmpty(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onLoading(isLoading: Boolean) {
        swipeRefresh.isRefreshing = isLoading
    }

    private fun init() {
        presenter = SearchUserPresenter()
        adapter = SearchUserAdapter(data)

        swipeRefresh.setOnRefreshListener {
            data.clear()
            if (isNotNull(q))  presenter.searchUsers(q!!) else swipeRefresh.isRefreshing = false
        }

        setAdapter()
        endLessScroll()
        onAttachView()
    }

    private fun setAdapter() {
        rvUsers.layoutManager = linearLayoutManager
        rvUsers.addItemDecoration(DefaultItemDecoration(spacing = 20.dp, includeEdge = true))
        rvUsers.adapter = adapter
    }

    private fun endLessScroll() {
        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val countItem = linearLayoutManager.itemCount - 1
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (isLastPosition && enableEndless) {
                    q?.let { presenter.searchUsers(it, page = page) }
                }
            }
        })
    }
}