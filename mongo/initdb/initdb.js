var db = connect("mongodb://root:pestillo@localhost:27017/admin");

db = db.getSiblingDB('prison-images'); // we can not use "use" statement here to switch db

db.createUser(
    {
        user: "javier",
        pwd: "pestillo",
        roles: [ { role: "readWrite", db: "prison-images"} ],
    }
)