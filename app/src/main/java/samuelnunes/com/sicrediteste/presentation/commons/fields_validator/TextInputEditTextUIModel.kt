package samuelnunes.com.sicrediteste.presentation.commons.fields_validator

import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

data class TextInputEditTextUIModel(
    val isEnabled: Boolean? = null,
    val errorMessage: String? = null
)

fun TextInputEditText.validateRule(
    rules: List<ValidationRule>,
    callback: ((TextInputEditTextUIModel) -> Unit)? = null
): Boolean {
    val textInputlayout = this.parent.parent as TextInputLayout
    val viewEnabled = textInputlayout.isEnabled

    this.doAfterTextChanged {
        textInputlayout.error = null
        textInputlayout.isErrorEnabled = false
    }

    val validateText = this.text.toString().trim()

    for (i in rules.indices) {
        val isNotValid = rules[i].predicate.test(validateText)
        val message = rules[i].errorMessage

        if (isNotValid) {
            if (!viewEnabled) textInputlayout.isEnabled = true
            textInputlayout.error = context.resources.getString(message)
            callback?.invoke(TextInputEditTextUIModel(isEnabled = viewEnabled))
            return false
        } else continue
    }

    return true

}

