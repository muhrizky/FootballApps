package id.ac.undip.ce.student.muhammadrizqi.footballapps.util

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}