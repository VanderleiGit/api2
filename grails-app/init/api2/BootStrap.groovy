import api2.Cidade
import api2.Funcionario
import api2.Reajuste

class BootStrap {

    def init = { servletContext ->
        def cidade1 = new Cidade(nome: "Cidade A").save()
        def cidade2 = new Cidade(nome: "Cidade B").save()
        def cidade3 = new Cidade(nome: "Cidade C").save()

        def funcionario1 = new Funcionario(nome: "Funcionário 1", cidade: cidade1).save()
        def funcionario2 = new Funcionario(nome: "Funcionário 2", cidade: cidade2).save()

        def reajuste1 = new Reajuste(valor: 500.00, funcionario: funcionario1).save()

        println "Dados de mockup inseridos com sucesso!"
    }

    def destroy = {
        // se funcionar deve se limpar dados de mockup
    }
}