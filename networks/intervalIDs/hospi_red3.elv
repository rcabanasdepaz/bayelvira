// Influence Diagram
//   Elvira format 

idiagram  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node Valor(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =50;
pos_y =1050;
relevance = 7.0;
purpose = "";
min = 1.255;
max = 999.694;
precision = 2;
}

node DHBrb(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =650;
relevance = 7.0;
purpose = "";
num-states = 6;
states = (NULO BAJO MEDIO ALTO MUY_ALTO EXTREMO);
}

node DHBrb2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =550;
relevance = 7.0;
purpose = "";
num-states = 6;
states = (NULO BAJO MEDIO ALTO MUY_ALTO EXTREMO);
}

node DHBrb1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
pos_y =550;
relevance = 7.0;
purpose = "";
num-states = 6;
states = (NULO BAJO MEDIO ALTO MUY_ALTO EXTREMO);
}

node DTer(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =950;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (NULO BAJO MEDIO ALTO MUY_ALTO);
}

node DTer2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =850;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (NULO BAJO MEDIO ALTO MUY_ALTO);
}

node DTer1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =750;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (NULO BAJO MEDIO ALTO MUY_ALTO);
}

node PatLG(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =350;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (Leve Grave MuyGrave);
}

node IRh(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =250;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Ausente Presente);
}

node CHgb2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
pos_y =350;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (Baja Media Alta);
}

node CBrb2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =450;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Normal Alta);
}

node EH2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =50;
pos_y =50;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (menos_de_24horas mas_de_24horas);
}

node CHgb1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =250;
pos_y =350;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (Baja Media Alta);
}

node CBrb1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
pos_y =450;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Normal Alta);
}

node EH1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =343;
pos_y =20;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (menos_de_12horas mas_de_12horas);
}

node Rzm(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =588;
pos_y =37;
relevance = 7.0;
purpose = "";
num-states = 4;
states = ("Negra" "Caucasica" "Asiatica" "Gitana");
}

node AP(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =922;
pos_y =241;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Primeriza Multipara);
}

node Ict(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =250;
pos_y =550;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (Normal Amarillo Pies Calabaza);
}

node TCmIRh(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =350;
pos_y =350;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Negativo Positivo);
}

node TChIRh(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =250;
pos_y =450;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (Negativo Positivo);
}

node FRhm(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =985;
pos_y =105;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (NEGATIVO POSITIVO);
}

node FRhh(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
pos_y =150;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (NEGATIVO POSITIVO);
}

node Terapia2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =784;
pos_y =721;
relevance = 7.0;
purpose = "";
num-states = 9;
states = (TerNo ObsAlt Obs6 Fot6 Fot12 Fot24 Fot6ExaFot6 Fot6ExaFot12 ObsTer);
}

node Terapia1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =667;
pos_y =482;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (TerNo Fot6 Fot12 Fot24 ObsTer);
}

// Links of the associated graph:

link AP IRh;

link AP Terapia1;

link CBrb1 DHBrb1;

link CBrb1 DTer1;

link CBrb1 Ict;

link CBrb1 Terapia1;

link CBrb2 DHBrb2;

link CBrb2 DTer2;

link CBrb2 Terapia2;

link CHgb1 CBrb1;

link CHgb1 Terapia1;

link CHgb2 CBrb2;

link CHgb2 Terapia2;

link DHBrb Valor;

link DHBrb1 DHBrb;

link DHBrb2 DHBrb;

link DTer Valor;

link DTer1 DTer;

link DTer2 DTer;

link EH1 CBrb1;

link EH1 DHBrb1;

link EH1 DTer1;

link EH1 Terapia1;

link EH2 CBrb2;

link EH2 DHBrb2;

link EH2 DTer2;

link EH2 Terapia2;

link FRhh IRh;

link FRhm FRhh;

link FRhm IRh;

link IRh CHgb1;

link IRh CHgb2;

link IRh PatLG;

link IRh TChIRh;

link IRh TCmIRh;

link Ict Terapia1;

link PatLG DHBrb1;

