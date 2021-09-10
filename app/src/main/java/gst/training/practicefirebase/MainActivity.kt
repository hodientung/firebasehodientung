package gst.training.practicefirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    var p :MutableList<Student> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = FirebaseDatabase.getInstance()
        val a = database.getReference("user")
        val k = arrayListOf(
            Student("ho dien tung", "0868290096"),
            Student("nguyen phuong hang", "123456789")
        )

        a.setValue(k)
        getData()
        Log.e("Dien tung", p[1].name)
    }

    fun getData() {
        val database = FirebaseDatabase.getInstance()
        val a = database.getReference("user")
        a.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               for (item in snapshot.children){
                   val student = item.getValue(Student::class.java)
                   student?.let { p.add(it) }
               }
                Log.e("Dien tung", p.size.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}