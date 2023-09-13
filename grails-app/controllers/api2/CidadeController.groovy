package api2

import grails.converters.JSON
import grails.gorm.transactions.Transactional


class CidadeController {

    static allowedMethods = [save: "POST", list: "GET", update: "PUT", delete: "DELETE", get: "GET"]

    @Transactional
    def save() {
        def cidadeData = request.JSON
        def cidade = new Cidade(cidadeData)
        cidade.save()
        render status: 201, text: 'Created'
    }

    def list() {
        def cidades = Cidade.list()
        render cidades as JSON
    }

    @Transactional
    def update(Long id) {
        def cidade = Cidade.get(id)
        def cidadeData = request.JSON
        cidade.properties = cidadeData
        cidade.save()
        render status: 200, text: 'OK'
    }

    @Transactional
    def delete(Long id) {
        def cidade = Cidade.get(id)
        cidade.delete()
        render status: 204, text: 'No Content'
    }

    def get(Long id) {
        def cidade = Cidade.get(id)
        render cidade as JSON
    }
}