package kioli.myalgia.common.di

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

open class InjectedConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ConstraintLayout(context, attrs), KodeinAware {

    // closestKodein() automatically fetches activity Kodein scope.
    private val layoutKodein by closestKodein()

    override val kodein = Kodein.lazy {
        extend(layoutKodein)
        import(viewModule())
    }

    open fun viewModule() = Kodein.Module {}

}