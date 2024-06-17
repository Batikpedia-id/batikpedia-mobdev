//package com.dicoding.batikpedia.ui.model
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.dicoding.batikpedia.api.response.PredictResponse
//import com.dicoding.batikpedia.api.retrofit.ApiConfig
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.asRequestBody
//import java.io.File
//
//class ResultViewModel : ViewModel() {
//    private val _batikListResponse = MutableLiveData<BatikListResponse>()
//    val batikListResponse: LiveData<BatikListResponse> = _batikListResponse
//
//    private val _predictResponse = MutableLiveData<PredictResponse>()
//    val predictResponse: LiveData<PredictResponse> = _predictResponse
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun getMotif(motif: String?) {
//        if (motif == null) {
//            Log.e(TAG, "motif cannot be null")
//            return
//        }
//
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val motifPart = MultipartBody.Part.createFormData("motif", motif)
//                val response = withContext(Dispatchers.IO) {
//                    ApiConfig.connectToApiService().getNamaMotif(motifPart)
//                }
//                _isLoading.value = false
//                _batikListResponse.value = response
//            } catch (e: Exception) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${e.message}")
//            }
//        }
//    }
//
//    fun predictBatik(imageFile: File) {
//        _isLoading.value = true
//        viewModelScope.launch {
//            try {
//                val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//                val imagePart = MultipartBody.Part.createFormData("photo", imageFile.name, requestImageFile)
//
//                val response = withContext(Dispatchers.IO) {
//                    ApiConfig.connectToApiService().predictMotif(imagePart)
//                }
//                _isLoading.value = false
//                _predictResponse.value = response
//            } catch (e: Exception) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${e.message}")
//            }
//        }
//    }
//
//    companion object {
//        const val TAG = "ResultViewModel"
//    }
//}