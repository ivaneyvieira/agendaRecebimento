package br.com.astrosoft.agenda.model

import br.com.astrosoft.framework.util.pdfToText

fun processaPdf(pdfFile: String): String {
  val text = pdfToText(pdfFile)
  val regex = """\t.*\t.*\t.*\t.*\t.*\t.*""".toRegex()
  
  return text.lines()
    .mapNotNull {line ->
      if(regex.containsMatchIn(line))
        line
      else null
    }
    .joinToString("\n") {line ->
      line.split("\t")
        .joinToString("\t") {
          it.trim()
        }
    }
}

fun main() {
  val text = processaPdf("/home/ivaneyvieira/pedido_venda_286910.pdf")
  println(text)
  val text2 = processaPdf("/home/ivaneyvieira/tigre.pdf")
  println(text2)
}
