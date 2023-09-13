package api2

import grails.converters.JSON
import javax.transaction.Transactional

class FuncionarioController {

    static allowedMethods = [save: "POST", list: "GET", update: "PUT", delete: "DELETE", get: "GET"]

    @Transactional
    def save() {
        def funcionarioData = request.JSON
        def cidade = Cidade.get(funcionarioData.cidadeId)
        def funcionario = new Funcionario(nome: funcionarioData.nome, cidade: cidade)
        funcionario.save()
        render status: 201, text: 'Created'
    }

    def list() {
        def funcionarios = Funcionario.list()
        render funcionarios as JSON
    }

    @Transactional
    def update(Long id) {
        def funcionario = Funcionario.get(id)
        def funcionarioData = request.JSON
        funcionario.nome = funcionarioData.nome
        funcionario.cidade = Cidade.get(funcionarioData.cidadeId)
        funcionario.save()
        render status: 200, text: 'OK'
    }

    @Transactional
    def delete(Long id) {
        def funcionario = Funcionario.get(id)
        funcionario.delete()
        render status: 204, text: 'No Content'
    }

    def get(Long id) {
        def funcionario = Funcionario.get(id)
        render funcionario as JSON
    }
}