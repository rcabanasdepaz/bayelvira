idiagram ICTNEO_1706
{
   title = "Conversion a partir de formato IctNeo";
   author = "Manuel Gomez";
   default node states = (ausente presente);

//Description of NODES

node Valor (continuous) {
    kind-of-node = utility;
    min = 1.254629;
    max = 999.694336;
}

node DHBrb {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO  EXTREMO );
}

node DHBrb2 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO  EXTREMO );
}

node DHBrb1 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO  EXTREMO );
}

node DTer {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO );
}

node DTer2 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO );
}

node DTer1 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NULO  BAJO  MEDIO  ALTO  MUY_ALTO );
}

node PatLG {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Leve  Grave  MuyGrave );
}

node Ecm {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Ausente  Presente );
}

node Sep {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Ausente  Presente );
}

node Axp {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Ausente  Presente );
}

node IRh {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Ausente  Presente );
}

node IG {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Ausente  Presente );
}

node CHgb2 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Baja  Media  Alta );
}

node CBrb2 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Normal  Alta );
}

node EH2 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  menos_de_24horas  mas_de_24horas );
}

node CHgb1 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Baja  Media  Alta );
}

node CBrb1 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Normal  Alta );
}

node EH1 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  menos_de_12horas  mas_de_12horas );
}

node EM {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  de15_18  de_19_35  de_35_mas );
}

node Rzm {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Negra  Caucasica  Asiatica  Gitana );
}

node Enf {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NoEnferma  Enferma );
}

node PI {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NoInstrumental  Instrumental );
}

node AP {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Primeriza  Multipara );
}

node Ict {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Normal  Amarillo  Pies  Calabaza );
}

node PN {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  P499_999  P1000_1499  P1500_2499  P2500 );
}

node EG {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Prematuro  aTermino  posTermino );
}

node TRe {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  R1_2  R3  R4_5 );
}

node TpH {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  pH_menor_7  ph_mayor_7 );
}

node TA5 {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  A0_3  A4_7  A8_10 );
}

node TCmIRh {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Negativo  Positivo );
}

node TChIRh {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Negativo  Positivo );
}

node TChIG {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  Negativo  Positivo );
}

node Gsm {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  CERO  A  B  AB );
}

node Gsh {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  CERO  A  B  AB );
}

node FRhm {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NEGATIVO  POSITIVO );
}

node FRhh {
    kind-of-node = chance;
    type-of-variable = finite-states;
    states = (  NEGATIVO  POSITIVO );
}

node Terapia2 {
    kind-of-node = decision;
    type-of-variable = finite-states;
    states = (  TerNo  ObsAlt  Obs6  Fot6  Fot12  Fot24  Fot6ExaFot6  Fot6ExaFot12  ObsTer );
}

node Terapia1 {
    kind-of-node = decision;
    type-of-variable = finite-states;
    states = (  TerNo  Fot6  Fot12  Fot24  ObsTer );
}

//Description of LINKS

link DTer Valor;

link DHBrb Valor;

link DHBrb1 DHBrb;

link DHBrb2 DHBrb;

link CBrb2 DHBrb2;

link PatLG DHBrb2;

link EH2 DHBrb2;

link CBrb1 DHBrb1;

link PatLG DHBrb1;

link EH1 DHBrb1;

link DTer1 DTer;

link DTer2 DTer;

link Terapia2 DTer2;

link CBrb2 DTer2;

link EH2 DTer2;

link Terapia1 DTer1;

link CBrb1 DTer1;

link EH1 DTer1;

link IG PatLG;

link IRh PatLG;

link Axp PatLG;

link Sep PatLG;

link Ecm PatLG;

link FRhm IRh;

link FRhh IRh;

link AP IRh;

link Gsm IG;

link Gsh IG;

link IG CHgb2;

link IRh CHgb2;

link Axp CHgb2;

link Sep CHgb2;

link Ecm CHgb2;

link CHgb2 CBrb2;

link EH2 CBrb2;

link IG CHgb1;

link IRh CHgb1;

link Axp CHgb1;

link Sep CHgb1;

link Ecm CHgb1;

link CHgb1 CBrb1;

link EH1 CBrb1;

link Rzm Ict;

link CBrb1 Ict;

link EG PN;

link Axp TRe;

link Axp TpH;

link Axp TA5;

link IRh TCmIRh;

link TCmIRh TChIRh;

link IRh TChIRh;

link IG TChIG;

link FRhm FRhh;

link EH2 Terapia2;

link CBrb2 Terapia2;

link CHgb2 Terapia2;

link Terapia1 Terapia2;

link EH1 Terapia1;

link CBrb1 Terapia1;

link CHgb1 Terapia1;

link EM Terapia1;

link Ict Terapia1;

link Rzm Terapia1;

link Enf Terapia1;

link TA5 Terapia1;

link TpH Terapia1;

link TRe Terapia1;

link Gsm Terapia1;

link TChIG Terapia1;

link TChIRh Terapia1;

link TCmIRh Terapia1;

link AP Terapia1;

link PI Terapia1;

link EG Terapia1;

link PN Terapia1;

// Relaciones 

relation Valor DTer DHBrb
{
  values=table(
               [NULO NULO] = 100,
               [NULO BAJO] = 0,
               [NULO MEDIO] = 0,
               [NULO ALTO] = 0,
               [NULO MUY_ALTO] = 0,
               [NULO EXTREMO] = 0,
               [BAJO NULO] = 95,
               [BAJO BAJO] = 90,
               [BAJO MEDIO] = 85,
               [BAJO ALTO] = 1,
               [BAJO MUY_ALTO] = 0,
               [BAJO EXTREMO] = 0,
               [MEDIO NULO] = 90,
               [MEDIO BAJO] = 85,
               [MEDIO MEDIO] = 80,
               [MEDIO ALTO] = 75,
               [MEDIO MUY_ALTO] = 0,
               [MEDIO EXTREMO] = 0,
               [ALTO NULO] = 0,
               [ALTO BAJO] =0,
               [ALTO MEDIO] = 10,
               [ALTO ALTO] = 85,
               [ALTO MUY_ALTO] = 80,
               [ALTO EXTREMO] = 75,
               [MUY_ALTO NULO] = 0,
               [MUY_ALTO BAJO] =0,
               [MUY_ALTO MEDIO] =4,
               [MUY_ALTO ALTO] = 95,
               [MUY_ALTO MUY_ALTO] = 90,
               [MUY_ALTO EXTREMO] = 85);
}

