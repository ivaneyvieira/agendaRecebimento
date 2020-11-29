package br.com.astrosoft.agenda.model.beans

import java.time.LocalDate

class ProdutoPedido(
  val aliqICMS: Double,
  val aliqIPI: Double,
  val barcode: String,
  val codigo: String,
  val codigoFor: Int,
  val custoUnitario: Double,
  val dataEntrega: LocalDate,
  val dataPedido: LocalDate,
  val descricao: String,
  val fornecedor: String,
  val grade: String,
  val loja: Int,
  val numeroPedido: Int,
  val qtPendente: Double,
  val refFab: String,
  val unidade: String,
  val vlPendente: Double
                   )