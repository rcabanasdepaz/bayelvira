// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x14_1 x14_2 x14_3);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x15_1 x15_2 x15_3);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X1;

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X7;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X10 D1;

link X10 X1;

link X10 X13;

link X11 D1;

link X11 X10;

link X12 D1;

link X12 X3;

link X12 X9;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X7;

link X15 D1;

link X15 X10;

link X15 X13;

link X3 X2;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X14;

link X6 D2;

link X6 X13;

link X7 D2;

link X8 D2;

link X8 X6;

link X9 D1;

link X9 X10;

link X9 X14;

link X9 X3;

//Network Relationships: 

relation X9 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.04816383669672894 0.17509765234179203 0.4965385928691506 0.824902347658208 0.4552975704341204 );
}

relation X10 X15 X9 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4736935658538384 1.0 0.0 0.03721935872189281 0.5563530941339979 0.3151846724535417 0.26715102074794805 1.0 0.0 0.9057405313357315 0.8195356510035247 1.0 1.0 1.0 0.7131616973045458 1.0 0.7267995239963212 0.33450223811728846 1.0 0.5336700154560182 1.0 0.40623839059666933 0.7527375780619486 0.20149660274594533 0.0 1.0 0.5626508983139056 0.5263064341461615 0.0 0.0 0.9627806412781071 0.44364690586600214 0.6848153275464584 0.7328489792520521 0.0 1.0 0.0942594686642685 0.18046434899647543 0.0 0.0 0.0 0.28683830269545413 0.0 0.2732004760036788 0.6654977618827116 0.0 0.46632998454398167 0.0 0.5937616094033306 0.24726242193805129 0.7985033972540546 1.0 0.0 0.4373491016860944 );
}

relation X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6793884521178116 0.11302348611301961 0.20758806176916872 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X13 X15 X6 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.5295315491180496 0.0 0.43341674978632433 0.0 1.0 0.3358389038531537 1.0 0.9248230043890947 0.0 0.0 1.0 1.0 0.47046845088195044 1.0 0.5665832502136756 1.0 0.0 0.6641610961468463 0.0 0.07517699561090525 1.0 1.0 );
}

relation X14 X5 X9 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.7494375734877821 0.7422544755027365 0.5021002218742175 0.28184788081755957 0.1906962572329473 0.675130476083147 0.04059398207182932 0.008910219204698075 1.0 0.0 0.0 0.0 0.2505624265122179 0.0 0.10572380470236524 0.7181521191824405 0.8093037427670526 0.2270866257129331 0.4560875366402296 0.0 0.0 0.0 0.9752125591911701 0.0 0.0 0.2577455244972635 0.3921759734234172 0.0 0.0 0.09778289820391975 0.503318481287941 0.9910897807953021 0.0 1.0 0.02478744080882987 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9639616872580484 0.0 0.03603831274195152 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7001615609117575 0.39263036540900126 0.2998384390882425 0.6073696345909988 );
}

relation X7 X14 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6972259787935928 1.0 0.0 0.06908831709836344 1.0 1.0 0.0 1.0 0.3837596705506766 0.30277402120640723 0.0 1.0 0.9309116829016365 0.0 0.0 1.0 0.0 0.6162403294493233 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X1 D2 X10 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.026546859377391138 0.0 0.47315821967176186 0.24853329256199183 0.0 0.18522556253491884 0.03854348452660904 0.6334386348705692 0.7115054850985213 0.0 0.0 0.0 0.0 1.0 0.3731909144796483 0.3072155769540975 0.9157426865408719 0.0 0.058772752906897964 0.005785669818061266 0.0 0.25002556992251734 0.0 0.4093529241101164 0.08605403438796262 1.0 0.48699509233430943 0.5398418446397621 0.0 0.2045761566001888 0.38403853065642785 0.46639778534351717 0.792226942204126 1.0 1.5867477880199046E-4 0.2717823264371931 0.5928914057035152 0.08145151336275398 0.14865278117571773 0.7442075986890696 0.0 0.814774437465081 0.0019017558417629384 0.0 0.21524053618175087 0.45823512941461614 0.45437246093913536 0.7858902875679524 0.08374056160709584 0.0 0.0828550865376685 0.49461774509475837 0.0 1.0 0.9152631223310012 0.9942143301819387 0.042679936000509254 0.6831411961153605 0.0 0.39097472024694546 0.0 0.0 0.0 0.4601581553602378 0.03976514668628539 0.6046890717743988 0.04816599318752503 0.36018061498389353 0.20777305779587396 0.0 0.0 0.728217673562807 0.3805617349190936 0.918548486637246 0.3781889991525205 0.007259108748938436 1.0 0.0 0.9595547596316281 0.36656136512943077 0.07325397871972793 0.5417648705853839 0.5456275390608647 0.21410971243204757 0.9162594383929041 0.0 0.5439539989826832 0.19816667795114404 0.08425731345912811 0.0 0.02596412476210093 0.0 0.9573200639994908 0.06683323396212214 0.0 0.19967235564293806 0.9139459656120374 0.0 0.5130049076656905 0.0 0.9602348533137146 0.19073477162541239 0.5677954761560471 0.17342159967258944 0.0 0.0 0.9998413252211981 );
}

relation X2 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6352281864592715 0.5907043026564527 0.4532835130238928 1.0 0.0 1.0 0.8589382460822158 0.0 0.0 0.6061744521923622 0.0 0.0 0.3647718135407286 0.40929569734354737 0.5467164869761072 0.0 1.0 0.0 0.14106175391778417 1.0 1.0 0.39382554780763784 0.0 0.0 );
}

relation X3 D2 X12 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4279932855127886 0.5311291435520225 0.0 0.23928711856980833 0.006718744615672715 0.0 0.5425086788056998 0.4274638180805042 1.0 0.7381331265814419 0.27297387405966994 0.0 0.0 1.0 0.8575459640511417 1.0 0.0 1.0 0.6128487134594077 0.5631573303996061 0.0 0.0 0.3383776671103164 0.6728757704691835 0.0 0.5883121083042894 0.18497979396081463 1.0 0.7358021396116147 0.42587068374806447 1.0 0.7801580038880604 0.025272479773662072 0.6411954652141097 0.0 0.7383388987087094 0.5720067144872114 0.4688708564479776 1.0 0.7607128814301917 0.9932812553843273 1.0 0.45749132119430025 0.5725361819194957 0.0 0.261866873418558 0.72702612594033 0.0 1.0 0.0 0.1424540359488583 0.0 1.0 0.0 0.38715128654059233 0.4368426696003938 0.0 1.0 0.6616223328896835 0.32712422953081655 1.0 0.4116878916957107 0.8150202060391853 0.0 0.2641978603883853 0.5741293162519355 0.0 0.21984199611193958 0.9747275202263379 0.3588045347858903 1.0 0.2616611012912907 );
}

relation X4 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.34475603555350004 0.8330780533435072 0.0 0.0 0.0 0.0 0.5294061749036211 0.1669219466564929 0.015947207646557302 0.0 0.9447991934141078 1.0 0.12583778954287883 0.0 0.9840527923534427 1.0 0.05520080658589222 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (148.8918155666763 0.0 0.0 0.0 152.25387663847314 167.8429049427014 );
}

}
