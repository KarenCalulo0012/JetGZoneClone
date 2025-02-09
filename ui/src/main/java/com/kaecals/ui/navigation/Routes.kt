package com.kaecals.ui.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object MoreRoute : Route

@Serializable
data object HomeRoute : Route

@Serializable
data object PromoRoute : Route

@Serializable
data object WalletRoute: Route

@Serializable
data object AccountRoute: Route

@Serializable
data object LoginRoute: Route

