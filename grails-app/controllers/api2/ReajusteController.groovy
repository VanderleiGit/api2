package api2

import grails.converters.JSON
import grails.gorm.transactions.Transactional


class ReajusteController {

    static allowedMethods = [save: "POST", list: "GET", update: "PUT", delete: "DELETE", get: "GET"]

    @Transactional
    def save() {
        def reajusteData = request.JSON
        def funcionario = Funcionario.get(reajusteData.funcionarioId)
        def reajuste = new Reajuste(valorSalario: reajusteData.valorSalario, funcionario: funcionario)
        reajuste.save()
        render status: 201, text: 'Created'
    }

    def list() {
        def reajustes = ReajusteSalario.list()
        render reajustes as JSON
    }

    @Transactional
    def update(Long id) {
        def reajuste = ReajusteSalario.get(id)
        def reajusteData = request.JSON
        reajuste.valorSalario = reajusteData.valorSalario
        reajuste.funcionario = Funcionario.get(reajusteData.funcionarioId)
        reajuste.save()
        render status: 200, text: 'OK'
    }

    @Transactional
    def delete(Long id) {
        def reajuste = ReajusteSalario.get(id)
        reajuste.delete()
        render status: 204, text: 'No Content'
    }

    def get(Long id) {
        def reajuste = ReajusteSalario.get(id)
        render reajuste as JSON
    }
}