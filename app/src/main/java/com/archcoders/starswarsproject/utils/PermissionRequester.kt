package com.archcoders.starswarsproject.utils

import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener

class PermissionRequester(private val fragment: Fragment, private val permission: String) {

    fun request(continuation: (Boolean) -> Unit) {
        Dexter
            .withContext(fragment.requireActivity())
            .withPermission(permission)
            .withListener(object : BasePermissionListener() {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    continuation(true)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    continuation(false)
                }
            }
            ).check()
    }
}