package com.example.taxation.models

class Appointment{

    var TransactionId: String? = ""
    var Schedule: String? = ""
    var Name: String? = ""
    var Address: String? = ""

    constructor()

    constructor(TransactionId: String?, Schedule: String?, Name: String?, Address: String?) {
        this.TransactionId = TransactionId
        this.Schedule = Schedule
        this.Name = Name
        this.Address = Address
    }
}