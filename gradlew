package com.conduent.bw.modules.reimbursemyself.hsa

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.conduent.bw.R
import com.conduent.bw.modules.base.BaseFragment
import com.conduent.bw.modules.common.dialog.ModelDialog
import com.conduent.bw.utils.BWConstants
import com.conduent.bw.utils.Utils
import kotlinx.android.synthetic.main.fragment_hsa_summary_info.*
import kotlinx.android.synthetic.main.layout_mini_account_card.*
import kotlinx.android.synthetic.main.layout_model_dialog.*
import kotlinx.android.synthetic.main.layout_submit_back_btns.*

class HSASummaryInfoFragment : BaseFragment(), View.OnClickListener {

    val TAG: String = HSASummaryInfoFragment::class.java.simpleName

    /**
     * Callback interface to handle events
     */
    interface HSASummaryInfoListener {
        fun handleHSASummaryInfoSubmit()
        fun handleHSASummaryInfoBack()
    }

    private var callback: HSASummaryInfoListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hsa_summary_info, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HSASummaryInfoListener) {
            callback = context
        } else {
            throw RuntimeException("$context must implement HSASummaryInfoListener")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        btn_submit.setOnClickListener(this)
        btn_back.setOnClickListener(this)
        setUpData()
    }


    @SuppressLint("SetTextI18n")
    private fun setUpData() {

        setMiniCardData(ll_account_card, activity!!, HSAReimburseMyselfActivity.bundleData.hsaAccountData)
        tv_amount.text =
            Utils.appendDollar(context!!, HSAReimburseMyselfActivity.bundleData.amountToReimburse)

        if (HSAReimburseMyselfActivity.bundleData.paymentType == 1) {
            tv_depositing_paying.text = getString(R.string.depositing_to_text)
            tv_account_details.text = HSAReimburseMyselfActivity.bundleData.maskedBankAccountNo
            tv_payment_type.text = getString(R.string.electronic_transfer)
        } else {
            tv_depositing_paying.text = getString(R.string.paying_to)
            tv_account_details.text = String.format(
                resources.getString(R.string.address_text),
                HSAReimburseMyselfActivity.bundleData.bankAccountProfile?.address?.addressLine1,
                HSAReimburseMyselfActivity.bundleData.bankAccountProfile?.address?.addressLine2,
                HSAReimburseMyselfActivity.bundleData.bankAccountProfile?.address?.city,
                HSAReimburseMyselfActivity.bundleData.bankAccountProfile?.address?.state,
                HSAReimburseMyselfActivity.bundleData.bankAccountProfile?.address?.zip
            )
            tv_payment_type.text = getString(R.string.paper_check)
        }

        if (HSAReimburseMyselfActivity.bundleData.frequency == BWConstants.RecurringType.RECURRING.type) {
            ll_recurring.visibility = View.VISIBLE
            tv_stat_date.text = HSAReimburseMyselfActivity.bundleData.issueDate
            tv_end_date.text = HSAReimburseMyselfActivity.bundleData.endDate
            tv_payment_schedule.text = HSAReimburseMyselfActivity.bundleData.paymentSchedule
            tv_total_payments.text = HSAReimburseMyselfActivity.bundleData.numberOfPayments
            val totalContribution =
                HSAReimburseMyselfActivity.bundleData.numberOfPayments.toInt() * HSAReimburseMyselfActivity.bundleData.amountToReimburse.toFloat()
            tv_total_amount.text = Utils.appendDollar(this.requireContext(), totalContribution.toString())
            tv_frequency.text = getString(R.string.recurring_text)
        } else {
            ll_recurring.visibility = View.GONE
            tv_total_amount.text =
                Utils.appendDollar(context!!, HSAReimburseMyselfActivity.bundleData.amountToReimburse)
            tv_frequency.text = getString(R.string.one_time_text2)
            tv_total_amount.text =
                Utils.appendDollar(context!!, HSAReimburseMyselfActivity.bundleData.amountToReimburse)
        }


        if (HSAReimburseMyselfActivity.bundleData.memo.isNotEmpty()) {
            ll_transaction_memo.visibility = View.VISIBLE
            tv_transaction_memo.text = HSAReimburseMyselfActivity.bundleData.memo
        } else {
            ll_transaction_memo.visibility = View.GONE
        }

        if (HSAReimburseMyselfActivity.bundleData.notes.isNotEmpty()) {
            ll_note_to_payee.visibility = View.VISIBLE
            tv_note_to_payee.text = HSAReimburseMyselfActivity.bundleData.notes
        } else {
            ll_note_to_payee.visibility = View.GONE
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.