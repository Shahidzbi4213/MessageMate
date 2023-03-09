package com.example.messagemate.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagemate.model.User
import com.example.messagemate.ui.components.TopApp
import com.example.messagemate.ui.screens.destinations.ChatDetailScreenDestination
import com.example.messagemate.ui.screens.destinations.ProfileScreenDestination
import com.example.messagemate.ui.screens.mainsub.Calls
import com.example.messagemate.ui.screens.mainsub.Chats
import com.example.messagemate.ui.screens.mainsub.Status
import com.example.messagemate.ui.theme.MainBlue
import com.example.messagemate.ui.theme.MainWhite
import com.example.messagemate.ui.theme.MainYellow
import com.example.messagemate.utils.TabItem

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/4/2023.

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun MainScreen(navigator: DestinationsNavigator) {

    val list = listOf(TabItem.Chats, TabItem.Status, TabItem.Calls)
    val pagerState = rememberPagerState()


    Column(modifier = Modifier.fillMaxSize()) {
        TopApp(navigator = navigator)
        Tabs(pagerState = pagerState, list = list)
        TabsContent(pagerState = pagerState, list = list,navigator)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, list: List<TabItem>, navigator: DestinationsNavigator) {

    HorizontalPager(
        pageCount = list.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) {

        when (it) {
            0 -> Chats{ user ->
                navigator.navigate(ChatDetailScreenDestination(user))
            }
            1 -> Status()
            2 -> Calls()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState, list: List<TabItem>) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = MainBlue,
        contentColor = MainWhite,
        indicator = {
            TabRowDefaults.Indicator(
                color = MainYellow,
                height = 3.dp,
                modifier = Modifier.tabIndicatorOffset(
                    it[pagerState.currentPage]
                )
            )
        }
    ) {
        list.forEachIndexed { index, tab ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)

                }
            }, text = {
                Text(
                    text = tab.tabName, fontWeight = FontWeight.Bold, fontSize = 14.sp,
                    color = MainWhite
                )
            })
        }
    }
}
