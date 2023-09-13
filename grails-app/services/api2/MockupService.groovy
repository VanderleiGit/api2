package api2

import grails.gorm.transactions.Transactional

import java.time.LocalDate

@Transactional
class MockupService {

    def popularDados() {
        Cidade cidade = new Cidade()
        cidade.setNome("Nova Santa Rita")
        cidade.save(flush: true)

        Cidade cidade2 = new Cidade()
        cidade2.setNome("Portão")
        cidade2.save(flush: true)

        Cidade cidade3 = new Cidade()
        cidade3.setNome("Sapiranga")
        cidade3.save(flush: true)

        Funcionario funcionario = new Funcionario()
        funcionario.setNome("Paulo")
        funcionario.setCidade(cidade2)
        funcionario.save(flush: true)

        Funcionario funcionario2 = new Funcionario()
        funcionario2.setNome("Júnior")
        funcionario2.setCidade(cidade)
        funcionario2.save(flush: true)

        Reajuste reajuste = new Reajuste()
        reajuste.setDataReajuste(LocalDate.now())
        reajuste.setValorSalario(200 as BigDecimal)
        reajuste.setFuncionario(funcionario2)
        reajuste.save(flush: true)
    }
}