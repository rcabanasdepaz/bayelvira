// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
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
pos_x =7;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
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
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =1;
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

link D1 X11;

link D1 X12;

link D1 X13;

link D1 X14;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X12;

link X10 D1;

link X10 X7;

link X11 D2;

link X11 V0;

link X11 X14;

link X12 D2;

link X12 V0;

link X13 D2;

link X13 X11;

link X14 D2;

link X14 X12;

link X14 X15;

link X14 X5;

link X15 D2;

link X15 X1;

link X2 X15;

link X4 X3;

link X4 X9;

link X5 X1;

link X6 X15;

link X6 X2;

link X7 D1;

link X7 X11;

link X8 D1;

link X8 X11;

link X9 D1;

link X9 X11;

//Network Relationships: 

relation X7 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7417518880285255 0.5922723000078193 0.25824811197147446 0.4077276999921808 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.21529294387738415 0.7847070561226158 );
}

relation X9 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.805016502774815 0.5955544055249337 0.194983497225185 0.4044455944750663 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4431069250188485 0.5568930749811515 );
}

relation X11 D1 X13 X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5496198844861445 0.2106938932981813 0.4104335259634394 0.37979193592525445 0.6169996705052629 0.8123676554052464 0.5424373303144812 0.4736538551782553 0.47054247070475375 0.34003304064898726 0.4914458211359636 0.7136640932657874 0.25560297831511836 0.9246305728231514 0.6021334837514835 0.9651757759243522 0.9744475406935245 0.008141417538404623 0.6282442813468309 0.5232626176980419 0.3301226967890083 0.4241076255404703 0.07378962220021296 0.6756952271105313 0.17802794586721662 0.6405472847284562 0.2731020354702241 0.4887661823102562 0.6422887261477301 0.05737721336319141 0.3168788350159503 0.6304068370759215 0.45038011551385543 0.7893061067018187 0.5895664740365606 0.6202080640747456 0.383000329494737 0.18763234459475364 0.45756266968551873 0.5263461448217448 0.5294575292952464 0.6599669593510128 0.5085541788640364 0.2863359067342126 0.7443970216848816 0.07536942717684852 0.3978665162485166 0.034824224075647815 0.025552459306475594 0.9918585824615953 0.3717557186531691 0.476737382301958 0.6698773032109916 0.5758923744595297 0.926210377799787 0.32430477288946874 0.8219720541327834 0.35945271527154377 0.7268979645297758 0.5112338176897439 0.35771127385226986 0.9426227866368084 0.6831211649840496 0.36959316292407857 );
}

relation X12 D1 X1 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.47292545227452976 0.4609541674265117 0.1947465666694314 0.45283550418422147 0.3735331254493676 0.5563080470471388 0.8561621473568626 0.7338946211069476 0.5270745477254704 0.5390458325734884 0.8052534333305686 0.5471644958157785 0.6264668745506324 0.4436919529528611 0.14383785264313742 0.2661053788930524 );
}

relation X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5605013782110818 0.5327568443468572 0.43949862178891813 0.4672431556531427 );
}

relation X14 D1 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18615995034698185 0.8428820602053753 0.33676439027022337 0.4792520647057488 0.8138400496530181 0.15711793979462463 0.6632356097297766 0.5207479352942512 );
}

relation X15 X14 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.37743244086247074 0.5202281961060882 0.5417954149759008 0.4076912813055701 0.8202391068403483 0.46925482967299925 0.6586490658173916 0.5731716487863591 0.6225675591375293 0.47977180389391194 0.4582045850240993 0.5923087186944298 0.1797608931596517 0.5307451703270007 0.34135093418260837 0.42682835121364093 );
}

relation X1 D1 X15 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5513786920924626 0.3164785905143729 0.6238245176448779 0.5040774148981345 0.873501563374775 0.7627791178408782 0.7012503149535206 0.6144312877174519 0.44862130790753746 0.6835214094856271 0.3761754823551221 0.4959225851018655 0.12649843662522495 0.23722088215912188 0.2987496850464795 0.3855687122825481 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.10919847554821549 0.8628953071224921 0.8908015244517845 0.13710469287750798 );
}

relation X3 D1 D2 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9182701432159573 0.8356818424512806 0.8263634659187109 0.6991859244102521 0.8934609536364139 0.6539954690333545 0.4379170637897911 0.8939157237951459 0.08172985678404265 0.16431815754871928 0.17363653408128915 0.3008140755897479 0.10653904636358617 0.3460045309666455 0.5620829362102089 0.10608427620485399 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.19221462857128435 0.8077853714287158 );
}

relation X5 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5311544246900051 0.48331899365086933 0.468845575309995 0.5166810063491307 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5813689869071195 0.46608686459969106 0.41863101309288064 0.5339131354003089 );
}

relation V0 D1 D2 X11 X12 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (218.703045137913 245.74370946367563 205.49528307905675 232.53594740481938 178.76752966349667 205.8081939892593 165.55976760464046 192.6004319304031 249.1806002007596 276.22126452652225 235.97283814190337 263.013502467666 209.24508472634332 236.28574905210596 196.03732266748708 223.0779869932497 );
}

}
