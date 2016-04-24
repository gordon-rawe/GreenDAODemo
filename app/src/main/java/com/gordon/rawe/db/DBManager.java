package com.gordon.rawe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gordon.rawe.models.DaoMaster;
import com.gordon.rawe.models.DaoSession;
import com.gordon.rawe.models.Person;
import com.gordon.rawe.models.PersonDao;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * Created by gordon on 16/4/23.
 */
public class DBManager {
    public static final String DB_NAME = "gordon.db";
    private static DBManager ourInstance = new DBManager();
    private DaoMaster master;
    private DaoSession session;


    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {
    }

    public void configure(Context context) {
        final DaoMaster.OpenHelper helper = new DaoMaster.OpenHelper(context, DB_NAME, null) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                DaoMaster.dropAllTables(db, false);
                DaoMaster.createAllTables(db, false);
            }
        };
        master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
    }

    public void createPerson(String name, String content) {
        final Person person = new Person();
        person.setComment(content);
        person.setName(name);
        final PersonDao dao = session.getPersonDao();
        session.runInTx(new Runnable() {
            @Override
            public void run() {
                dao.insert(person);
            }
        });
    }

    public List<Person> getAllPeople() {
        PersonDao dao = session.getPersonDao();
        Query builder = dao.queryBuilder().distinct().build();
        List<Person> allPeople = builder.list();
        return allPeople;
    }

    public void deleteAllPeople() {
        final PersonDao dao = session.getPersonDao();
        session.runInTx(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public Person getPersonByName(String name) {
        final PersonDao dao = session.getPersonDao();
        Query query = dao.queryBuilder().where(PersonDao.Properties.Name.eq(name)).build();
        List all = query.list();
        if (all != null) return (Person) all.get(0);
        else return null;
    }

    public void deletePersonByName(String name) {
        final PersonDao dao = session.getPersonDao();
        final Person person = getPersonByName(name);
        session.runInTx(new Runnable() {
            @Override
            public void run() {
                dao.delete(person);
            }
        });
    }
}
