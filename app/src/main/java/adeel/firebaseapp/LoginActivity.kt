package adeel.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        var btnLogin:  Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener(View.OnClickListener {
            var username: EditText = findViewById(R.id.editTextUsernameLogin)
            var password: EditText = findViewById(R.id.editTextPasswordLogin)
            var usernameText:String = username.text.toString()
            var passwordText:String = password.text.toString()
            authenticateUser(usernameText, passwordText)
        })
    }
    private fun authenticateUser(email: String, password: String) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.d("Login", user!!.email + " successfully login.")
                    Toast.makeText(this@LoginActivity, "Authentication successful.", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e("Login", "failure", task.exception)
                    Toast.makeText(this@LoginActivity, "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

}