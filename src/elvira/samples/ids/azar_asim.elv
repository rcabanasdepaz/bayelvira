idiagram azar
{
	title="Red bayesiana de juego de azar";
	autor="MGO";
	default node states=(ausente, presente);

// Nodos

  node factorX {
      title="Para que se añadan arcos de memoria";
      kind-of-node=chance;
      states=(factor1,factor2);
  }

  node DECCOMP {
      title="Decision sobre la composicion";
      kind-of-node=decision;
      states=(igual,distinto);
  }
  
  node COMP {
      title="Composicion de la urna";
      kind-of-node=chance;
      type-of-variable=finite-states;
      states=(r10n10, r7n3);
  }

  node EX1 {
	title="Primera extraccion";
      kind-of-node=chance;
      type-of-variable=finite-states;
	states=(roja,negra);
  }

  node EX2 {
	title="Segunda extraccion";
      kind-of-node=chance;
      type-of-variable=finite-states;
	states=(roja,negra);
   }

  node APUESTA {
	title="Apuesta final";
	kind-of-node=decision;
	type-of-variable=finite-states;
	states=(Rojo,Negro);
  }


  node VALOR (continuous) {
	title="Valoracion del proceso";
	kind-of-node=utility;
	min=0.0;
	max=100.0;
  }

// Enlaces

  link factorX DECCOMP;
  link DECCOMP COMP;
  link DECCOMP APUESTA;
  link COMP EX1;
  link COMP EX2;
  link EX1 EX2;
  link EX1 APUESTA;
  link EX2 VALOR;
  link APUESTA VALOR;

// Relaciones

  relation factorX
  {
	 values=table([factor1]=0.5,
			       [factor2]=0.5);
  }

  relation COMP DECCOMP
  {
	values=table([r10n10,igual]=1,
		[r10n10,distinto]=0,
		[r7n3,igual]=0,
		[r7n3,distinto]=1);
  }

  relation EX1 COMP
  {
	values=table([roja,r10n10]=0.5,
		     [roja,r7n3]=0.7,
		     [negra,r10n10]=0.5,
		     [negra,r7n3]=0.3);
  }

  relation EX2 COMP EX1
  {
	values=table([roja,r10n10,roja]=0.47,
		     [roja,r10n10,negra]=0.53,
		     [roja,r7n3,roja]=0.67,
		     [roja,r7n3,negra]=0.77,
		     [negra,r10n10,roja]=0.53,
		     [negra,r10n10,negra]=0.47,
		     [negra,r7n3,roja]=0.33,
		     [negra,r7n3,negra]=0.23);
  }

  relation VALOR APUESTA EX2
  {
       values=table([Rojo,roja]=115.0
		    [Rojo,negra]=0.0
		    [Negro,roja]=20.0
		    [Negro,negra]=100.0);
  }

relation DECCOMP COMP
{
  kind-of-relation=constraint;
  values=logical-expression(DECCOMP in {igual} <-> COMP in {r10n10});
}
relation DECCOMP COMP
{
  kind-of-relation=constraint;
  values=logical-expression(DECCOMP in {distinto} <-> COMP in {r7n3});
}

}
