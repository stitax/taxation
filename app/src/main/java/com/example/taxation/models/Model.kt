package com.sti.taxation.models

class Model{
    var arpNumber: String? = null
    var pin: String? = null
    var image: String? = null
    var owner: String? = null
    var ownerAddress: String? = null
    var ownerTelNo: String? = null
    var occupant: String? = null
    var occupantAddress: String? = null
    var occupantTelNo: String? = null
    var location: String? = null
    var street: String? = null
    var barangay: String? = null
    var tax_payable: Double? = null
    var status: String? = null

    constructor(){

    }

    constructor(
        arpNumber: String?,
        pin: String?,
        image: String?,
        owner: String?,
        ownerAddress: String?,
        ownerTelNo: String?,
        occupant: String?,
        occupantAddress: String?,
        occupantTelNo: String?,
        location: String?,
        street: String?,
        barangay: String?,
        tax_payable: Double?,
        status: String?
    ) {
        this.arpNumber = arpNumber
        this.pin = pin
        this.image = image
        this.owner = owner
        this.ownerAddress = ownerAddress
        this.ownerTelNo = ownerTelNo
        this.occupant = occupant
        this.occupantAddress = occupantAddress
        this.occupantTelNo = occupantTelNo
        this.location = location
        this.street = street
        this.barangay = barangay
        this.tax_payable = tax_payable
        this.status = status
    }


}