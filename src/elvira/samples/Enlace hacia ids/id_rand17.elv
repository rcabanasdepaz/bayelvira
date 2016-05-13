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
values= table (0.3332977347389941 0.8556962529655477 0.6667022652610058 0.14430374703445217 );
}

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.35372832325589443 1.0 0.6462716767441057 0.0 );
}

relation X10 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6463778641428158 0.8630874497933966 0.12739116211956072 0.0 0.22623097373762344 0.13691255020660345 );
}

relation X4 X5 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.32837139102642643 0.04498236193177712 0.6643322624414868 0.0 0.0 0.4577886159777151 0.0 0.0317101716992195 0.9755941029648623 0.6716286089735735 0.9550176380682229 0.33566773755851326 1.0 0.0 0.542211384022285 1.0 0.9682898283007805 0.024405897035137767 );
}

relation X5 X8 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4209328674368139 0.1541416541425298 0.0 0.47372679182921124 0.7498452971505184 0.09714023790224419 0.4924133926026748 0.03470558284037044 0.0 0.27919308709479485 0.0 0.126316143214684 0.08665373996051132 0.8111527630170997 0.0 0.24708012107599386 0.25015470284948166 0.7765436188830718 );
}

relation X6 X5 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.5557674910202188 0.9281897152428388 0.4653316850463305 0.7957857436442828 1.0 1.0 0.44423250897978117 0.07181028475716127 0.5346683149536695 0.20421425635571722 );
}

relation X1 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.36680697404436285 0.20984662871030765 0.2427694906182424 0.36272836199363184 0.7533310478073503 0.0 0.516534732657502 0.021601107279123913 0.7293298171727042 0.33776401568678976 0.17080062478518376 0.0 0.32240166510427576 0.34541710757813543 0.4296138524572251 0.6372716380063681 0.2063323035341424 0.0 0.15059785961776576 0.6150388777763175 0.2706701828272958 0.5205123511083595 0.19387081503760317 1.0 0.31079136085136133 0.444736263711557 0.3276166569245325 0.0 0.040336648658507286 1.0 0.3328674077247322 0.3633600149445586 0.0 0.1417236332048508 0.6353285601772131 0.0 );
}

relation X2 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.506620005622117 0.28776317986935473 0.0 0.08451014720371526 0.7138156082948489 0.42235157215011515 0.49337999437788305 0.7122368201306452 1.0 0.9154898527962848 0.2861843917051511 0.5776484278498848 );
}

relation X3 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16527751590217546 0.812837738560425 0.8347224840978246 0.18716226143957504 );
}

relation V0 D1 D2 X5 X6 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (224.97412900184398 287.99780789480513 290.5506094678785 0.0 279.6975354185769 342.721214311538 176.09601201314544 239.1196909061066 241.67249247917997 304.6961713721411 230.81941842987834 293.8430973228395 170.34006319097918 233.36374208394034 235.91654365701368 298.9402225499748 0.0 288.08714850067327 121.46194620228067 184.48562509524183 187.0384266683152 250.06210556127635 176.18535261901354 239.2090315119747 241.22093884694658 304.24461773990777 306.7974193129811 369.82109820594223 295.94434526367945 358.9680241566406 192.34282185824807 255.36650075120923 257.91930232428257 320.94298121724376 0.0 310.0899071679421 );
}

}
