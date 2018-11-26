package kioli.myalgia.section.history.repository

import kioli.myalgia.common.error.MyError

internal class HistoryEmpty : MyError.FeatureError()

internal class HistoryErrorSaving : MyError.FeatureError()
