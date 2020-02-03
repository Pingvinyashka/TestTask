package com.example.test.model.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.test.presentation.model.RateLocalModel

class RateDiffUtilCallback(private val oldList: List<RateLocalModel>?, private val newList: List<RateLocalModel>): DiffUtil.Callback() {
    override fun getOldListSize() = oldList?.size ?: 0
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList?.get(oldItemPosition)?.code == newList[newItemPosition].code
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList?.get(oldItemPosition) == newList[newItemPosition]
}