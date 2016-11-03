[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-GreenDAOCrud-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4587)

# GreenDAOCrud
This is a sample project which helps anybody who wish to work with GreenDAO library.
This application contains all the thing you need to start creating project using GreenDAO.

 

### Tech

GreenDAOCrud uses a number of open source lib to work properly:

* [RXJava] - Reactive Extensions for the JVM
* [RXAndroid] - Reactive Extensions for Android
* [butterknife] -cast the corresponding view
* [rxbinding] -For click events

### Integrate for custom projects

Edit MainGenerator.java
�Change the package name and table names and add more tables on your requirements

```sh
public static void main(String[] args)  throws Exception {
        //place where db folder will be created inside the project folder
        Schema schema = new Schema(1,"com.apex.greendao.db");

        createTableUser(schema);

        //  ./app/src/main/java/   ----   com/codekrypt/greendao/db is the full path
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }


public static void createTableUser(Schema schema)
    {
        //Entity i.e. Class to be stored in the database // ie table LOG
        Entity user_entity= schema.addEntity("User");
        user_entity.addIdProperty().autoincrement();
        user_entity.addStringProperty("firstname").notNull();
        user_entity.addStringProperty("surname").notNull();
        user_entity.addStringProperty("nic").notNull().unique();
        user_entity.addStringProperty("age").notNull();
    }
....
```

Use the InitDatabase class for easy of development all you have to do is call and initialize

```sh
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
    ............
....
```


