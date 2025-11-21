package com.pjasoft.recipeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjasoft.recipeapp.data.KtorfitClient
import com.pjasoft.recipeapp.domain.dtos.Login
import com.pjasoft.recipeapp.domain.dtos.Register
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var userId: Int? = null
        private set

    fun register(
        name:String,
        email:String,
        password:String,
        onResult: (Boolean,String) -> Unit
    ){
        viewModelScope.launch {
            try{
                val service = KtorfitClient.createAuthService()
                val register = Register(
                    name = name,
                    email = email,
                    password = password
                )
                val result = service.register(register)
                if(result.isLogged){
                    userId = result.userId
                    // QUE EL USUARIO SE REGISTRO Y SE LOGUEO
                    println("Logueao")
                    onResult(true,result.message)
                    println(result.toString())
                }
                else{
                    // OCURRIO UN ERROR
                    onResult(false,result.message)
                    println("No logueao")
                    println(result.toString())
                }
            }
            catch (e: Exception){
                onResult(false,"Error al registrar")
                print(e.toString())
            }
        }
    }

    fun login(
        email : String,
        password : String,
        onResult : (Boolean,String) -> Unit
    ){
        viewModelScope.launch {
            try{
                val service = KtorfitClient.createAuthService()
                val login = Login(
                    email = email,
                    password = password
                )
                val result = service.login(login)
                if(result.isLogged){
                    userId = result.userId
                    onResult(true,result.message)
                }
                else{
                    onResult(false,result.message)
                }
            }
            catch (e: Exception){
                onResult(false,"Error al loguearse")
            }
        }
    }
}