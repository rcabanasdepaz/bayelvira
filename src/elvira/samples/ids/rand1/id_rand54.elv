// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
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

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
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
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
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
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
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
pos_x =3;
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

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X3;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 V0;

link X11 X8;

link X2 V0;

link X2 X1;

link X4 D2;

link X4 V0;

link X5 D2;

link X5 X10;

link X5 X7;

link X6 D2;

link X6 X2;

link X6 X4;

link X7 D2;

link X7 X8;

link X8 D1;

link X8 X2;

link X9 D1;

link X9 X11;

//Network Relationships: 

relation X8 X11 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7091197087050604 0.2955765168824828 0.4256834246991202 0.3794399945741927 0.46021945616208004 0.3615155644458314 0.2201078070336797 0.29224196711974987 0.47121768960560884 0.1991335806049797 0.5340756443574279 0.26299744432974265 0.07077248426125997 0.41218151599776726 0.10309888569527097 0.42142642482082765 0.005704899480491984 0.37548699122442597 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7981933143677225 0.20180668563227758 );
}

relation X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5838016038701891 0.7638542013631572 0.5076054293539676 0.41619839612981097 0.23614579863684282 0.49239457064603254 );
}

relation X11 X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.18081055502650448 0.3150159919580667 0.37516418794155043 0.25556510789255066 0.20037073547950482 0.36898395805832457 0.3261567160535163 0.23602275143645932 0.6188187094939908 0.3160000499836088 0.2986790960049333 0.50841214067099 );
}

relation X4 D1 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.289919211191071 0.302091709839056 0.4319717558517843 0.510131453755705 0.9088912193085443 0.602546940641224 0.7100807888089289 0.697908290160944 0.5680282441482156 0.489868546244295 0.09110878069145577 0.39745305935877606 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.15863477119301747 0.3771872608918161 0.4641779679151665 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.371948183608932 0.11222799263639122 0.5303747464646531 0.6280518163910681 0.8877720073636088 0.46962525353534684 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.47935866106636094 0.7687304060184402 0.2461850396708237 0.520641338933639 0.23126959398155977 0.7538149603291763 );
}

relation X1 D1 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.03721431941157169 0.5518873044318705 0.33813005567888565 0.7038660043519369 0.31851259893059913 0.3852832175581106 0.03500231493100961 0.44238339755208483 0.35457121662825464 0.27395692645795133 0.521969092790566 0.3674358309361453 0.9277833656574187 0.005729298016044809 0.3072987276928597 0.022177069190111746 0.15951830827883487 0.24728095150574403 );
}

relation X2 X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8167541301229412 0.023585043556631526 0.5177236687334648 0.6304397530065092 0.3080505545523585 0.3424243299706817 0.1832458698770588 0.9764149564433686 0.48227633126653524 0.36956024699349077 0.6919494454476414 0.6575756700293184 );
}

relation X3 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7646707314865748 0.6067762903059051 0.6184011889298002 0.19217957239470793 0.18974605683984674 0.4703358048519057 0.2688656563140339 0.24445209928588513 0.9004871996908361 0.11608082725988164 0.7642149796808494 0.5380975173023095 0.40282391090349773 0.6287897546806972 0.8090618586951405 0.652117338832488 0.1398820762950697 0.8811018656390186 0.23532926851342523 0.39322370969409504 0.3815988110701998 0.807820427605292 0.8102539431601532 0.5296641951480944 0.7311343436859661 0.7555479007141148 0.09951280030916385 0.8839191727401184 0.23578502031915055 0.46190248269769063 0.5971760890965023 0.3712102453193029 0.19093814130485948 0.34788266116751193 0.8601179237049303 0.11889813436098137 );
}

relation V0 D1 D2 X11 X2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (266.20865419544594 232.53254004216345 173.1126927540423 139.43657860075982 280.5119188741005 246.83580472081798 187.41595743269687 153.73984327941434 307.5525831998631 273.8764690465806 214.45662175845945 180.78050760517695 253.98056394334827 220.30444979006577 160.88460250194464 127.20848834866216 268.28382862200283 234.6077144687203 175.1878671805992 141.51175302731667 295.32449294776546 261.64837879448294 202.22853150636183 168.5524173530793 296.6862092582926 263.0100951050101 203.59024781688896 169.91413366360644 310.9894739369471 277.3133597836646 217.89351249554346 184.21739834226096 338.0301382627097 304.35402410942726 244.9341768213061 211.2580626680236 284.4581190061949 250.7820048529124 191.3621575647913 157.68604341150876 298.7613836848494 265.08526953156695 205.6654222434458 171.9893080901633 325.80204801061205 292.1259338573296 232.70608656920842 199.02997241592593 287.30928414703675 253.63316999375422 194.21332270563312 160.5372085523506 301.61254882569125 267.9364346724088 208.51658738428762 174.84047323100512 328.6532131514539 294.9770989981714 235.55725171005025 201.88113755676775 275.0811938949391 241.40507974165655 181.98523245353545 148.30911830025292 289.3844585735936 255.70834442031108 196.28849713218995 162.61238297890745 316.4251228993562 282.74900874607374 223.32916145795258 189.65304730467008 );
}

}
