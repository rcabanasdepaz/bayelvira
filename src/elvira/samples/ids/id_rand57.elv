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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x10_1 x10_2 x10_3);
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

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
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

link D1 X2;

link D1 X4;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X10 D1;

link X10 X5;

link X3 X1;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 V0;

link X5 X4;

link X5 X6;

link X6 D2;

link X6 V0;

link X7 D1;

link X7 X8;

link X7 X9;

link X8 D1;

link X8 X5;

link X9 D1;

link X9 X1;

link X9 X10;

//Network Relationships: 

relation X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X8 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7292532168989508 0.014070478580580003 0.2707467831010491 0.98592952141942 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 1.0 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7595966425612832 0.12240331254399675 0.23593770415199813 0.7285805788651842 0.004465653286718683 0.14901610859081893 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.8504672259116527 1.0 0.0 0.7015210288198692 0.8029677582532764 0.2881583484091114 0.0 1.0 0.0 0.14953277408834734 0.0 1.0 0.29847897118013084 0.19703224174672362 0.7118416515908885 1.0 );
}

relation X5 X8 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.2158791146550279 0.9804999905359844 0.6363520295433694 0.49417921180855634 0.643603053409803 0.0 0.5835582021732656 0.019500009464015738 0.36364797045663055 0.0 0.35077933809739664 1.0 0.20056268317170656 0.0 0.0 0.5058207881914437 0.005617608492800271 );
}

relation X6 X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.15947168805451611 1.0 0.09717306099160071 0.0 0.8212213692668461 0.0 0.8405283119454839 0.0 0.9028269390083993 1.0 0.17877863073315386 );
}

relation X1 D2 X3 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.051890248601382215 0.017830535149325692 0.48413559900523095 0.4871874816183283 0.09984142168433802 0.0038666369426645247 0.33886659927702756 0.4271404233278749 0.12206301773280338 0.0 0.6855167985035964 0.0 0.0 0.643461846794401 0.2852642598407157 0.4254949687991531 0.06883364617214603 0.0 1.0 0.4400951758094803 0.37554221865985465 0.2658960268413309 0.0 0.8447751151902435 0.0 0.6894807265946369 0.3047412475084433 0.5043455715245057 0.060805048452897054 0.0944203801716512 0.3374357282289834 0.0 0.6194887329981891 0.0 0.31448320149640363 0.04119782278617686 0.0 0.3216608396265842 0.7135971397169223 0.0 0.675466707924899 0.9704374300973401 0.0 0.2815313927721 0.0 0.37692650853146065 0.0 0.10849372270502844 0.9481097513986178 0.2926887382560375 0.21112315348632563 0.008466946857165918 0.8393535298627649 0.9017129828856841 0.32369767249398906 0.5728595766721252 0.25844824926900756 1.0 0.0 0.9588021772138231 0.0 0.03487731357901475 0.0011386004423619867 0.574505031200847 0.25569964590295496 0.029562569902659902 0.0 0.2783734314184196 0.6244577813401453 0.35717746462720834 1.0 0.04673116210472803 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5777021148462477 0.5323304828509845 0.18042053926057822 0.0 0.37326325505484714 1.0 0.4222978851537523 0.46766951714901556 0.8195794607394217 1.0 0.6267367449451529 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.4247157607863326 0.0 0.5752842392136673 );
}

relation V0 D1 D2 X5 X6 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (261.13361308896526 261.6804312623934 0.0 216.41081326849252 238.14279575609362 238.68961392952176 301.55011356046657 302.09693173389473 256.2804955665656 256.82731373999377 278.55929622759487 0.0 276.75729440186353 277.3041125752917 0.0 0.0 253.76647706899183 254.31329524241997 317.1737948733648 317.7206130467929 271.9041768794639 272.45099505289204 0.0 294.72979571392125 282.7656044792337 283.3124226526618 237.4959864853327 238.04280465876087 259.774787146362 260.32160531979014 323.1821049507349 323.7289231241631 0.0 278.4593051302621 300.1912876178632 300.73810579129133 );
}

}
