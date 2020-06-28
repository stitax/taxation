package com.example.taxation.models

class Appointment{

    var Arp_Number: String? = ""
    var Schedule: String? = ""
    var Name: String? = ""
    var Address: String? = ""

    constructor()

    constructor(Arp_Number: String?, Schedule: String?, Name: String?, Address: String?) {
        this.Arp_Number = Arp_Number
        this.Schedule = Schedule
        this.Name = Name
        this.Address = Address
    }
}