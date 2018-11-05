package kioli.myalgia.common.di

import android.content.Context
import android.util.AttributeSet
import kioli.myalgia.common.component.ElementContainer
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

open class InjectedContainer @JvmOverloads constructor(context: Context,
                                                       attrs: AttributeSet? = null,
                                                       defStyleAttr: Int = 0)
    : ElementContainer(context, attrs, defStyleAttr), KodeinAware {

    // closestKodein() automatically fetches activity Kodein scope.
    private val layoutKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(layoutKodein)
        import(viewModule())
    }

    open fun viewModule() = Kodein.Module {}

}