relation DHBrb DHBrb1 DHBrb2 
{
   values=table(
               [ NULO NULO NULO ] = 0.980000,
               [ NULO NULO BAJO ] = 0.000000,
               [ NULO NULO MEDIO ] = 0.000000,
               [ NULO NULO ALTO ] = 0.000000,
               [ NULO NULO MUY_ALTO ] = 0.000000,
               [ NULO NULO EXTREMO ] = 0.000000,
               [ NULO BAJO NULO ] = 0.000000,
               [ NULO BAJO BAJO ] = 0.000000,
               [ NULO BAJO MEDIO ] = 0.000000,
               [ NULO BAJO ALTO ] = 0.000000,
               [ NULO BAJO MUY_ALTO ] = 0.000000,
               [ NULO BAJO EXTREMO ] = 0.000000,
               [ NULO MEDIO NULO ] = 0.000000,
               [ NULO MEDIO BAJO ] = 0.000000,
               [ NULO MEDIO MEDIO ] = 0.000000,
               [ NULO MEDIO ALTO ] = 0.000000,
               [ NULO MEDIO MUY_ALTO ] = 0.000000,
               [ NULO MEDIO EXTREMO ] = 0.000000,
               [ NULO ALTO NULO ] = 0.000000,
               [ NULO ALTO BAJO ] = 0.000000,
               [ NULO ALTO MEDIO ] = 0.000000,
               [ NULO ALTO ALTO ] = 0.000000,
               [ NULO ALTO MUY_ALTO ] = 0.000000,
               [ NULO ALTO EXTREMO ] = 0.000000,
               [ NULO MUY_ALTO NULO ] = 0.000000,
               [ NULO MUY_ALTO BAJO ] = 0.000000,
               [ NULO MUY_ALTO MEDIO ] = 0.000000,
               [ NULO MUY_ALTO ALTO ] = 0.000000,
               [ NULO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ NULO MUY_ALTO EXTREMO ] = 0.000000,
               [ NULO EXTREMO NULO ] = 0.000000,
               [ NULO EXTREMO BAJO ] = 0.000000,
               [ NULO EXTREMO MEDIO ] = 0.000000,
               [ NULO EXTREMO ALTO ] = 0.000000,
               [ NULO EXTREMO MUY_ALTO ] = 0.000000,
               [ NULO EXTREMO EXTREMO ] = 0.000000,
               [ BAJO NULO NULO ] = 0.020000,
               [ BAJO NULO BAJO ] = 0.970000,
               [ BAJO NULO MEDIO ] = 0.000000,
               [ BAJO NULO ALTO ] = 0.000000,
               [ BAJO NULO MUY_ALTO ] = 0.000000,
               [ BAJO NULO EXTREMO ] = 0.000000,
               [ BAJO BAJO NULO ] = 0.970000,
               [ BAJO BAJO BAJO ] = 0.950000,
               [ BAJO BAJO MEDIO ] = 0.000000,
               [ BAJO BAJO ALTO ] = 0.000000,
               [ BAJO BAJO MUY_ALTO ] = 0.000000,
               [ BAJO BAJO EXTREMO ] = 0.000000,
               [ BAJO MEDIO NULO ] = 0.000000,
               [ BAJO MEDIO BAJO ] = 0.000000,
               [ BAJO MEDIO MEDIO ] = 0.000000,
               [ BAJO MEDIO ALTO ] = 0.000000,
               [ BAJO MEDIO MUY_ALTO ] = 0.000000,
               [ BAJO MEDIO EXTREMO ] = 0.000000,
               [ BAJO ALTO NULO ] = 0.000000,
               [ BAJO ALTO BAJO ] = 0.000000,
               [ BAJO ALTO MEDIO ] = 0.000000,
               [ BAJO ALTO ALTO ] = 0.000000,
               [ BAJO ALTO MUY_ALTO ] = 0.000000,
               [ BAJO ALTO EXTREMO ] = 0.000000,
               [ BAJO MUY_ALTO NULO ] = 0.000000,
               [ BAJO MUY_ALTO BAJO ] = 0.000000,
               [ BAJO MUY_ALTO MEDIO ] = 0.000000,
               [ BAJO MUY_ALTO ALTO ] = 0.000000,
               [ BAJO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ BAJO MUY_ALTO EXTREMO ] = 0.000000,
               [ BAJO EXTREMO NULO ] = 0.000000,
               [ BAJO EXTREMO BAJO ] = 0.000000,
               [ BAJO EXTREMO MEDIO ] = 0.000000,
               [ BAJO EXTREMO ALTO ] = 0.000000,
               [ BAJO EXTREMO MUY_ALTO ] = 0.000000,
               [ BAJO EXTREMO EXTREMO ] = 0.000000,
               [ MEDIO NULO NULO ] = 0.000000,
               [ MEDIO NULO BAJO ] = 0.030000,
               [ MEDIO NULO MEDIO ] = 0.970000,
               [ MEDIO NULO ALTO ] = 0.000000,
               [ MEDIO NULO MUY_ALTO ] = 0.000000,
               [ MEDIO NULO EXTREMO ] = 0.000000,
               [ MEDIO BAJO NULO ] = 0.030000,
               [ MEDIO BAJO BAJO ] = 0.050000,
               [ MEDIO BAJO MEDIO ] = 0.970000,
               [ MEDIO BAJO ALTO ] = 0.000000,
               [ MEDIO BAJO MUY_ALTO ] = 0.000000,
               [ MEDIO BAJO EXTREMO ] = 0.000000,
               [ MEDIO MEDIO NULO ] = 0.980000,
               [ MEDIO MEDIO BAJO ] = 0.970000,
               [ MEDIO MEDIO MEDIO ] = 0.950000,
               [ MEDIO MEDIO ALTO ] = 0.000000,
               [ MEDIO MEDIO MUY_ALTO ] = 0.000000,
               [ MEDIO MEDIO EXTREMO ] = 0.000000,
               [ MEDIO ALTO NULO ] = 0.000000,
               [ MEDIO ALTO BAJO ] = 0.000000,
               [ MEDIO ALTO MEDIO ] = 0.000000,
               [ MEDIO ALTO ALTO ] = 0.000000,
               [ MEDIO ALTO MUY_ALTO ] = 0.000000,
               [ MEDIO ALTO EXTREMO ] = 0.000000,
               [ MEDIO MUY_ALTO NULO ] = 0.000000,
               [ MEDIO MUY_ALTO BAJO ] = 0.000000,
               [ MEDIO MUY_ALTO MEDIO ] = 0.000000,
               [ MEDIO MUY_ALTO ALTO ] = 0.000000,
               [ MEDIO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ MEDIO MUY_ALTO EXTREMO ] = 0.000000,
               [ MEDIO EXTREMO NULO ] = 0.000000,
               [ MEDIO EXTREMO BAJO ] = 0.000000,
               [ MEDIO EXTREMO MEDIO ] = 0.000000,
               [ MEDIO EXTREMO ALTO ] = 0.000000,
               [ MEDIO EXTREMO MUY_ALTO ] = 0.000000,
               [ MEDIO EXTREMO EXTREMO ] = 0.000000,
               [ ALTO NULO NULO ] = 0.000000,
               [ ALTO NULO BAJO ] = 0.000000,
               [ ALTO NULO MEDIO ] = 0.030000,
               [ ALTO NULO ALTO ] = 0.970000,
               [ ALTO NULO MUY_ALTO ] = 0.000000,
               [ ALTO NULO EXTREMO ] = 0.000000,
               [ ALTO BAJO NULO ] = 0.000000,
               [ ALTO BAJO BAJO ] = 0.000000,
               [ ALTO BAJO MEDIO ] = 0.030000,
               [ ALTO BAJO ALTO ] = 0.980000,
               [ ALTO BAJO MUY_ALTO ] = 0.000000,
               [ ALTO BAJO EXTREMO ] = 0.000000,
               [ ALTO MEDIO NULO ] = 0.020000,
               [ ALTO MEDIO BAJO ] = 0.030000,
               [ ALTO MEDIO MEDIO ] = 0.050000,
               [ ALTO MEDIO ALTO ] = 0.970000,
               [ ALTO MEDIO MUY_ALTO ] = 0.000000,
               [ ALTO MEDIO EXTREMO ] = 0.000000,
               [ ALTO ALTO NULO ] = 0.980000,
               [ ALTO ALTO BAJO ] = 0.970000,
               [ ALTO ALTO MEDIO ] = 0.960000,
               [ ALTO ALTO ALTO ] = 0.950000,
               [ ALTO ALTO MUY_ALTO ] = 0.000000,
               [ ALTO ALTO EXTREMO ] = 0.000000,
               [ ALTO MUY_ALTO NULO ] = 0.000000,
               [ ALTO MUY_ALTO BAJO ] = 0.000000,
               [ ALTO MUY_ALTO MEDIO ] = 0.000000,
               [ ALTO MUY_ALTO ALTO ] = 0.000000,
               [ ALTO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ ALTO MUY_ALTO EXTREMO ] = 0.000000,
               [ ALTO EXTREMO NULO ] = 0.000000,
               [ ALTO EXTREMO BAJO ] = 0.000000,
               [ ALTO EXTREMO MEDIO ] = 0.000000,
               [ ALTO EXTREMO ALTO ] = 0.000000,
               [ ALTO EXTREMO MUY_ALTO ] = 0.000000,
               [ ALTO EXTREMO EXTREMO ] = 0.000000,
               [ MUY_ALTO NULO NULO ] = 0.000000,
               [ MUY_ALTO NULO BAJO ] = 0.000000,
               [ MUY_ALTO NULO MEDIO ] = 0.000000,
               [ MUY_ALTO NULO ALTO ] = 0.030000,
               [ MUY_ALTO NULO MUY_ALTO ] = 0.970000,
               [ MUY_ALTO NULO EXTREMO ] = 0.000000,
               [ MUY_ALTO BAJO NULO ] = 0.000000,
               [ MUY_ALTO BAJO BAJO ] = 0.000000,
               [ MUY_ALTO BAJO MEDIO ] = 0.000000,
               [ MUY_ALTO BAJO ALTO ] = 0.020000,
               [ MUY_ALTO BAJO MUY_ALTO ] = 0.980000,
               [ MUY_ALTO BAJO EXTREMO ] = 0.000000,
               [ MUY_ALTO MEDIO NULO ] = 0.000000,
               [ MUY_ALTO MEDIO BAJO ] = 0.000000,
               [ MUY_ALTO MEDIO MEDIO ] = 0.000000,
               [ MUY_ALTO MEDIO ALTO ] = 0.030000,
               [ MUY_ALTO MEDIO MUY_ALTO ] = 0.970000,
               [ MUY_ALTO MEDIO EXTREMO ] = 0.000000,
               [ MUY_ALTO ALTO NULO ] = 0.020000,
               [ MUY_ALTO ALTO BAJO ] = 0.030000,
               [ MUY_ALTO ALTO MEDIO ] = 0.040000,
               [ MUY_ALTO ALTO ALTO ] = 0.050000,
               [ MUY_ALTO ALTO MUY_ALTO ] = 0.980000,
               [ MUY_ALTO ALTO EXTREMO ] = 0.000000,
               [ MUY_ALTO MUY_ALTO NULO ] = 0.990000,
               [ MUY_ALTO MUY_ALTO BAJO ] = 0.980000,
               [ MUY_ALTO MUY_ALTO MEDIO ] = 0.970000,
               [ MUY_ALTO MUY_ALTO ALTO ] = 0.960000,
               [ MUY_ALTO MUY_ALTO MUY_ALTO ] = 0.950000,
               [ MUY_ALTO MUY_ALTO EXTREMO ] = 0.000000,
               [ MUY_ALTO EXTREMO NULO ] = 0.000000,
               [ MUY_ALTO EXTREMO BAJO ] = 0.000000,
               [ MUY_ALTO EXTREMO MEDIO ] = 0.000000,
               [ MUY_ALTO EXTREMO ALTO ] = 0.000000,
               [ MUY_ALTO EXTREMO MUY_ALTO ] = 0.000000,
               [ MUY_ALTO EXTREMO EXTREMO ] = 0.000000,
               [ EXTREMO NULO NULO ] = 0.000000,
               [ EXTREMO NULO BAJO ] = 0.000000,
               [ EXTREMO NULO MEDIO ] = 0.000000,
               [ EXTREMO NULO ALTO ] = 0.000000,
               [ EXTREMO NULO MUY_ALTO ] = 0.030000,
               [ EXTREMO NULO EXTREMO ] = 1.000000,
               [ EXTREMO BAJO NULO ] = 0.000000,
               [ EXTREMO BAJO BAJO ] = 0.000000,
               [ EXTREMO BAJO MEDIO ] = 0.000000,
               [ EXTREMO BAJO ALTO ] = 0.000000,
               [ EXTREMO BAJO MUY_ALTO ] = 0.020000,
               [ EXTREMO BAJO EXTREMO ] = 1.000000,
               [ EXTREMO MEDIO NULO ] = 0.000000,
               [ EXTREMO MEDIO BAJO ] = 0.000000,
               [ EXTREMO MEDIO MEDIO ] = 0.000000,
               [ EXTREMO MEDIO ALTO ] = 0.000000,
               [ EXTREMO MEDIO MUY_ALTO ] = 0.030000,
               [ EXTREMO MEDIO EXTREMO ] = 1.000000,
               [ EXTREMO ALTO NULO ] = 0.000000,
               [ EXTREMO ALTO BAJO ] = 0.000000,
               [ EXTREMO ALTO MEDIO ] = 0.000000,
               [ EXTREMO ALTO ALTO ] = 0.000000,
               [ EXTREMO ALTO MUY_ALTO ] = 0.020000,
               [ EXTREMO ALTO EXTREMO ] = 1.000000,
               [ EXTREMO MUY_ALTO NULO ] = 0.010000,
               [ EXTREMO MUY_ALTO BAJO ] = 0.020000,
               [ EXTREMO MUY_ALTO MEDIO ] = 0.030000,
               [ EXTREMO MUY_ALTO ALTO ] = 0.040000,
               [ EXTREMO MUY_ALTO MUY_ALTO ] = 0.050000,
               [ EXTREMO MUY_ALTO EXTREMO ] = 1.000000,
               [ EXTREMO EXTREMO NULO ] = 1.000000,
               [ EXTREMO EXTREMO BAJO ] = 1.000000,
               [ EXTREMO EXTREMO MEDIO ] = 1.000000,
               [ EXTREMO EXTREMO ALTO ] = 1.000000,
               [ EXTREMO EXTREMO MUY_ALTO ] = 1.000000,
               [ EXTREMO EXTREMO EXTREMO ] = 1.000000);
}

relation DHBrb2 CBrb2 PatLG EH2 
{
   values=table(
               [ NULO Normal Leve menos_de_24horas ] = 0.980000,
               [ NULO Normal Leve mas_de_24horas ] = 0.990000,
               [ NULO Normal Grave menos_de_24horas ] = 0.980000,
               [ NULO Normal Grave mas_de_24horas ] = 0.990000,
               [ NULO Normal MuyGrave menos_de_24horas ] = 0.980000,
               [ NULO Normal MuyGrave mas_de_24horas ] = 0.990000,
               [ NULO Alta Leve menos_de_24horas ] = 0.100000,
               [ NULO Alta Leve mas_de_24horas ] = 0.150000,
               [ NULO Alta Grave menos_de_24horas ] = 0.000000,
               [ NULO Alta Grave mas_de_24horas ] = 0.000000,
               [ NULO Alta MuyGrave menos_de_24horas ] = 0.000000,
               [ NULO Alta MuyGrave mas_de_24horas ] = 0.000000,
               [ BAJO Normal Leve menos_de_24horas ] = 0.020000,
               [ BAJO Normal Leve mas_de_24horas ] = 0.010000,
               [ BAJO Normal Grave menos_de_24horas ] = 0.020000,
               [ BAJO Normal Grave mas_de_24horas ] = 0.010000,
               [ BAJO Normal MuyGrave menos_de_24horas ] = 0.020000,
               [ BAJO Normal MuyGrave mas_de_24horas ] = 0.010000,
               [ BAJO Alta Leve menos_de_24horas ] = 0.200000,
               [ BAJO Alta Leve mas_de_24horas ] = 0.250000,
               [ BAJO Alta Grave menos_de_24horas ] = 0.100000,
               [ BAJO Alta Grave mas_de_24horas ] = 0.150000,
               [ BAJO Alta MuyGrave menos_de_24horas ] = 0.000000,
               [ BAJO Alta MuyGrave mas_de_24horas ] = 0.000000,
               [ MEDIO Normal Leve menos_de_24horas ] = 0.000000,
               [ MEDIO Normal Leve mas_de_24horas ] = 0.000000,
               [ MEDIO Normal Grave menos_de_24horas ] = 0.000000,
               [ MEDIO Normal Grave mas_de_24horas ] = 0.000000,
               [ MEDIO Normal MuyGrave menos_de_24horas ] = 0.000000,
               [ MEDIO Normal MuyGrave mas_de_24horas ] = 0.000000,
               [ MEDIO Alta Leve menos_de_24horas ] = 0.300000,
               [ MEDIO Alta Leve mas_de_24horas ] = 0.350000,
               [ MEDIO Alta Grave menos_de_24horas ] = 0.200000,
               [ MEDIO Alta Grave mas_de_24horas ] = 0.250000,
               [ MEDIO Alta MuyGrave menos_de_24horas ] = 0.100000,
               [ MEDIO Alta MuyGrave mas_de_24horas ] = 0.150000,
               [ ALTO Normal Leve menos_de_24horas ] = 0.000000,
               [ ALTO Normal Leve mas_de_24horas ] = 0.000000,
               [ ALTO Normal Grave menos_de_24horas ] = 0.000000,
               [ ALTO Normal Grave mas_de_24horas ] = 0.000000,
               [ ALTO Normal MuyGrave menos_de_24horas ] = 0.000000,
               [ ALTO Normal MuyGrave mas_de_24horas ] = 0.000000,
               [ ALTO Alta Leve menos_de_24horas ] = 0.200000,
               [ ALTO Alta Leve mas_de_24horas ] = 0.150000,
               [ ALTO Alta Grave menos_de_24horas ] = 0.300000,
               [ ALTO Alta Grave mas_de_24horas ] = 0.300000,
               [ ALTO Alta MuyGrave menos_de_24horas ] = 0.150000,
               [ ALTO Alta MuyGrave mas_de_24horas ] = 0.200000,
               [ MUY_ALTO Normal Leve menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Normal Leve mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Normal Grave menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Normal Grave mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Normal MuyGrave menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Normal MuyGrave mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Alta Leve menos_de_24horas ] = 0.200000,
               [ MUY_ALTO Alta Leve mas_de_24horas ] = 0.100000,
               [ MUY_ALTO Alta Grave menos_de_24horas ] = 0.250000,
               [ MUY_ALTO Alta Grave mas_de_24horas ] = 0.200000,
               [ MUY_ALTO Alta MuyGrave menos_de_24horas ] = 0.300000,
               [ MUY_ALTO Alta MuyGrave mas_de_24horas ] = 0.250000,
               [ EXTREMO Normal Leve menos_de_24horas ] = 0.000000,
               [ EXTREMO Normal Leve mas_de_24horas ] = 0.000000,
               [ EXTREMO Normal Grave menos_de_24horas ] = 0.000000,
               [ EXTREMO Normal Grave mas_de_24horas ] = 0.000000,
               [ EXTREMO Normal MuyGrave menos_de_24horas ] = 0.000000,
               [ EXTREMO Normal MuyGrave mas_de_24horas ] = 0.000000,
               [ EXTREMO Alta Leve menos_de_24horas ] = 0.000000,
               [ EXTREMO Alta Leve mas_de_24horas ] = 0.000000,
               [ EXTREMO Alta Grave menos_de_24horas ] = 0.150000,
               [ EXTREMO Alta Grave mas_de_24horas ] = 0.100000,
               [ EXTREMO Alta MuyGrave menos_de_24horas ] = 0.450000,
               [ EXTREMO Alta MuyGrave mas_de_24horas ] = 0.400000);
}

