package kioli.myalgia.common.di

import android.content.Context
import android.util.AttributeSet
import kioli.myalgia.common.component.ElementContainer
import kioli.myalgia.section.main.state.manager.IStateManager
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

internal open class InjectedContainer @JvmOverloads constructor(context: Context,
                                                                attrs: AttributeSet? = null,
                                                                defStyleAttr: Int = 0)
    : ElementContainer(context, attrs, defStyleAttr), KodeinAware {

    // closestKodein() automatically fetches activity Kodein scope.
    private val layoutKodein by closestKodein()

    protected val stateManager by instance<IStateManager>()

    override val kodein = Kodein.lazy {
        extend(layoutKodein)
        import(elementModule())
    }

    open fun elementModule() = Kodein.Module("module generic element", false) {}

}