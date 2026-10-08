package com.example.responsi.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.responsi.ui.components.EmptyState
import com.example.responsi.ui.components.ErrorState
import com.example.responsi.ui.components.FilterChipRow
import com.example.responsi.ui.components.LoadingState
import com.example.responsi.ui.components.MealCard
import com.example.responsi.ui.components.SearchBarComponent
import com.example.responsi.ui.components.TopHeaderBar
import com.example.responsi.ui.theme.TerracottaPrimary
import com.example.responsi.ui.theme.TextPrimary

@Composable
fun HomeScreen(
    onMealClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val favoriteMealIds by viewModel.favoriteMealIds.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopHeaderBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBarComponent(
                query = searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                onSearch = { viewModel.loadMeals(it) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            FilterChipRow(
                categories = viewModel.categoriesList,
                selectedCategory = selectedCategory,
                onCategorySelected = { viewModel.onCategorySelected(it) },
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (val state = uiState) {
                is HomeUiState.Loading -> {
                    LoadingState(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }

                is HomeUiState.Error -> {
                    ErrorState(
                        errorMessage = state.message,
                        onRetry = { viewModel.loadMeals(searchQuery) },
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }

                is HomeUiState.Success -> {
                    if (state.meals.isEmpty()) {
                        EmptyState(
                            query = searchQuery,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Resep Populer Dunia",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(Color(0xFFFCEEE6))
                                                .padding(horizontal = 8.dp, vertical = 3.dp)
                                        ) {
                                            Text(
                                                text = "${state.meals.size} Resep",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TerracottaPrimary
                                            )
                                        }
                                    }

                                    TextButton(onClick = { }) {
                                        Text(
                                            text = "Lihat Semua",
                                            fontSize = 13.sp,
                                            color = TerracottaPrimary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }

                            items(
                                items = state.meals,
                                key = { it.id }
                            ) { meal ->
                                MealCard(
                                    meal = meal,
                                    onMealClick = onMealClick,
                                    isFavorite = favoriteMealIds.contains(meal.id),
                                    onFavoriteToggle = { viewModel.toggleFavorite(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
