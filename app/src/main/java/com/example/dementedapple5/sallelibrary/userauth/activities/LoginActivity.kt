package com.example.dementedapple5.sallelibrary.userauth.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.dementedapple5.sallelibrary.R
import com.example.dementedapple5.sallelibrary.mainmenu.TabbedActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
/**
 * Pantalla de inicio de sesión.
 *
 * Se encarga de mostrar la pantalla donde el usuario puede iniciar sesión con Google.
 *
 * @see [AppCompatActivity]
 * @see [View.OnClickListener]
 * @see [OnCompleteListener]
 * @see [Bundle]
 * @see [GoogleSignInOptions]
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener, OnCompleteListener<AuthResult> {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth
    private val RCODE_SIGN_IN = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        signup_button.setOnClickListener(this)

        if (mAuth.currentUser != null) {
            updateUI()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.signup_button -> signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RCODE_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun onComplete(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            updateUI()
        } else {
            Toast.makeText(this, R.string.error_sign_in, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Inicia la operación de inicio de sesión con Google, iniciando un [GoogleSignInClient.getSignInIntent].
     *
     * @see [startActivityForResult]
     */
    private fun signIn() {
        val intent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RCODE_SIGN_IN)
    }

    /**
     * Se encarga de comprobar el estado de la tarea a la hora del [startActivityForResult] en el inicio de sesión y devolver un resultado dependiendo de si ha habido éxito o no.
     *
     * [completedTask] Tarea que se ejecuta al iniciar sesión.
     *
     * @see [GoogleSignInAccount]
     */
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        } catch (e: ApiException) {
            Toast.makeText(this, e.statusCode.toString(), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Se encarga de iniciar sesión completamente y crear el usuario en Firebase con las credenciales recogidas de [GoogleAuthProvider.getCredential].
     *
     * [accountSigningIn] Cuenta que escoge el usuario para iniciar sesión con Google.
     */
    private fun firebaseAuthWithGoogle(accountSigningIn: GoogleSignInAccount) {
        val accountCredentials: AuthCredential = GoogleAuthProvider.getCredential(accountSigningIn.idToken, null)

        mAuth.signInWithCredential(accountCredentials).addOnCompleteListener(this)
    }

    /**
     * Se encarga de pasar a la [TabbedActivity] y finalizar la [LoginActivity]
     */
    private fun updateUI() {
        val intent = Intent(this, TabbedActivity::class.java)
        startActivity(intent)
        finish()
    }
}
