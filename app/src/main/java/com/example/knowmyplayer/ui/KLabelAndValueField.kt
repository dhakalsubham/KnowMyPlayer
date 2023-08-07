package com.example.knowmyplayer.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.knowmyplayer.R
import com.example.knowmyplayer.databinding.KLabelAndValueFieldBinding

/**
//   KnowMyPlayer
//   Created by Subham Dhakal on 8/6/23.
//   Copyright © 2023. All rights reserved.
 */
class KLabelAndValueField : LinearLayout {
    private lateinit var binding: KLabelAndValueFieldBinding
    private var label = ""
    private var value = ""


    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initKLabelAndValueField(context, attributeSet)

    }


    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttributeSet: Int) :
            super(context, attributeSet, defStyleAttributeSet) {
        initKLabelAndValueField(context, attributeSet)

    }

    private fun initKLabelAndValueField(context: Context, attributeSet: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.k_label_and_value_field, this, true)
        binding = KLabelAndValueFieldBinding.bind(view)
        val data = context.obtainStyledAttributes(attributeSet, R.styleable.KLabelAndValueField)
        binding.textViewLabel.text = data.getString(R.styleable.KLabelAndValueField_label).toString()
        if (data.getInt(R.styleable.KLabelAndValueField_gravity, -1) != -1){
            binding.linearLayoutFieldValue.gravity = Gravity.CENTER
        }
    }

    fun setValue(value: String) {
        binding.textViewValue.text = value
    }


}