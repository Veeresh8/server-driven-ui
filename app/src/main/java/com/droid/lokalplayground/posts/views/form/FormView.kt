package com.droid.lokalplayground.posts.views.form

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.droid.lokalplayground.R
import com.droid.lokalplayground.posts.Form
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
@AndroidEntryPoint
class FormView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var layoutContainer: LinearLayout
    private var btnSubmit: Button
    private var tvTitle: TextView

    init {
        val view = inflate(context, R.layout.item_form, this)
        layoutContainer = view.findViewById(R.id.viewInputContainer)
        btnSubmit = view.findViewById(R.id.btnAction)
        tvTitle = view.findViewById(R.id.tvTitle)
    }

    @ModelProp
    fun setData(form: Form) {
        tvTitle.text = form.formMeta?.title
        form.formItems.forEach {
            val formData = buildForm(it)
            layoutContainer.addView(formData)
        }
    }

    private fun buildForm(formItem: Form.FormItem): TextInputLayout {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val textInputLayout = TextInputLayout(
            context,
            null,
            com.airbnb.viewmodeladapter.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox
        )
        textInputLayout.hint = formItem.hint
        textInputLayout.layoutParams = layoutParams

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val textInputEditText = TextInputEditText(context)
        when (formItem.type) {
            "number" -> {
                textInputEditText.inputType = InputType.TYPE_CLASS_NUMBER
                textInputEditText.filters = arrayOf<InputFilter>(LengthFilter(formItem.textLimit))
            }
            "text" -> {
                textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
                textInputEditText.filters = arrayOf<InputFilter>(LengthFilter(formItem.textLimit))
            }
        }

        textInputEditText.layoutParams = params

        val padding = resources.getDimension(R.dimen.text_padding).toInt()
        textInputLayout.setPadding(padding, padding, padding, padding)
        textInputLayout.setHintTextAppearance(R.style.MyTextInputLayout)

        val margin = resources.getDimension(R.dimen.text_margin).toInt()
        layoutParams.setMargins(margin, 0, margin, 0)

        val textSize = resources.getDimension(R.dimen.text_size).toInt()
        textInputEditText.textSize = textSize.toFloat()

        textInputLayout.addView(textInputEditText)
        return textInputLayout
    }
}