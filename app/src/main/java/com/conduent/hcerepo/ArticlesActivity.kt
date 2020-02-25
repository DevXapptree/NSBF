package com.conduent.hcerepo

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson


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
