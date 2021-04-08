package ipvc.estg.trabalhopratico.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.trabalhopratico.dao.NotesDao
import ipvc.estg.trabalhopratico.entities.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Notes::class), version = 1, exportSchema = false)
public abstract class NotesDB: RoomDatabase() {


    abstract fun NotesDao(): NotesDao
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override  fun onOpen(db: SupportSQLiteDatabase){
            super.onOpen(db)
            INSTANCE?.let{database->scope.launch {
                var notesDao=database.NotesDao()

                // Codigo da professora comentado no video
                //delete all content here
                // AppDao.deleteAll()
                // Add sample words
                var note= Notes(1,"Buraco ","Existe um buraco na estrada da abelheira")
                notesDao.insert(note)


            }}

        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotesDB {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "Notes_database"
                )//IMPORTANTE ISTO E NECESSARIO QUANDO QUERES DAR RESET A BASE DE DADOS
                    // .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

}