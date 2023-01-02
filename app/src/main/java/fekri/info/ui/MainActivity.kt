package fekri.info.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fekri.info.databinding.ActivityMainBinding
import fekri.info.ux.Person
import fekri.info.ux.PersonAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* set data(adapter) and fill out the recyclerview with theme */
        val data = arrayListOf<Person>(
            Person(
                name = "Mohammad Reza",
                phoneNumber = "98 914 100 1010",
                country = "Iran",
                urlImage = "https://avatars.githubusercontent.com/u/92860582?v=4"
            ),
            Person(
                name = "Mike",
                phoneNumber = "1 244 222 1212",
                country = "USA",
                urlImage = "https://www.theportlandclinic.com/wp-content/uploads/2019/07/Person-Curtis_4x5-e1564616444404-300x300.jpg"
            ),
            Person(
                name = "Loyce",
                phoneNumber = "1 345 235 2323",
                country = "USA",
                urlImage = "https://www.hhs.gov/sites/default/files/styles/bio_280x280/public/loyce-pace.jpg?h=7496abec&itok=r49vUAFg"
            ),
            Person(
                name = "Mustafa",
                phoneNumber = "90 539 576 0477",
                country = "Turkey",
                urlImage = "https://wikisound.ir/wp-content/uploads/2022/09/mustafa_ceceli.jpg"
            ),
            Person(
                name = "Alvaro",
                phoneNumber = "34 911 5697 3198",
                country = "Spain",
                urlImage = "https://img.a.transfermarkt.technology/portrait/big/253839-1583437076.jpg?lm=1"
            ),
            Person(
                name = "Mrs Merkel",
                phoneNumber = "49 3723 247611",
                country = "German",
                urlImage = "https://i0.web.de/image/974/36348974,pd=1/angela-merkel.jpg"
            ),
            Person(
                name = "Olivia",
                phoneNumber = "1 555 909 9345",
                country = "Canada",
                urlImage = "https://upload.wikimedia.org/wikipedia/commons/3/3d/Olivia_Rodrigo_at_Vice_President%27s_West_Wing_office_%282%29.jpg"
            )
        )
        val mAdapter = PersonAdapter(data)

        binding.recyclerMain.adapter = mAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }
}