link PatLG DHBrb2;

link Rzm Ict;

link Rzm Terapia1;

link TChIRh Terapia1;

link TCmIRh TChIRh;

link TCmIRh Terapia1;

link Terapia1 DTer1;

link Terapia1 Terapia2;

link Terapia2 DTer2;

//Network Relationships: 

relation Valor DTer DHBrb { 
comment = "";
kind-of-relation = utility;
deterministic=false;
values= table (100.0 0.0 0.0 0.0 0.0 0.0 95.0 90.0 85.0 1.0 0.0 0.0 90.0 85.0 80.0 75.0 0.0 0.0 0.0 0.0 10.0 85.0 80.0 75.0 0.0 0.0 4.0 95.0 90.0 85.0 );
}

relation DHBrb DHBrb1 DHBrb2 { 
comment = "";
deterministic=false;
values= table (0.98 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.02 0.97 0.0 0.0 0.0 0.0 0.97 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.0 0.03 0.05 0.97 0.0 0.0 0.0 0.98 0.97 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.0 0.0 0.03 0.98 0.0 0.0 0.02 0.03 0.05 0.97 0.0 0.0 0.98 0.97 0.96 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.0 0.0 0.02 0.98 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.02 0.03 0.04 0.05 0.98 0.0 0.99 0.98 0.97 0.96 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 1.0 0.0 0.0 0.0 0.0 0.02 1.0 0.0 0.0 0.0 0.0 0.03 1.0 0.0 0.0 0.0 0.0 0.02 1.0 0.01 0.02 0.03 0.04 0.05 1.0 1.0 1.0 1.0 1.0 1.0 1.0 );
}

relation DHBrb2 CBrb2 PatLG EH2 { 
comment = "";
deterministic=false;
values= table (0.98 0.99 0.98 0.99 0.98 0.99 0.1 0.15 0.0 0.0 0.0 0.0 0.02 0.01 0.02 0.01 0.02 0.01 0.2 0.25 0.1 0.15 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.3 0.35 0.2 0.25 0.1 0.15 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.15 0.3 0.3 0.15 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.1 0.25 0.2 0.3 0.25 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.15 0.1 0.45 0.4 );
}

relation DHBrb1 CBrb1 PatLG EH1 { 
comment = "";
deterministic=false;
values= table (0.98 0.99 0.98 0.99 0.98 0.99 0.05 0.1 0.0 0.0 0.0 0.0 0.02 0.01 0.02 0.01 0.02 0.01 0.1 0.2 0.05 0.1 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.3 0.1 0.2 0.05 0.1 0.0 0.0 0.0 0.0 0.0 0.0 0.3 0.2 0.2 0.3 0.2 0.15 0.0 0.0 0.0 0.0 0.0 0.0 0.35 0.2 0.45 0.25 0.25 0.3 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.15 0.5 0.45 );
}

relation DTer DTer1 DTer2 { 
comment = "";
deterministic=false;
values= table (0.97 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.0 0.97 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.03 0.05 0.97 0.0 0.0 0.98 0.97 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.97 0.0 0.0 0.0 0.03 0.98 0.0 0.02 0.03 0.05 0.97 0.0 0.98 0.97 0.96 0.95 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 1.0 0.0 0.0 0.0 0.02 1.0 0.0 0.0 0.0 0.03 1.0 0.02 0.03 0.04 0.05 1.0 1.0 1.0 1.0 1.0 1.0 );
}

