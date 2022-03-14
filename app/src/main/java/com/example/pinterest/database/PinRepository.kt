package com.example.pinterest.database

import android.app.Application
import com.example.pinterest.model.Pin
import com.example.pinterestappclone.managers.RoomManager

class PinRepository(application: Application) {

    private val dp = RoomManager.getInstance(application)
    private var pinDao = dp.pinDao()

    fun savePhoto(pin: Pin) {
        pinDao.savePhoto(pin)
    }

    fun getAllSavedPhotos(): List<Pin> {
        return pinDao.getAllSavedPhotos()
    }

    fun clearSavedPhotos() {
        pinDao.clearSavedPhotos()
    }

    fun removeSavedPhotos(id: Int) {
        pinDao.removeSavedPhotos(id)
    }

}