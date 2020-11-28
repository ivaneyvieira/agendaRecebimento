package br.com.astrosoft.agenda.view.agenda

import br.com.astrosoft.AppConfig
import br.com.astrosoft.agenda.model.beans.UserSaci
import br.com.astrosoft.agenda.view.AgendaDevolucaoLayout
import br.com.astrosoft.agenda.viewmodel.agenda.AgendaDevolucaoViewModel
import br.com.astrosoft.agenda.viewmodel.agenda.IAgendaDevolucaoView
import br.com.astrosoft.framework.view.ViewLayout
import br.com.astrosoft.framework.view.tabPanel
import com.github.mvysny.karibudsl.v10.tabSheet
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(layout = AgendaDevolucaoLayout::class)
@PageTitle("Agenda Devolução")
class AgendaDevolucaoView: ViewLayout<AgendaDevolucaoViewModel>(), IAgendaDevolucaoView {
  override val viewModel: AgendaDevolucaoViewModel = AgendaDevolucaoViewModel(this)
  override val tabPedidoPendente = TabPedidoPendente(viewModel.tabPedidoPendente)
  
  override fun isAccept(user: UserSaci) = true
  
  init {
    tabSheet {
      val username = AppConfig.userSaci
      setSizeFull()
      if(username?.pedidoPendente == true)
        tabPanel(tabPedidoPendente)
    }
  }
}