relation DTer2 Terapia2 CBrb2 EH2 { 
comment = "";
deterministic=false;
values= table (1.0 1.0 1.0 1.0 0.99 0.99 0.0 0.0 0.05 0.03 0.95 0.03 0.0 0.0 0.3 0.35 0.0 0.0 0.2 0.5 0.99 0.99 0.0 0.0 0.0 0.0 0.99 0.95 0.0 0.0 0.99 0.95 0.99 0.8 0.0 0.0 0.0 0.0 0.0 0.0 0.01 0.01 0.0 0.0 0.475 0.485 0.05 0.97 0.3 0.05 0.7 0.65 0.2 0.2 0.8 0.5 0.01 0.01 0.0 0.0 0.0 0.0 0.01 0.05 0.0 0.0 0.01 0.05 0.01 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.02 0.475 0.485 0.0 0.0 0.7 0.95 0.0 0.0 0.7 0.8 0.0 0.0 0.0 0.0 0.0 0.02 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.02 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.99 0.98 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.1 0.0 0.0 0.0 0.0 0.0 0.99 0.98 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.98 0.8 0.0 0.0 0.0 0.0 0.0 0.0 0.01 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.01 0.0 1.0 1.0 0.0 0.0 1.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 );
}

relation DTer1 Terapia1 CBrb1 EH1 { 
comment = "";
deterministic=false;
values= table (1.0 1.0 0.0 0.0 0.0 0.0 0.96 0.98 0.0 0.0 0.96 0.98 0.0 0.0 0.98 0.98 0.98 0.98 0.0 0.0 0.0 0.0 0.0 0.0 0.95 1.0 0.02 0.02 0.0 0.99 0.04 0.02 0.0 0.0 0.02 0.02 0.02 0.02 0.0 0.0 0.0 0.0 0.0 0.0 0.05 0.0 0.01 0.0 0.98 0.01 0.0 0.0 1.0 1.0 0.0 0.0 0.0 0.0 0.02 0.05 0.0 0.0 0.0 0.0 0.0 0.0 0.01 0.0 0.02 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.03 0.05 0.0 0.0 1.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.95 0.9 );
}

relation PatLG IRh { 
comment = "";
deterministic=false;
values= table (1.0 0.0 0.0 0.7 0.0 0.3 );
}

relation IRh FRhm FRhh AP { 
comment = "";
deterministic=false;
values= table (0.99 0.99 0.99 0.99 0.95 0.9 0.99 0.99 0.01 0.01 0.01 0.01 0.05 0.1 0.01 0.01 );
}

relation CHgb2 IRh { 
comment = "";
deterministic=false;
values= table (0.05 0.7 0.75 0.25 0.2 0.05 );
}

relation CBrb2 CHgb2 EH2 { 
comment = "";
deterministic=false;
values= table (0.8 0.0 0.8 0.8 0.0 0.8 0.2 1.0 0.2 0.2 1.0 0.2 );
}

relation EH2 { 
comment = "";
deterministic=false;
values= table (0.72 0.28 );
}

relation CHgb1 IRh { 
comment = "";
deterministic=false;
values= table (0.01 0.95 0.79 0.05 0.2 0.0 );
}

relation CBrb1 CHgb1 EH1 { 
comment = "";
deterministic=false;
values= table (0.85 0.0 0.0 0.0 0.0 0.0 0.15 1.0 1.0 1.0 1.0 1.0 );
}

relation EH1 { 
comment = "";
deterministic=false;
values= table (0.69 0.31 );
}

relation Rzm { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.01 0.89 0.04 0.06 );
}

relation AP { 
comment = "";
deterministic=false;
values= table (0.93 0.07 );
}

relation Ict Rzm CBrb1 { 
comment = "";
deterministic=false;
values= table (0.8 0.3 0.97 0.05 0.85 0.1 0.85 0.1 0.05 0.1 0.03 0.1 0.01 0.1 0.01 0.1 0.05 0.1 0.0 0.1 0.04 0.15 0.04 0.15 0.1 0.5 0.0 0.75 0.1 0.65 0.1 0.65 );
}

relation TCmIRh IRh { 
comment = "";
deterministic=false;
values= table (0.99 0.5 0.01 0.5 );
}

relation TChIRh TCmIRh IRh { 
comment = "";
deterministic=false;
values= table (0.99 0.1 0.8 0.05 0.01 0.9 0.2 0.95 );
}

relation FRhm { 
comment = "";
deterministic=false;
values= table (0.1316 0.8684 );
}

relation FRhh FRhm { 
comment = "";
deterministic=false;
values= table (0.3 0.09 0.7 0.91 );
}

}
