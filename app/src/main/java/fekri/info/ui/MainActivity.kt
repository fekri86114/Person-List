package fekri.info.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fekri.info.databinding.ActivityMainBinding
import fekri.info.databinding.DialogAddNewItemBinding
import fekri.info.databinding.DialogDeleteItemBinding
import fekri.info.databinding.DialogUpdateItemBinding
import fekri.info.ux.adapter.PersonAdapter
import fekri.info.ux.data.Person
import fekri.info.ux.room.PersonDao
import fekri.info.ux.room.PersonDatabase

const val BASE_URL_IMAGE = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food"

class MainActivity : AppCompatActivity(), PersonAdapter.PersonEvents {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: PersonAdapter
    private lateinit var personDao: PersonDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personDao = PersonDatabase.getDatabase(this).personDao

        val sharedPreferences = getSharedPreferences("personList", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_run", true)) {
            firstRun()
            sharedPreferences.edit().putBoolean("first_run", false).apply()
        }

        showAllData()
        // remove all food from database
        binding.btnRemoveAllPersons.setOnClickListener {
            removeAllData()
        }
        // add new person
        binding.btnAddNewPerson.setOnClickListener {
            addNewPerson()
        }
        // search data | database
        binding.edtSearch.addTextChangedListener { editTextInput ->
            searchOnDatabase(editTextInput!!.toString())
        }

    }

    private fun searchOnDatabase(editTextInput: String) {

        if (editTextInput.isNotEmpty()) {
            // filter data 'h'
            val searchedData = personDao.searchPersons(editTextInput)
            myAdapter.setData(ArrayList(searchedData))
        } else {
            // show all data -->
            val data = personDao.getAllPersons()
            myAdapter.setData(ArrayList(data))
        }

    }

    private fun addNewPerson() {
        val dialog = AlertDialog.Builder(this).create()

        val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
        dialog.setView(dialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        // what to do when clicked on Done button -->
        dialogBinding.dialogBtnDone.setOnClickListener {

            if (
                dialogBinding.dialogEdtPersonName.text!!.isNotEmpty() &&
                dialogBinding.dialogEdtPersonNumber.text!!.isNotEmpty() &&
                dialogBinding.dialogEdtPersonCountry.text!!.isNotEmpty()
            ) { /* if input is not empty add changes --> */

                val txtName = dialogBinding.dialogEdtPersonName.text.toString()
                val txtPhoneNumber = dialogBinding.dialogEdtPersonNumber.text.toString()
                val txtCountry = dialogBinding.dialogEdtPersonCountry.text.toString()

                val randomForUrl = (1 until 12).random()
                val urlPic = "$BASE_URL_IMAGE$randomForUrl.jpg"

                val newPerson = Person(
                    name = txtName,
                    phoneNumber = txtPhoneNumber,
                    urlImage = urlPic,
                    country = txtCountry
                )

                myAdapter.addPerson(newPerson)
                personDao.insertOrUpdate(newPerson)

                dialog.dismiss()
                binding.recyclerMain.scrollToPosition(0)

            } else {
                Toast.makeText(this, "Please, fill out the blanks!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun removeAllData() {
        personDao.deleteAllPerson() // delete all persons
        showAllData() // show all data
    }

    private fun showAllData() {
        /* create(fill out) adapter and make recyclerview done */
        val personData = personDao.getAllPersons()

        myAdapter = PersonAdapter(ArrayList(personData), this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    private fun firstRun() {
        // Insert All persons
        val personList = arrayListOf(
            Person(
                name = "Mike",
                phoneNumber = "1 650 513 0514",
                country = "USA",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
            ),
            Person(
                name = "Anja",
                phoneNumber = "49 30 1234567",
                country = "German",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg"
            ),
            Person(
                name = "Kateryna",
                phoneNumber = "380 3112 34567",
                country = "Ukraine",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg"
            ),
            Person(
                name = "Sophie",
                phoneNumber = "33 891 060 952",
                country = "France",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg"
            ),
            Person(
                name = "Mert",
                phoneNumber = "90 212 555 1212",
                country = "Turkey",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg"
            ),
            Person(
                name = "Ali",
                phoneNumber = "98 914 200 2020",
                country = "Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg"
            ),
            Person(
                name = "Elláda",
                phoneNumber = "30 21 1234 567",
                country = "Greece",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg"
            ),
            Person(
                name = "Naya",
                phoneNumber = "91 12 345 6789",
                country = "India",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg"
            ),
            Person(
                name = "Eva",
                phoneNumber = "7 123 4567 901",
                country = "Russia",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg"
            ),
            Person(
                name = "Daniel",
                phoneNumber = "52 5615927192",
                country = "Mexico",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg"
            ),
            Person(
                name = "Hiroko",
                phoneNumber = "81 123 5678 9101",
                country = "Japan",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg"
            ),
            Person(
                name = "Ai",
                phoneNumber = "86 123 4567 8910",
                country = "China",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg"
            )

        )

        personDao.insertAllPerson(personList)

    }

    override fun onPersonClicked(person: Person, position: Int) {
        /* show dialog, set data of items to make changes */
        val dialog = AlertDialog.Builder(this).create()

        val dialogUpdateItemBinding = DialogUpdateItemBinding.inflate(layoutInflater)
        dialog.setView(dialogUpdateItemBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        // set the texts -->
        dialogUpdateItemBinding.dialogEdtPersonName.setText(person.name)
        dialogUpdateItemBinding.dialogEdtPersonNumber.setText(person.phoneNumber)
        dialogUpdateItemBinding.dialogEdtPersonCountry.setText(person.country)

        dialogUpdateItemBinding.dialogUpdateBtnCancel.setOnClickListener {
            dialog.dismiss() // close dialog
        }

        dialogUpdateItemBinding.dialogUpdateBtnUpdate.setOnClickListener {

            // check the user completed data or not -->
            if (
                dialogUpdateItemBinding.dialogEdtPersonName.length() > 0 &&
                dialogUpdateItemBinding.dialogEdtPersonNumber.length() > 0 &&
                dialogUpdateItemBinding.dialogEdtPersonCountry.length() > 0
            ) {

                val txtName = dialogUpdateItemBinding.dialogEdtPersonName.text.toString()
                val txtNumber = dialogUpdateItemBinding.dialogEdtPersonNumber.text.toString()
                val txtCountry = dialogUpdateItemBinding.dialogEdtPersonCountry.text.toString()

                // create new person to add to the recyclerview -->
                val newPerson = Person(
                    id = person.id,
                    name = txtName,
                    phoneNumber = txtNumber,
                    country = txtCountry,
                    urlImage = person.urlImage
                )

                // update item in adapter -->
                myAdapter.updatedPerson(newPerson, position)

                // update item in database -->
                personDao.insertOrUpdate(newPerson)

                // close dialog after updating -->
                dialog.dismiss()

            } else {
                Toast.makeText(this, "Please, fill out the blanks :-)", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onPersonLongClicked(person: Person, position: Int) {

        val dialog = AlertDialog.Builder(this).create()

        val dialogDeleteItemBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteItemBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteItemBinding.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss() // close dialog
        }

        dialogDeleteItemBinding.dialogBtnDeleteYes.setOnClickListener {
            dialog.dismiss()

            // delete from adapter -->
            myAdapter.deletePerson(person, position)

            // delete from database -->
            personDao.deletePerson(person)
        }

    }
}