relation DHBrb1 CBrb1 PatLG EH1 
{
   values=table(
               [ NULO Normal Leve menos_de_12horas ] = 0.980000,
               [ NULO Normal Leve mas_de_12horas ] = 0.990000,
               [ NULO Normal Grave menos_de_12horas ] = 0.980000,
               [ NULO Normal Grave mas_de_12horas ] = 0.990000,
               [ NULO Normal MuyGrave menos_de_12horas ] = 0.980000,
               [ NULO Normal MuyGrave mas_de_12horas ] = 0.990000,
               [ NULO Alta Leve menos_de_12horas ] = 0.050000,
               [ NULO Alta Leve mas_de_12horas ] = 0.100000,
               [ NULO Alta Grave menos_de_12horas ] = 0.000000,
               [ NULO Alta Grave mas_de_12horas ] = 0.000000,
               [ NULO Alta MuyGrave menos_de_12horas ] = 0.000000,
               [ NULO Alta MuyGrave mas_de_12horas ] = 0.000000,
               [ BAJO Normal Leve menos_de_12horas ] = 0.020000,
               [ BAJO Normal Leve mas_de_12horas ] = 0.010000,
               [ BAJO Normal Grave menos_de_12horas ] = 0.020000,
               [ BAJO Normal Grave mas_de_12horas ] = 0.010000,
               [ BAJO Normal MuyGrave menos_de_12horas ] = 0.020000,
               [ BAJO Normal MuyGrave mas_de_12horas ] = 0.010000,
               [ BAJO Alta Leve menos_de_12horas ] = 0.100000,
               [ BAJO Alta Leve mas_de_12horas ] = 0.200000,
               [ BAJO Alta Grave menos_de_12horas ] = 0.050000,
               [ BAJO Alta Grave mas_de_12horas ] = 0.100000,
               [ BAJO Alta MuyGrave menos_de_12horas ] = 0.000000,
               [ BAJO Alta MuyGrave mas_de_12horas ] = 0.000000,
               [ MEDIO Normal Leve menos_de_12horas ] = 0.000000,
               [ MEDIO Normal Leve mas_de_12horas ] = 0.000000,
               [ MEDIO Normal Grave menos_de_12horas ] = 0.000000,
               [ MEDIO Normal Grave mas_de_12horas ] = 0.000000,
               [ MEDIO Normal MuyGrave menos_de_12horas ] = 0.000000,
               [ MEDIO Normal MuyGrave mas_de_12horas ] = 0.000000,
               [ MEDIO Alta Leve menos_de_12horas ] = 0.200000,
               [ MEDIO Alta Leve mas_de_12horas ] = 0.300000,
               [ MEDIO Alta Grave menos_de_12horas ] = 0.100000,
               [ MEDIO Alta Grave mas_de_12horas ] = 0.200000,
               [ MEDIO Alta MuyGrave menos_de_12horas ] = 0.050000,
               [ MEDIO Alta MuyGrave mas_de_12horas ] = 0.100000,
               [ ALTO Normal Leve menos_de_12horas ] = 0.000000,
               [ ALTO Normal Leve mas_de_12horas ] = 0.000000,
               [ ALTO Normal Grave menos_de_12horas ] = 0.000000,
               [ ALTO Normal Grave mas_de_12horas ] = 0.000000,
               [ ALTO Normal MuyGrave menos_de_12horas ] = 0.000000,
               [ ALTO Normal MuyGrave mas_de_12horas ] = 0.000000,
               [ ALTO Alta Leve menos_de_12horas ] = 0.300000,
               [ ALTO Alta Leve mas_de_12horas ] = 0.200000,
               [ ALTO Alta Grave menos_de_12horas ] = 0.200000,
               [ ALTO Alta Grave mas_de_12horas ] = 0.300000,
               [ ALTO Alta MuyGrave menos_de_12horas ] = 0.200000,
               [ ALTO Alta MuyGrave mas_de_12horas ] = 0.150000,
               [ MUY_ALTO Normal Leve menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Normal Leve mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Normal Grave menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Normal Grave mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Normal MuyGrave menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Normal MuyGrave mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Alta Leve menos_de_12horas ] = 0.350000,
               [ MUY_ALTO Alta Leve mas_de_12horas ] = 0.200000,
               [ MUY_ALTO Alta Grave menos_de_12horas ] = 0.450000,
               [ MUY_ALTO Alta Grave mas_de_12horas ] = 0.250000,
               [ MUY_ALTO Alta MuyGrave menos_de_12horas ] = 0.250000,
               [ MUY_ALTO Alta MuyGrave mas_de_12horas ] = 0.300000,
               [ EXTREMO Normal Leve menos_de_12horas ] = 0.000000,
               [ EXTREMO Normal Leve mas_de_12horas ] = 0.000000,
               [ EXTREMO Normal Grave menos_de_12horas ] = 0.000000,
               [ EXTREMO Normal Grave mas_de_12horas ] = 0.000000,
               [ EXTREMO Normal MuyGrave menos_de_12horas ] = 0.000000,
               [ EXTREMO Normal MuyGrave mas_de_12horas ] = 0.000000,
               [ EXTREMO Alta Leve menos_de_12horas ] = 0.000000,
               [ EXTREMO Alta Leve mas_de_12horas ] = 0.000000,
               [ EXTREMO Alta Grave menos_de_12horas ] = 0.200000,
               [ EXTREMO Alta Grave mas_de_12horas ] = 0.150000,
               [ EXTREMO Alta MuyGrave menos_de_12horas ] = 0.500000,
               [ EXTREMO Alta MuyGrave mas_de_12horas ] = 0.450000);
}

