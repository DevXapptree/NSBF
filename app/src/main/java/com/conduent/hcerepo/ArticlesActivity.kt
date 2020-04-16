package com.conduent.hcerepo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray


class ArticlesActivity : AppCompatActivity(), ArticlesAdapter.OnSelect {

    private var articlesAdapter: ArticlesAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        initViews()
    }

    private fun initViews() {
        val dataBundle = intent.extras ?: return
        val mData = dataBundle.getString("DATA", "")

        val jsonArray = JSONArray(mData)

        articlesAdapter = ArticlesAdapter(this, jsonArray)

        recyclerView = findViewById(R.id.recyclerView);
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(mLayoutManager)
        recyclerView?.adapter = articlesAdapter;

    }

    override fun onItemSelected(articleData: String?) {
        val intent = Intent(this, StartReadingDetailActivity::class.java)
        intent.putExtra("DATA", articleData)
        startActivity(intent)
    }
}
