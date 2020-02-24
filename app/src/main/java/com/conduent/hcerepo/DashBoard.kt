package com.conduent.hcerepo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.conduent.hcesdk.*
import com.conduent.hcesdk.core.HCEEngine
import com.conduent.hcesdk.core.IHCEEngine
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        start_reading.setOnClickListener(this)
        retrieve_offer.setOnClickListener(this)
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
                if (!crFileName.isNullOrEmpty()) {
                    simpleProgressBar.progress = 28;
                    val crStr = Utils.convertStreamToString(assets.open(crFileName!!))
                    sdk!!.startReading(ReadParameters(SourceType.HCE, crStr), this)
                }
            }
            R.id.retrieve_offer -> {
                if (!crFileName.isNullOrEmpty()) {
                    val crStr = Utils.convertStreamToString(assets.open(crFileName!!))
                    sdk!!.retrieveRemoteOffer(ReadParameters(SourceType.HCE, crStr), this)
                }
            }
        }
    }

    override fun onStarted() {
        simpleProgressBar.progress = 47;
    }

    override fun onEnded(cardParsedContent: String?) {
        Log.i("NSBF", cardParsedContent)
        Toast.makeText(this, "Read Complete", Toast.LENGTH_SHORT).show()
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
        Toast.makeText(this, "Read Error", Toast.LENGTH_LONG).show()
    }

    override fun onTimeOut() {
    }

    override fun onRetrieveRemoteOffer() {
    }

    override fun onRetrieveRemoteOfferError(error: Failure?) {
    }

}
