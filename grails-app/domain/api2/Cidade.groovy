package api2

class Cidade {
    String nome

    static constraints = {
        nome nullable: false, maxSize: 50
    }

    static mapping = {
        id generator: 'increment'
        version false
        cache true
    }
}