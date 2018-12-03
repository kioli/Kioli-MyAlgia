package kioli.myalgia.common.mapper

/**
 * Interface for model mappers.
 * It provides helper methods that facilitate retrieving of models from outer data source layers
 *
 * @param <L> the LEFT model input type
 * @param <R> the RIGHT model return type
 */
interface Mapper<L, R> {

    fun mapToRight(type: L): R

    fun mapToLeft(type: R): L

}