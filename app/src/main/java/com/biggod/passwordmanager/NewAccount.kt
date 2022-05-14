package com.biggod.passwordmanager

import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import com.biggod.passwordmanager.databinding.ActivityNewAccountBinding
import org.json.JSONObject
import java.io.File
import java.nio.charset.StandardCharsets


class NewAccount : AppCompatActivity() {

    private lateinit var binding: ActivityNewAccountBinding
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        binding.accountViewModel = viewModel
        binding.lifecycleOwner = this

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("New Account")

        binding.add.setOnClickListener {
            viewModel.onSubmit()
            val newAccount = JSONObject(viewModel.toMap()).toString()

            val masterKey = MasterKey.Builder(this, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()


            val fileToWrite = "credentials.txt"
            val encryptedFile = EncryptedFile.Builder(
                this,
                File(filesDir, fileToWrite),
                masterKey,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val fileContent = newAccount.toByteArray(StandardCharsets.UTF_8)
            encryptedFile.openFileOutput().apply {
                write(fileContent)
                flush()
                close()
            }

        }


    }
}