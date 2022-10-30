package com.example.proiectdac

import android.content.ContentValues.TAG
import android.content.Intent
import com.google.firebase.auth.AuthResult;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.security_page_1.*
import kotlinx.android.synthetic.main.security_page_2.*
import kotlinx.android.synthetic.main.security_page_3.*

class ExistingAccountHandler : AppCompatActivity() {
    private var userName:String =""
    private lateinit var dataBaseHandler: DataBaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_page_2)
        userName =""
        dataBaseHandler = DataBaseHandler(this)
        btnConnect.setOnClickListener{
            logUser()
        }
    }

    public fun getUserName(): String {
        return userName
    }

    private fun logUser(): ArrayList<User> {
        var ok =0
        var usrList:ArrayList<User> = ArrayList()
        usrList=dataBaseHandler.logInUser()
        var name = editText6.text.toString()
        var password = editText5.text.toString()
       for(x in usrList)
       {
           if(x.name == name && x.password == password)
           {
               ok=1;
                userName=name.toString()
               Toast.makeText(this,"Succes",Toast.LENGTH_SHORT).show()
               val intent = Intent(this, HomeHandler::class.java)
           startActivity(intent)
               break;
            }
       }
        if(ok==0)
        Toast.makeText(this,"Wrong name or password",Toast.LENGTH_SHORT).show()

        return usrList
    }


/*private lateinit var binding:ActivityMainBinding
//view binding
private lateinit var googleSignInClient: GoogleSignInClient
private lateinit var firebaseAuth: FirebaseAuth
//constants
private companion object{
    private const val RC_SIGN_IN=100
    private const val TAG = "GOOGLE_SIGN_IN_TAG"
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure the Google SignIn
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()//we only need email from google account
            .build()
        googleSignInClient = GoogleSignIn.getClient(this ,googleSignInOptions)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
       // checkUser()

        //Google SignIn Button, Click to begin Google SignIn
       binding.googleSignInBtn.setOnClickListener{
            //begin Google SignIn
            Log.d(TAG,"onCreate: begin Google SignIn")
           // Toast.makeText(this, "Please complete both fields", Toast.LENGTH_SHORT).show()
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)


        }
    }

    private fun checkUser() {
        Log.d(TAG,"checkUser Function             !!!!!!!!!!!")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN)
        {
            Log.d(TAG,"onActivityResult:Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = accountTask.getResult(ApiException::class.java)

                firebaseAuthWithGoogleAccount(account)
            }
            catch (e:Exception){
                Log.d(TAG,"onActivityResult ${e.message}")
                Toast.makeText(this, "asdasd", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
Log.d(TAG,"firebaseAuthWithGoogleAccount: begin firebase auth with google account")

        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                //loggin succes
                Log.d(TAG,"firebaseAuthWithGoogleAccount: LoggedIn")
                Toast.makeText(this, account.email, Toast.LENGTH_SHORT).show()

                //get loggedIn user
                val firebaseUser = firebaseAuth.currentUser
                //get user info
                val uid = firebaseUser!!.uid
                val email = firebaseUser.email


                Log.d(TAG, "firebaseAuthWithGoogleAccount:$uid ")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: $email ")

                //check if user is new or existing
                val intent = Intent(this, CardInfoHandler::class.java)
                startActivity(intent)
            }
    }


    val database = Firebase.database("https://proiect-android-e1d82-default-rtdb.europe-west1.firebasedatabase.app")
    val myRef = database.getReference().child("User")
    private lateinit var messagesListener: ValueEventListener
val context=this

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.security_page_2)

        btnConnect.setOnClickListener {
            val name=editText6.text;
            val password=editText5.text;
            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please complete both fields", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, CardInfoHandler::class.java)
            startActivity(intent)
        }
    val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);
    }*/


*/
}

