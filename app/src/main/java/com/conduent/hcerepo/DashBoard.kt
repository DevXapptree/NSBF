package com.conduent.hcerepo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.conduent.hcerepo.entities.BufferData
import com.conduent.hcesdk.*
import com.conduent.hcesdk.core.HCEEngine
import com.conduent.hcesdk.core.IHCEEngine
import com.conduent.hcesdk.entities.remoteoffer.response.RemoteResponse
import com.conduent.hcesdk.entities.result.ContractResult
import com.conduent.hcesdk.entities.result.HCECardResult
import com.conduent.hcesdk.entities.valuesapi.ProductDescription
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dash_board.*
import java.io.IOException


class DashBoard : AppCompatActivity(), View.OnClickListener, ReadCallback, RetrieveRemoteOfferCallback {


    private var crFiles = ArrayList<String>()
    private var languages: Array<String>? = null
    private var crFileName: String? = null
    private var selectedLang: String? = null
    private var sdk: IHCEEngine? = null
    private var mCardData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        start_reading.setOnClickListener(this)
        retrieve_offer.setOnClickListener(this)
        get_buffer.setOnClickListener(this)
        sdk = HCEEngine.getInstance()

        sp_cr.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                /*languages[position]*/
                simpleProgressBar.progress = 0
                crFileName = "cr/" + crFiles[position]
                mCardData = Utils.convertStreamToString(assets.open(crFileName!!))

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        sp_lang.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                /*languages[position]*/
                selectedLang = languages!![position]

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        getAllFiles();
    }

    private fun getAllFiles() {
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
                    crFiles.add(filename)
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        languages = resources.getStringArray(R.array.Languages)
        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crFiles)
        val langAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages)
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // attaching data adapter to spinner
        sp_cr.adapter = dataAdapter
        sp_lang.adapter = langAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start_reading -> {

                if (!mCardData.isNullOrEmpty()) {
                    simpleProgressBar.progress = 28
                    sdk!!.startReading(ReadParameters(SourceType.HCE, mCardData), this)
                }
            }
            R.id.retrieve_offer -> {
                if (!mCardData.isNullOrEmpty()) {
                    simpleProgressBar.progress = 28
                    sdk!!.retrieveRemoteOffer(ReadParameters(SourceType.HCE, mCardData), this)
                }
            }
            R.id.get_buffer -> {
                val buffers = sdk!!.GetBuffers(this)
                val bufferData = Gson().fromJson(buffers, BufferData::class.java)
                Log.i("BufferData", Gson().toJson(bufferData))
                mCardData = convertBufferToCRFormat(bufferData)
                Toast.makeText(this, "MCard buffer loaded", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convertBufferToCRFormat(buffer: BufferData): String {
        val hceCardData = HCECardData()
        hceCardData.answerSelectApplication =
            "6f 2a 84 10 a0 00 00 04 04 01 25 09 01 01 00 00 00 00 00 00 a5 16 bf 0c 13 c7 08 00 00 00 00 27 9B 97 92 53 07 0a 3c 11 42 14 10 01 "
        hceCardData.answerSelectFileRT = "85 17 00 01 00 00 00 12 12 00 00 01 03 01 01 00 7e 7e 7e 00 00 00 00 00 00 "
        val hceRecordFiles = ArrayList<HCERecordFile>()

        for (bufRecordFile in buffer.buffersImage.recordFiles) {
            val hceFile = HCERecordFile()
            val hceRecordDataList = ArrayList<HCERecordData>()

            for (bufRecData in bufRecordFile.recordData.record) {
                val hceRecordData = HCERecordData()
                hceRecordData.record = bufRecData
                hceRecordDataList.add(hceRecordData)
            }

            hceFile.sfi = bufRecordFile.sfi
            hceFile.recordData = hceRecordDataList
            hceRecordFiles.add(hceFile)
        }
        hceCardData.recordFiles = hceRecordFiles
        return Gson().toJson(hceCardData)
    }

    override fun onStarted() {
        simpleProgressBar.progress = 47;
    }

    override fun onEnded(cardParsedContent: String?) {
        Log.i("NSBF", cardParsedContent)
        //Toast.makeText(this, "Read Complete", Toast.LENGTH_SHORT).show()
        simpleProgressBar.progress = 100;
        val mData = Gson().fromJson(cardParsedContent, HCECardResult::class.java)
        val cdList = mData.contracts
        val newContracts = ArrayList<ContractResult>()
        for (contract in cdList) {
            val productDesc = contract.contractDescriptions
            val newProductDesc = ProductDescription()
            when (selectedLang) {
                "FR" -> {
                    newProductDesc.fr = productDesc.fr
                    contract.contractDescriptions = newProductDesc
                }
                "EN" -> {
                    newProductDesc.en = productDesc.en
                    contract.contractDescriptions = newProductDesc
                }
                "DE" -> {
                    newProductDesc.de = productDesc.de
                    contract.contractDescriptions = newProductDesc
                }
                "ES" -> {
                    newProductDesc.es = productDesc.es
                    contract.contractDescriptions = newProductDesc
                }
            }
            newContracts.add(contract)
        }
        mData.contracts = newContracts
        Log.i("NSBF DESC", Gson().toJson(mData))
        val intent = Intent(this, StartReadingDetailActivity::class.java)
        //intent.putExtra("DATA_BUNDLE", mData)
        intent.putExtra("DATA", Gson().toJson(mData))
        startActivity(intent)
    }

    override fun onError(error: Failure?) {
        Log.i("NSBF", "Failed")
        Handler(mainLooper).post(Runnable {
            simpleProgressBar.progress = 0
            Toast.makeText(this, "Read Error or CR Data not found", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onTimeOut() {
    }

    override fun onReadTerminated() {
    }

    override fun onContractReceived(articlesData: String?) {
        simpleProgressBar.progress = 50
        Log.i("NSBF", articlesData)
        simpleProgressBar.progress = 70
//        Gson().fromJson(articlesData, TypeToken<List<RemoteResponse>>() {}.type)
        val playerArray = Gson().fromJson(articlesData, Array<RemoteResponse>::class.java)
        simpleProgressBar.progress = 100

        if(playerArray.size > 0) {
            val intent = Intent(this, ArticlesActivity::class.java)
            intent.putExtra("DATA", articlesData)
            startActivity(intent)
        }else{
            Handler(mainLooper).post(Runnable {
                simpleProgressBar.progress = 0
                Toast.makeText(this, "No Product found on Remote Data", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onError(error: Failure?, message: String?) {
        Handler(mainLooper).post(Runnable {
            simpleProgressBar.progress = 0
            Toast.makeText(this, "Read Error or CR Data not found", Toast.LENGTH_SHORT).show()
        })
    }
}
