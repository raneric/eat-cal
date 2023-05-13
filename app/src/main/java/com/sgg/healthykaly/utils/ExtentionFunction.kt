package com.sgg.healthykaly.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgg.healthykaly.ui.adapter.FindListAdapter


/**
 * Checks if the RecyclerView is scrolled to the top of the RecyclerView.
 *
 * @return true if the top item is visible, false otherwise.
 */
fun LinearLayoutManager.isAtTop(): Boolean {
    return this.findFirstVisibleItemPosition() == 0
}

/**
 * Checks if the CombinedLoadStates represents a loading state.
 *
 * @return true if the refresh load state is Loading, false otherwise.
 */
fun CombinedLoadStates.isLoading(): Boolean {
    return this.refresh is LoadState.Loading
}

/**
 * Checks if the CombinedLoadStates represents an error state.
 *
 * @return true if the refresh load state is Error, false otherwise.
 */
fun CombinedLoadStates.isError(): Boolean {
    return this.refresh is LoadState.Error
}

/**
 * Checks if the FindListAdapter is empty.
 *
 * @return true if the adapter has no items, false otherwise.
 */
fun FindListAdapter.isEmpty(): Boolean {
    return this.itemCount == 0
}

/**
 * Checks if the FindListAdapter is not empty.
 *
 * @return true if the adapter has no items, false otherwise.
 */
fun FindListAdapter.isNotEmpty(): Boolean {
    return this.itemCount > 0
}