relation DTer DTer1 DTer2 
{
   values=table(
               [ NULO NULO NULO ] = 0.970000,
               [ NULO NULO BAJO ] = 0.000000,
               [ NULO NULO MEDIO ] = 0.000000,
               [ NULO NULO ALTO ] = 0.000000,
               [ NULO NULO MUY_ALTO ] = 0.000000,
               [ NULO BAJO NULO ] = 0.000000,
               [ NULO BAJO BAJO ] = 0.000000,
               [ NULO BAJO MEDIO ] = 0.000000,
               [ NULO BAJO ALTO ] = 0.000000,
               [ NULO BAJO MUY_ALTO ] = 0.000000,
               [ NULO MEDIO NULO ] = 0.000000,
               [ NULO MEDIO BAJO ] = 0.000000,
               [ NULO MEDIO MEDIO ] = 0.000000,
               [ NULO MEDIO ALTO ] = 0.000000,
               [ NULO MEDIO MUY_ALTO ] = 0.000000,
               [ NULO ALTO NULO ] = 0.000000,
               [ NULO ALTO BAJO ] = 0.000000,
               [ NULO ALTO MEDIO ] = 0.000000,
               [ NULO ALTO ALTO ] = 0.000000,
               [ NULO ALTO MUY_ALTO ] = 0.000000,
               [ NULO MUY_ALTO NULO ] = 0.000000,
               [ NULO MUY_ALTO BAJO ] = 0.000000,
               [ NULO MUY_ALTO MEDIO ] = 0.000000,
               [ NULO MUY_ALTO ALTO ] = 0.000000,
               [ NULO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ BAJO NULO NULO ] = 0.030000,
               [ BAJO NULO BAJO ] = 0.970000,
               [ BAJO NULO MEDIO ] = 0.000000,
               [ BAJO NULO ALTO ] = 0.000000,
               [ BAJO NULO MUY_ALTO ] = 0.000000,
               [ BAJO BAJO NULO ] = 0.970000,
               [ BAJO BAJO BAJO ] = 0.950000,
               [ BAJO BAJO MEDIO ] = 0.000000,
               [ BAJO BAJO ALTO ] = 0.000000,
               [ BAJO BAJO MUY_ALTO ] = 0.000000,
               [ BAJO MEDIO NULO ] = 0.000000,
               [ BAJO MEDIO BAJO ] = 0.000000,
               [ BAJO MEDIO MEDIO ] = 0.000000,
               [ BAJO MEDIO ALTO ] = 0.000000,
               [ BAJO MEDIO MUY_ALTO ] = 0.000000,
               [ BAJO ALTO NULO ] = 0.000000,
               [ BAJO ALTO BAJO ] = 0.000000,
               [ BAJO ALTO MEDIO ] = 0.000000,
               [ BAJO ALTO ALTO ] = 0.000000,
               [ BAJO ALTO MUY_ALTO ] = 0.000000,
               [ BAJO MUY_ALTO NULO ] = 0.000000,
               [ BAJO MUY_ALTO BAJO ] = 0.000000,
               [ BAJO MUY_ALTO MEDIO ] = 0.000000,
               [ BAJO MUY_ALTO ALTO ] = 0.000000,
               [ BAJO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ MEDIO NULO NULO ] = 0.000000,
               [ MEDIO NULO BAJO ] = 0.030000,
               [ MEDIO NULO MEDIO ] = 0.970000,
               [ MEDIO NULO ALTO ] = 0.000000,
               [ MEDIO NULO MUY_ALTO ] = 0.000000,
               [ MEDIO BAJO NULO ] = 0.030000,
               [ MEDIO BAJO BAJO ] = 0.050000,
               [ MEDIO BAJO MEDIO ] = 0.970000,
               [ MEDIO BAJO ALTO ] = 0.000000,
               [ MEDIO BAJO MUY_ALTO ] = 0.000000,
               [ MEDIO MEDIO NULO ] = 0.980000,
               [ MEDIO MEDIO BAJO ] = 0.970000,
               [ MEDIO MEDIO MEDIO ] = 0.950000,
               [ MEDIO MEDIO ALTO ] = 0.000000,
               [ MEDIO MEDIO MUY_ALTO ] = 0.000000,
               [ MEDIO ALTO NULO ] = 0.000000,
               [ MEDIO ALTO BAJO ] = 0.000000,
               [ MEDIO ALTO MEDIO ] = 0.000000,
               [ MEDIO ALTO ALTO ] = 0.000000,
               [ MEDIO ALTO MUY_ALTO ] = 0.000000,
               [ MEDIO MUY_ALTO NULO ] = 0.000000,
               [ MEDIO MUY_ALTO BAJO ] = 0.000000,
               [ MEDIO MUY_ALTO MEDIO ] = 0.000000,
               [ MEDIO MUY_ALTO ALTO ] = 0.000000,
               [ MEDIO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ ALTO NULO NULO ] = 0.000000,
               [ ALTO NULO BAJO ] = 0.000000,
               [ ALTO NULO MEDIO ] = 0.030000,
               [ ALTO NULO ALTO ] = 0.970000,
               [ ALTO NULO MUY_ALTO ] = 0.000000,
               [ ALTO BAJO NULO ] = 0.000000,
               [ ALTO BAJO BAJO ] = 0.000000,
               [ ALTO BAJO MEDIO ] = 0.030000,
               [ ALTO BAJO ALTO ] = 0.980000,
               [ ALTO BAJO MUY_ALTO ] = 0.000000,
               [ ALTO MEDIO NULO ] = 0.020000,
               [ ALTO MEDIO BAJO ] = 0.030000,
               [ ALTO MEDIO MEDIO ] = 0.050000,
               [ ALTO MEDIO ALTO ] = 0.970000,
               [ ALTO MEDIO MUY_ALTO ] = 0.000000,
               [ ALTO ALTO NULO ] = 0.980000,
               [ ALTO ALTO BAJO ] = 0.970000,
               [ ALTO ALTO MEDIO ] = 0.960000,
               [ ALTO ALTO ALTO ] = 0.950000,
               [ ALTO ALTO MUY_ALTO ] = 0.000000,
               [ ALTO MUY_ALTO NULO ] = 0.000000,
               [ ALTO MUY_ALTO BAJO ] = 0.000000,
               [ ALTO MUY_ALTO MEDIO ] = 0.000000,
               [ ALTO MUY_ALTO ALTO ] = 0.000000,
               [ ALTO MUY_ALTO MUY_ALTO ] = 0.000000,
               [ MUY_ALTO NULO NULO ] = 0.000000,
               [ MUY_ALTO NULO BAJO ] = 0.000000,
               [ MUY_ALTO NULO MEDIO ] = 0.000000,
               [ MUY_ALTO NULO ALTO ] = 0.030000,
               [ MUY_ALTO NULO MUY_ALTO ] = 1.000000,
               [ MUY_ALTO BAJO NULO ] = 0.000000,
               [ MUY_ALTO BAJO BAJO ] = 0.000000,
               [ MUY_ALTO BAJO MEDIO ] = 0.000000,
               [ MUY_ALTO BAJO ALTO ] = 0.020000,
               [ MUY_ALTO BAJO MUY_ALTO ] = 1.000000,
               [ MUY_ALTO MEDIO NULO ] = 0.000000,
               [ MUY_ALTO MEDIO BAJO ] = 0.000000,
               [ MUY_ALTO MEDIO MEDIO ] = 0.000000,
               [ MUY_ALTO MEDIO ALTO ] = 0.030000,
               [ MUY_ALTO MEDIO MUY_ALTO ] = 1.000000,
               [ MUY_ALTO ALTO NULO ] = 0.020000,
               [ MUY_ALTO ALTO BAJO ] = 0.030000,
               [ MUY_ALTO ALTO MEDIO ] = 0.040000,
               [ MUY_ALTO ALTO ALTO ] = 0.050000,
               [ MUY_ALTO ALTO MUY_ALTO ] = 1.000000,
               [ MUY_ALTO MUY_ALTO NULO ] = 1.000000,
               [ MUY_ALTO MUY_ALTO BAJO ] = 1.000000,
               [ MUY_ALTO MUY_ALTO MEDIO ] = 1.000000,
               [ MUY_ALTO MUY_ALTO ALTO ] = 1.000000,
               [ MUY_ALTO MUY_ALTO MUY_ALTO ] = 1.000000);
}

relation DTer2 Terapia2 CBrb2 EH2 
{
   values=table(
               [ NULO TerNo Normal menos_de_24horas ] = 1.000000,
               [ NULO TerNo Normal mas_de_24horas ] = 1.000000,
               [ NULO TerNo Alta menos_de_24horas ] = 1.000000,
               [ NULO TerNo Alta mas_de_24horas ] = 1.000000,
               [ NULO ObsAlt Normal menos_de_24horas ] = 0.990000,
               [ NULO ObsAlt Normal mas_de_24horas ] = 0.990000,
               [ NULO ObsAlt Alta menos_de_24horas ] = 0.000000,
               [ NULO ObsAlt Alta mas_de_24horas ] = 0.000000,
               [ NULO Obs6 Normal menos_de_24horas ] = 0.050000,
               [ NULO Obs6 Normal mas_de_24horas ] = 0.030000,
               [ NULO Obs6 Alta menos_de_24horas ] = 0.950000,
               [ NULO Obs6 Alta mas_de_24horas ] = 0.030000,
               [ NULO Fot6 Normal menos_de_24horas ] = 0.000000,
               [ NULO Fot6 Normal mas_de_24horas ] = 0.000000,
               [ NULO Fot6 Alta menos_de_24horas ] = 0.300000,
               [ NULO Fot6 Alta mas_de_24horas ] = 0.350000,
               [ NULO Fot12 Normal menos_de_24horas ] = 0.000000,
               [ NULO Fot12 Normal mas_de_24horas ] = 0.000000,
               [ NULO Fot12 Alta menos_de_24horas ] = 0.200000,
               [ NULO Fot12 Alta mas_de_24horas ] = 0.500000,
               [ NULO Fot24 Normal menos_de_24horas ] = 0.990000,
               [ NULO Fot24 Normal mas_de_24horas ] = 0.990000,
               [ NULO Fot24 Alta menos_de_24horas ] = 0.000000,
               [ NULO Fot24 Alta mas_de_24horas ] = 0.000000,
               [ NULO Fot6ExaFot6 Normal menos_de_24horas ] = 0.000000,
               [ NULO Fot6ExaFot6 Normal mas_de_24horas ] = 0.000000,
               [ NULO Fot6ExaFot6 Alta menos_de_24horas ] = 0.990000,
               [ NULO Fot6ExaFot6 Alta mas_de_24horas ] = 0.950000,
               [ NULO Fot6ExaFot12 Normal menos_de_24horas ] = 0.000000,
               [ NULO Fot6ExaFot12 Normal mas_de_24horas ] = 0.000000,
               [ NULO Fot6ExaFot12 Alta menos_de_24horas ] = 0.990000,
               [ NULO Fot6ExaFot12 Alta mas_de_24horas ] = 0.950000,
               [ NULO ObsTer Normal menos_de_24horas ] = 0.990000,
               [ NULO ObsTer Normal mas_de_24horas ] = 0.800000,
               [ NULO ObsTer Alta menos_de_24horas ] = 0.000000,
               [ NULO ObsTer Alta mas_de_24horas ] = 0.000000,
               [ BAJO TerNo Normal menos_de_24horas ] = 0.000000,
               [ BAJO TerNo Normal mas_de_24horas ] = 0.000000,
               [ BAJO TerNo Alta menos_de_24horas ] = 0.000000,
               [ BAJO TerNo Alta mas_de_24horas ] = 0.000000,
               [ BAJO ObsAlt Normal menos_de_24horas ] = 0.010000,
               [ BAJO ObsAlt Normal mas_de_24horas ] = 0.010000,
               [ BAJO ObsAlt Alta menos_de_24horas ] = 0.000000,
               [ BAJO ObsAlt Alta mas_de_24horas ] = 0.000000,
               [ BAJO Obs6 Normal menos_de_24horas ] = 0.475000,
               [ BAJO Obs6 Normal mas_de_24horas ] = 0.485000,
               [ BAJO Obs6 Alta menos_de_24horas ] = 0.050000,
               [ BAJO Obs6 Alta mas_de_24horas ] = 0.970000,
               [ BAJO Fot6 Normal menos_de_24horas ] = 0.300000,
               [ BAJO Fot6 Normal mas_de_24horas ] = 0.050000,
               [ BAJO Fot6 Alta menos_de_24horas ] = 0.700000,
               [ BAJO Fot6 Alta mas_de_24horas ] = 0.650000,
               [ BAJO Fot12 Normal menos_de_24horas ] = 0.200000,
               [ BAJO Fot12 Normal mas_de_24horas ] = 0.200000,
               [ BAJO Fot12 Alta menos_de_24horas ] = 0.800000,
               [ BAJO Fot12 Alta mas_de_24horas ] = 0.500000,
               [ BAJO Fot24 Normal menos_de_24horas ] = 0.010000,
               [ BAJO Fot24 Normal mas_de_24horas ] = 0.010000,
               [ BAJO Fot24 Alta menos_de_24horas ] = 0.000000,
               [ BAJO Fot24 Alta mas_de_24horas ] = 0.000000,
               [ BAJO Fot6ExaFot6 Normal menos_de_24horas ] = 0.000000,
               [ BAJO Fot6ExaFot6 Normal mas_de_24horas ] = 0.000000,
               [ BAJO Fot6ExaFot6 Alta menos_de_24horas ] = 0.010000,
               [ BAJO Fot6ExaFot6 Alta mas_de_24horas ] = 0.050000,
               [ BAJO Fot6ExaFot12 Normal menos_de_24horas ] = 0.000000,
               [ BAJO Fot6ExaFot12 Normal mas_de_24horas ] = 0.000000,
               [ BAJO Fot6ExaFot12 Alta menos_de_24horas ] = 0.010000,
               [ BAJO Fot6ExaFot12 Alta mas_de_24horas ] = 0.050000,
               [ BAJO ObsTer Normal menos_de_24horas ] = 0.010000,
               [ BAJO ObsTer Normal mas_de_24horas ] = 0.200000,
               [ BAJO ObsTer Alta menos_de_24horas ] = 0.000000,
               [ BAJO ObsTer Alta mas_de_24horas ] = 0.000000,
               [ MEDIO TerNo Normal menos_de_24horas ] = 0.000000,
               [ MEDIO TerNo Normal mas_de_24horas ] = 0.000000,
               [ MEDIO TerNo Alta menos_de_24horas ] = 0.000000,
               [ MEDIO TerNo Alta mas_de_24horas ] = 0.000000,
               [ MEDIO ObsAlt Normal menos_de_24horas ] = 0.000000,
               [ MEDIO ObsAlt Normal mas_de_24horas ] = 0.000000,
               [ MEDIO ObsAlt Alta menos_de_24horas ] = 0.000000,
               [ MEDIO ObsAlt Alta mas_de_24horas ] = 0.020000,
               [ MEDIO Obs6 Normal menos_de_24horas ] = 0.475000,
               [ MEDIO Obs6 Normal mas_de_24horas ] = 0.485000,
               [ MEDIO Obs6 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Obs6 Alta mas_de_24horas ] = 0.000000,
               [ MEDIO Fot6 Normal menos_de_24horas ] = 0.700000,
               [ MEDIO Fot6 Normal mas_de_24horas ] = 0.950000,
               [ MEDIO Fot6 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Fot6 Alta mas_de_24horas ] = 0.000000,
               [ MEDIO Fot12 Normal menos_de_24horas ] = 0.700000,
               [ MEDIO Fot12 Normal mas_de_24horas ] = 0.800000,
               [ MEDIO Fot12 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Fot12 Alta mas_de_24horas ] = 0.000000,
               [ MEDIO Fot24 Normal menos_de_24horas ] = 0.000000,
               [ MEDIO Fot24 Normal mas_de_24horas ] = 0.000000,
               [ MEDIO Fot24 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Fot24 Alta mas_de_24horas ] = 0.020000,
               [ MEDIO Fot6ExaFot6 Normal menos_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot6 Normal mas_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot6 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot6 Alta mas_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot12 Normal menos_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot12 Normal mas_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot12 Alta menos_de_24horas ] = 0.000000,
               [ MEDIO Fot6ExaFot12 Alta mas_de_24horas ] = 0.000000,
               [ MEDIO ObsTer Normal menos_de_24horas ] = 0.000000,
               [ MEDIO ObsTer Normal mas_de_24horas ] = 0.000000,
               [ MEDIO ObsTer Alta menos_de_24horas ] = 0.020000,
               [ MEDIO ObsTer Alta mas_de_24horas ] = 0.200000,
               [ ALTO TerNo Normal menos_de_24horas ] = 0.000000,
               [ ALTO TerNo Normal mas_de_24horas ] = 0.000000,
               [ ALTO TerNo Alta menos_de_24horas ] = 0.000000,
               [ ALTO TerNo Alta mas_de_24horas ] = 0.000000,
               [ ALTO ObsAlt Normal menos_de_24horas ] = 0.000000,
               [ ALTO ObsAlt Normal mas_de_24horas ] = 0.000000,
               [ ALTO ObsAlt Alta menos_de_24horas ] = 0.990000,
               [ ALTO ObsAlt Alta mas_de_24horas ] = 0.980000,
               [ ALTO Obs6 Normal menos_de_24horas ] = 0.000000,
               [ ALTO Obs6 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Obs6 Alta menos_de_24horas ] = 0.000000,
               [ ALTO Obs6 Alta mas_de_24horas ] = 0.000000,
               [ ALTO Fot6 Normal menos_de_24horas ] = 0.000000,
               [ ALTO Fot6 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Fot6 Alta menos_de_24horas ] = 0.000000,
               [ ALTO Fot6 Alta mas_de_24horas ] = 0.000000,
               [ ALTO Fot12 Normal menos_de_24horas ] = 0.100000,
               [ ALTO Fot12 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Fot12 Alta menos_de_24horas ] = 0.000000,
               [ ALTO Fot12 Alta mas_de_24horas ] = 0.000000,
               [ ALTO Fot24 Normal menos_de_24horas ] = 0.000000,
               [ ALTO Fot24 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Fot24 Alta menos_de_24horas ] = 0.990000,
               [ ALTO Fot24 Alta mas_de_24horas ] = 0.980000,
               [ ALTO Fot6ExaFot6 Normal menos_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot6 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot6 Alta menos_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot6 Alta mas_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot12 Normal menos_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot12 Normal mas_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot12 Alta menos_de_24horas ] = 0.000000,
               [ ALTO Fot6ExaFot12 Alta mas_de_24horas ] = 0.000000,
               [ ALTO ObsTer Normal menos_de_24horas ] = 0.000000,
               [ ALTO ObsTer Normal mas_de_24horas ] = 0.000000,
               [ ALTO ObsTer Alta menos_de_24horas ] = 0.980000,
               [ ALTO ObsTer Alta mas_de_24horas ] = 0.800000,
               [ MUY_ALTO TerNo Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO TerNo Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO TerNo Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO TerNo Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsAlt Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsAlt Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsAlt Alta menos_de_24horas ] = 0.010000,
               [ MUY_ALTO ObsAlt Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Obs6 Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Obs6 Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Obs6 Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Obs6 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6 Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6 Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6 Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot12 Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot12 Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot12 Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot12 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot24 Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot24 Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot24 Alta menos_de_24horas ] = 0.010000,
               [ MUY_ALTO Fot24 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6ExaFot6 Normal menos_de_24horas ] = 1.000000,
               [ MUY_ALTO Fot6ExaFot6 Normal mas_de_24horas ] = 1.000000,
               [ MUY_ALTO Fot6ExaFot6 Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6ExaFot6 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6ExaFot12 Normal menos_de_24horas ] = 1.000000,
               [ MUY_ALTO Fot6ExaFot12 Normal mas_de_24horas ] = 1.000000,
               [ MUY_ALTO Fot6ExaFot12 Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO Fot6ExaFot12 Alta mas_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsTer Normal menos_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsTer Normal mas_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsTer Alta menos_de_24horas ] = 0.000000,
               [ MUY_ALTO ObsTer Alta mas_de_24horas ] = 0.000000);
}

