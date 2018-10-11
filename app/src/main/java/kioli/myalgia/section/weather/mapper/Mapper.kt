package kioli.myalgia.section.weather.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <P> the PRESENTATION model input type
 * @param <D> the STORED model return type
 */
interface Mapper<P, S> {

    fun mapToStored(type: P): S

    fun mapToPresentation(type: S): P

}