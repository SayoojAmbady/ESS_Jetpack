package com.example.ess_jetpack.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ess_jetpack.R





@Composable
fun LoginScreen(){
    val context= LocalContext.current
    var employeeCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id=R.drawable.ess_log_bg_new),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            //Logo
            Image(
                painter = painterResource(id = R.drawable.ic_ess_emp_log_icon),
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
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            //EmployeeCode
            TextField(
                value = employeeCode,
                onValueChange = { employeeCode = it },
                label = { Text("Employee Code") },
                singleLine = true,

                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,

                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,

                    )
            )


            //Password
            TextField(
                value = password,
                onValueChange = { newValue ->
                    if (newValue.length <= 16) {
                        password = newValue
                    }

                },

                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = androidx.compose.ui.text.TextStyle( color = Color.Black),

                visualTransformation = if (passwordVisible) VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                            contentDescription = null

                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,

                    focusedLabelColor =Color.Black,
                    unfocusedLabelColor = Color.Gray,
                )

            )

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Device ID")
            }
            Spacer(modifier = Modifier.height(25.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_ess_login_button),
                contentDescription = "Login",
                modifier = Modifier
                    .size(76.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {

                        val empError = validateEmployeeCode(employeeCode)
                        val passError = validatePassword(password)

                        when {
                            empError != null -> {
                                Toast.makeText(context, empError, Toast.LENGTH_SHORT).show()
                            }

                            passError != null -> {
                                Toast.makeText(context, passError, Toast.LENGTH_SHORT).show()
                            }

                            else -> {
                                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            )



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
fun validateEmployeeCode(empCode: String): String? {
    return when {
        empCode.isEmpty() -> "Employee Code cannot be empty"
        empCode.contains(" ") -> "Spaces are not allowed"
        !empCode.all { it.isDigit() } -> "Only numbers allowed"
        empCode.length < 4 -> "Minimum 4 digits required"
        empCode.length > 10 -> "Maximum 10 digits allowed"
        else -> null
    }
}