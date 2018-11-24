package kioli.myalgia.common.di

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

open class InjectedRelativeLayout @JvmOverloads constructor(context: Context,
                                                            attrs: AttributeSet? = null,
                                                            defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr), KodeinAware {

    // closestKodein() automatically fetches activity Kodein scope.
    private val layoutKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(layoutKodein)
        import(linearLayoutModule())
    }

    open fun linearLayoutModule() = Kodein.Module("module generic linear layout", false) {}

}