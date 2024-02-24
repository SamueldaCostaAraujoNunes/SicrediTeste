package samuelnunes.com.sicrediteste.presentation.commons.fields_validator

import androidx.annotation.StringRes
import androidx.core.util.Predicate
import samuelnunes.com.sicrediteste.R

open class ValidationRule(
    @StringRes val errorMessage: Int = R.string.default_error_message,
    val predicate: Predicate<String>
)

object EmptyTextRule : ValidationRule(
    errorMessage = R.string.text_empty_error_message,
    predicate = { it.isNullOrBlank() })

object EmailTextRule : ValidationRule(
    errorMessage = R.string.email_error_message,
    predicate = { !Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\$").matches(it) }
)

object NameTextRule : ValidationRule(
    errorMessage = R.string.name_error_message,
    predicate = { !Regex("^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$").matches(it) }
)