package com.sti.taxation.models

class Model {


    var image : String? = null
    var owner : String? = null

    constructor(){

    }

    constructor(image: String?, owner: String?) {
        this.image = image
        this.owner = owner
    }

}