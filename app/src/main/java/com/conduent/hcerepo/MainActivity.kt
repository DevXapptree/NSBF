package com.conduent.hcerepo

import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.conduent.hcesdk.*
import com.conduent.hcesdk.core.HCEEngine
import com.conduent.hcesdk.entities.result.HCECardResult
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import android.content.res.AssetManager
import java.io.IOException


class MainActivity : AppCompatActivity(), ReadCallback, View.OnClickListener, RetrieveRemoteOfferCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_convert.setOnClickListener(this)
        button_fetch.setOnClickListener(this)
        button_retrieve_remote.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_convert -> {
                addCommand("[converting cr file to string ...]")
                val crStr = Utils.convertStreamToString(assets.open("LecteurCSC420 le 19-05-22 15-24.txt"))
                addCommand("[conversion complete cr file ...]")
                Log.i("CR DATA", crStr)
                val mData = Gson().fromJson(crStr, HCECardData::class.java)
                Log.i("CR DATA", Gson().toJson(mData))
                addCommand(Gson().toJson(mData))

                val sdk = HCEEngine.getInstance();
                sdk.startReading(ReadParameters(SourceType.HCE, crStr), this)
                //sdk.pingMe(this)
                addCommand("[startReading ...]")
            }
            R.id.button_fetch -> {
//                val sdk = HCEEngine.getInstance(this);
//                sdk.retrieveRemoteOffer()

                try {
                    // for assets folder add empty string
                    val fileList = assets.list("cr")
                    // for assets/subFolderInAssets add only subfolder name
                    if (fileList == null) {
                        // dir does not exist or is not a directory
                    } else {
                        for (i in fileList.indices) {
                            // Get filename of file or directory
                            val filename = fileList[i]
                        }
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            R.id.button_retrieve_remote -> {
                val crStr = Utils.convertStreamToString(assets.open("LecteurCSC420 le 19-05-22 15-24.txt"))
                val sdk = HCEEngine.getInstance();
                sdk.retrieveRemoteOffer(ReadParameters(SourceType.HCE, crStr), this);
            }
        }
    }

    fun addCommand(str: String) {
        command_view.text = command_view.text.toString() + "\n" + str
    }

    override fun onRetrieveRemoteOffer() {
        Log.i("HCE", "Remote complete")
    }

    override fun onRetrieveRemoteOfferError(error: Failure?) {
        Log.i("HCE", "Remote error")
    }

    /*Start Read Callback*/
    override fun onStarted() {
    }

    override fun onEnded(cardParsedContent: String?) {
        Log.i("HCE", "complete")
        Log.i("HCE", cardParsedContent)
    }

    override fun onError(error: Failure?) {
        Log.i("HCE", "error")
    }

    override fun onTimeOut() {
    }
}
