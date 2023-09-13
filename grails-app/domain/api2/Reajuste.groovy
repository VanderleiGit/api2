package api2

import java.time.LocalDate

class Reajuste {
    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false, scale: 2
        funcionario nullable: false
    }

    static mapping = {
        id generator: 'increment'
        version false
        cache true
    }
}