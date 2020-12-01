package br.com.astrosoft.agenda.model.beans

import java.time.LocalDate

class ProdutoPedido(
  val loja: Int,
  val sigla : String,
  val numeroPedido: Int,
  val barcode: String,
  val codigo: String,
  val codigoFor: Int,
  val custoUnitario: Double,
  val dataEntrega: LocalDate,
  val dataPedido: LocalDate,
  val descricao: String,
  val fornecedor: String,
  val grade: String,
  val qtPendente: Int,
  val refFab: String,
  val unidade: String,
  val vlPendente: Double,
  val aliqICMS: Double,
  val aliqIPI: Double,
                   ){
  var item : Int = 0
}