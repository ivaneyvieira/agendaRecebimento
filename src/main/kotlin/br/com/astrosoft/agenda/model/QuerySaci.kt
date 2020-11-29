package br.com.astrosoft.agenda.model

import br.com.astrosoft.AppConfig
import br.com.astrosoft.agenda.model.beans.ProdutoPedido
import br.com.astrosoft.agenda.model.beans.UserSaci
import br.com.astrosoft.framework.model.QueryDB
import br.com.astrosoft.framework.util.DB

class QuerySaci: QueryDB(driver, url, username, password) {
  fun findUser(login: String?): UserSaci? {
    login ?: return null
    val sql = "/sqlSaci/userSenha.sql"
    return query(sql, UserSaci::class) {
      addParameter("login", login)
      addOptionalParameter("appName", AppConfig.shortName)
    }.firstOrNull()
  }
  
  fun findAllUser(): List<UserSaci> {
    val sql = "/sqlSaci/userSenha.sql"
    return query(sql, UserSaci::class) {
      addParameter("login", "TODOS")
      addOptionalParameter("appName", AppConfig.shortName)
    }
  }
  
  fun updateUser(user: UserSaci) {
    val sql = "/sqlSaci/updateUser.sql"
    script(sql) {
      addOptionalParameter("login", user.login)
      addOptionalParameter("bitAcesso", user.bitAcesso)
      addOptionalParameter("appName", AppConfig.shortName)
    }
  }
  
  fun findProdutoPedido(): List<ProdutoPedido> {
    val sql = "/sqlSaci/produtosPendentes.sql"
    return query(sql, ProdutoPedido::class)
  }
  
  companion object {
    private val db = DB("saci")
    internal val driver = db.driver
    internal val url = db.url
    internal val username = db.username
    internal val password = db.password
    internal val test = db.test
    val ipServer =
      url.split("/")
        .getOrNull(2)
  }
}

val saci = QuerySaci()