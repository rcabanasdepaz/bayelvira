// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x15_1 x15_2);
}

node X16(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x16_1 x16_2);
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
pos_x =4;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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
pos_x =4;
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

link D1 X5;

link D1 X7;

link D1 X8;

link D1 X9;

link D2 V0;

link D2 X1;

link X10 D1;

link X10 X11;

link X10 X3;

link X11 D1;

link X11 X1;

link X12 D1;

link X12 X10;

link X13 D1;

link X13 X3;

link X14 D1;

link X14 X10;

link X15 D1;

link X15 X10;

link X16 D1;

link X16 X10;

link X2 X5;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X7;

link X5 X8;

link X6 D2;

link X6 X12;

link X6 X13;

link X7 D2;

link X7 X8;

link X8 D2;

link X9 D2;

link X9 X7;

link X9 X8;

//Network Relationships: 

relation X10 X14 X15 X16 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9040306596191592 0.20794014139028802 0.6732678779006533 0.0 0.0 0.9987021241454099 0.5228680140170138 0.0 0.051558271763488796 0.6473385611469132 1.0 0.9795674038736574 0.36423522111604173 0.6287362683328439 0.07280040294864285 0.8105970073297403 0.4380215482042111 0.8541456049188244 0.6809699596460284 0.4925286924906423 0.0 0.0 0.6408237338033127 0.48677743680728314 0.09596934038084079 0.792059858609712 0.3267321220993467 1.0 1.0 0.0012978758545901135 0.4771319859829861 1.0 0.9484417282365112 0.3526614388530868 0.0 0.02043259612634267 0.6357647788839582 0.37126373166715604 0.9271995970513571 0.1894029926702598 0.5619784517957889 0.14585439508117562 0.3190300403539717 0.5074713075093576 1.0 1.0 0.35917626619668724 0.5132225631927169 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5806029146808951 0.9029587532018637 0.41939708531910497 0.09704124679813637 );
}

relation X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.802885484650168 0.10617756118812456 0.06453442951224399 0.6510922443542485 0.1325800858375881 0.24273019445762692 );
}

relation X13 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.06518409807845932 0.5539604185630446 0.0 0.16241378809963447 0.9348159019215407 0.2836257933373211 );
}

relation X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X16 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9346082754577748 0.06539172454222515 );
}

relation X5 X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.30193896956952554 1.0 0.0 0.0 0.5807264259029187 1.0 0.6980610304304745 0.0 1.0 1.0 0.4192735740970812 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5658505003460297 0.4341494996539703 );
}

relation X7 X5 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7611922278795968 0.3957901411664949 1.0 0.0838078462776209 0.4827556577298488 0.22534384360414209 0.0 0.0 0.0 1.0 0.7961332401685479 0.0 0.23880777212040322 0.6042098588335051 0.0 0.916192153722379 0.5172443422701513 0.7746561563958578 1.0 1.0 1.0 0.0 0.20386675983145205 1.0 );
}

relation X8 X5 X7 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6545442007934429 0.08210478060530671 0.7270079237883367 0.0 0.9947008554070828 0.08093125385164146 0.0 0.6262839995336071 0.30155215993326456 1.0 0.0 0.0 0.5806196611280715 0.8332087068549577 1.0 1.0 0.0 0.4988029982179606 0.0 0.2251151323047023 1.0 0.2931070647955462 0.3334116674145637 0.39762003469530344 0.3454557992065571 0.9178952193946933 0.2729920762116634 1.0 0.005299144592917238 0.9190687461483584 1.0 0.3737160004663929 0.6984478400667354 0.0 1.0 0.0 0.4193803388719286 0.16679129314504235 0.0 0.0 1.0 0.5011970017820395 0.0 0.7748848676952977 0.0 0.7068929352044537 0.6665883325854363 0.6023799653046966 );
}

relation X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5457887131641282 0.0 0.0 0.45421128683587175 0.0 0.0 );
}

relation X1 D2 X11 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.26872140139460654 0.11559990039836769 0.4903824775712591 1.0 0.12273446599421083 0.0 0.7223105275957317 0.0 0.3721775202865018 0.0 0.13490034228987136 0.5746488452081631 0.005148285161696417 0.002591507488993476 0.4030257781855289 0.8554054723900638 0.0 0.0 0.6667593058582619 1.0 0.06855163288521347 0.11745652790525477 0.3677581010046465 0.7825125226291728 0.013626400157050115 0.0 0.08040112313303316 0.6940430728967024 0.9201639716613152 0.5377691280744723 0.23131412868481405 0.46862868234049304 0.3060167425755909 0.7380581038353984 0.3636171243866488 1.0 0.0 0.45539246580909815 0.5096175224287409 0.0 0.20195344575633906 0.554527532595797 0.02835390522894162 0.0 0.2256878733210515 0.7919992201893342 0.7370992152863343 0.0 0.0 0.36100568620301365 0.16762223112650143 0.0 0.4863466655621729 0.46380262504376557 0.3093974346827644 0.0 0.749409217283777 0.0 0.003829623606936724 0.0722325336015478 0.6404545820598623 0.0 0.47887060125653774 0.0 0.010123918715583376 0.0 0.7492236479761414 0.2123336205587834 0.33832375429062744 0.26194189616460156 0.48708492997015906 0.0 0.7312785986053935 0.42900763379253426 0.0 0.0 0.6753120882494501 0.445472467404203 0.24933556717532668 0.0 0.4021346063924467 0.20800077981066592 0.12800044242379433 0.4253511547918368 0.9948517148383035 0.6364028063079928 0.42935199068796953 0.1445945276099361 0.5136533344378271 0.5361973749562344 0.02384325945897361 0.0 0.18203914983100972 0.8825434720947453 0.6284122753884168 0.14525494376927944 0.3459190177830877 1.0 0.4407282756104291 0.30595692710329764 0.06971210962310141 0.4622308719255277 0.019462223339044505 0.3190376971007235 0.3556595031337816 0.0 0.14929794564319207 );
}

relation X2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 0.0 0.0 0.0 0.0 );
}

relation X3 X10 X13 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.22286616721633246 0.5032293139206242 0.698694697943624 1.0 0.7694527360027074 0.0 0.6661841814787194 0.2816864446856359 0.0 0.5468814407446403 0.6437256931326035 0.7120308819919582 0.5131009877465917 0.9355501703637528 0.7001906283916939 0.5595565863462796 0.0 0.9849585113439708 0.7771338327836675 0.4967706860793759 0.301305302056376 0.0 0.23054726399729267 1.0 0.3338158185212807 0.7183135553143641 1.0 0.4531185592553596 0.3562743068673965 0.28796911800804187 0.4868990122534082 0.06444982963624721 0.2998093716083061 0.44044341365372036 1.0 0.015041488656029112 );
}

relation X4 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2321927264486754 1.0 0.5670466915317419 0.11314945633702131 0.20070389883466913 0.0 0.3882706098288225 0.0 0.20292465054589026 0.0 0.7714589663427804 0.0 0.3795366637225021 0.0 0.23002865792236787 0.8868505436629786 0.027837134822550295 1.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (127.6741130082199 130.86666765484216 120.01456924552608 0.0 0.0 106.26884003500848 );
}

}
