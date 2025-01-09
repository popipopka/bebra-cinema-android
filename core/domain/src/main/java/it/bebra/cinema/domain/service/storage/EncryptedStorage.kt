package it.bebra.cinema.domain.service.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedStorage(
    private val context: Context
) {
    companion object {
        const val FILE_NAME = "secret_shared_prefs"
    }

    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences
        .create(
            context,
            FILE_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun getString(key: StorageKeys) = encryptedSharedPreferences.getString(key.toString(), null)

    fun putString(key: StorageKeys, value: String) {
        encryptedSharedPreferences.edit(true) {
            putString(key.toString(), value)
        }
    }

    fun remove(key: StorageKeys) {
        encryptedSharedPreferences.edit(true) {
            remove(key.toString())
        }
    }
}