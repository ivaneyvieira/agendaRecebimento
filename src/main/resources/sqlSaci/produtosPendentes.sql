SELECT O.storeno                                                                                AS loja,
       O.no                                                                                     AS numeroPedido,
       CAST(O.date AS DATE)                                                                     AS dataPedido,
       O.vendno                                                                                 AS codigoFor,
       V.name                                                                                   AS fornecedor,
       I.prdno                                                                                  AS codigo,
       TRIM(MID(P.name, 1, 37))                                                                 AS descricao,
       TRIM(MID(P.name, 37, 3))                                                                 AS unidade,

       I.grade                                                                                  AS grade,
       P.mfno_ref                                                                               AS refFab,
       TRIM(IFNULL(B.barcode, P.barcode))                                                       AS barcode,
       I.icms / 100                                                                             AS aliqICMS,
       I.ipi / 100                                                                              AS aliqIPI,

       CAST(ROUND(IF(O.dataEntrega = 0, DATE_ADD(O.date, INTERVAL O.deliv DAY) * 1,
		     O.dataEntrega)) AS DATE)                                                   AS dataEntrega,
       ROUND(SUM(IF((I.qtty * I.mult - I.qttyCancel * I.mult - I.qttyRcv * I.mult) <= 0, 0,
		    (I.qtty * I.mult - I.qttyCancel * I.mult - I.qttyRcv * I.mult) / 1000)),
	     4)                                                                                 AS qtPendente,
       I.cost                                                                                   AS custoUnitario,
       ROUND(SUM(IF((I.qtty * I.mult - I.qttyCancel * I.mult - I.qttyRcv * I.mult) <= 0, 0,
		    (I.qtty * I.mult - I.qttyCancel * I.mult - I.qttyRcv * I.mult) / 1000) *
		 I.cost),
	     4)                                                                                 AS vlPendente
FROM sqldados.oprd           AS I
  INNER JOIN sqldados.ords   AS O
	       ON O.no = I.ordno AND O.storeno = I.storeno AND O.auxShort2 = 0
  LEFT JOIN  sqldados.vend   AS V
	       ON V.no = O.vendno
  LEFT JOIN  sqldados.prd    AS P
	       ON P.no = I.prdno
  LEFT JOIN  sqldados.prdbar AS B
	       ON B.prdno = I.prdno AND B.grade = I.grade
WHERE O.storeno IN (1, 3, 5, 6, 8, 9, 10, 11, 12, 13)
  AND O.status = 0
  AND V.name NOT LIKE 'ENGECOPI%'
GROUP BY I.storeno, I.ordno, I.prdno, I.grade
HAVING qtPendente > 0
