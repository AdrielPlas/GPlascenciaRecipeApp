package com.pjasoft.recipeapp.domain.dtos.utils

import androidx.compose.ui.focus.FocusManager

actual fun hideKeyboard(focusManager: FocusManager) {
    focusManager.clearFocus()
}