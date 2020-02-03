package com.example.test.model.adapters

import android.text.InputFilter
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ApplicationClass
import com.example.test.R
import com.example.test.extension.scaleTo
import com.example.test.presentation.model.RateLocalModel
import com.example.test.utils.DecimalDigitsInputFilter
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.list_item_currency.view.*
import java.util.concurrent.TimeUnit


class CurrencyAdapter(
    private val currencyChanges: (RateLocalModel, CharSequence) -> Unit,
    private val click: (RateLocalModel) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var mCurrenciesList: ArrayList<RateLocalModel> = arrayListOf()
    private var selectedPosition = 0

    fun updateRates(list: List<RateLocalModel>) {

        val authorDiffUtilCallback = RateDiffUtilCallback(mCurrenciesList, list)
        val authorDiffResult = DiffUtil.calculateDiff(authorDiffUtilCallback, true)
        mCurrenciesList = ArrayList(list)
        authorDiffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder is ViewHolderMeasureInstrList) {

            (mCurrenciesList[position]).also { rateModel ->

                with(holder.inputTxt) {

                    this.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(2))
                    this.setText(rateModel.value.toFloat().scaleTo(2))
                    this.setOnTouchListener { v, event ->
                        if (event.action == MotionEvent.ACTION_UP)
                            click(rateModel)
                        false

                    }

                    if (this.text.length >= selectedPosition)
                        this.setSelection(selectedPosition)

                    RxTextView.textChanges(this)
                        .skip(1)
                        .filter { rateModel.active && this.hasFocus() }
                        .debounce(150, TimeUnit.MILLISECONDS)
                        .filter { it.isNotEmpty() }
                        .subscribe({ inputText ->
                            selectedPosition = this.selectionStart
                            currencyChanges.invoke(rateModel, inputText)
                        }, {
                            it.printStackTrace()
                        })
                }

                holder.codeTxt.text = rateModel.code
                holder.description.text = rateModel.description
                holder.flag.setImageDrawable(ApplicationClass.instanse.getDrawable(rateModel.flag))

                holder.layout.setOnClickListener {
                    click(rateModel)
                    holder.inputTxt.requestFocus()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh =
            ViewHolderMeasureInstrList(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_currency,
                    parent,
                    false
                )
            )
        return vh
    }


    class ViewHolderMeasureInstrList(itemView: View) : ViewHolder(itemView) {
        val inputTxt = itemView.currencyInputTxt
        val codeTxt = itemView.currencyCodeTxt
        val layout = itemView.currencyLayout
        val description = itemView.currencyDescriptionTxt
        val flag = itemView.currencyFlagTxt
    }

    open class ViewHolder(mViewGroup: View) : RecyclerView.ViewHolder(mViewGroup)

    override fun getItemCount(): Int {
        return mCurrenciesList.size
    }


}