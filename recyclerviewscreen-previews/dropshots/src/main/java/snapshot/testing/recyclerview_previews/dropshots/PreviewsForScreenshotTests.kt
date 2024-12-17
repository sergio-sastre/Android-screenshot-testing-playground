package snapshot.testing.recyclerview_previews.dropshots

import android.content.res.Configuration
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.viewinterop.AndroidView
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import snapshot.testing.recyclerview_previews.dropshots.utils.DropshotsConfig
import snapshot.testing.recyclerview_previews.shot.utils.ThemeProvider
import snapshot.testing.recyclerview_previews.shot.utils.TrainingPreviewInteractionListener
import snapshot.testing.recyclerview_previews.shot.utils.emptyItem
import snapshot.testing.recyclerview_previews.shot.utils.generateMemoriseItem
import snapshot.testing.recyclerview_previews.shot.utils.initialTrainingItem

@DropshotsConfig(comparisonThreshold = 0.85f)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, apiLevel = 31)
@Composable
fun MemoriseTextViewHolderPreview() {

    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val contextWrapper =
                ContextThemeWrapper(context, R.style.Theme_RoadToEffectiveSnapshotTesting)
            val containerParent = FrameLayout(contextWrapper)
            val container =
                LayoutInflater.from(contextWrapper).inflate(R.layout.memorise_row, containerParent)

            val viewHolder = MemoriseViewHolder(
                container = container,
                itemEventListener = null,
                animationDelay = 0L
            )

            viewHolder.bind(
                generateMemoriseItem(
                    rightAligned = false,
                    activity = context,
                    titleSuffix = " API ${Build.VERSION.SDK_INT}"
                )
            )

            viewHolder.itemView
        },
    )
}

@DropshotsConfig(comparisonThreshold = 0.85f)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "b+sr+Latn")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, locale = "b+sr+Cyrl")
@Composable
fun TrainingViewHolderPreview(
    @PreviewParameter(provider = ThemeProvider::class) theme: Int
) {
    val oldTrainingItem = remember { mutableStateOf(initialTrainingItem) }

    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val contextWrapper = ContextThemeWrapper(context, theme)
            val parentContainer = FrameLayout(contextWrapper)
            val container =
                LayoutInflater.from(contextWrapper).inflate(R.layout.training_row, parentContainer)

            val viewHolder = TrainingViewHolder(
                container = container
            )

            viewHolder.bind(
                item = initialTrainingItem,
                languageClickedListener =
                TrainingPreviewInteractionListener(oldTrainingItem) { newTrainingItemPayload ->
                    viewHolder.update(newTrainingItemPayload)
                }
            )

            viewHolder.itemView
        }
    )
}

@DropshotsConfig(comparisonThreshold = 0.85f)
@Preview(locale = "b+ar+XB")
@Preview(locale = "b+en+XA")
@Composable
fun TrainingViewHolderEmptyStatePreview() {

    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val contextWrapper =
                ContextThemeWrapper(context, R.style.Theme_RoadToEffectiveSnapshotTesting)
            val parentContainer = FrameLayout(contextWrapper)
            val container =
                LayoutInflater.from(contextWrapper).inflate(R.layout.training_row, parentContainer)

            val viewHolder = TrainingViewHolder(container = container)

            viewHolder.bind(
                item = emptyItem,
                languageClickedListener = null
            )

            viewHolder.itemView
        }
    )
}