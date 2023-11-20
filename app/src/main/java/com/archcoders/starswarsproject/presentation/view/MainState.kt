package com.archcoders.starswarsproject.presentation.view

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.archcoders.domain.model.CharacterDO
import com.archcoders.starswarsproject.R
import com.archcoders.starswarsproject.data.database.CharacterDB
import com.archcoders.starswarsproject.data.model.ErrorModel
import com.archcoders.starswarsproject.utils.Constants.CHARACTER_ID
import com.archcoders.starswarsproject.utils.PermissionRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.buildState(
    context: Context,
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) = MainState(context, scope, navController, locationPermissionRequester)

class MainState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequester: PermissionRequester
) {

    fun onCharacterClicked(character: CharacterDO) {
        val bundle = Bundle()
        bundle.putSerializable(CHARACTER_ID,character.id)
        navController.navigate(R.id.detail_dest,bundle)

    }

    fun requestLocationPermission(afterRequest: (Boolean) -> Unit) {
        scope.launch {
            locationPermissionRequester.request(afterRequest)
        }
    }

    fun errorToString(error: ErrorModel) = when (error) {
        is ErrorModel.Connectivity -> context.getString(R.string.connectivity_error)
        is ErrorModel.Server -> context.getString(R.string.server_error) + error.code
        is ErrorModel.Unknown -> context.getString(R.string.unknown_error) + error.message
    }


}