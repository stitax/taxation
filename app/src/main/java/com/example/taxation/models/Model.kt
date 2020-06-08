package com.sti.taxation.models

class Model{
    var Owner: String? = ""
    var tax_payable: String? = ""
    var image: String? = ""

    constructor(){

    }

    constructor(Owner: String?, tax_payable: String?, image: String?) {
        this.Owner = Owner
        this.tax_payable = tax_payable
        this.image = image
    }

}