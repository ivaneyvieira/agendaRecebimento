package br.com.astrosoft.agenda.viewmodel.agenda

import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.ViewModel

class AgendaDevolucaoViewModel(view: IAgendaDevolucaoView): ViewModel<IAgendaDevolucaoView>(view) {
  val tabPedidoPendente = PedidoPendenteTab(this)
}

interface IAgendaDevolucaoView: IView {
  val tabPedidoPendente: IPedidoPendente
}