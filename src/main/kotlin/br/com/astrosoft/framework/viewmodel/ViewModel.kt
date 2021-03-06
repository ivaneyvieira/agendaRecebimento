package br.com.astrosoft.framework.viewmodel

import br.com.astrosoft.framework.view.log

open class ViewModel<V: IView>(val view: V) {
  fun exec(block: () -> Unit) = exec(view, block)
}

fun exec(view: IView, block: () -> Unit) {
  try {
    block()
  } catch(e: EViewModelFail) {
    view.showError(e.message ?: "Erro generico")
    log?.error(e.toString())
    throw e
  }
}

fun fail(message: String): Nothing {
  throw EViewModelFail(message)
}

interface IView {
  fun showError(msg: String)
  fun showWarning(msg: String)
  fun showInformation(msg: String)
}