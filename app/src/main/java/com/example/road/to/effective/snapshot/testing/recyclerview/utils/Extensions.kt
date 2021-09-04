package com.example.road.to.effective.snapshot.testing.recyclerview.utils

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
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

private val suffixes: NavigableMap<Long, String> = TreeMap(
    mapOf(
        Pair(1_000L, "k"),
        Pair(1_000_000L, "M"),
        Pair(1_000_000_000L, "G"),
        Pair(1_000_000_000_000L, "T"),
        Pair(1_000_000_000_000_000L, "P"),
        Pair(1_000_000_000_000_000_000L, "E")
    )
)

fun Long.asShortString(): String {
    //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
    if (this == Long.MIN_VALUE) return (Long.MIN_VALUE + 1).asShortString()
    if (this < 0) return "-" + (-this).asShortString()
    if (this < 1000) return this.toString() //deal with easy case
    val e = suffixes.floorEntry(this) ?: return ""
    val divideBy = e.key
    val suffix = e.value
    val truncated = this / (divideBy / 10) //the number part of the output times 10
    val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
    return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(this.context).inflate(layoutRes, this, false)

fun Int.px(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun <K, V> Map<K, Collection<V>>.sortByValueSize(): Map<K, Collection<V>> =
    this.toList().sortedByDescending { (_, value) -> value.size }.toMap()

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