package com.conduent.hcerepo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        output_view.text = mData
    }
}
