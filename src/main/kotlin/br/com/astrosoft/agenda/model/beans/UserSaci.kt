package br.com.astrosoft.agenda.model.beans

import br.com.astrosoft.agenda.model.saci
import kotlin.math.pow

class UserSaci {
  var no: Int = 0
  var name: String = ""
  var login: String = ""
  var senha: String = ""
  var bitAcesso: Int = 0
  var prntno: Int = 0
  var impressora: String = ""
  
  //Outros campos
  var ativo
    get() = (bitAcesso and BIT_ATIVO) != 0 || admin
    set(value) {
      bitAcesso = if(value) bitAcesso or BIT_ATIVO
      else bitAcesso and BIT_ATIVO.inv()
    }
  var pedidoPendente
    get() = (bitAcesso and BIT_PEDIDO_PENDENTE) != 0 || admin
    set(value) {
      bitAcesso = if(value) bitAcesso or BIT_PEDIDO_PENDENTE
      else bitAcesso and BIT_PEDIDO_PENDENTE.inv()
    }
  val admin
    get() = login == "ADM"
  
  companion object {
    private val BIT_ATIVO = 2.pow(0)
    private val BIT_PEDIDO_PENDENTE = 2.pow(1)
    
    fun findAll(): List<UserSaci>? {
      return saci.findAllUser()
        .filter {it.ativo}
    }
    
    fun updateUser(user: UserSaci) {
      saci.updateUser(user)
    }
    
    fun findUser(login: String?): UserSaci? {
      return saci.findUser(login)
    }
  }
}

fun Int.pow(e: Int): Int = this.toDouble()
  .pow(e)
  .toInt()