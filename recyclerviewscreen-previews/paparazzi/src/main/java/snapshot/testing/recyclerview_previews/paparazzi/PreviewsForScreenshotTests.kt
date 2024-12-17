package snapshot.testing.recyclerview_previews.paparazzi

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
import snapshot.testing.recyclerview_previews.paparazzi.utils.PaparazziConfig
import snapshot.testing.recyclerview_previews.paparazzi.utils.PaparazziConfig.RenderingMode
import snapshot.testing.recyclerview_previews.paparazzi.utils.ThemeProvider
import snapshot.testing.recyclerview_previews.paparazzi.utils.TrainingPreviewInteractionListener
import snapshot.testing.recyclerview_previews.paparazzi.utils.emptyItem
import snapshot.testing.recyclerview_previews.paparazzi.utils.generateMemoriseItem
import snapshot.testing.recyclerview_previews.paparazzi.utils.initialTrainingItem

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


@PaparazziConfig(
    enableAccessibility = true,
    renderingMode = RenderingMode.NORMAL
)
@Preview // WARNING: Do not use 'locale' in Preview, or it crashes due to https://github.com/cashapp/paparazzi/issues/1276
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