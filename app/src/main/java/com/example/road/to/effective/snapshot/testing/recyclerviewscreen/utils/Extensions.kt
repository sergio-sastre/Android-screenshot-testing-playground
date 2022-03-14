package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.TextUtils
import android.util.LayoutDirection
import android.util.LayoutDirection.LTR
import android.util.LayoutDirection.RTL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.data.Language
import java.text.NumberFormat
import java.util.*

fun Language.getCountryFlag(): Int {
    return when (this) {
        Language.Spanish -> R.drawable.ic_spain_flag_48dp
        Language.English -> R.drawable.ic_uk_flag_48dp
        Language.German -> R.drawable.ic_germany_flag_48dp
        Language.Russian -> R.drawable.ic_russia_flag_48dp
        Language.Portuguese -> R.drawable.ic_portugal_flag_48dp
        Language.French -> R.drawable.ic_france_flag_48dp
        Language.Italian -> R.drawable.ic_italy_flag_48dp
    }
}

fun ImageView.setFlagImage(language: Language) {
    val langFlag = language.getCountryFlag()
    setImageResource(langFlag)
}

fun ImageView.setLandmarkImage(landmark: Int, language: Language) {
    val langLandmarkId = when (language) {
        Language.Spanish -> null
        Language.English -> R.drawable.ic_uk_gherkin
        Language.German -> R.drawable.ic_germany_berlin_tower
        Language.Russian -> R.drawable.ic_russia_moscow_cathedral
        Language.Portuguese -> null
        Language.French -> null
        Language.Italian -> null
    }
    langLandmarkId?.let { setImageResource(it) }
}

fun IntArray.bubbleSortDescending(doOnSwap: (initPos: Int, finalPos: Int) -> Unit = { _, _ -> }): IntArray {
    var swap = true
    var swapCounter = 0
    while (swap) {
        swap = false
        for (i in size - 1 downTo 1) {
            if (this[i] > this[i - 1]) {
                val temp = this[i]
                this[i] = this[i - 1]
                this[i - 1] = temp
                swapCounter++
                swap = true
                if (i == 1) {
                    doOnSwap(swapCounter, i - 1)
                    swapCounter = 0
                }
            } else if (swapCounter > 0) {
                doOnSwap(i + swapCounter, i)
                swapCounter = 0
            }
        }
    }
    return this
}

private val suffixes: NavigableMap<Long, Int> = TreeMap(
    mapOf(
        Pair(1_000L, R.string.thousands),
        Pair(1_000_000L, R.string.millions),
        Pair(1_000_000_000L, R.string.billions),
        Pair(1_000_000_000_000L, R.string.trillions),
        Pair(1_000_000_000_000_000L, R.string.million_millions),
        Pair(1_000_000_000_000_000_000L, R.string.million_billion)
    )
)

fun Long.asShortString(
    context: Context,
): String {
    //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
    val locale = context.mainLocale()
    val numberFormat = NumberFormat.getInstance(locale)
    if (this == Long.MIN_VALUE) return (Long.MIN_VALUE + 1).asShortString(context)
    if (this < 0) return "-" + (-this).asShortString(context)
    if (this < 1_000) return numberFormat.format(this) //deal with easy case
    val e = suffixes.floorEntry(this) ?: return ""
    val divideBy = e.key
    val suffix = context.getString(e.value)
    val truncated = this / (divideBy / 10) //the number part of the output times 10
    val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
    return if (hasDecimal) {
        numberFormat.format(truncated / 10.0) + suffix
    } else numberFormat.format(truncated / 10) + suffix
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(this.context).inflate(layoutRes, this, false)

fun Int.px(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.dp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun <K, V> Map<K, Collection<V>>.sortByValueSize(): Map<K, Collection<V>> =
    this.toList().sortedByDescending { (_, value) -> value.size }.toMap()

fun Context.mainLocale() =
    if (Build.VERSION.SDK_INT >= 24) {
        this.resources.configuration.locales[0]
    } else {
        this.resources.configuration.locale
    }

/**
 * Returns the same set but with {@param item} to the set if does not contain item, or without it otherwise
 */
fun <T> Set<T>.addOrRemove(item: T): Set<T> = toMutableSet().apply {
    if (this.contains(item)) {
        remove(item)
    } else {
        add(item)
    }
}