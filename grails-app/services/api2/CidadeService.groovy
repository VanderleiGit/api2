package api2

import grails.gorm.transactions.Transactional
import grails.web.api.ServletAttributes
import javassist.NotFoundException
import org.springframework.dao.DataIntegrityViolationException

@Transactional
class CidadeService implements ServletAttributes {

    Map save(Map<String, String> requestJson) {
        def retorno = [success: true]

        def nome = requestJson.nome
        def cidade = criarCidade(nome)

        retorno.cidade = cidade

        return retorno
    }

    Map list() {
        def retorno = [total: 0, records: []]

        def cidadeList = Cidade.list()

        retorno.total = cidadeList.size()
        retorno.records = cidadeList

        return retorno
    }

    Map update(Long id, Map<String, String> requestJson) {
        def retorno = [success: true]

        def nome = requestJson.nome
        def cidade = atualizarCidade(id, nome)

        return retorno
    }

    Map delete(Long id) {
        def retorno = [success: true]

        def cidade = Cidade.findById(id)

        if (!cidade) {
            throw new NotFoundException("Não encontrada cidade para ${id}")
        }

        try {
            cidade.delete(flush: true)
        } catch (DataIntegrityViolationException e) {
            retorno.success = false
            retorno.message = "Registro associado a um funcionário."
            retorno.error = e.getMessage()
        }

        return retorno
    }

    Cidade get(Long id) {
        return Cidade.get(id)
    }

     Cidade criarCidade(nome) {
        def cidade = new Cidade(nome: nome).save(flush: true)
        return cidade
    }

     Cidade atualizarCidade(id, nome) {
        def cidade = Cidade.get(id)
        cidade.nome = nome
        cidade.save(flush: true)
        return cidade
    }
}
