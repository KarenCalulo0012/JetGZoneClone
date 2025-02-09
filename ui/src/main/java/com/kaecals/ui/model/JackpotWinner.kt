package com.kaecals.ui.model

import com.kaecals.jetgzoneclone.ui.R

data class JackpotWinner(
    val avatar: Int,
    val userName: String,
    val time: String,
    val jackpotAmount: Int,
)

val jackpotWinnerList =
    listOf(
        JackpotWinner(R.drawable.ic_launcher_foreground, "Jayson Guillen", "12:21 PM", 119_887_295),
        JackpotWinner(R.drawable.ic_launcher_foreground, "Malvin Garcia", "12:21 PM", 119_887_295),
        JackpotWinner(R.drawable.ic_launcher_foreground, "Stephen Siapno", "12:21 PM", 119_887_295),
        JackpotWinner(R.drawable.ic_launcher_foreground, "Karen Calulo", "12:21 PM", 119_887_295),
        JackpotWinner(R.drawable.ic_launcher_foreground, "Rodelito Tangonan", "12:21 PM", 119_887_295),
    )
