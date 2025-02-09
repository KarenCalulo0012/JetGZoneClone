package com.kaecals.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.kaecals.ui.navigation.AccountRoute
import com.kaecals.ui.navigation.HomeRoute
import com.kaecals.ui.navigation.MoreRoute
import com.kaecals.ui.navigation.PromoRoute
import com.kaecals.ui.navigation.Route
import com.kaecals.ui.navigation.WalletRoute

data class BottomNavItem(
    val drawable: ImageVector,
    val name: String,
    val route: Route,
)

val navigationItem =
    listOf(
        BottomNavItem(drawable = Icons.Default.Menu, name = "More", route = MoreRoute),
        BottomNavItem(drawable = Icons.Default.Home, name = "Home", route = HomeRoute),
        BottomNavItem(drawable = Icons.Default.Favorite, name = "Promo", route = PromoRoute),
        BottomNavItem(drawable = Icons.Default.ShoppingCart, name = "Wallet", route = WalletRoute),
        BottomNavItem(drawable = Icons.Default.Person, name = "Account", route = AccountRoute),
    )
