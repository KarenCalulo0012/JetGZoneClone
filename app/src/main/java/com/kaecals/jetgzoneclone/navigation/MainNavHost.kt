package com.kaecals.jetgzoneclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kaecals.ui.navigation.AccountRoute
import com.kaecals.ui.navigation.HomeRoute
import com.kaecals.ui.navigation.MoreRoute
import com.kaecals.ui.navigation.PromoRoute
import com.kaecals.ui.navigation.WalletRoute
import com.kaecals.ui.screen.AccountScreen
import com.kaecals.ui.screen.HomeScreen
import com.kaecals.ui.screen.MoreScreen
import com.kaecals.ui.screen.PromoScreen
import com.kaecals.ui.screen.WalletScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<MoreRoute> { MoreScreen() }
        composable<HomeRoute> { HomeScreen() }
        composable<PromoRoute> { PromoScreen() }
        composable<WalletRoute> { WalletScreen() }
        composable<AccountRoute> { AccountScreen() }
    }
}