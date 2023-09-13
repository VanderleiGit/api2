package api2

import grails.gorm.transactions.Transactional
import grails.web.api.ServletAttributes

@Transactional
class FuncionarioService implements ServletAttributes {

    Map save() {
        def retorno = [success: true]

        def dadosFuncionario = request.JSON
        def nome = dadosFuncionario.nome
        def cidadeId = dadosFuncionario.cidadeId

        def funcionario = criarFuncionario(nome, cidadeId)

        if (!funcionario.validate()) {
            retorno.success = false
            retorno.errors = funcionario.getErrors()
        } else {
            retorno.registro = getShowRecord(funcionario)
        }

        return retorno
    }

    Map list() {
        def retorno = [success: true]

        def funcionarioList = Funcionario.list()

        retorno.total = funcionarioList.size()
        retorno.registro = funcionarioList.collect { getShowRecord(it) }

        return retorno
    }

    Map update() {
        def retorno = [success: true]
        def id = params.id as Long
        def dadosFuncionario = request.JSON
        def nome = dadosFuncionario.nome
        def cidadeId = dadosFuncionario.cidadeId

        def funcionario = atualizarFuncionario(id, nome, cidadeId)

        retorno.registro = getShowRecord(funcionario)

        return retorno
    }

    Map delete() {
        def retorno = [success: true]
        def id = params.id as Long

        def funcionario = Funcionario.get(id)

        funcionario.delete(flush: true)

        return retorno
    }

    Map get(Long id) {
        def retorno = [success: true]

        def funcionario = Funcionario.get(id)

        retorno.registro = getShowRecord(funcionario)

        return retorno
    }

     Funcionario criarFuncionario(nome, cidadeId) {
        def cidade = Cidade.get(cidadeId)
        return new Funcionario(nome: nome, cidade: cidade).save(flush: true)
    }

     Funcionario atualizarFuncionario(id, nome, cidadeId) {
        def funcionario = Funcionario.get(id)
        def cidade = Cidade.get(cidadeId)
        funcionario.nome = nome
        funcionario.cidade = cidade
        funcionario.save(flush: true)
        return funcionario
    }

     Map getShowRecord(Funcionario funcionario) {
        return [
                id: funcionario.id,
                nome: funcionario.nome,
                cidade: [
                        id: funcionario.cidade.id,
                        nome: funcionario.cidade.nome
                ]
        ]
    }
}
