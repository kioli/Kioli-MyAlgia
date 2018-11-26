package kioli.myalgia.element.pain

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import kioli.myalgia.R
import kioli.myalgia.common.component.CheckableImageView
import kioli.myalgia.common.di.InjectedContainer

internal class MoodElement(context: Context) : InjectedContainer(context) {

    private val moodIcons by lazy {
        listOf(R.drawable.ic_mood1,
                R.drawable.ic_mood2,
                R.drawable.ic_mood3,
                R.drawable.ic_mood4,
                R.drawable.ic_mood5)
    }

    private val moods = arrayListOf(CheckableImageView(context),
            CheckableImageView(context),
            CheckableImageView(context),
            CheckableImageView(context),
            CheckableImageView(context))

    init {
        setTitle("Feeling")
        val content = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            addMoodsToElement(this)
        }
        addView(content)
    }

    private fun addMoodsToElement(layout: LinearLayout) {
        val moodPadding = resources.getDimensionPixelOffset(R.dimen.mood_icon_padding)
        moods.forEachIndexed { index, mood ->
            mood.layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT, 1f).apply {
                setMargins(moodPadding, 0, moodPadding, 0)
            }
            mood.tag = index
            setMoodUnchecked(mood)
            mood.adjustViewBounds = true
            mood.scaleType = ImageView.ScaleType.FIT_CENTER
            mood.setImageDrawable(AppCompatResources.getDrawable(context, moodIcons[index]))
            mood.setOnClickListener { v -> moodSelected(v) }
            layout.addView(mood)
        }
    }

    private fun moodSelected(view: View) {
        moods.forEachIndexed { index, mood ->
            if (mood.tag != view.tag) {
                setMoodUnchecked(mood)
                return@forEachIndexed
            }
            stateManager.setMood(index)
            setMoodChecked(mood)
        }
    }

    private fun setMoodChecked(mood: CheckableImageView) {
        mood.isChecked = true
        mood.setColorFilter(ContextCompat.getColor(context, R.color.mood), PorterDuff.Mode.SRC_IN)
    }

    private fun setMoodUnchecked(mood: CheckableImageView) {
        mood.isChecked = false
        mood.setColorFilter(ContextCompat.getColor(context, R.color.dim), PorterDuff.Mode.SRC_IN)
    }

}