relation DTer1 Terapia1 CBrb1 EH1 
{
   values=table(
               [ NULO TerNo Normal menos_de_12horas ] = 1.000000,
               [ NULO TerNo Normal mas_de_12horas ] = 1.000000,
               [ NULO TerNo Alta menos_de_12horas ] = 0.000000,
               [ NULO TerNo Alta mas_de_12horas ] = 0.000000,
               [ NULO Fot6 Normal menos_de_12horas ] = 0.000000,
               [ NULO Fot6 Normal mas_de_12horas ] = 0.000000,
               [ NULO Fot6 Alta menos_de_12horas ] = 0.960000,
               [ NULO Fot6 Alta mas_de_12horas ] = 0.980000,
               [ NULO Fot12 Normal menos_de_12horas ] = 0.000000,
               [ NULO Fot12 Normal mas_de_12horas ] = 0.000000,
               [ NULO Fot12 Alta menos_de_12horas ] = 0.960000,
               [ NULO Fot12 Alta mas_de_12horas ] = 0.980000,
               [ NULO Fot24 Normal menos_de_12horas ] = 0.000000,
               [ NULO Fot24 Normal mas_de_12horas ] = 0.000000,
               [ NULO Fot24 Alta menos_de_12horas ] = 0.980000,
               [ NULO Fot24 Alta mas_de_12horas ] = 0.980000,
               [ NULO ObsTer Normal menos_de_12horas ] = 0.980000,
               [ NULO ObsTer Normal mas_de_12horas ] = 0.980000,
               [ NULO ObsTer Alta menos_de_12horas ] = 0.000000,
               [ NULO ObsTer Alta mas_de_12horas ] = 0.000000,
               [ BAJO TerNo Normal menos_de_12horas ] = 0.000000,
               [ BAJO TerNo Normal mas_de_12horas ] = 0.000000,
               [ BAJO TerNo Alta menos_de_12horas ] = 0.000000,
               [ BAJO TerNo Alta mas_de_12horas ] = 0.000000,
               [ BAJO Fot6 Normal menos_de_12horas ] = 0.950000,
               [ BAJO Fot6 Normal mas_de_12horas ] = 1.000000,
               [ BAJO Fot6 Alta menos_de_12horas ] = 0.020000,
               [ BAJO Fot6 Alta mas_de_12horas ] = 0.020000,
               [ BAJO Fot12 Normal menos_de_12horas ] = 0.000000,
               [ BAJO Fot12 Normal mas_de_12horas ] = 0.990000,
               [ BAJO Fot12 Alta menos_de_12horas ] = 0.040000,
               [ BAJO Fot12 Alta mas_de_12horas ] = 0.020000,
               [ BAJO Fot24 Normal menos_de_12horas ] = 0.000000,
               [ BAJO Fot24 Normal mas_de_12horas ] = 0.000000,
               [ BAJO Fot24 Alta menos_de_12horas ] = 0.020000,
               [ BAJO Fot24 Alta mas_de_12horas ] = 0.020000,
               [ BAJO ObsTer Normal menos_de_12horas ] = 0.020000,
               [ BAJO ObsTer Normal mas_de_12horas ] = 0.020000,
               [ BAJO ObsTer Alta menos_de_12horas ] = 0.000000,
               [ BAJO ObsTer Alta mas_de_12horas ] = 0.000000,
               [ MEDIO TerNo Normal menos_de_12horas ] = 0.000000,
               [ MEDIO TerNo Normal mas_de_12horas ] = 0.000000,
               [ MEDIO TerNo Alta menos_de_12horas ] = 0.000000,
               [ MEDIO TerNo Alta mas_de_12horas ] = 0.000000,
               [ MEDIO Fot6 Normal menos_de_12horas ] = 0.050000,
               [ MEDIO Fot6 Normal mas_de_12horas ] = 0.000000,
               [ MEDIO Fot6 Alta menos_de_12horas ] = 0.010000,
               [ MEDIO Fot6 Alta mas_de_12horas ] = 0.000000,
               [ MEDIO Fot12 Normal menos_de_12horas ] = 0.980000,
               [ MEDIO Fot12 Normal mas_de_12horas ] = 0.010000,
               [ MEDIO Fot12 Alta menos_de_12horas ] = 0.000000,
               [ MEDIO Fot12 Alta mas_de_12horas ] = 0.000000,
               [ MEDIO Fot24 Normal menos_de_12horas ] = 1.000000,
               [ MEDIO Fot24 Normal mas_de_12horas ] = 1.000000,
               [ MEDIO Fot24 Alta menos_de_12horas ] = 0.000000,
               [ MEDIO Fot24 Alta mas_de_12horas ] = 0.000000,
               [ MEDIO ObsTer Normal menos_de_12horas ] = 0.000000,
               [ MEDIO ObsTer Normal mas_de_12horas ] = 0.000000,
               [ MEDIO ObsTer Alta menos_de_12horas ] = 0.020000,
               [ MEDIO ObsTer Alta mas_de_12horas ] = 0.050000,
               [ ALTO TerNo Normal menos_de_12horas ] = 0.000000,
               [ ALTO TerNo Normal mas_de_12horas ] = 0.000000,
               [ ALTO TerNo Alta menos_de_12horas ] = 0.000000,
               [ ALTO TerNo Alta mas_de_12horas ] = 0.000000,
               [ ALTO Fot6 Normal menos_de_12horas ] = 0.000000,
               [ ALTO Fot6 Normal mas_de_12horas ] = 0.000000,
               [ ALTO Fot6 Alta menos_de_12horas ] = 0.010000,
               [ ALTO Fot6 Alta mas_de_12horas ] = 0.000000,
               [ ALTO Fot12 Normal menos_de_12horas ] = 0.020000,
               [ ALTO Fot12 Normal mas_de_12horas ] = 0.000000,
               [ ALTO Fot12 Alta menos_de_12horas ] = 0.000000,
               [ ALTO Fot12 Alta mas_de_12horas ] = 0.000000,
               [ ALTO Fot24 Normal menos_de_12horas ] = 0.000000,
               [ ALTO Fot24 Normal mas_de_12horas ] = 0.000000,
               [ ALTO Fot24 Alta menos_de_12horas ] = 0.000000,
               [ ALTO Fot24 Alta mas_de_12horas ] = 0.000000,
               [ ALTO ObsTer Normal menos_de_12horas ] = 0.000000,
               [ ALTO ObsTer Normal mas_de_12horas ] = 0.000000,
               [ ALTO ObsTer Alta menos_de_12horas ] = 0.030000,
               [ ALTO ObsTer Alta mas_de_12horas ] = 0.050000,
               [ MUY_ALTO TerNo Normal menos_de_12horas ] = 0.000000,
               [ MUY_ALTO TerNo Normal mas_de_12horas ] = 0.000000,
               [ MUY_ALTO TerNo Alta menos_de_12horas ] = 1.000000,
               [ MUY_ALTO TerNo Alta mas_de_12horas ] = 1.000000,
               [ MUY_ALTO Fot6 Normal menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot6 Normal mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot6 Alta menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot6 Alta mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot12 Normal menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot12 Normal mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot12 Alta menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot12 Alta mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot24 Normal menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot24 Normal mas_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot24 Alta menos_de_12horas ] = 0.000000,
               [ MUY_ALTO Fot24 Alta mas_de_12horas ] = 0.000000,
               [ MUY_ALTO ObsTer Normal menos_de_12horas ] = 0.000000,
               [ MUY_ALTO ObsTer Normal mas_de_12horas ] = 0.000000,
               [ MUY_ALTO ObsTer Alta menos_de_12horas ] = 0.950000,
               [ MUY_ALTO ObsTer Alta mas_de_12horas ] = 0.900000);
}

