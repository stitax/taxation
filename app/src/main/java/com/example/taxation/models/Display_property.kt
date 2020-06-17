package com.example.taxation.models

data class Display_property (
    var arpNumber: String? = null,
    var tin: String? = null,
    var image: String? = null,
    var owner: String? = null,
    var ownerAddress: String? = null,
    var ownerTelNo: String? = null,
    var occupant: String? = null,
    var occupantAddress: String? = null,
    var occupantTelNo: String? = null,
    var location: String? = null,
    var street: String? = null,
    var barangay: String? = null,
    var tax_payable: Double? = null,
    var status: String? = null
)