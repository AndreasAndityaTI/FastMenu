package id.ac.ukdw.fastmenu.view.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tasktrackerapp.R
import com.google.firebase.auth.FirebaseAuth
import id.ac.ukdw.fastmenu.view.SignInActivity

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Find the logout button
        val logoutButton: Button = view.findViewById(R.id.btn_logout)

        // Set click listener for the logout button
        logoutButton.setOnClickListener {
            // Perform Firebase logout
            FirebaseAuth.getInstance().signOut()

            // Redirect to login activity or perform other actions
            val intent = Intent(activity, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}

