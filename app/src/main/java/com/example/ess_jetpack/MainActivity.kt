package com.example.ess_jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ess_jetpack.ui.theme.ESS_JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ESS_JetpackTheme {
LoginScreen()
            }
        }
    }
}

fun validatePassword(password: String): String? {
    return when {
        password.isEmpty() -> "Password cannot be empty"
        password.length < 6 -> "Minimum 6 characters required"
        password.length > 16 -> "Maximum 16 characters allowed"
        password.contains(" ") -> "Spaces are not allowed"
        else -> null
    }
}
fun validateEmployeeCode(Empcode: String): String? {
    return when {
        Empcode.isEmpty() -> "Employee Code cannot be empty"
        Empcode.contains(" ") -> "Spaces are not allowed"
        Empcode.all { it.isDigit() } -> "Only numbers allowed"
        Empcode.length < 4 -> "Minimum 4 digits required"
        Empcode.length > 10 -> "Maximum 10 digits allowed"
        else -> null
    }
}



@Composable
fun LoginScreen(){
    val context= LocalContext.current
var employeeCode by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var employeeError by remember { mutableStateOf<String?>(null) }

      Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Spacer(modifier = Modifier.height(40.dp))
        //Logo
        Image(painter = painterResource(id = R.drawable.ic_ess_emp_log_icon),
            contentDescription = "ESS Logo",
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome,",
            fontSize = 24.sp
        )
        Text(
            text = "Sign in to continue",
            fontSize= 16.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        //EmployeeCode
          TextField(
              value = employeeCode,
              onValueChange = { employeeCode = it },
              label = { Text("Employee Code") },
              singleLine = true,
              isError = employeeError != null,
              supportingText = {
                  if (employeeError != null) {
                      Text(employeeError!!)
                  }
              },
              modifier = Modifier.fillMaxWidth(),
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              colors = TextFieldDefaults.colors(
                  focusedContainerColor = Color.Transparent,
                  unfocusedContainerColor = Color.Transparent,
                  disabledContainerColor = Color.Transparent
              )
          )


          //Password
        TextField(
            value = password,
            onValueChange = {newValue ->
                if (newValue.length<=16){
                    password = newValue
                }

            },

           isError = passwordError!=null,
            label = {Text("Password")},
            modifier = Modifier.fillMaxWidth(),

            visualTransformation = if (passwordVisible) VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {passwordVisible=!passwordVisible}) {
                    Icon(
                        imageVector = if(passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null

                    )
                }
            }

        )

          Spacer(modifier = Modifier.height(20.dp))
          Row {

              Text(text = "Device ID")
          }
          Spacer(modifier = Modifier.height(25.dp))

         Button(
             onClick = {
                 employeeError=validateEmployeeCode(employeeCode)
                 passwordError=validatePassword(password)
                 if (passwordError==null &&employeeError==null){
                     //LoginSucess
                     Toast.makeText(context,"Login Success",Toast.LENGTH_SHORT).show()
                 }
             }
         ) {Text("Login") }


    }
}
