package api2

import grails.gorm.transactions.Transactional
import javassist.NotFoundException

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Transactional
class ReajusteService {

    Map save() {
        def idFuncionario = request.JSON.idFuncionario
        def funcionario = Funcionario.get(idFuncionario)

        if (!funcionario) {
            throw new NotFoundException("Funcionario não encontrado")
        }

        def dataReajuste = LocalDate.parse(request.JSON.dataReajuste, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        def valorSalario = request.JSON.valorSalario.replace(",", ".") as BigDecimal

        def reajusteSalario = new Reajuste(
                dataReajuste: dataReajuste,
                valorSalario: valorSalario,
                funcionario: funcionario
        ).save(flush: true)

        return [
                success: true,
                registro: [
                        id: reajusteSalario.id,
                        dataReajuste: reajusteSalario.dataReajuste.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        valorSalario: reajusteSalario.valorSalario,
                        funcionario: [
                                id: reajusteSalario.funcionario.id,
                                nome: reajusteSalario.funcionario.nome
                        ]
                ]
        ]
    }

    Map list() {
        def reajusteSalarioList = ReajusteSalario.findAll()

        return [
                total: reajusteList.size(),
                registros: reajusteList.collect {
                    [
                            id: it.id,
                            dataReajuste: it.dataReajuste.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            valorSalario: it.valorSalario,
                            funcionario: [
                                    id: it.funcionario.id,
                                    nome: it.funcionario.nome
                            ]
                    ]
                }
        ]
    }

    Map update() {
        def id = params.long("id")
        def idFuncionario = request.JSON.idFuncionario
        def funcionario = Funcionario.get(idFuncionario)

        if (!funcionario) {
            throw new NotFoundException("Funcionario não encontrado para ${idFuncionario}")
        }

        def record = Reajuste.get(id)
        record.funcionario = funcionario
        record.dataReajuste = LocalDate.parse(request.JSON.dataReajuste, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        record.valorSalario = request.JSON.valorSalario.replace(",", ".") as BigDecimal
        record.save(flush: true)

        return [
                success: true,
                registro: [
                        id: record.id,
                        dataReajuste: record.dataReajuste.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        valorSalario: record.valorSalario,
                        funcionario: [
                                id: record.funcionario.id,
                                nome: record.funcionario.nome
                        ]
                ]
        ]
    }
}