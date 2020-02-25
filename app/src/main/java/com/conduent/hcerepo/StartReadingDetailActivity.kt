package com.conduent.hcerepo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.*
import android.widget.Toast
import com.conduent.hcesdk.entities.result.HCECardResult
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_start_reading_detail.*

class StartReadingDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_reading_detail)
        initViews()
    }

    private fun initViews() {
        val dataBundle = intent.extras ?: return
        val mData = dataBundle.getString("DATA", "")
        val mDataBundle = dataBundle.getSerializable("DATA_BUNDLE")
        if(mDataBundle!=null){
            val outRes = mDataBundle as HCECardResult
            val outStr = Gson().toJson(outRes)
            output_view.text = outStr
        }
        output_view.text = Html.fromHtml(mData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_copy -> {
                val cm= getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Card Data", output_view.text.toString())
                cm.primaryClip = clipData
                Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