relation PatLG IG IRh Axp Sep Ecm
{
   values=table(
               [ Leve Ausente Ausente Ausente Ausente Ausente ] = 0.002400,
               [ Leve Ausente Ausente Ausente Ausente Presente ] = 0.000480,
               [ Leve Ausente Ausente Ausente Presente Ausente ] = 0.000240,
               [ Leve Ausente Ausente Ausente Presente Presente ] = 0.000048,
               [ Leve Ausente Ausente Presente Ausente Ausente ] = 0.000240,
               [ Leve Ausente Ausente Presente Ausente Presente ] = 0.000048,
               [ Leve Ausente Ausente Presente Presente Ausente ] = 0.000024,
               [ Leve Ausente Ausente Presente Presente Presente ] = 0.000005,
               [ Leve Ausente Presente Ausente Ausente Ausente ] = 0.000240,
               [ Leve Ausente Presente Ausente Ausente Presente ] = 0.000048,
               [ Leve Ausente Presente Ausente Presente Ausente ] = 0.000024,
               [ Leve Ausente Presente Ausente Presente Presente ] = 0.000005,
               [ Leve Ausente Presente Presente Ausente Ausente ] = 0.000024,
               [ Leve Ausente Presente Presente Ausente Presente ] = 0.000005,
               [ Leve Ausente Presente Presente Presente Ausente ] = 0.000002,
               [ Leve Ausente Presente Presente Presente Presente ] = 0.000000,
               [ Leve Presente Ausente Ausente Ausente Ausente ] = 0.000480,
               [ Leve Presente Ausente Ausente Ausente Presente ] = 0.000096,
               [ Leve Presente Ausente Ausente Presente Ausente ] = 0.000048,
               [ Leve Presente Ausente Ausente Presente Presente ] = 0.000010,
               [ Leve Presente Ausente Presente Ausente Ausente ] = 0.000048,
               [ Leve Presente Ausente Presente Ausente Presente ] = 0.000010,
               [ Leve Presente Ausente Presente Presente Ausente ] = 0.000005,
               [ Leve Presente Ausente Presente Presente Presente ] = 0.000001,
               [ Leve Presente Presente Ausente Ausente Ausente ] = 0.000048,
               [ Leve Presente Presente Ausente Ausente Presente ] = 0.000010,
               [ Leve Presente Presente Ausente Presente Ausente ] = 0.000005,
               [ Leve Presente Presente Ausente Presente Presente ] = 0.000001,
               [ Leve Presente Presente Presente Ausente Ausente ] = 0.000005,
               [ Leve Presente Presente Presente Ausente Presente ] = 0.000001,
               [ Leve Presente Presente Presente Presente Ausente ] = 0.000000,
               [ Leve Presente Presente Presente Presente Presente ] = 0.000000,
               [ Grave Ausente Ausente Ausente Ausente Ausente ] = 0.137600,
               [ Grave Ausente Ausente Ausente Ausente Presente ] = 0.069520,
               [ Grave Ausente Ausente Ausente Presente Ausente ] = 0.055760,
               [ Grave Ausente Ausente Ausente Presente Presente ] = 0.027952,
               [ Grave Ausente Ausente Presente Ausente Ausente ] = 0.041760,
               [ Grave Ausente Ausente Presente Ausente Presente ] = 0.020952,
               [ Grave Ausente Ausente Presente Presente Ausente ] = 0.016776,
               [ Grave Ausente Ausente Presente Presente Presente ] = 0.008395,
               [ Grave Ausente Presente Ausente Ausente Ausente ] = 0.041760,
               [ Grave Ausente Presente Ausente Ausente Presente ] = 0.020952,
               [ Grave Ausente Presente Ausente Presente Ausente ] = 0.016776,
               [ Grave Ausente Presente Ausente Presente Presente ] = 0.008395,
               [ Grave Ausente Presente Presente Ausente Ausente ] = 0.012576,
               [ Grave Ausente Presente Presente Ausente Presente ] = 0.006295,
               [ Grave Ausente Presente Presente Presente Ausente ] = 0.005038,
               [ Grave Ausente Presente Presente Presente Presente ] = 0.002520,
               [ Grave Presente Ausente Ausente Ausente Ausente ] = 0.125520,
               [ Grave Presente Ausente Ausente Ausente Presente ] = 0.062904,
               [ Grave Presente Ausente Ausente Presente Ausente ] = 0.050352,
               [ Grave Presente Ausente Ausente Presente Presente ] = 0.025190,
               [ Grave Presente Ausente Presente Ausente Ausente ] = 0.037752,
               [ Grave Presente Ausente Presente Ausente Presente ] = 0.018890,
               [ Grave Presente Ausente Presente Presente Ausente ] = 0.015115,
               [ Grave Presente Ausente Presente Presente Presente ] = 0.007559,
               [ Grave Presente Presente Ausente Ausente Ausente ] = 0.037752,
               [ Grave Presente Presente Ausente Ausente Presente ] = 0.018890,
               [ Grave Presente Presente Ausente Presente Ausente ] = 0.015115,
               [ Grave Presente Presente Ausente Presente Presente ] = 0.007559,
               [ Grave Presente Presente Presente Ausente Ausente ] = 0.011335,
               [ Grave Presente Presente Presente Ausente Presente ] = 0.005669,
               [ Grave Presente Presente Presente Presente Ausente ] = 0.004536,
               [ Grave Presente Presente Presente Presente Presente ] = 0.002268,
               [ MuyGrave Ausente Ausente Ausente Ausente Ausente ] = 0.860000,
               [ MuyGrave Ausente Ausente Ausente Ausente Presente ] = 0.930000,
               [ MuyGrave Ausente Ausente Ausente Presente Ausente ] = 0.944000,
               [ MuyGrave Ausente Ausente Ausente Presente Presente ] = 0.972000,
               [ MuyGrave Ausente Ausente Presente Ausente Ausente ] = 0.958000,
               [ MuyGrave Ausente Ausente Presente Ausente Presente ] = 0.979000,
               [ MuyGrave Ausente Ausente Presente Presente Ausente ] = 0.983200,
               [ MuyGrave Ausente Ausente Presente Presente Presente ] = 0.991600,
               [ MuyGrave Ausente Presente Ausente Ausente Ausente ] = 0.958000,
               [ MuyGrave Ausente Presente Ausente Ausente Presente ] = 0.979000,
               [ MuyGrave Ausente Presente Ausente Presente Ausente ] = 0.983200,
               [ MuyGrave Ausente Presente Ausente Presente Presente ] = 0.991600,
               [ MuyGrave Ausente Presente Presente Ausente Ausente ] = 0.987400,
               [ MuyGrave Ausente Presente Presente Ausente Presente ] = 0.993700,
               [ MuyGrave Ausente Presente Presente Presente Ausente ] = 0.994960,
               [ MuyGrave Ausente Presente Presente Presente Presente ] = 0.997480,
               [ MuyGrave Presente Ausente Ausente Ausente Ausente ] = 0.874000,
               [ MuyGrave Presente Ausente Ausente Ausente Presente ] = 0.937000,
               [ MuyGrave Presente Ausente Ausente Presente Ausente ] = 0.949600,
               [ MuyGrave Presente Ausente Ausente Presente Presente ] = 0.974800,
               [ MuyGrave Presente Ausente Presente Ausente Ausente ] = 0.962200,
               [ MuyGrave Presente Ausente Presente Ausente Presente ] = 0.981100,
               [ MuyGrave Presente Ausente Presente Presente Ausente ] = 0.984880,
               [ MuyGrave Presente Ausente Presente Presente Presente ] = 0.992440,
               [ MuyGrave Presente Presente Ausente Ausente Ausente ] = 0.962200,
               [ MuyGrave Presente Presente Ausente Ausente Presente ] = 0.981100,
               [ MuyGrave Presente Presente Ausente Presente Ausente ] = 0.984880,
               [ MuyGrave Presente Presente Ausente Presente Presente ] = 0.992440,
               [ MuyGrave Presente Presente Presente Ausente Ausente ] = 0.988660,
               [ MuyGrave Presente Presente Presente Ausente Presente ] = 0.994330,
               [ MuyGrave Presente Presente Presente Presente Ausente ] = 0.995464,
               [ MuyGrave Presente Presente Presente Presente Presente ] = 0.997732);
}

relation Ecm 
{
   values=table(
               [ Ausente ] = 0.990000,
               [ Presente ] = 0.010000);
}

relation Sep 
{
   values=table(
               [ Ausente ] = 0.960000,
               [ Presente ] = 0.040000);
}

relation Axp 
{
   values=table(
               [ Ausente ] = 0.830000,
               [ Presente ] = 0.170000);
}

relation IRh FRhm FRhh AP 
{
   values=table(
               [ Ausente NEGATIVO NEGATIVO Primeriza ] = 0.990000,
               [ Ausente NEGATIVO NEGATIVO Multipara ] = 0.990000,
               [ Ausente NEGATIVO POSITIVO Primeriza ] = 0.990000,
               [ Ausente NEGATIVO POSITIVO Multipara ] = 0.990000,
               [ Ausente POSITIVO NEGATIVO Primeriza ] = 0.950000,
               [ Ausente POSITIVO NEGATIVO Multipara ] = 0.900000,
               [ Ausente POSITIVO POSITIVO Primeriza ] = 0.990000,
               [ Ausente POSITIVO POSITIVO Multipara ] = 0.990000,
               [ Presente NEGATIVO NEGATIVO Primeriza ] = 0.010000,
               [ Presente NEGATIVO NEGATIVO Multipara ] = 0.010000,
               [ Presente NEGATIVO POSITIVO Primeriza ] = 0.010000,
               [ Presente NEGATIVO POSITIVO Multipara ] = 0.010000,
               [ Presente POSITIVO NEGATIVO Primeriza ] = 0.050000,
               [ Presente POSITIVO NEGATIVO Multipara ] = 0.100000,
               [ Presente POSITIVO POSITIVO Primeriza ] = 0.010000,
               [ Presente POSITIVO POSITIVO Multipara ] = 0.010000);
}

relation IG Gsm Gsh 
{
   values=table(
               [ Ausente CERO CERO ] = 1.000000,
               [ Ausente CERO A ] = 0.900000,
               [ Ausente CERO B ] = 0.900000,
               [ Ausente CERO AB ] = 0.900000,
               [ Ausente A CERO ] = 0.950000,
               [ Ausente A A ] = 1.000000,
               [ Ausente A B ] = 0.950000,
               [ Ausente A AB ] = 0.900000,
               [ Ausente B CERO ] = 0.900000,
               [ Ausente B A ] = 0.900000,
               [ Ausente B B ] = 1.000000,
               [ Ausente B AB ] = 0.900000,
               [ Ausente AB CERO ] = 0.950000,
               [ Ausente AB A ] = 0.950000,
               [ Ausente AB B ] = 0.950000,
               [ Ausente AB AB ] = 1.000000,
               [ Presente CERO CERO ] = 0.000000,
               [ Presente CERO A ] = 0.100000,
               [ Presente CERO B ] = 0.100000,
               [ Presente CERO AB ] = 0.100000,
               [ Presente A CERO ] = 0.050000,
               [ Presente A A ] = 0.000000,
               [ Presente A B ] = 0.050000,
               [ Presente A AB ] = 0.100000,
               [ Presente B CERO ] = 0.100000,
               [ Presente B A ] = 0.100000,
               [ Presente B B ] = 0.000000,
               [ Presente B AB ] = 0.100000,
               [ Presente AB CERO ] = 0.050000,
               [ Presente AB A ] = 0.050000,
               [ Presente AB B ] = 0.050000,
               [ Presente AB AB ] = 0.000000);
}

