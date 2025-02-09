package com.kaecals.ui.model

import com.kaecals.jetgzoneclone.ui.R

data class GameCategoryModel(val gameCategoryImage: Int, val gameCategoryName: String)

val defaultGameCategoryList = listOf(
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "All"),
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "Poker"),
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "Slot"),
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "Casino"),
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "Bingo"),
    GameCategoryModel(gameCategoryImage = R.drawable.ic_launcher_foreground, gameCategoryName = "Fishing"),
)
