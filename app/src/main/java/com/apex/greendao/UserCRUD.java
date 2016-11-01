package com.apex.greendao;

import android.app.Activity;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apex.greendao.DBInterface.InitDatabase;
import com.apex.greendao.db.DaoSession;
import com.apex.greendao.db.User;
import com.apex.greendao.db.UserDao;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class UserCRUD extends Activity {

    @Bind(R.id.txtFirstname)
    EditText txtFirstname;

    @Bind(R.id.txtSurname)
    EditText txtSurname;

    @Bind(R.id.txtNIC)
    EditText txtNIC;

    @Bind(R.id.txtAge)
    EditText txtAge;

    @Bind(R.id.btnAdd)
    Button btnAdd;

    @Bind(R.id.btnSearch)
    Button btnSearch;

    @Bind(R.id.btnUpdate)
    Button btnUpdate;

    @Bind(R.id.btnRemove)
    Button btnRemove;

    @Bind(R.id.btnClearAll)
    Button btnClearAll;

    UserDao user;
    InitDatabase iDB=new InitDatabase();
    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_crud);
        ButterKnife.bind(this);

        daoSession=iDB.getSession(this);
        user=daoSession.getUserDao();


        RxView.clicks(btnAdd)
                .subscribe(btnAddClick);

        RxView.clicks(btnSearch)
                .subscribe(btnSearchClick);

        RxView.clicks(btnUpdate)
                .subscribe(btnUpdateClick);

        RxView.clicks(btnRemove)
                .subscribe(btnRemoveClick);

        RxView.clicks(btnClearAll)
                .subscribe(btnClearAllClick);
    }

    public Action1<Void> btnAddClick=new Action1<Void>() {
        @Override
        public void call(Void aVoid) {

            addNewUser();

        }
    };

    public Action1<Void> btnSearchClick=new Action1<Void>() {
        @Override
        public void call(Void aVoid) {
            searchUserwithNIC();
        }
    };

    public Action1<Void> btnUpdateClick=new Action1<Void>() {
        @Override
        public void call(Void aVoid) {
            updateUserwithNIC();
        }
    };

    public Action1<Void> btnRemoveClick=new Action1<Void>() {
        @Override
        public void call(Void aVoid) {
            removeUserwithNIC();
        }
    };

    public Action1<Void> btnClearAllClick=new Action1<Void>() {
        @Override
        public void call(Void aVoid) {
            removeAll();
        }
    };

    public void addNewUser()
    {
        User person = new User();
        person.setFirstname(getData(txtFirstname));
        person.setAge(getData(txtAge));
        person.setNic(getData(txtNIC));
        person.setSurname(getData(txtSurname));
        try {
            long f = user.insert(person);

            if (f > 0) {
                showToast("User added");
                clearAllTextData();
            }
            else
            {
                showToast("User not added");
            }
        }
        catch (SQLiteConstraintException e)
        {
            showToast("User with the same NFC id Exists");
        }
    }

    public void searchUserwithNIC()
    {
        List<User> userinfo = user.queryBuilder().where(UserDao.Properties.Nic.eq(getData(txtNIC)) ).list();
        if(userinfo.size()>0)
        {
            User u=userinfo.get(0);
            setText(txtFirstname,u.getFirstname());
            setText(txtSurname,u.getSurname());
            setText(txtAge,u.getAge());
        }
        else
        {
            showToast("User not found");
        }
    }

    public void updateUserwithNIC()
    {
        List<User> userinfo = user.queryBuilder().where(UserDao.Properties.Nic.eq(getData(txtNIC)) ).list();
        if(userinfo.size()>0)
        {
            User u=userinfo.get(0);
            u.setFirstname(getData(txtFirstname));
            u.setSurname(getData(txtSurname));
            u.setAge(getData(txtAge));
            long f=user.insertOrReplace(u);

            if (f > 0) {
                showToast("User updated");
                clearAllTextData();
            }
            else
            {
                showToast("User not updated");
            }
        }
        else
        {
            showToast("User not found");
        }
    }

    public void removeUserwithNIC()
    {
        List<User> userinfo = user.queryBuilder().where(UserDao.Properties.Nic.eq(getData(txtNIC)) ).list();
        if(userinfo.size()>0)
        {
            User u=userinfo.get(0);
            user.delete(u);
            showToast("User deleted");
            clearAllTextData();
        }
        else
        {
            showToast("User not found");
        }
    }

    public void removeAll()
    {
        user.deleteAll();
        showToast("All records deleted");
        clearAllTextData();
    }

    public void clearAllTextData()
    {
        setText(txtFirstname,"");
        setText(txtSurname,"");
        setText(txtAge,"");
        setText(txtNIC,"");
    }

    public void setText(EditText view,String data)
    {
         view.setText(data);
    }


    public String getData(EditText view)
    {
        return view.getText().toString().trim();
    }
    public void showToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
