package com.submission.jetsportcars.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.submission.jetsportcars.di.Injection
import com.submission.jetsportcars.model.CarList
import com.submission.jetsportcars.ui.common.UiState
import com.submission.jetsportcars.ui.components.CarItem
import com.submission.jetsportcars.ui.screen.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideCarRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllCars()
            }
            is UiState.Success -> {
                HomeContent(
                    carList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
            }
        }
    }
}

@Composable
fun HomeContent(
    carList: List<CarList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(carList) { data ->
            CarItem(
                brand = data.car.brand,
                logo = data.car.logo,
                founder = data.car.founder,
                modifier = Modifier.clickable {
                    navigateToDetail(data.car.id)
                }
            )
        }
    }
}