relation CHgb2 IG IRh Axp Sep Ecm 
{
   values=table(
               [ Baja Ausente Ausente Ausente Ausente Ausente ] = 0.228000,
               [ Baja Ausente Ausente Ausente Ausente Presente ] = 0.136800,
               [ Baja Ausente Ausente Ausente Presente Ausente ] = 0.114000,
               [ Baja Ausente Ausente Ausente Presente Presente ] = 0.068400,
               [ Baja Ausente Ausente Presente Ausente Ausente ] = 0.159600,
               [ Baja Ausente Ausente Presente Ausente Presente ] = 0.095760,
               [ Baja Ausente Ausente Presente Presente Ausente ] = 0.079800,
               [ Baja Ausente Ausente Presente Presente Presente ] = 0.047880,
               [ Baja Ausente Presente Ausente Ausente Ausente ] = 0.209760,
               [ Baja Ausente Presente Ausente Ausente Presente ] = 0.125856,
               [ Baja Ausente Presente Ausente Presente Ausente ] = 0.104880,
               [ Baja Ausente Presente Ausente Presente Presente ] = 0.062928,
               [ Baja Ausente Presente Presente Ausente Ausente ] = 0.146832,
               [ Baja Ausente Presente Presente Ausente Presente  ] = 0.088099,
               [ Baja Ausente Presente Presente Presente Ausente  ] = 0.073416,
               [ Baja Ausente Presente Presente Presente Presente  ] = 0.044050,
               [ Baja Presente Ausente Ausente Ausente Ausente  ] = 0.123120,
               [ Baja Presente Ausente Ausente Ausente Presente  ] = 0.073872,
               [ Baja Presente Ausente Ausente Presente Ausente  ] = 0.061560,
               [ Baja Presente Ausente Ausente Presente Presente  ] = 0.036936,
               [ Baja Presente Ausente Presente Ausente Ausente  ] = 0.086184,
               [ Baja Presente Ausente Presente Ausente Presente  ] = 0.051710,
               [ Baja Presente Ausente Presente Presente Ausente  ] = 0.043092,
               [ Baja Presente Ausente Presente Presente Presente  ] = 0.025855,
               [ Baja Presente Presente Ausente Ausente Ausente  ] = 0.113270,
               [ Baja Presente Presente Ausente Ausente Presente  ] = 0.067962,
               [ Baja Presente Presente Ausente Presente Ausente  ] = 0.056635,
               [ Baja Presente Presente Ausente Presente Presente  ] = 0.033981,
               [ Baja Presente Presente Presente Ausente Ausente  ] = 0.079289,
               [ Baja Presente Presente Presente Ausente Presente  ] = 0.047574,
               [ Baja Presente Presente Presente Presente Ausente  ] = 0.039645,
               [ Baja Presente Presente Presente Presente Presente  ] = 0.023787,
               [ Media Ausente Ausente Ausente Ausente Ausente  ] = 0.157866,
               [ Media Ausente Ausente Ausente Ausente Presente  ] = 0.133306,
               [ Media Ausente Ausente Ausente Presente Ausente  ] = 0.240997,
               [ Media Ausente Ausente Ausente Presente Presente  ] = 0.180098,
               [ Media Ausente Ausente Presente Ausente Ausente  ] = 0.129800,
               [ Media Ausente Ausente Presente Ausente Presente  ] = 0.106820,
               [ Media Ausente Ausente Presente Presente Ausente  ] = 0.186448,
               [ Media Ausente Ausente Presente Presente Presente  ] = 0.138493,
               [ Media Ausente Presente Ausente Ausente Ausente  ] = 0.176106,
               [ Media Ausente Presente Ausente Ausente Presente  ] = 0.144250,
               [ Media Ausente Presente Ausente Presente Ausente  ] = 0.250117,
               [ Media Ausente Presente Ausente Presente Presente  ] = 0.185570,
               [ Media Ausente Presente Presente Ausente Ausente  ] = 0.142568,
               [ Media Ausente Presente Presente Ausente Presente  ] = 0.114480,
               [ Media Ausente Presente Presente Presente Ausente  ] = 0.192832,
               [ Media Ausente Presente Presente Presente Presente  ] = 0.142324,
               [ Media Presente Ausente Ausente Ausente Ausente  ] = 0.193290,
               [ Media Presente Ausente Ausente Ausente Presente  ] = 0.147615,
               [ Media Presente Ausente Ausente Presente Ausente  ] = 0.229537,
               [ Media Presente Ausente Ausente Presente Presente  ] = 0.166832,
               [ Media Presente Ausente Presente Ausente Ausente  ] = 0.151124,
               [ Media Presente Ausente Presente Ausente Presente  ] = 0.114405,
               [ Media Presente Ausente Presente Presente Ausente  ] = 0.175231,
               [ Media Presente Ausente Presente Presente Presente  ] = 0.126971,
               [ Media Presente Presente Ausente Ausente Ausente  ] = 0.203140,
               [ Media Presente Presente Ausente Ausente Presente  ] = 0.153525,
               [ Media Presente Presente Ausente Presente Ausente  ] = 0.234462,
               [ Media Presente Presente Ausente Presente Presente  ] = 0.169787,
               [ Media Presente Presente Presente Ausente Ausente  ] = 0.158018,
               [ Media Presente Presente Presente Ausente Presente  ] = 0.118542,
               [ Media Presente Presente Presente Presente Ausente  ] = 0.178678,
               [ Media Presente Presente Presente Presente Presente  ] = 0.129039,
               [ Alta Ausente Ausente Ausente Ausente Ausente  ] = 0.614134,
               [ Alta Ausente Ausente Ausente Ausente Presente  ] = 0.729894,
               [ Alta Ausente Ausente Ausente Presente Ausente  ] = 0.645003,
               [ Alta Ausente Ausente Ausente Presente Presente  ] = 0.751502,
               [ Alta Ausente Ausente Presente Ausente Ausente  ] = 0.710600,
               [ Alta Ausente Ausente Presente Ausente Presente  ] = 0.797420,
               [ Alta Ausente Ausente Presente Presente Ausente  ] = 0.733752,
               [ Alta Ausente Ausente Presente Presente Presente  ] = 0.813627,
               [ Alta Ausente Presente Ausente Ausente Ausente  ] = 0.614134,
               [ Alta Ausente Presente Ausente Ausente Presente  ] = 0.729894,
               [ Alta Ausente Presente Ausente Presente Ausente  ] = 0.645003,
               [ Alta Ausente Presente Ausente Presente Presente  ] = 0.751502,
               [ Alta Ausente Presente Presente Ausente Ausente  ] = 0.710600,
               [ Alta Ausente Presente Presente Ausente Presente  ] = 0.797420,
               [ Alta Ausente Presente Presente Presente Ausente  ] = 0.733752,
               [ Alta Ausente Presente Presente Presente Presente  ] = 0.813627,
               [ Alta Presente Ausente Ausente Ausente Ausente  ] = 0.683590,
               [ Alta Presente Ausente Ausente Ausente Presente  ] = 0.778513,
               [ Alta Presente Ausente Ausente Presente Ausente  ] = 0.708903,
               [ Alta Presente Ausente Ausente Presente Presente  ] = 0.796232,
               [ Alta Presente Ausente Presente Ausente Ausente  ] = 0.762692,
               [ Alta Presente Ausente Presente Ausente Presente  ] = 0.833885,
               [ Alta Presente Ausente Presente Presente Ausente  ] = 0.781677,
               [ Alta Presente Ausente Presente Presente Presente  ] = 0.847174,
               [ Alta Presente Presente Ausente Ausente Ausente  ] = 0.683590,
               [ Alta Presente Presente Ausente Ausente Presente  ] = 0.778513,
               [ Alta Presente Presente Ausente Presente Ausente  ] = 0.708903,
               [ Alta Presente Presente Ausente Presente Presente  ] = 0.796232,
               [ Alta Presente Presente Presente Ausente Ausente  ] = 0.762692,
               [ Alta Presente Presente Presente Ausente Presente  ] = 0.833885,
               [ Alta Presente Presente Presente Presente Ausente  ] = 0.781677,
               [ Alta Presente Presente Presente Presente Presente  ] = 0.847174);
}

relation CBrb2 CHgb2 EH2 
{
   values=table(
               [ Normal Baja menos_de_24horas ] = 0.800000,
               [ Normal Baja mas_de_24horas ] = 0.000000,
               [ Normal Media menos_de_24horas ] = 0.800000,
               [ Normal Media mas_de_24horas ] = 0.800000,
               [ Normal Alta menos_de_24horas ] = 0.000000,
               [ Normal Alta mas_de_24horas ] = 0.800000,
               [ Alta Baja menos_de_24horas ] = 0.200000,
               [ Alta Baja mas_de_24horas ] = 1.000000,
               [ Alta Media menos_de_24horas ] = 0.200000,
               [ Alta Media mas_de_24horas ] = 0.200000,
               [ Alta Alta menos_de_24horas ] = 1.000000,
               [ Alta Alta mas_de_24horas ] = 0.200000);
}

relation EH2 
{
   values=table(
               [ menos_de_24horas ] = 0.720000,
               [ mas_de_24horas ] = 0.280000);
}

