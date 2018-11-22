package antoinepetetin.fr.pocandroidlibrary

class FakeDataModel {

    //static fields
    companion object {
        var idStatic: Int = 0;
    }
    var id: Int = 0
    var textToDisplay: String = ""

    constructor(text: String){
        this.id = idStatic++
        this.textToDisplay = text
    }


}