package com.example.proiectdac

class User {
    var name: String = ""
    var surname: String = ""
    var password: String = ""
    var passcode:  String = ""
    var id: Int = 0

    constructor(name:String,surname:String, password:String, passcode:String, id:Int)
    {
        this.name=name;
        this.surname=surname;
        this.password=password;
        this.passcode=passcode;
        this.id=id;
    }
}