relation CHgb1 IG IRh Axp Sep Ecm
{
   values=table(
               [ Baja Ausente Ausente Ausente Ausente Ausente ] = 0.000000,
               [ Baja Ausente Ausente Ausente Ausente Presente ] = 0.000000,
               [ Baja Ausente Ausente Ausente Presente Ausente ] = 0.000000,
               [ Baja Ausente Ausente Ausente Presente Presente ] = 0.000000,
               [ Baja Ausente Ausente Presente Ausente Ausente ] = 0.000000,
               [ Baja Ausente Ausente Presente Ausente Presente ] = 0.000000,
               [ Baja Ausente Ausente Presente Presente Ausente ] = 0.000000,
               [ Baja Ausente Ausente Presente Presente Presente ] = 0.000000,
               [ Baja Ausente Presente Ausente Ausente Ausente ] = 0.000000,
               [ Baja Ausente Presente Ausente Ausente Presente ] = 0.000000,
               [ Baja Ausente Presente Ausente Presente Ausente ] = 0.000000,
               [ Baja Ausente Presente Ausente Presente Presente ] = 0.000000,
               [ Baja Ausente Presente Presente Ausente Ausente ] = 0.000000,
               [ Baja Ausente Presente Presente Ausente Presente ] = 0.000000,
               [ Baja Ausente Presente Presente Presente Ausente ] = 0.000000,
               [ Baja Ausente Presente Presente Presente Presente ] = 0.000000,
               [ Baja Presente Ausente Ausente Ausente Ausente ] = 0.000000,
               [ Baja Presente Ausente Ausente Ausente Presente ] = 0.000000,
               [ Baja Presente Ausente Ausente Presente Ausente ] = 0.000000,
               [ Baja Presente Ausente Ausente Presente Presente ] = 0.000000,
               [ Baja Presente Ausente Presente Ausente Ausente ] = 0.000000,
               [ Baja Presente Ausente Presente Ausente Presente ] = 0.000000,
               [ Baja Presente Ausente Presente Presente Ausente ] = 0.000000,
               [ Baja Presente Ausente Presente Presente Presente ] = 0.000000,
               [ Baja Presente Presente Ausente Ausente Ausente ] = 0.000000,
               [ Baja Presente Presente Ausente Ausente Presente ] = 0.000000,
               [ Baja Presente Presente Ausente Presente Ausente ] = 0.000000,
               [ Baja Presente Presente Ausente Presente Presente ] = 0.000000,
               [ Baja Presente Presente Presente Ausente Ausente ] = 0.000000,
               [ Baja Presente Presente Presente Ausente Presente ] = 0.000000,
               [ Baja Presente Presente Presente Presente Ausente ] = 0.000000,
               [ Baja Presente Presente Presente Presente Presente ] = 0.000000,
               [ Media Ausente Ausente Ausente Ausente Ausente ] = 0.000000,
               [ Media Ausente Ausente Ausente Ausente Presente ] = 0.000000,
               [ Media Ausente Ausente Ausente Presente Ausente ] = 0.000000,
               [ Media Ausente Ausente Ausente Presente Presente ] = 0.000000,
               [ Media Ausente Ausente Presente Ausente Ausente ] = 0.000000,
               [ Media Ausente Ausente Presente Ausente Presente ] = 0.000000,
               [ Media Ausente Ausente Presente Presente Ausente ] = 0.000000,
               [ Media Ausente Ausente Presente Presente Presente ] = 0.000000,
               [ Media Ausente Presente Ausente Ausente Ausente ] = 0.000000,
               [ Media Ausente Presente Ausente Ausente Presente ] = 0.000000,
               [ Media Ausente Presente Ausente Presente Ausente ] = 0.000000,
               [ Media Ausente Presente Ausente Presente Presente ] = 0.000000,
               [ Media Ausente Presente Presente Ausente Ausente ] = 0.000000,
               [ Media Ausente Presente Presente Ausente Presente ] = 0.000000,
               [ Media Ausente Presente Presente Presente Ausente ] = 0.000000,
               [ Media Ausente Presente Presente Presente Presente ] = 0.000000,
               [ Media Presente Ausente Ausente Ausente Ausente ] = 0.000000,
               [ Media Presente Ausente Ausente Ausente Presente ] = 0.000000,
               [ Media Presente Ausente Ausente Presente Ausente ] = 0.000000,
               [ Media Presente Ausente Ausente Presente Presente ] = 0.000000,
               [ Media Presente Ausente Presente Ausente Ausente ] = 0.000000,
               [ Media Presente Ausente Presente Ausente Presente ] = 0.000000,
               [ Media Presente Ausente Presente Presente Ausente ] = 0.000000,
               [ Media Presente Ausente Presente Presente Presente ] = 0.000000,
               [ Media Presente Presente Ausente Ausente Ausente ] = 0.000000,
               [ Media Presente Presente Ausente Ausente Presente ] = 0.000000,
               [ Media Presente Presente Ausente Presente Ausente ] = 0.000000,
               [ Media Presente Presente Ausente Presente Presente ] = 0.000000,
               [ Media Presente Presente Presente Ausente Ausente ] = 0.000000,
               [ Media Presente Presente Presente Ausente Presente ] = 0.000000,
               [ Media Presente Presente Presente Presente Ausente ] = 0.000000,
               [ Media Presente Presente Presente Presente Presente ] = 0.000000,
               [ Alta Ausente Ausente Ausente Ausente Ausente ] = 1.000000,
               [ Alta Ausente Ausente Ausente Ausente Presente ] = 1.000000,
               [ Alta Ausente Ausente Ausente Presente Ausente ] = 1.000000,
               [ Alta Ausente Ausente Ausente Presente Presente ] = 1.000000,
               [ Alta Ausente Ausente Presente Ausente Ausente ] = 1.000000,
               [ Alta Ausente Ausente Presente Ausente Presente ] = 1.000000,
               [ Alta Ausente Ausente Presente Presente Ausente ] = 1.000000,
               [ Alta Ausente Ausente Presente Presente Presente ] = 1.000000,
               [ Alta Ausente Presente Ausente Ausente Ausente ] = 1.000000,
               [ Alta Ausente Presente Ausente Ausente Presente ] = 1.000000,
               [ Alta Ausente Presente Ausente Presente Ausente ] = 1.000000,
               [ Alta Ausente Presente Ausente Presente Presente ] = 1.000000,
               [ Alta Ausente Presente Presente Ausente Ausente ] = 1.000000,
               [ Alta Ausente Presente Presente Ausente Presente ] = 1.000000,
               [ Alta Ausente Presente Presente Presente Ausente ] = 1.000000,
               [ Alta Ausente Presente Presente Presente Presente ] = 1.000000,
               [ Alta Presente Ausente Ausente Ausente Ausente ] = 1.000000,
               [ Alta Presente Ausente Ausente Ausente Presente ] = 1.000000,
               [ Alta Presente Ausente Ausente Presente Ausente ] = 1.000000,
               [ Alta Presente Ausente Ausente Presente Presente ] = 1.000000,
               [ Alta Presente Ausente Presente Ausente Ausente ] = 1.000000,
               [ Alta Presente Ausente Presente Ausente Presente ] = 1.000000,
               [ Alta Presente Ausente Presente Presente Ausente ] = 1.000000,
               [ Alta Presente Ausente Presente Presente Presente ] = 1.000000,
               [ Alta Presente Presente Ausente Ausente Ausente ] = 1.000000,
               [ Alta Presente Presente Ausente Ausente Presente ] = 1.000000,
               [ Alta Presente Presente Ausente Presente Ausente ] = 1.000000,
               [ Alta Presente Presente Ausente Presente Presente ] = 1.000000,
               [ Alta Presente Presente Presente Ausente Ausente ] = 1.000000,
               [ Alta Presente Presente Presente Ausente Presente ] = 1.000000,
               [ Alta Presente Presente Presente Presente Ausente ] = 1.000000,
               [ Alta Presente Presente Presente Presente Presente ] = 1.000000);
}

relation CBrb1 CHgb1 EH1 
{
   values=table(
               [ Normal Baja menos_de_12horas ] = 0.850000,
               [ Normal Baja mas_de_12horas ] = 0.000000,
               [ Normal Media menos_de_12horas ] = 0.000000,
               [ Normal Media mas_de_12horas ] = 0.000000,
               [ Normal Alta menos_de_12horas ] = 0.000000,
               [ Normal Alta mas_de_12horas ] = 0.000000,
               [ Alta Baja menos_de_12horas ] = 0.150000,
               [ Alta Baja mas_de_12horas ] = 1.000000,
               [ Alta Media menos_de_12horas ] = 1.000000,
               [ Alta Media mas_de_12horas ] = 1.000000,
               [ Alta Alta menos_de_12horas ] = 1.000000,
               [ Alta Alta mas_de_12horas ] = 1.000000);
}

relation EH1 
{
   values=table(
               [ menos_de_12horas ] = 0.690000,
               [ mas_de_12horas ] = 0.310000);
}

relation EM 
{
   values=table(
               [ de15_18 ] = 0.110000,
               [ de_19_35 ] = 0.700000,
               [ de_35_mas ] = 0.190000);
}

relation Rzm 
{
   values=table(
               [ Negra ] = 0.010000,
               [ Caucasica ] = 0.890000,
               [ Asiatica ] = 0.040000,
               [ Gitana ] = 0.060000);
}

relation Enf 
{
   values=table(
               [ NoEnferma ] = 0.840000,
               [ Enferma ] = 0.160000);
}

relation PI 
{
   values=table(
               [ NoInstrumental ] = 0.850000,
               [ Instrumental ] = 0.150000);
}

relation AP 
{
   values=table(
               [ Primeriza ] = 0.930000,
               [ Multipara ] = 0.070000);
}

relation Ict Rzm CBrb1 
{
   values=table(
               [ Normal Negra Normal ] = 0.800000,
               [ Normal Negra Alta ] = 0.300000,
               [ Normal Caucasica Normal ] = 0.970000,
               [ Normal Caucasica Alta ] = 0.050000,
               [ Normal Asiatica Normal ] = 0.850000,
               [ Normal Asiatica Alta ] = 0.100000,
               [ Normal Gitana Normal ] = 0.850000,
               [ Normal Gitana Alta ] = 0.100000,
               [ Amarillo Negra Normal ] = 0.050000,
               [ Amarillo Negra Alta ] = 0.100000,
               [ Amarillo Caucasica Normal ] = 0.030000,
               [ Amarillo Caucasica Alta ] = 0.100000,
               [ Amarillo Asiatica Normal ] = 0.010000,
               [ Amarillo Asiatica Alta ] = 0.100000,
               [ Amarillo Gitana Normal ] = 0.010000,
               [ Amarillo Gitana Alta ] = 0.100000,
               [ Pies Negra Normal ] = 0.050000,
               [ Pies Negra Alta ] = 0.100000,
               [ Pies Caucasica Normal ] = 0.000000,
               [ Pies Caucasica Alta ] = 0.100000,
               [ Pies Asiatica Normal ] = 0.040000,
               [ Pies Asiatica Alta ] = 0.150000,
               [ Pies Gitana Normal ] = 0.040000,
               [ Pies Gitana Alta ] = 0.150000,
               [ Calabaza Negra Normal ] = 0.100000,
               [ Calabaza Negra Alta ] = 0.500000,
               [ Calabaza Caucasica Normal ] = 0.000000,
               [ Calabaza Caucasica Alta ] = 0.750000,
               [ Calabaza Asiatica Normal ] = 0.100000,
               [ Calabaza Asiatica Alta ] = 0.650000,
               [ Calabaza Gitana Normal ] = 0.100000,
               [ Calabaza Gitana Alta ] = 0.650000);
}

relation PN EG 
{
   values=table(
               [ P499_999 Prematuro ] = 0.210000,
               [ P499_999 aTermino ] = 0.010000,
               [ P499_999 posTermino ] = 0.010000,
               [ P1000_1499 Prematuro ] = 0.240000,
               [ P1000_1499 aTermino ] = 0.220000,
               [ P1000_1499 posTermino ] = 0.150000,
               [ P1500_2499 Prematuro ] = 0.360000,
               [ P1500_2499 aTermino ] = 0.330000,
               [ P1500_2499 posTermino ] = 0.300000,
               [ P2500 Prematuro ] = 0.190000,
               [ P2500 aTermino ] = 0.440000,
               [ P2500 posTermino ] = 0.540000);
}

relation EG 
{
   values=table(
               [ Prematuro ] = 0.300000,
               [ aTermino ] = 0.690000,
               [ posTermino ] = 0.010000);
}

relation TRe Axp 
{
   values=table(
               [ R1_2 Ausente ] = 0.900000,
               [ R1_2 Presente ] = 0.050000,
               [ R3 Ausente ] = 0.050000,
               [ R3 Presente ] = 0.350000,
               [ R4_5 Ausente ] = 0.050000,
               [ R4_5 Presente ] = 0.600000);
}

relation TpH Axp 
{
   values=table(
               [ pH_menor_7 Ausente ] = 0.010000,
               [ pH_menor_7 Presente ] = 0.800000,
               [ ph_mayor_7 Ausente ] = 0.990000,
               [ ph_mayor_7 Presente ] = 0.200000);
}

relation TA5 Axp 
{
   values=table(
               [ A0_3 Ausente ] = 0.010000,
               [ A0_3 Presente ] = 0.200000,
               [ A4_7 Ausente ] = 0.100000,
               [ A4_7 Presente ] = 0.700000,
               [ A8_10 Ausente ] = 0.890000,
               [ A8_10 Presente ] = 0.100000);
}

relation TCmIRh IRh 
{
   values=table(
               [ Negativo Ausente ] = 0.990000,
               [ Negativo Presente ] = 0.500000,
               [ Positivo Ausente ] = 0.010000,
               [ Positivo Presente ] = 0.500000);
}

relation TChIRh TCmIRh IRh 
{
   values=table(
               [ Negativo Negativo Ausente ] = 0.990000,
               [ Negativo Negativo Presente ] = 0.100000,
               [ Negativo Positivo Ausente ] = 0.800000,
               [ Negativo Positivo Presente ] = 0.050000,
               [ Positivo Negativo Ausente ] = 0.010000,
               [ Positivo Negativo Presente ] = 0.900000,
               [ Positivo Positivo Ausente ] = 0.200000,
               [ Positivo Positivo Presente ] = 0.950000);
}

relation TChIG IG 
{
   values=table(
               [ Negativo Ausente ] = 0.990000,
               [ Negativo Presente ] = 0.050000,
               [ Positivo Ausente ] = 0.010000,
               [ Positivo Presente ] = 0.950000);
}

relation Gsm 
{
   values=table(
               [ CERO ] = 0.440000,
               [ A ] = 0.430000,
               [ B ] = 0.100000,
               [ AB ] = 0.030000);
}

relation Gsh 
{
   values=table(
               [ CERO ] = 0.440000,
               [ A ] = 0.430000,
               [ B ] = 0.100000,
               [ AB ] = 0.030000);
}

relation FRhm 
{
   values=table(
               [ NEGATIVO ] = 0.131600,
               [ POSITIVO ] = 0.868400);
}

relation FRhh FRhm 
{
   values=table(
               [ NEGATIVO NEGATIVO ] = 0.300000,
               [ NEGATIVO POSITIVO ] = 0.090000,
               [ POSITIVO NEGATIVO ] = 0.700000,
               [ POSITIVO POSITIVO ] = 0.910000);
}

// Restricciones

relation Terapia1 Terapia2
{
  kind-of-relation=constraint;
  values=logical-expression(Terapia1 in {TerNo, ObsTer} <-> Terapia2 in {TerNo});
}
}
