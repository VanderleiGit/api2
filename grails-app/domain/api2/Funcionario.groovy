package api2

class Funcionario {
    String nome
    Cidade cidade

    static constraints = {
        nome nullable: false, maxSize: 50
        cidade nullable: false
    }

    static mapping = {
        id generator: 'increment'
        version false
        cache true
    }
}
